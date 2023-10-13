// text-manager stores and performs operations on text (a sequence of sentences sperated by a period)
// the text-manager is a server that receives a text from a client (user.c), performs an operation (Append, Delete, Remove, Search) and then sends the text back to the client
// System must use message queues to communicate with the text-manager and the user
// Available operations are:
// Append(sentence): A string including optional punctuation marks is sent as an argument for this command. The text-manager appends the string to the end of the text.
// Delete(W): The text-manager deletes every occurrence of the word W from the text.
// Remove(target-sentence): The text-manager removes the first occurrence of the target sentence from the text.
// Search(A): The text-manager searches for the first occurrence of the string A in the text and returns the first sentence containing it.
//

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

// Message structure to receive from user
struct msg_rcv
{
    long int msg_type;
    // Append, Remove, Search, Delete
    char command[MAX_TEXT];
    // depends on command
    char argument[MAX_TEXT];
};
// Message structure to send back to user
struct msg_pass
{
    long int msg_type;
    // main text
    char text[MAX_TEXT];
    // time in milliseconds
    long int time;
};

// operation functions
// add sentence to end of text
char *appendSentance(char *argument);
// delete word from text
char *deleteWord(char *argument);
// remove sentence from text
char *removeSentance(char *argument);
// search for string in text
char *searchWord(char *argument);

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
    long int msg_rec = 0;
    // init time using gettimeofday
    struct timeval start, end;

    // times vars
    long time_used;

    // open message queues
    serv_qid = msgget(SERVER_MQUEUE, 0666 | IPC_CREAT) < 0;
    if (serv_qid == -1)
    {
        perror("msgget (server2)");
        exit(1);
    }
    cli_qid = msgget(CLIENT_MQUEUE, 0666 | IPC_CREAT) < 0;
    if (cli_qid == -1)
    {
        perror("msgget (client2)");
        exit(1);
    }

    // check if input received from user.c message queue (msg_pass.command) is either Append, Delete, Remove, Search
    // if not, error and exit
    // if Append, get user input for sentence from user.c
    // if Delete, get user input for word from user.c
    // if Remove, get user input for sentence from user.c
    // if Search, get user input for string from user.c

    // get user input for command
    while (is_running)
    {
        // receive message from user.c and store in msg_rcv
        if (msgrcv(cli_qid, &msg_rcv, sizeof(msg_rcv), msg_rec, MSG_NOERROR) < 0)
        {
            perror("msgrcv (server2)");
            exit(EXIT_FAILURE);
        }

        // remove newline character from end of command and argument
        msg_rcv.command[strlen(msg_rcv.command) - 1] = '\n';
        msg_rcv.argument[strlen(msg_rcv.argument) - 1] = '\n';

        // print all received message
        printf("Received message from user.c:\n");
        printf("msg_type: %ld\n", msg_rcv.msg_type);
        printf("command: %s\n", msg_rcv.command);
        printf("argument: %s\n", msg_rcv.argument);

        // apply operation based on command received from user.c
        if (!strcmp(msg_rcv.command, "Append\n"))
        {
            printf("Append sentance received in text-manager: %s\n", msg_rcv.argument);
            // measure time taken to perform operation and store in msg_pass
            gettimeofday(&start, NULL);
            strcpy(msg_pass.text, appendSentance(msg_rcv.argument));
            gettimeofday(&end, NULL);
            // calculate time taken to perform operation in milliseconds
            time_used = (end.tv_sec - start.tv_sec) * 1000 + (end.tv_usec - start.tv_usec) / 1000;

            // print time taken to perform operation
            printf("Time taken to perform operation: %ld\n", time_used);

            // send time taken to user.c
            msg_pass.msg_type = 1;
            msg_pass.time = time_used;
        }
        else if (!strcmp(msg_rcv.command, "Delete\n"))
        {
            printf("Delete word received in text-manager: %s\n", msg_rcv.argument);

            gettimeofday(&start, NULL);
            strcpy(msg_pass.text, deleteWord(msg_rcv.argument));
            gettimeofday(&end, NULL);
            // calculate time taken to perform operation in milliseconds
            time_used = (end.tv_sec - start.tv_sec) * 1000 + (end.tv_usec - start.tv_usec) / 1000;

            // print time taken to perform operation
            printf("Time taken to perform operation: %ld\n", time_used);

            // send time taken to user.c
            msg_pass.msg_type = 1;
            msg_pass.time = time_used;
        }
        else if (!strcmp(msg_rcv.command, "Remove\n"))
        {
            printf("Remove sentance received in text-manager: %s\n", msg_rcv.argument);

            gettimeofday(&start, NULL);
            strcpy(msg_pass.text, removeSentance(msg_rcv.argument));
            gettimeofday(&end, NULL);
            // calculate time taken to perform operation in milliseconds
            time_used = (end.tv_sec - start.tv_sec) * 1000 + (end.tv_usec - start.tv_usec) / 1000;

            // print time taken to perform operation
            printf("Time taken to perform operation: %ld\n", time_used);

            // send time taken to user.c
            msg_pass.msg_type = 1;
            msg_pass.time = time_used;
        }
        else if (!strcmp(msg_rcv.command, "Search\n"))
        {
            printf("Search string received in text-manager: %s\n", msg_rcv.argument);

            gettimeofday(&start, NULL);
            strcpy(msg_pass.text, searchWord(msg_rcv.argument));
            gettimeofday(&end, NULL);
            // calculate time taken to perform operation in milliseconds
            time_used = (end.tv_sec - start.tv_sec) * 1000 + (end.tv_usec - start.tv_usec) / 1000;

            // print time taken to perform operation
            printf("Time taken to perform operation: %ld\n", time_used);

            // send time taken to user.c
            msg_pass.msg_type = 1;
            msg_pass.time = time_used;
        }
        else if (!strcmp(msg_rcv.command, "Exit\n"))
        {
            printf("Exit received in text-manager\n");
            is_running = false;
        }
        else
        {
            printf("Error: Invalid command received from user.c\n");
            exit(EXIT_FAILURE);
        }

        // print new text
        printf("New text: %s\n", text);

        // send message back to user.c
        if (msgsnd(cli_qid, &msg_pass, sizeof(msg_pass), 0) < 0)
        {
            perror("msgsnd");
            exit(1);
        }
    }

    return 0;
}

