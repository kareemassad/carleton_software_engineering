import random

#sum of loaded dice chance == 1
die1 = (0.1, 0.1, 0.2, 0.3, 0.2, 0.1)  
die2 = (0.3, 0.1, 0.2, 0.1, 0.05, 0.25)

def roll(allDie):
    randomRoll = random.random()
    total = 0
    result = 1
    for mass in allDie:
        total += mass
        if randomRoll < total:
            return result
        result+=1

print("this is dice 1")

for _ in range(10):
    print(roll(die1), end=" ")

print("")
print("")

print("this is dice 2", end="\n") 

for _ in range(10):
    print(roll(die2), end=" ")    


