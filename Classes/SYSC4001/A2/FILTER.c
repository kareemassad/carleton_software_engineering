// Concurrent Filtering using Semaphores
// Devise a concurrent program to filter a list of seven distint numerals and letters.
// Given [5,A,9,M,W,6,Z] the output should be [A,M,W,Z,5,6,9].
// Each of the 7 elements must be stored in an array in shared memory.
// The shared memory shall be accessed by three concurrent processes P1,P2, and P3.
// P1 is associated with B[1], B[2], B[3]
// P2 is associated with B[3], B[4], B[5]
// P3 is associated with B[5], B[6], B[7]
// Each process checks it's three elements until the entire list is filtered.
// Whenever a process finds a letter in one of it's elements, it swaps it with the element before it.
// Ensure mutal exclusion using semaphores
// Test with: 5A9MW6Z

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <signal.h>
#include <wait.h>
#include <stdbool.h>
#include "semun.h"
#include <ctype.h>

#define LIST_SIZE 7

static int sem_id;
static bool debug = false;
char local_array[LIST_SIZE];
static bool skip_one = true;

// Semaphore stuff from textbook (Pg. 585)

static int set_semvalue(int i)
{
    union semun sem_union;
    sem_union.val = 1;
    if (semctl(sem_id, i, SETVAL, sem_union) == -1)
    {
        return 0;
    }
    return 1;
}

static void del_semvalue(int i)
{
    union semun sem_union;
    if (semctl(sem_id, i, IPC_RMID, sem_union) == -1)
        if (debug)
        {
            fprintf(stderr, "failed to delete semaphore\n");
        }
}

static int semaphore_p(int i)
{
    struct sembuf sem_b;
    //= 0
    sem_b.sem_num = i;
    sem_b.sem_op = -1;
    sem_b.sem_flg = SEM_UNDO;
    if (semop(sem_id, &sem_b, 1) == -1)
    {
        if (debug)
        {
            fprintf(stderr, "semaphore_p failed\n");
        }
        return (0);
    }
    return (1);
}
static int semaphore_v(int i)
{
    struct sembuf sem_b;
    //= 0
    sem_b.sem_num = i;
    sem_b.sem_op = 1; /* V() */
    sem_b.sem_flg = SEM_UNDO;
    if (semop(sem_id, &sem_b, 1) == -1)
    {
        if (debug)
        {
            fprintf(stderr, "semaphore_v failed\n");
        }
        return (0);
    }
    return (1);
}

