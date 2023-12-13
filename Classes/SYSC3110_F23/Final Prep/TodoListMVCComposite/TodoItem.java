//TodoItem.java
public class TodoItem extends TodoComposite {
    private String text;

    public TodoItem(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println("- " + text);
    }

    public String toXML() {
        return "<todo>" + this.text + "</todo>";
    }
}
