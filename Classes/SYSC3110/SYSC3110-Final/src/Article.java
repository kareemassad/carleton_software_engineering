public class Article {
    private String firstname, lastname;

    public Article(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    stratPickerInterface smStrategy;

    public void setStrategy(stratPickerInterface smStrategy) {
        this.smStrategy = smStrategy;
    }

    public void format(String name) {
        smStrategy.formatWith(name);
    }
}
