#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <wait.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/shm.h>

int main()
{
    int shmid;
    int *shared_memory;
    pid_t pid;
    pid_t child_pid;
    int random_number;
    int THRESHOLD = 50;
    bool generated = false;
    int exit_code;
    bool signal_received = false;

    // create shared memory
    shmid = shmget((key_t)1234, sizeof(random_number), IPC_CREAT | 0666);
    if (shmid == -1)
    {
        perror("shmget failed \n");
        exit(EXIT_FAILURE);
    }
    // make memory accessible to child
    shared_memory = (int *)shmat(shmid, (void *)0, 0);
    if (shared_memory == (void *)-1)
    {
        perror("shmat failed \n");
        exit(EXIT_FAILURE);
    }
    printf("shared memory attached at %X\n", shared_memory);

    // create fork and attach shared memory
    child_pid = fork();

    switch (child_pid)
    {
    // Error
    case -1:
        perror("fork failed");
        exit(1);
    // Child
    case 0:
        // generate random number
        while (!generated)
        {
            random_number = rand() % 100;
            printf("random number: %d\n", random_number);
            if (random_number > THRESHOLD)
            {
                generated = true;
            }
        }
        // at this point we have a random number > threshold
        // store this number in shared memory
        *shared_memory = random_number;
        // detach shared memory
        if (shmdt(shared_memory) == -1)
        {
            perror("shmdt failed");
            exit(1);
        }

        // send signal to parent to read current value from shared memory
        kill(getppid(), SIGALRM);
        signal_received = true;

        exit_code = 50;
    // Parent
    default:
        // wait for signal from child
        while (true)
        {
            pause();
            if (signal_received == SIGALRM)
            {
                // read random number from shared memory
                random_number = *shared_memory;
                printf("random number: %d\n", random_number);
                if (random_number > THRESHOLD)
                {
                    // this is what we expect
                    printf("random number is over threshold\n");
                }
                else
                {
                    printf("random number is under threshold\n");
                }
                exit_code = 0;
                break;
            }
        }
    }

    // wait for child process to finish selecting a number
    if (child_pid != 0)
    {
        int stat_val;
        child_pid = wait(&stat_val);

        printf("child has finished: PID = %d \n", child_pid);
        if (WIFEXITED(stat_val))
        {
            printf("Child exited with code %d \n", WEXITSTATUS(stat_val));
        }
        else
        {
            printf("Child terminated abnormally \n");
        }
    }
    exit(exit_code);
}
