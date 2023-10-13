public class BuddyInfo {


    private String name;
    private String address;
    private String phone_number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public BuddyInfo(String name, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public BuddyInfo(){
        this("Kareem", "Home Address", "111111");
    }


    public static void main(String[] args) {
        System.out.println("Hello World");

        BuddyInfo buddy = new BuddyInfo("Krimzon", "Taro", "222222");
        BuddyInfo buddy_standard = new BuddyInfo();
        System.out.println("Hello " + buddy.getName() + " and " + buddy_standard.getName());
    }
}
