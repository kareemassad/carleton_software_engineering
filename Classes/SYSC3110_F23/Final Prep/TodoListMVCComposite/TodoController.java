public class TodoController {
    private TodoComposite todoList;
    private TodoView view;

    public TodoController(TodoComposite todoList, TodoView view) {
        this.todoList = todoList;
        this.view = view;
    }

    public void addTodoItem(TodoComposite item) {
        todoList.add(item);
    }

    public void displayTodoList() {
        view.displayTodoList(todoList);
    }
}
