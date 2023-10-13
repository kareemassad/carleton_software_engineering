import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

    List<TodoList> todoList = new ArrayList<TodoList>();

    public void addTodoItem(TodoItem todoItem){
        todoList.add(todoItem);
    }
    public void addTodoProject(TodoProject todoProject){
        todoList.add(todoProject);
    }
    public void removeTodoItem(TodoItem todoItem) {
        todoList.remove(todoItem);
    }

    public void removeTodoProject(TodoProject todoProject) {
        todoList.remove(todoProject);
    }

    public TodoList getChild(int i) {
        return (TodoList) todoList.get(i);
    }

    public void printAll() {
        for(int i=0;i<todoList.size();i++){
            todoList.get(i).toString();
            System.out.println(todoList.get(i));
        }
    }

    public static void toXML(TodoList todoList) {
        try {
            FileWriter fileWriter = new FileWriter("test.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bufferedWriter.newLine();
            bufferedWriter.write("<todo>");
            bufferedWriter.newLine();
            for (int i = 0; i < todoList.size(); i++) {
                // No time to implement so pseudocode
                //if item at index i is null, print <todo>
                //if item at index i is not null, and contains a list, it is a todoProject. Then iterate children and output like below

                TodoItem item = (TodoItem) todoList.get(i);
                bufferedWriter.write("\t");
                bufferedWriter.write("<todo>");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t");
                bufferedWriter.write("<name>" + item.getName().replaceAll("\\s+", "") + "</name>");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t");
                bufferedWriter.write("<description>" + item.getDescription().replaceAll("\\s+", "") + "</description>");
                bufferedWriter.newLine();
                bufferedWriter.write("\t");
                bufferedWriter.write("</todo>");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("</todo>");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private TodoList get(int i) {
        TodoList item = this.todoList.get(i);
        return item;
    }

    private int size() {
        return this.todoList.size();
    }

}