// method to swap two elements in an array
void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main()
{
    // create a boolean called debug
    bool debug = false;
    // ask user if they want debug mode
    printf("Debug mode? (y/n): ");
    char input;
    scanf("%c", &input);
    if (input == 'y')
    {
        debug = true;
    }
    else
    {
        debug = false;
    }
    // create a 7 element array in shared memory
    int shmid;
    int *shared_memory;

    // create shared memory
    shmid = shmget((key_t)1234, sizeof(int) * LIST_SIZE, IPC_CREAT | 0666);
    if (shmid == -1)
    {
        if (debug)
        {
            perror("shmget failed \n");
        }
        exit(EXIT_FAILURE);
    }

    // make memory accessible to child
    shared_memory = (int *)shmat(shmid, (void *)0, 0);
    if (shared_memory == (void *)-1)
    {
        if (debug)
        {
            perror("shmat failed \n");
        }
        exit(EXIT_FAILURE);
    }
    if (debug)
    {
        printf("shared memory attached at %X\n", shared_memory);
    }

    // take a 7 char string from the user and store it in shared memory
    printf("Enter 7 characters: ");
    char input_string[LIST_SIZE];
    scanf("%s", input_string);
    // store the string in shared memory and print it out
    for (int i = 0; i < LIST_SIZE; i++)
    {
        shared_memory[i] = input_string[i];
        if (debug)
        {
            printf("%c", shared_memory[i]);
        }
    }

    // create 2 semaphores using semget
    int semid;
    semid = semget((key_t)1234, 2, IPC_CREAT | 0666);

    // set semaphore values
    set_semvalue(0);
    set_semvalue(1);

    // create 3 child processes
    pid_t child_pid;
    int target_index_min;
    int target_index_max;
    for (int j = 0; j < 3; j++)
    {
        child_pid = fork();

        switch (child_pid)
        {
        // Error
        case -1:
            if (debug)
            {
                perror("fork failed");
            }
            exit(1);
        // Child
        case 0:

            if (debug)
            {
                printf("Child %d created\n", j);
            }
            // confine each process to a specific range of elements
            switch (j)
            {
            case 0:
                target_index_min = 0;
                target_index_max = 2;
                break;
            case 1:
                target_index_min = 2;
                target_index_max = 4;
                break;
            case 2:
                target_index_min = 4;
                target_index_max = 6;
                break;
            }
            // set semaphore values
            set_semvalue(0);
            set_semvalue(1);

            // loop through each element in the range and check if n-1 isdigit and n is not a digit, swap if true and using semaphores to control the critical section
            // dont allow the process to swap if it is the first element in the list
            for (int i = target_index_min; i < target_index_max; i++)
            {
                if (debug)
                {
                    printf("Child %d checking element %d\n", j, i);
                }
                // check if n-1 is a digit and n is not a digit
                // skip if it is the first element

                if (i == 0 && skip_one)
                {
                    if (debug)
                    {
                        printf("Child %d skipping element %d\n", j, i);
                    }
                    skip_one = false;
                    continue;
                }
                if (isdigit(shared_memory[i - 1]) && !isdigit(shared_memory[i]))
                {
                    if (debug)
                    {
                        printf("Child %d swapping element %d and %d\n", j, i - 1, i);
                    }
                    // swap the elements
                    swap(&shared_memory[i - 1], &shared_memory[i]);
                    // wait for semaphore 0 to be available
                    semaphore_p(0);
                    // print out the list
                    if (debug)
                    {
                        printf("Child %d: ", j);
                        for (int k = 0; k < LIST_SIZE; k++)
                        {
                            printf("%c", shared_memory[k]);
                        }
                        printf("\n");
                    }
                    // signal semaphore 1
                    semaphore_v(1);
                }
            }
            // release semaphores
            del_semvalue(0);
            del_semvalue(1);

            // for (int i = target_index_min; i < target_index_max; i++)
            // {
            //     if (isdigit(shared_memory[i - 1]) && !isdigit(shared_memory[i]))
            //     {
            //         if (debug)
            //         {
            //             printf("Child %d performing swap at i = %d\n", j, i);
            //         }
            //         semaphore_p(0);
            //         swap(&shared_memory[i - 1], &shared_memory[i]);
            //         semaphore_v(0);
            //     }
            // }

            // print out the array
            // if (debug)
            // {
            //     printf("\n");
            //     for (int i = 0; i < LIST_SIZE; i++)
            //     {
            //         printf("%c", shared_memory[i]);
            //     }
            //     printf("\n");
            // }

        // parent
        default:
            // wait for children to finish
            for (int i = 0; i < 3; i++)
            {
                int status;
                pid_t child_pid = wait(&status);
                // wait(&status);
                if (debug)
                {
                    printf("Child %d exited with status %d\n", child_pid, status);
                }
            }
            if (debug)
            {
                printf("Parent: All children have terminated\n");
            }

            // store the array locally

            for (int i = 0; i < LIST_SIZE; i++)
            {
                local_array[i] = shared_memory[i];
            }

            // delete shared memory
            if (shmctl(shmid, IPC_RMID, 0) == -1)
            {
                perror("shmctl(IPC_RMID) failed");
                exit(EXIT_FAILURE);
            }
            if (debug)
            {
                printf("shared memory deleted\n");
            }
        }
    }
    // print out the array

    printf("\n");
    for (int i = 0; i < LIST_SIZE; i++)
    {
        printf("%c", local_array[i]);
    }
    printf("\n");
}
