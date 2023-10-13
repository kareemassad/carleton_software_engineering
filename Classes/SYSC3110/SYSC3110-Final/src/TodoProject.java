import java.util.*;

public class TodoProject extends TodoList {


    public List<TodoItem> todoItemList = new ArrayList<TodoItem>();

    public TodoProject(TodoItem item) {
        todoItemList.add(item);
    }
    public TodoProject() {
        //if takes nothing, contains nothing.
        this.todoItemList = null;
    }

    public void addTodoItem(TodoItem todoItem){
        todoItemList.add(todoItem);
    }
    public void removeTodoItem(TodoItem todoItem) {
        todoItemList.remove(todoItem);
    }

    public TodoItem getChild(int i) {
        return todoItemList.get(i);
    }
}
