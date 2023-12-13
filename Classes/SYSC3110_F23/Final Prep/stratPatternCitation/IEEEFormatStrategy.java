// IEEEFormatStrategy.java
public class IEEEFormatStrategy implements CitationStrategy{
    @Override
    public String formatName(String firstName, String lastName){
        return firstName.charAt(0) + ". " + lastName;
    }
}
