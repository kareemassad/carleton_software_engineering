/**
 * This program is used to learn about inheritance
 * 
 * @author Kareem El Assad
 * @version 1.0
 * 
 */
class Fish {
    public void animalSound() {
        System.out.println("The fish makes a sound");
    }
}

class Clownfish extends Fish {
    public void animalSound() {
        System.out.println("The clownfish says: wee wee");
    }
}

class Puffer extends Fish {
    //prints origin when not specified
    // public void animalSound() {
    //     System.out.println("The puffer says: bloop bloop");
    // }
}

class MainClass {
    public static void main(String[] args) {
        Fish myFish = new Fish(); //Create fish object
        Fish myClown = new Clownfish(); //Create a clownfish object
        Fish myPuffer = new Puffer(); //Create a pufferfish object

        myFish.animalSound();
        myClown.animalSound();
        myPuffer.animalSound();
    }
}