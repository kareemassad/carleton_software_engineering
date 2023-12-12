public class Address {
    public int getHomeNo() {
        return homeNo;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void setHomeNo(int homeNo) {
        this.homeNo = homeNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    int homeNo;
    String street;
    String city;
    public Address(int home, String street, String city){
        this.homeNo = home;
        this.street = street;
        this.city = city;
    }

    public String toXML(){
        return "<address>\n\t\t\t" +
                "<homeNumber>"+ this.homeNo +"</homeNumber>\n\t\t\t" +
                "<street>"+ this.street + "</street>\n\t\t\t" +
                "<city>" + this.city + "</city>\n\t\t</address>\n";
    }

    @Override
    public String toString(){
        return homeNo + ";" + street + ";" + city;
    }




}
