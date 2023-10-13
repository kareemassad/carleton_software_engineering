#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <wait.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/shm.h>

int performCollatz(int n)
{
    int step_count = 0;
    if (n == 1)
    {
        // done so return step count
        return step_count;
    }

    // if even then return num/2
    while (n != 1)
    {
        if (n % 2 == 0)
        {
            step_count++;
            n = n / 2;
        }
        //  If odd return num*3+1
        else
        {
            step_count++;
            n = 3 * n + 1;
        }
    }
    return step_count;
}

// finds the minimum value in the array
int findMin(int S[3])
{
    int min;
    // returns the minimum value in the array
    for (int i = 0; i < 3; i++)
    {
        if (i == 0)
        {
            min = S[i];
        }
        else if (S[i] < min)
        {
            min = S[i];
        }
    }
    return min;
}

// finds the maximum value in the array
int findMax(int S[3])
{
    int max;
    // returns the maximum value in the array
    for (int i = 0; i < 3; i++)
    {
        if (i == 0)
        {
            max = S[i];
        }
        else if (S[i] > max)
        {
            max = S[i];
        }
    }
    return max;
}

int findAvg(int S[3])
{
    int avg;

    avg = (S[0] + S[1] + S[2]) / 3;
    return avg;
}

void printInfo(int S[3])
{
    // n = S[i];
    // calculate the minimum number of steps, maximum number of steps, and the average number of steps
    int min1 = findMin(S);
    int max1 = findMax(S);
    int avg1 = findAvg(S);

    printf("The minimum number of steps is %d\n", min1);
    printf("The maximum number of steps is %d\n", max1);
    printf("The average number of steps is %d\n", avg1);
}
int main()
{
    int n;
    // S will hold the number of steps for each number to reach 1 from C
    int S[3];

    // shared mem stuff
    int shmid;
    int *shared_memory;
    pid_t pid;
    pid_t child_pid;

    printf("Enter a number: \n");
    scanf("%d", &n);
    // if n is less than 1, print error message and exit
    if (n < 1)
    {
        printf("Error: Number must be greater than 0\n");
        exit(0);
    }

    printf("Registered Input: %d \n", n);

    int C[3] = {n, 2 * n, 3 * n};
    int c_length = sizeof(C) / sizeof(C[0]);

    // print C
    printf("C: %d %d %d \n", C[0], C[1], C[2]);

    // perform collatz on each element of C in different forks
    // create shared memory
    shmid = shmget((key_t)1234, sizeof(S), IPC_CREAT | 0666);
    if (shmid == -1)
    {
        perror("shmget failed \n");
        exit(EXIT_FAILURE);
    }
    // now make memory accessible to 3 children
    shared_memory = (int *)shmat(shmid, (void *)0, 0);
    if (shared_memory == (void *)-1)
    {
        perror("shmat failed \n");
        exit(EXIT_FAILURE);
    }
    printf("shared memory attached at %X\n", shared_memory);

    // time of day stuff
    struct timeval start, end;
    gettimeofday(&start, NULL);

    // store all results in S
    for (int i = 0; i < 3; i++)
    {
        child_pid = fork();

        switch (child_pid)
        {
        case -1:
            // Error
            perror("fork failed");
            exit(1);

        case 0:
            // Child
            printf("Child Process: Working with Collatz Function n: %d \n", C[i]);
            // perform collatz on C[0]
            S[i] = performCollatz(C[i]);
            // store result in shared memory
            shared_memory[i] = S[i];
            // print shared_memory
            printf("Child Process: Shared Memory: %d \n", shared_memory[i]);
            exit(0);

        default:
            // parent
            printf("Parent Process: Waiting for Collatz Function n: %d \n", C[i]);
            // wait for all children to finish
            waitpid(child_pid, NULL, 0);

            // store shared memory data in S
            S[i] = shared_memory[i];

            printf("Parent Process: Shared Memory: %d \n", shared_memory[i]);
            // end time of day stuff
            gettimeofday(&end, NULL);
            printf("Time taken to count to 10^5 is : %ld micro seconds\n",
                   ((end.tv_sec * 1000000 + end.tv_usec) -
                    (start.tv_sec * 1000000 + start.tv_usec)));
            break;
        }
    }

    // wait for children to finish
    for (int i = 0; i < 3; i++)
    {
        int status;
        pid_t child_pid = wait(&status);
        // wait(&status);
        printf("Child %d exited with status %d\n", child_pid, status);
    }

    // print shared memory
    printf("Shared Memory: %d %d %d \n", shared_memory[0], shared_memory[1], shared_memory[2]);

    // print S
    printf("S: %d %d %d \n", S[0], S[1], S[2]);

    // print info
    printInfo(S);
}