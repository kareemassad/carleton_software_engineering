public class TodoProject extends TodoComposite{
    private String name;

    public TodoProject(String name){
        this.name = name;
    }
    @Override
    public void display() {
        System.out.println(name + ":");
        for (TodoComposite item : children){
            item.display();
        }
    }
}
