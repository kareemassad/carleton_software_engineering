import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
    private String tag;
    private String text;
    private List<XMLElement> subElements;
    private Map<String, String> attributes;

    public XMLElement(String tag){
        this.tag = tag;
        this.text = "";
        this.subElements = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public void addSubElement(XMLElement element){
        this.subElements.add(element);
        this.text = null;
    }

    public void setText(String test){
        this.text = text;
        this.subElements.clear();
    }
    public void addAttribute(String key, String value){
        this.attributes.put(key,value);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(tag);
        for(Map.Entry<String, String> entry: attributes.entrySet()){
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        sb.append(">");

        if(text != null){
            sb.append(text);
        }else{
            for(XMLElement e : subElements){
                sb.append(e.toString());
            }
        }
        sb.append("</").append(tag).append(">");

        return sb.toString();
    }
    public static void main(String[] args) {
        XMLElement course = new XMLElement("course");
        XMLElement code = new XMLElement("code");
        code.setText("SYSC3110");
        XMLElement prof = new XMLElement("prof");
        prof.setText("Wafa");
        XMLElement clasz = new XMLElement("class");
        XMLElement s1 = new XMLElement("student");
        s1.setText("Michael");
        XMLElement s2 = new XMLElement("student");
        s2.setText("Alan");

        clasz.addSubElement(s1);
        clasz.addSubElement(s2);

        course.addSubElement(code);
        course.addSubElement(prof);
        code.addSubElement(clasz);

        System.out.println(course);
    }

}

