import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeXMLParser extends DefaultHandler {

    private ArrayList<Employee> employees;
    private StringBuilder elementContent;

    @Override
    public void startDocument(){

        employees = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if(qName.equalsIgnoreCase("Employee")){
            Employee currentEmployee = new Employee(0, null, null, new Address(0, null, null));
            employees.add(currentEmployee);
        }
        elementContent = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length){
        elementContent.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if(qName.equalsIgnoreCase("ID")){
            employees.get(employees.size()-1).setID(Integer.parseInt(elementContent.toString()));
        }else if(qName.equalsIgnoreCase("name")){
            employees.get(employees.size()-1).setName(elementContent.toString());
        }else if(qName.equalsIgnoreCase("department")){
            employees.get(employees.size()-1).setDepartment(elementContent.toString());
        }else if(qName.equalsIgnoreCase("homeNumber")) {
            int homeNo = Integer.parseInt(elementContent.toString());
            employees.get(employees.size()-1).getAddress().setHomeNo(homeNo);
        } else if(qName.equalsIgnoreCase("street")) {
            String street = elementContent.toString();
            employees.get(employees.size()-1).getAddress().setStreet(street);
        }else if(qName.equalsIgnoreCase("city")) {
            String city = elementContent.toString();
            employees.get(employees.size()-1).getAddress().setCity(city);
        }
    }



    public ArrayList<Employee> readXMLEmployeeFile(String fileName) throws IOException {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            File file = new File(fileName);
            parser.parse(file, this);
            return employees;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }
}
