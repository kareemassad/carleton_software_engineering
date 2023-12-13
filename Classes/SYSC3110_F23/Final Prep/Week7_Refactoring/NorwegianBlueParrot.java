package Bird_Example;

public class NorwegianBlueParrot extends Bird{
    @Override
    public String plumage() {

        return (super.getVoltage() > 100) ? "scorched" : "beautiful";
    }
}