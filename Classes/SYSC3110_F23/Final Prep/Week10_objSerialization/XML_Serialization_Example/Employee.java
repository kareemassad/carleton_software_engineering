import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
    int ID;
    String name;
    String department;
    Address address;

    public Employee(int id, String name, String dep, Address address){
        this.ID = id;
        this.name = name;
        this.department = dep;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toXML(){
        String address = this.address.toXML();
        return "\t<Employee>\n\t\t" +
                "<ID>"+ this.getID() +"</ID>\n\t\t" +
                "<name>"+ this.getName() + "</name>\n\t\t" +
                "<department>" + this.getDepartment() + "</department>\n\t\t" +
                this.getAddress().toXML() +
                "\t</Employee>";
    }


}
