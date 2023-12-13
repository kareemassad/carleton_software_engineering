//ACMFormatStrategy.java
public class ACMFormatStrategy implements CitationStrategy {
    @Override
    public String formatName(String firstName, String lastName) {
        return firstName + " " + lastName + ".";
    }
}
