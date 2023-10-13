
interface Animals {
    //interface methods have no body like abstract methods
    public void animalSound();
    public void sleep();
}
class Piggy implements Animals {
    public void animalSound() {
        //body of animalSound() is implemented here
        System.out.println("The pig says: oinkeriino");
    }
    public void sleep() {
        //body of slepe() is implemented here
        System.out.println("Zzz Zzz");
    }
}
class Main {
    public static void main(String[] args) {
        Piggy a = new Piggy();
        a.animalSound();    
        a.sleep();
    }
}