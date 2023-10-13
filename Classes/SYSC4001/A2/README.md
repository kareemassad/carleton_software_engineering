# Assignment 2

## Goal of the Design

- The goal of this assignment is to seperate the letters from the numbers in a given string using multiple child processes.
- This is done by having child processes dedicated to certain indices of the string.
- To accomplish this, the string must be stored in shared memory and the child processes must read from and write to the shared memory.
- To handle the shared sections of the array, the child processes use semaphores to ensure safety in the critical section and that the shared memory is not accessed by more than one child process at a time.

## How to run the program

1. Extract the content of the .tar file into a folder.
2. Run `make` in the folder terminal.
3. Run the executable file generated using `./FILTER`
4. The program will ask if you would like to run it in debug mode and then it will ask for a 7 character input.

## Pseudocode for the program

1. The program asks for the user if they would like to run in debug mode.
2. The program creates a shared memory segment with a size of 7.
3. The program makes the shared memory accessible to children.
4. The program asks the user for a 7 character input.
5. The program creates 2 semaphores then creates the 3 child processes
6. Each child is assigned a sepecific set of indices that it will read from and write to.
7. For example, The first process will control indicies 0-2, the second process will control indicies 2-4, and the third process will control the indicies 4-6.
8. If the item at i is not a digit and the item at i-1 is a digit, then swap them. This is done to ensure that the numbers are in the correct order.
9. Semaphores are used in the critical section to ensure that the shared memory is not accessed by more than one child process at a time.
10. The program will also skip the first digit as otherwise, the algorithm will switch the first digit(i) with the (i-1)th digit which will always be 0.
11. After all the processes are completed, the parent will store the array locally, print it out, delete the shared memory, then exit.

## Discussion Test Results

I believe that the program is almost working correctly less a small bug. The algorithm ran into issues at first as the first digit was swapping with the digit before it. This meant that it was always swapping with 0. I added a fix that would skip that first digit to avoid this issue but I believe it caused the program to ignore the last digit.

I also suspect that there might be a race condition prohibiting it from being fully sorted.

### Test 1 : 5A9MW6Z

Expected Result: AMWZ596
Actual Result: AMW596Z

### Test 2 : 123CDEF

Expected Result: CDEF123
Actual Result: 1CDE23F

