//Abstract Class
abstract class Animal {
    //Abstract Method makes the program not compile unless the other subclasses implement the method
    public abstract void animalSound();
    
    //Normal Method
    public void sleep() {
        System.out.println("Zzz Zzz");
    }
}

//Subclass (Inherit from Fish)
class Pig extends Animal {
    //Normal Method
    public void animalSound(){
        //The body of animalSound() is provided here
        System.out.println("The pig says: oinkers");
    }
}

class MyMainClass {
    public static void main(String[] args) {
        // Creates a pig object
        Pig myPig = new Pig(); 
        myPig.animalSound();
        myPig.sleep();
    }
}