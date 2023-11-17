package test;

import model.AddressBook;
import model.BuddyInfo;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class AddressBookTest {

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
