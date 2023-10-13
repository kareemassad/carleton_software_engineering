public class ACMStrat implements stratPickerInterface {
    public void formatWith(String stratName) {
        System.out.println("Formatting with " + stratName);
    }

    public String format(Article article) {
        String firstname = article.getFirstname();
        String lastname = article.getLastname();
        String formatted = null;
        formatted = firstname + " " + lastname + ".";
        return formatted;
    }
}
