import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class XmlParseHandler extends DefaultHandler {
    private static final String TAG_TODO = "todo";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";

    private static TodoList todoList;
    private StringBuilder textElement;

    public static void readSAX(File f) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        DefaultHandler dh = new DefaultHandler() {
            Boolean checkerName = false;
            Boolean checkerDescription = false;

            TodoItem todoItem = new TodoItem("name1", "desc1");

            public void startDocument() throws SAXException {
                // System.out.println("Start document");
                todoList = new TodoList();
            }

            public void startElement(String u, String In, String qName, Attributes a) {
                // System.out.println("START: " + qName);
                // create TodoItem objects and add to addressbook.
                if (qName.equals(TAG_TODO)) {
                    todoList = new TodoList();
                }
                if (qName.equals(TAG_NAME)) {
                    checkerName = true;
                }
                if (qName.equals(TAG_DESCRIPTION)) {
                    checkerDescription = true;
                }
            }

            public void endElement(String uri, String localName, String qName) {
                // System.out.println("END: " + qName);
                if (qName.equals(TAG_TODO)) {
                    todoList.addTodoItem(todoItem);
                }
            }

            public void characters(char[] ch, int start, int length) {
                // System.out.println("CHARS: " + new String(ch, start, length));
                if (checkerName) {
                    todoItem.setName(new String(ch, start, length));
                    checkerName = false;
                }
                if (checkerDescription) {
                    todoItem.setDescription(new String(ch, start, length));
                    checkerDescription = false;
                }

            }
        };
        s.parse(f, dh);
    }

    public static void main(String[] args) {
        try {
            readSAX(new File("test.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(todoList);
    }

    public static TodoList getTodoList() {
        return todoList;
    }

}
