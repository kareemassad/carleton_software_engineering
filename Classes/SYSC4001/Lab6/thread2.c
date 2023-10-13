
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

char message[] = "Hello World!";
int run_now = 1; // 1 = main , 2 = new thread

void *thread_function(void *arg)
{
    printf(">> thread function is running. Argument was %s\n", (char *)arg);
    // int test = 100;
    //  printf(">> checking scope of test %d", test); doesnt work
    sleep(3);
    strcpy(message, "Goodbye");
    pthread_exit("Thank you for the cpu time");
}

int main(int argc, char const *argv[])
{
    // int test = 100; doesnt work
    int res;
    pthread_t thread;
    void *thread_result;

    res = pthread_create(&thread, NULL, thread_function, (void *)message);
    if (res != 0)
    {
        printf(">> Thread creation failed: %d\n", res);
        exit(EXIT_FAILURE);
    }

    int print_count1 = 0;

    while (print_count1++ < 20)
    {
        printf("print count1 %d\n", print_count1);
        if (run_now == 1)
        {
            printf("1 \n");
            run_now = 2;
        }
        else
        {
            sleep(1);
        }
    }

    printf(">> Waiting for thread to finish...\n");
    res = pthread_join(thread, &thread_result);
    // printf(">> test %d\n", test);
    if (res != 0)
    {
        printf(">> Thread join failed: %d\n", res);
        exit(EXIT_FAILURE);
    }

    printf(">> Thread joined, result is: %s\n", (char *)thread_result);
    printf(">> Message is now %s \n", message);

    return 0;
}