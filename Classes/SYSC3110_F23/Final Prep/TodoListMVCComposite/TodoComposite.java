import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TodoComposite.java
public abstract class TodoComposite implements Serializable {
    protected List<TodoComposite> children = new ArrayList<>();

    public void add(TodoComposite item) {
        children.add(item);
    }

    public void remove(TodoComposite item) {
        children.remove(item);
    }

    public abstract void display();

    public abstract String toXML();
}
