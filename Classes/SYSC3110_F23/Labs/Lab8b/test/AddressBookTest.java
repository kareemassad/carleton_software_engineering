//import model.AddressBook;
//import model.BuddyInfo;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class AddressBookTest {

    @Test
    public void testExportImportXML() throws IOException{
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(new BuddyInfo("Kareem", "12342sdads", "12321123"));
        addressBook.addBuddy(new BuddyInfo("Smith", "asdasdsad", "7686787"));
        addressBook.addBuddy(new BuddyInfo("Stefan", "124125asds", "5r767y"));
        String xmlFileName = "test_addressbook.xml";
        addressBook.exportToXmlFile(xmlFileName);

        AddressBook addressBook2 = new AddressBook();
        addressBook2.importFromXmlFile(xmlFileName);
        assertEquals(addressBook.getSize(), addressBook2.getSize());

        for (int i = 0; i < addressBook.getSize(); i++){
            BuddyInfo b1 = addressBook.get(i);
            BuddyInfo b2 = addressBook2.get(i);
            assertEquals(b1.getName(), b2.getName());
            assertEquals(b1.getAddress(), b2.getAddress());
            assertEquals(b1.getPhone_number(), b2.getPhone_number());
        }
    }

    @Test
    public void testExportImport() throws IOException {
        AddressBook addressBook1 = new AddressBook();
        addressBook1.addBuddy(new BuddyInfo("katey", "123 streeet", "12341234"));
        addressBook1.addBuddy(new BuddyInfo("Smithers", "123 streeet", "12341234"));
        addressBook1.addBuddy(new BuddyInfo("Kareem", "123 streeet", "12341234"));

        String fileName = "test_addressbook.txt";
        addressBook1.save(fileName);

        AddressBook addressBook2 = new AddressBook();
        addressBook2.importAddressBook(fileName);

        assertEquals(addressBook1.getSize(), addressBook2.getSize());
    }
}
