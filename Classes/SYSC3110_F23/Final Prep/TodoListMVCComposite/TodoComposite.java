import java.util.ArrayList;
import java.util.List;

public abstract class TodoComposite {
    protected List<TodoComposite> children = new ArrayList<>();

    public void add(TodoComposite item){
        children.add(item);
    }

    public void remove(TodoComposite item){
        children.remove(item);
    }

    public abstract void display();
}

