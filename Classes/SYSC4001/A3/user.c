// User is a client process
// User is responsible for sending the commands to the text-manager
// Use "gettimeofday" to find the average time it takes to perform an operation
// print the average time required to perform the operations corresponding to the command that was sent
// System must use 2 message queues to communicate with the text-manager and the user.
// One message queue is for sending data from User to text-manager and the other is for receiving data from text-manager to User.
// Each sentence transferred must be no more than 35 characters long.

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <sys/msg.h>
#include <string.h>
#include <stdbool.h>

#define SERVER_MQUEUE 1234
#define CLIENT_MQUEUE 4321
#define MAX_TEXT 35
#define MAX_ARG_PAGES 32

// Message structure to receive from text-manager
struct msg_rcv
{
    long int msg_type;
    // main text
    char text[MAX_TEXT];
    long int time;
};
// Message structure to send to text-manager
struct msg_pass
{
    long int msg_type;
    // Append, Remove, Search, Delete
    char command[MAX_TEXT];
    // depends on command
    char arguement[MAX_TEXT];
};

// init message queues
static int serv_qid = -1;
static int cli_qid = -1;

char text[MAX_TEXT];

int main(int argc, char const *argv[])
{

    // init
    struct msg_pass msg_pass;
    struct msg_rcv msg_rcv;
    bool is_running = true;
    char helper_buffer[MAX_TEXT];
    // open message queues
    // server
    serv_qid = msgget(SERVER_MQUEUE, 0666 | IPC_CREAT);
    if (serv_qid == -1)
    {
        fprintf(stderr, "msgget for server failed with error: %d\n", errno);
        exit(EXIT_FAILURE);
    }
    // client
    cli_qid = msgget(CLIENT_MQUEUE, 0666 | IPC_CREAT);
    if (cli_qid == -1)
    {
        fprintf(stderr, "msgget for client failed with error: %d\n", errno);
        exit(EXIT_FAILURE);
    }

    // get user input for command
    // options are: Append, Delete, Remove, Search
    // if command is not one of the above, repeat until user enters a valid command
    // if command is Append, get user input for sentence
    // if command is Delete, get user input for word
    // if command is Remove, get user input for sentence
    // if command is Search, get user input for string

    while (is_running)
    {
        // init
        msg_pass.msg_type = 1;

        // get user input for command using fgets
        printf("Enter command (Append, Delete, Remove, Search):  ");
        fgets(helper_buffer, MAX_TEXT, stdin);
        strcpy(msg_pass.command, helper_buffer);

        // if command is Append, get user input for sentence
        if (strcmp(msg_pass.command, "Append\n") == 0)
        {
            printf("Enter sentence:  ");
            fgets(helper_buffer, MAX_TEXT, stdin);
            strcpy(msg_pass.arguement, helper_buffer);
        }
        // if command is Delete, get user input for word
        else if (strcmp(msg_pass.command, "Delete\n") == 0)
        {
            printf("Enter word:  ");
            fgets(helper_buffer, MAX_TEXT, stdin);
            strcpy(msg_pass.arguement, helper_buffer);
        }
        // if command is Remove, get user input for sentence
        else if (strcmp(msg_pass.command, "Remove\n") == 0)
        {
            printf("Enter sentence:  ");
            fgets(helper_buffer, MAX_TEXT, stdin);
            strcpy(msg_pass.arguement, helper_buffer);
        }
        // if command is Search, get user input for string
        else if (strcmp(msg_pass.command, "Search\n") == 0)
        {
            printf("Enter string:  ");
            fgets(helper_buffer, MAX_TEXT, stdin);
            strcpy(msg_pass.arguement, helper_buffer);
        }
        else if (strcmp(msg_pass.command, "Exit\n") == 0)
        {
            is_running = false;
        }
        else
        {
            printf("Invalid command.\n");
            continue;
        }

        // print message before sending
        printf("Sending message to text-manager.c:\n");
        printf("Command: %s\n", msg_pass.command);
        printf("Argument: %s\n", msg_pass.arguement);

        // send message to text-manager
        if (msgsnd(serv_qid, &msg_pass, MAX_TEXT, 0) == -1)
        {
            perror("msgsnd");
            exit(1);
        }

        // receive message from text-manager
        if (msgrcv(cli_qid, &msg_rcv, MAX_TEXT, 0, MSG_NOERROR) == -1)
        {
            perror("msgrcv");
            exit(1);
        }
    }
    // print the average time required to perform the operations corresponding to the command that was sent
    printf("Average time required to perform the operations corresponding to the command that was sent: %ld\n", msg_rcv.time);
    // close message queues
    if (msgctl(serv_qid, IPC_RMID, NULL) < 0)
    {
        perror("msgctl (server)");
        exit(1);
    }
    if (msgctl(cli_qid, IPC_RMID, NULL) < 0)
    {
        perror("msgctl (client)");
        exit(1);
    }

    return 0;
}

