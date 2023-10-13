# Assignment 3

## How to run the program

1. Extract the content of the .tar file into a folder.
2. Run `make` in the folder terminal.
3. Run the executable files generated using `./Text-manager` (first) and `./User.c` (second)
4. The program will ask for the desired command then for the argument needed.
5. Typing "End" will end the program.

## Pseudocode for the program

1. The user.c program will begin by asking the user for a command then an argument.
2. The command is either Append, Delete, Remove, Search, or End.
3. Based on the command, the program will ask for a second argument.
4. The program will then send this to the text-manager.c program as a struct.
5. After that, the text manager will call the corresponding function command(argument).
6. While the function runs, the program will compute and store the total time taken by the function.
7. This will be stored in the struct that will be sent back to user.c
8. After the function has finished, the program will print the result and store it in the struct.
9. That struct is then sent back to the user.c and printed.

## Pseudocode for Append, Delete, Remove, Search, End

### Append

1. The program checks if the original text is empty, if it is, it will copy the argument to the text.
2. Otherwise, It will append the argument to the text using strcat.

### Remove and Delete

1. The program checks if the word is in the text. If it is, it will delete the word and store the result in the text.
2. Then, it will return the text

### Search

1. Uses strstr() to check if the word is in the text.
2. If it is, it will increment count and repeat until it is no longer in the text.

### End

1. Exits the program.

Note: Ran out of time to fix the search function. 