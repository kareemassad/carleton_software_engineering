import java.io.*;
import java.util.ArrayList;

public class EmployeeModel {
    private ArrayList<Employee> employees;

    public EmployeeModel() {
        super();
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        if(employee != null) {
            this.employees.add(employee);
        }
    }

   public boolean removeEmployee(Employee employee){
        return this.employees.remove(employee);
    }

    public String toXML(){
        StringBuilder XML = new StringBuilder("<Employees>");
        for (int i = 0; i < this.employees.size(); i++) {
            Employee employee = this.employees.get(i);
            XML.append("\n").append(employee.toXML());
        }
        XML.append("\n</Employees>");
        return XML.toString();
    }

    public void importFromXmlFile(String filename) {
        EmployeeXMLParser parser = new EmployeeXMLParser();
        try {
            ArrayList<Employee> temp = parser.readXMLEmployeeFile(filename);
            for (Employee employee: temp) {
                this.addEmployee(employee);
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void exportToXmlFile(String filename) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            out.write(this.toXML());
            out.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

