/* SYSC 2006 Fall 2019 Lab  9 - linked_list.h */

/* A node in a singly-linked list that stores values of type int. */

typedef struct node {
  int data;
  struct node *next;
} node_t;

/* Functions presented in lectures. */

node_t *push(node_t *head, int data);
int length(node_t *head);
void print_list(node_t *head);

/* Functions that are implemented in Lab 9. */

int count(node_t *head, int target);
int max(node_t *head);
int fetch(node_t *head, int index);
int index(node_t *head, int target);
void extend(node_t *head, node_t *other);
