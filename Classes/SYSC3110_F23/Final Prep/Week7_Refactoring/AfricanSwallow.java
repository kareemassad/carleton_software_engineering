package Bird_Example;

public class AfricanSwallow extends Bird{

    public String plumage() {
        return (super.numberOfCoconuts > 2) ? "tired" : "average";
    }
}
