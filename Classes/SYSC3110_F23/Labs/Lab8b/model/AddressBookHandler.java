import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AddressBookHandler extends DefaultHandler {
    private AddressBook addressBook;
    private StringBuilder currentValue = new StringBuilder();
    private BuddyInfo currentBuddy;
    private boolean isBuddyInfo;

    public AddressBookHandler(AddressBook addressBook){
        this.addressBook = addressBook;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        currentValue.setLength(0);
        if(qName.equalsIgnoreCase("BuddyInfo")){
            currentBuddy = new BuddyInfo();
            isBuddyInfo = true;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

    @Override
    public  void endElement(String uri, String localName, String qName) throws SAXException{
        if(isBuddyInfo){
            switch (qName.toLowerCase()){
                case "name":
                    currentBuddy.setName(currentValue.toString());
                    break;
                case "address":
                    currentBuddy.setAddress(currentValue.toString());
                    break;
                case "phonenumber":
                    currentBuddy.setPhone_number(currentValue.toString());
                    break;
                case "buddyinfo":
                    addressBook.addBuddy(currentBuddy);
                    isBuddyInfo = false;
                    break;
            }
        }
    }


}
