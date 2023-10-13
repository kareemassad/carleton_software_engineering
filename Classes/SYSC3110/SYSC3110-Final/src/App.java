import java.util.List;

public class App {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();

        System.out.println("created list");
        todoList.printAll();

        TodoItem sampleItem1 = new TodoItem("test1", "test1");
        TodoItem sampleItem2 = new TodoItem("test2", "test2");

        TodoProject todoProj1 = new TodoProject(sampleItem1);
        todoProj1.addTodoItem(sampleItem2);

        todoList.addTodoProject(todoProj1);

        System.out.println("added proj1 with 2 sub items");
        todoList.printAll();



        TodoItem item1 = new TodoItem("Kareem1", "desc1");
        TodoItem item2 = new TodoItem("Kareem2", "desc2");

        todoList.addTodoItem(item1);
        todoList.addTodoItem(item2);

        System.out.println("added item 1 and item 2 to todolist");
        todoList.printAll();

        System.out.println("removed item 1");
        todoList.removeTodoItem(item1);

        todoList.printAll();

        TodoList.toXML(todoList);
    }
}
