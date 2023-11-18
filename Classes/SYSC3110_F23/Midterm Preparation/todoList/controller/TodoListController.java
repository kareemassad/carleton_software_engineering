import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListController {
    private TodoListView view;
    private List<TodoItem> model;

    public TodoListController(TodoListView view){
        this.view = view;
        this.model = new ArrayList<>();

        model.add(new SimpleTodoItem("Task 1"));
        model.add(new SimpleTodoItem("Task 2"));

        updateView();

        view.addStrikeListener(e -> strikeThroughSelected());
        view.addAddButtonListener(e-> addNewTask());
    }

    private void addNewTask() {
        String taskText = JOptionPane.showInputDialog("Enter new task: ");
        if(taskText != null){
            model.add(new SimpleTodoItem(taskText));
            updateView();
        }
    }

    private void updateView() {
        List<String> items = new ArrayList<>();
        for(TodoItem item : model){
            items.add(item.getText() + (item.isCompleted() ? " (completed)" : ""));
        }
        view.setTodoItems(items);
    }

    private void strikeThroughSelected() {
        int index = view.getSelectedIndex();
        if(index != -1){
            TodoItem item = model.get(index);
            item.setCompleted(true);
            updateView();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoListView view = new TodoListView();
            new TodoListController(view);
        });
    }
}
