public class Main {
    public static void main(String[] args) {
        Address address = new Address(11, "Bronson road", "Ottawa");
        Employee emp = new Employee(1111,"Wafa", "SCE", address);
        address = new Address(22, "Carleton road", "Toronto");
        Employee emp2 = new Employee(2222,"Jack", "SCS", address);

        EmployeeModel model = new EmployeeModel();
        model.addEmployee(emp);
        model.addEmployee(emp2);

        model.exportToXmlFile("employees.xml");

        EmployeeModel model2 = new EmployeeModel();
        model2.importFromXmlFile("employees.xml");
        System.out.println(model2.toXML());



    }
}