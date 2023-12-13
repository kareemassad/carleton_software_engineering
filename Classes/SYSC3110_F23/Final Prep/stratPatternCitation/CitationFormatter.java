//CitationFormatter.java
public class CitationFormatter {
    private CitationStrategy strategy;

    public void setStrategy(CitationStrategy strategy) {
        this.strategy = strategy;
    }

    public String formatName(String firstName, String lastName) {
        return strategy.formatName(firstName, lastName);
    }
}
