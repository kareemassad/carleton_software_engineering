#!/bin/bach

# Cleaning
sh cleaning.sh

# Compiling unit tests for mySquareRoot.
cc -c mySquareRoot-UnitTests.c 
cc -c mySquareRoot.c
cc mySquareRoot-UnitTests.o mySquareRoot.o -o mySquareRoot-UnitTests
# Running those unit tests:
./mySquareRoot-UnitTests

# Compiling unit tests for root, with stub stubbing mySquareRoot.
# Notice that with these compilig instructions, linking will use the 
# mySquareRoot() function from mySquareRoot-Stub.c, i.e., the stub
# (rather than the version in mySquareRoot.c)
cc -c mySquareRoot-Stub.c
cc -c root.c
cc -c root-UnitTestWithStub.c
cc mySquareRoot-Stub.o root.o root-UnitTestWithStub.o -o root-UnitTestWithStubs
# running those unit tests
./root-UnitTestWithStubs

# compiling the entire program
cc -c mySquareRoot.c
cc -c root.c
cc -c quadratic.c
cc mySquareRoot.o root.o quadratic.o -o quadratic

