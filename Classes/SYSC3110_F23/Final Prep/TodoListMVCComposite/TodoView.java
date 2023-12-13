// TodoView.java
public class TodoView {
    public void displayTodoList(TodoComposite todoList) {
        todoList.display();
    }

    public void displayXMLTodoList(TodoComposite todoList) {
        todoList.toXML();
    }
}
