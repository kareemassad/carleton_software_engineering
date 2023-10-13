#include <time.h>
#include <sys/time.h>
#include <stdlib.h>
#include <stdio.h>

char message[] = "Hello World!";

void *thread_function(void *arg)
{
    printf(">> inside thread function");
}

int main(int argc, char const *argv[])
{

    struct timeval start, end;
    gettimeofday(&start, NULL);
    // add fork() to create a process
    // create fork of process
    pid_t pid;
    pid_t child_pid;

    child_pid = fork();

    switch (child_pid)
    {
    // Error
    case -1:
        perror("fork failed");
        exit(1);
    // Child
    case 0:
        // do something
        printf(">> inside child process");
        break;
    // Parent
    default:
        // add code to run in parent process
        // wait for child to finish
        printf(">> inside parent process");
        wait(NULL);
        printf(">> parent finish waiting for child");
        // do nothing
        break;
    }

    gettimeofday(&end, NULL);
    printf("> fork took : %ld microseconds\n", (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_usec - start.tv_usec));

    gettimeofday(&start, NULL);
    // add pthread_create() to create a thread
    // use pthread_create() to create 3 threads
    pthread_t thread;
    int res;
    void *thread_result;

    res = pthread_create(&thread, NULL, thread_function, (void *)message);
    if (res != 0)
    {
        printf(">> Thread creation failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Waiting for thread to finish...\n");
    res = pthread_join(thread, &thread_result);
    if (res != 0)
    {
        printf(">> Thread join failed: %d\n", res);
        exit(EXIT_FAILURE);
    }

    gettimeofday(&end, NULL);
    printf("> pthread_create took %ld microseconds \n", ((end.tv_sec * 1000000 + end.tv_usec) - (start.tv_sec * 1000000 + start.tv_usec)));

    return 0;
}
