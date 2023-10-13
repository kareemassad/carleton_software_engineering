#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <sys/sem.h>

#include "semun.h"

static int set_semvalue(void);
static void del_semvalue(void);
static int semaphore_p(void);
static int semaphore_v(void);

static int sem_id;

int main() {
	int i;
	int pause_time;

	srand((unsigned int)getpid());

	sem_id = semget((key_t)1234, 1, 0666 | IPC_CREAT);

	pid_t pid;
	pid = fork();

	switch(pid) {
	case -1:
		perror("fork failed");
		exit(1);
	case 0:
		/* Process 2 */
		if(!semaphore_p()) exit(EXIT_FAILURE);
		printf("Process 2 (%d) inside critical section\n", getpid()); fflush(stdout);
		pause_time = rand() % 3;
		sleep(pause_time);
		printf("Process 2 (%d) leaving critical section\n\n", getpid());
		if(!semaphore_v()) exit(EXIT_FAILURE);
		
		pause_time = rand() % 2;
		sleep(pause_time);
		break;
	default:
		/* Process 1 */
		if(!set_semvalue()) {
			fprintf(stderr, "Failed to initailize semaphore\n");
			exit(EXIT_FAILURE);
		}
		printf("Semaphore initialized successfully\n\n");
		
		sleep(3);
		if(!semaphore_p()) exit(EXIT_FAILURE);
		printf("Process 1 (%d) inside critical section\n", getpid()); fflush(stdout);
		pause_time = rand() % 3;
		sleep(pause_time);
		printf("Process 1 (%d) outside critical section\n\n", getpid());
		if(!semaphore_v()) exit(EXIT_FAILURE);
		
		pause_time = rand() % 2;
		sleep(pause_time);
		del_semvalue();
		break;	
	}
	exit(EXIT_SUCCESS);
}

/* Initializing the semaphore with value 1
Returns 1 if successful */
static int set_semvalue(void) {
	union semun sem_union;
	sem_union.val = 1;
	if(semctl(sem_id, 0, SETVAL, sem_union) == -1) return(0);
	return(1);
}

/* Used to delete a semaphore */
static void del_semvalue(void) {
	union semun sem_union;
	if(semctl(sem_id, 0, IPC_RMID, sem_union) == -1) {
		fprintf(stderr, "Failed to delete semaphore\n");
	} else {
		fprintf(stderr, "Succesfully deleted semaphore\n");
	}
}

/* Initalizes the wait() call for semaphore by
changing the value of the semaphore by -1.
Returns 1 if successful changes made */
static int semaphore_p(void) {
	struct sembuf sem_b;
	sem_b.sem_num = 0;
	sem_b.sem_op = -1; /* P() */
	sem_b.sem_flg = SEM_UNDO;
	if(semop(sem_id, &sem_b, 1) == -1) {
		fprintf(stderr, "semaphore_p failed\n");
		return(0);
	}
	return(1);
}

/* Initializes the signal() call for semaphore
by changing the value of the semaphore by 1.
Returns 1 if successful changes made */
static int semaphore_v(void) {
	struct sembuf sem_b;
	sem_b.sem_num = 0;
	sem_b.sem_op = 1; /* V() */
	sem_b.sem_flg = SEM_UNDO;
	if(semop(sem_id, &sem_b, 1) == -1) {
		fprintf(stderr, "semaphore_v failed\n");
		return(0);
	}
	return(1);
}		
