#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

void *thread_function(void *arg);
int main()
{
    int res;
    pthread_t a_thread;
    void *thread_result;

    res = pthread_create(&a_thread, NULL, thread_function, NULL);
    if (res != 0)
    {
        printf(">> Thread creation failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    sleep(3);
    // canceling thread
    printf(">> Canceling thread\n");
    res = pthread_cancel(a_thread);
    if (res != 0)
    {
        printf(">> Thread cancel failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Waiting for thread to finish...\n");
    res = pthread_join(a_thread, &thread_result);
    if (res != 0)
    {
        printf(">> Thread join failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Thread joined, result is: %s\n", (char *)thread_result);
    return 0;
}

void *thread_function(void *arg)
{
    int i, res;
    res = pthread_setcancelstate(PTHREAD_CANCEL_ENABLE, NULL);
    if (res != 0)
    {
        printf(">> Thread cancel state change failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    res = pthread_setcanceltype(PTHREAD_CANCEL_ASYNCHRONOUS, NULL);
    if (res != 0)
    {
        printf(">> Thread cancel type change failed: %d\n", res);
        exit(EXIT_FAILURE);
    }
    printf(">> Thread is running\n");
    for (i = 0; i < 10; i++)
    {
        printf(">> Thread is still running (%d) ... \n, i");
        sleep(1);
    }
    pthread_exit("0");
}