// Appends sentence to end of text and stores it in msg_pass.text
char *appendSentance(char *argument)
{
    // append sentence to end of text and store in msg_pass.text
    // if text is empty, just copy argument to text

    // print current text
    printf("Current text: %s\n", text);
    printf("Appending sentance to text.\n");

    // if text is empty, just copy argument to text
    if (strlen(text) == 0)
    {
        strcpy(text, argument);
    }
    else
    {
        // concat the argument sentence to the end of text
        strcat(text, argument);
    }

    printf("New text: %s\n", text);
    return text;
}

// Deletes word from text
char *deleteWord(char *argument)
{
    // delete word from text and store in msg_pass.text
    // if word is not found, print error message

    // print current text
    printf("Current text: %s\n", text);
    printf("Deleting word from text.\n");

    // check if word is in text
    if (strstr(text, argument) == NULL)
    {
        printf("Word not found.\n");
    }
    else
    {
        // delete word from text
        char *temp = text;
        char *temp2 = text;
        while (strstr(temp, argument) != NULL)
        {
            temp = strstr(temp, argument);
            temp2 = temp;
            temp += strlen(argument);
        }
        temp2[0] = '\0';
        temp2 += strlen(argument);
        strcpy(text, temp2);
    }

    printf("New text: %s\n", text);
    return text;
}

// Removes sentence from text
char *removeSentance(char *argument)
{
    // remove sentence from text and store in msg_pass.text
    // if sentence is not found, print error message

    // print current text
    printf("Current text: %s\n", text);
    printf("Removing sentance from text.\n");

    // check if sentance is in text
    if (strstr(text, argument) == NULL)
    {
        printf("Sentance not found.\n");
    }
    else
    {
        // remove sentance from text
        char *p = strstr(text, argument);
        char *q = p + strlen(argument);
        while (*q != '\0')
        {
            *p = *q;
            p++;
            q++;
        }
        *p = '\0';
    }

    printf("New text: %s\n", text);
    return text;
}

// Searches for word in text, prints it, then returns the original text
char *searchWord(char *argument)
{
    // search for word in text and store in msg_pass.text
    // if word is not found, print error message

    int count;
    // print current text
    printf("Current text: %s\n", text);
    printf("Searching for word in text.\n");

    // check if word is in text
    if (strstr(text, argument) == NULL)
    {
        printf("Word not found.\n");
    }
    else
    {
        // count number of times word is found in text
        count = 0;
        char *p = strstr(text, argument);
        while (p != NULL)
        {
            count++;
            p = strstr(p + 1, argument);
        }
    }

    // print number of occurrences
    printf("Number of occurrences: %d\n", count);
    // return number of occurrences
    return text;
}
