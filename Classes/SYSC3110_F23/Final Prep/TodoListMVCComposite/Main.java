public class Main {
    public static void main(String[] args) {
        TodoProject mainProject = new TodoProject("Main Project");
        TodoItem item1 = new TodoItem("Complete Java assignment");
        TodoItem item2 = new TodoItem("Read design patterns book");

        mainProject.add(item1);
        mainProject.add(item2);

        TodoProject subProject = new TodoProject("Sub Project");
        subProject.add(new TodoItem("Sub task 1"));
        subProject.add(new TodoItem("Sub task 2"));

        mainProject.add(subProject);

        TodoView view = new TodoView();
        TodoController controller = new TodoController(mainProject, view);

        controller.displayTodoList();
    }
}
