import random

start = 0
bound = 100000
tailsThree = 0

while start < bound:
    start = start + 1

    coin1 = (random.getrandbits(1))
    coin2 = (random.getrandbits(1))
    coin3 = (random.getrandbits(1))
    coin4 = (random.getrandbits(1))
    
    if coin1 + coin2 + coin3 + coin4 == 3:
        tailsThree = tailsThree + 1

print(tailsThree)

print(tailsThree / bound)

