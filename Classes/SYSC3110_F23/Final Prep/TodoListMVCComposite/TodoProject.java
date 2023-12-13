//TodoProject.java
public class TodoProject extends TodoComposite {
    private String name;

    public TodoProject(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println(name + ":");
        for (TodoComposite item : children) {
            item.display();
        }
    }

    public String toXML() {
        StringBuilder xmlBuilder = new StringBuilder("<todoProject>\n");
        for (TodoComposite item : children) {
            xmlBuilder.append(item.toXML()).append("\n");
        }
        xmlBuilder.append("</todoProject>");
        System.out.println(xmlBuilder.toString());
        return xmlBuilder.toString();
    }

}
