# Assignment 1

- Kareem El Assad
- 2022/02/21
- 101107739

Note: The program does not count the initial step.

## Archive Contents

- MakeFile
- COLLATZ.c
- COLLATZ Executable
- README.md

## How to run the program

1. Unzip the .tar archive
2. Run the Makefile using the command `make`

## Discussion

- This program implements the Collatz conjecture using 3 child forks of a parent process.
- The program takes in a number from the user. If the number is less than or equal to 1, the program will exit. If the number is greater than 1, the program will apply the conjecture.
- The program will run the conjecture 3 different times using x, 2x, and 3x. Each fork will be responsible for computing the conjecture for it's designated number.
- The program uses an array in shared memory to store the number of steps. 
- This means that the first fork will compute the number of steps for x, the second fork will compute the number of steps for 2x, and the third fork will compute the number of steps for 3x.
- The results for x can be found in the array at index 0, the results for 2x can be found at index 1, and the results for 3x can be found at index 2.

## Test Cases

### Case 1: x=25

- The Collatz sequence for 25 is: 25, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
- Total steps: 23 (24 including the initial step)
- Expected Output:

  ```C
    Registered Input: 25 
    C: 25 50 75 
    shared memory attached at E6F76000
    Parent Process: Waiting for Collatz Function n: 25 
    Child Process: Working with Collatz Function n: 25 
    Child Process: Shared Memory: 23 
    Parent Process: Shared Memory: 23 
    Time taken to count to 10^5 is : 570 micro seconds
    Parent Process: Waiting for Collatz Function n: 50 
    Child Process: Working with Collatz Function n: 50 
    Child Process: Shared Memory: 24 
    Parent Process: Shared Memory: 24 
    Time taken to count to 10^5 is : 912 micro seconds
    Parent Process: Waiting for Collatz Function n: 75 
    Child Process: Working with Collatz Function n: 75 
    Child Process: Shared Memory: 14 
    Parent Process: Shared Memory: 14 
    Time taken to count to 10^5 is : 1226 micro seconds
    Child -1 exited with status 32604
    Child -1 exited with status 32604
    Child -1 exited with status 32604
    Shared Memory: 23 24 14 
    S: 23 24 14 
    The minimum number of steps is 14
    The maximum number of steps is 24
    The average number of steps is 20

  ```

### Case 2: x=11

- The Collatz sequence for 11 is: 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
- Total steps: 14 (15 including the initial step)
- Expected Output:

  ```C
    [kareem@localhost A1]$ ./COLLATZ 
    Enter a number: 
    11
    Registered Input: 11 
    C: 11 22 33 
    shared memory attached at F57A6000
    Parent Process: Waiting for Collatz Function n: 11 
    Child Process: Working with Collatz Function n: 11 
    Child Process: Shared Memory: 14 
    Parent Process: Shared Memory: 14 
    Time taken to count to 10^5 is : 413 micro seconds
    Parent Process: Waiting for Collatz Function n: 22 
    Child Process: Working with Collatz Function n: 22 
    Child Process: Shared Memory: 15 
    Parent Process: Shared Memory: 15 
    Time taken to count to 10^5 is : 738 micro seconds
    Parent Process: Waiting for Collatz Function n: 33 
    Child Process: Working with Collatz Function n: 33 
    Child Process: Shared Memory: 26 
    Parent Process: Shared Memory: 26 
    Time taken to count to 10^5 is : 1223 micro seconds
    Child -1 exited with status 32702
    Child -1 exited with status 32702
    Child -1 exited with status 32702
    Shared Memory: 14 15 26 
    S: 14 15 26 
    The minimum number of steps is 14
    The maximum number of steps is 26
    The average number of steps is 18
  ```
