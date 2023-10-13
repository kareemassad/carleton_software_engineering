#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

// void *thread_function(void *arg);
pthread_mutex_t work_mutex;

#define WORK_SIZE 1024
char work_area[WORK_SIZE];
int time_to_exit = 0;

void *thread_function(void *arg)
{
    sleep(1);
    pthread_mutex_lock(&work_mutex);
    while (strncmp("end", work_area, 3) != 0)
    {
        printf("you input %d characters \n", strlen(work_area) - 1);
        work_area[0] = '\0';
        pthread_mutex_unlock(&work_mutex);
        sleep(1);
        pthread_mutex_lock(&work_mutex);
        while (work_area[0] == '\0')
        {
            pthread_mutex_unlock(&work_mutex);
            sleep(1);
            pthread_mutex_lock(&work_mutex);
        }
    }
    time_to_exit = 1;
    work_area[0] = '\0';
    pthread_mutex_unlock(&work_mutex);
    pthread_exit(NULL);
}

int main(int argc, char const *argv[])
{
    int res;
    pthread_t thread;
    void *thread_result;

    res = pthread_mutex_init(&work_mutex, NULL);
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
    pthread_mutex_lock(&work_mutex);

    printf(">>Input some test. enter 'end' to finish \n");
    while (!time_to_exit)
    {
        fgets(work_area, WORK_SIZE, stdin);
        // unlock mutex
        pthread_mutex_unlock(&work_mutex);
        while (1)
        {
            pthread_mutex_lock(&work_mutex);
            if (work_area[0] == '\0')
            {
                pthread_mutex_unlock(&work_mutex);
                sleep(1);
            }
            else
            {
                break;
            }
        }
    }
    pthread_mutex_unlock(&work_mutex);
    printf(">> Waiting for thread to finish...\n");
    res = pthread_join(thread, &thread_result);
    if (res != 0)
    {
        printf(">> Thread join failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Thread joined, result is: %s\n", (char *)thread_result);
    pthread_mutex_destroy(&work_mutex);
    return 0;
}
