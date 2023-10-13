union semun
{
	// from textbook
	int val;
	struct semid_ds *buf;
	unsigned short *array;
};
