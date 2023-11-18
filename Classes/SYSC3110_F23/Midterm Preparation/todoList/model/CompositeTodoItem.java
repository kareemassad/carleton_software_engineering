import java.util.ArrayList;
import java.util.List;

public class CompositeTodoItem implements TodoItem{
    private List<TodoItem> items;
    private boolean completed;

    public CompositeTodoItem(){
        items = new ArrayList<>();
        this.completed = false;
    }

    public void addItem(TodoItem item){
        items.add(item);
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for(TodoItem item : items){
            sb.append(item.getText()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
        for(TodoItem item : items){
            item.setCompleted(completed);
        }
    }
}
