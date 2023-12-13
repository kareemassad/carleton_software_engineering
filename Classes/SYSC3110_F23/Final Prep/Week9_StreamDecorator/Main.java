import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
            Employee emp = new Employee();
            emp.setID(11111);
            emp.setName("Wafa");
            emp.setDepartment("SCE");
            Address address = new Address(22, "Bronson road", "Ottawa");
            emp.setAddress(address);

            try {
                FileOutputStream out = new FileOutputStream("employee.ser");
                ObjectOutputStream objStream = new ObjectOutputStream(out);
                objStream.writeObject(emp);
                objStream.close();
                out.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FileInputStream inFile = new FileInputStream("employee.ser");
            ObjectInputStream os = new ObjectInputStream(inFile);
            try {
                Employee empolyee = (Employee) os.readObject();
                System.out.println(empolyee.getID());
                System.out.println(empolyee.getName());
                System.out.println(empolyee.getDepartment());
                System.out.println(empolyee.getAddress().toString());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }
    }
