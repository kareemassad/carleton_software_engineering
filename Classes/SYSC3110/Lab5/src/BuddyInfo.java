public class BuddyInfo {

    private String name;
    private String address;
    private String phoneNumber;

    public BuddyInfo() {
        this.name = "";
        this.address = "";
        this.phoneNumber = "";
    }

    public BuddyInfo(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress(){ return address; }

    public String getPhoneNumber(){ return phoneNumber; }

    public String toString(){
        return ("Name: " + this.getName() + " Address: "+this.getAddress()+ " Phone: " + this.getPhoneNumber());
    }

}