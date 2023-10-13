#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

sem_t bin_sem;

#define WORK_SIZE 1024
char work_area[WORK_SIZE];

void *thread_function(void *arg)
{
    sem_wait(&bin_sem);
    while (strncmp("end", work_area, 3) != 0)
    {
        printf("you input %d characters \n", strlen(work_area) - 1);
        sem_wait(&bin_sem);
    }
    pthread_exit(NULL);
}

int main(int argc, char const *argv[])
{
    int res;
    pthread_t thread;
    void *thread_result;

    res = sem_init(&bin_sem, 0, 0);
    if (res != 0)
    {
        printf(">> Semaphore initialization failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    res = pthread_create(&thread, NULL, thread_function, NULL);
    if (res != 0)
    {
        printf(">> Thread creation failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">>Input some test. enter 'end' to finish \n");
    while (strncmp("end", work_area, 3) != 0)
    {
        fgets(work_area, WORK_SIZE, stdin);
        sem_post(&bin_sem);
    }
    printf(">> Waiting for thread to finish...\n");
    res = pthread_join(thread, &thread_result);
    if (res != 0)
    {
        printf(">> Thread join failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Thread joined, result is: %s\n", (char *)thread_result);
    sem_destroy(&bin_sem);
    return 0;
}
