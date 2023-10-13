public class BuddyInfo {

    private String name;
    private String address;
    private String number;

    // Getter Methods
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getNumber() {
        return number;
    }

    // Setter Method
    public void setInfo(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }


    public BuddyInfo() {
        this("Marge", "742 Evergreen Terrace, Springfield", "555-1234" );
    }

    // Constructor
    public BuddyInfo(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    // main
    public static void main(String[] args) {
        BuddyInfo base = new BuddyInfo();
        String test = base.name;
        System.out.println(test);

        BuddyInfo obj = new BuddyInfo("Homer", "742 Evergreen Terrace, Springfield", "555-7334" );
        String name = obj.getName();
        System.out.println("Hello " + name);
    }
}
