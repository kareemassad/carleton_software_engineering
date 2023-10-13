seed = 27
a = 9515
c = 0
m = 32749

def generateValue():
    global seed
    x = (a * seed + c) % m
    seed = x
    return x/m

def main():
    for i in range(300):
        print(generateValue())

if __name__ == "__main__":
    main()