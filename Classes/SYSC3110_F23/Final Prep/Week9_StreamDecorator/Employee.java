import java.io.*;
import java.util.Scanner;

public class Employee implements Serializable {
    int ID;
    String name;
    String department;
    Address address;

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

   public static void writeFile(Employee emp){

       try {
           FileWriter file = new FileWriter("employee.txt");
           file.write(emp.ID + "," + emp.name + "," + emp.department + "," + emp.address.toString());
           file.close();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

    public static void readFile(Employee emp){

        try {
            File file = new File("employee.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
                String[] strings = data.split(",");
                emp.setID(Integer.parseInt(strings[0]));
                emp.setName(strings[1]);
                emp.setDepartment(strings[2]);
                String[] address = strings[3].split(";");
                Address temp = new Address(Integer.parseInt(address[0]), address[1], address[2]);
                emp.setAddress(temp);


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
