import java.io.*;

// TodoController.java
public class TodoController {
    private TodoComposite todoList;
    private TodoView view;

    public TodoController(TodoComposite todoList, TodoView view) {
        this.todoList = todoList;
        this.view = view;
    }

    public void saveTodoListToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(todoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTodoListFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            todoList = (TodoComposite) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTodoItem(TodoComposite item) {
        todoList.add(item);
    }

    public void displayTodoList() {
        view.displayTodoList(todoList);
    }

    public void displayXMLTodoList() {
        view.displayXMLTodoList(todoList);
    }
}
