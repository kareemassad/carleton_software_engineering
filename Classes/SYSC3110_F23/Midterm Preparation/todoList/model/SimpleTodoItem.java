public class SimpleTodoItem implements TodoItem{
    private String text;
    public boolean completed;

    public SimpleTodoItem(String text){
        this.text = text;
        this.completed = false;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
