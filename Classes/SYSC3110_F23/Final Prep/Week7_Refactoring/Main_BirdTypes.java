package Bird_Example;

public class Main_BirdTypes {
    public static void main(String[] args) {
        Bird bird = new EuropeanSwallow();
        System.out.println(bird.plumage());

        bird = new AfricanSwallow();
        bird.setVoltage(100);
        bird.setNumberOfCoconuts(3);
        System.out.println(bird.plumage());

        bird = new NorwegianBlueParrot();
        bird.setVoltage(100);
        bird.setNumberOfCoconuts(3);
        System.out.println(bird.plumage());


    }
}
