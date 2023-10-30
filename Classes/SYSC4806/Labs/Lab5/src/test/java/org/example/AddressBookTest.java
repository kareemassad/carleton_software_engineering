package org.example;

import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook buddies;
    private BuddyInfo buddy1;
    private BuddyInfo buddy2;
    private BuddyInfo buddy3;

    @Before
    public void setUp(){
        buddies = new AddressBook();
        buddy1 = new BuddyInfo("Kareem", "111111");
        buddy2 = new BuddyInfo("Susan", "222222");
        buddy3 = new BuddyInfo("Rebecca", "333333");

        // buddy1.setAddressBook(buddies);
        // buddy2.setAddressBook(buddies);
        // buddy3.setAddressBook(buddies);
    }

    @Test
    public void addBuddyInfo() {
        buddies.addBuddyInfo(buddy1);
        buddies.addBuddyInfo(buddy2);
        assertTrue(buddies.getBuddies().contains(buddy1));
        assertTrue(buddies.getBuddies().contains(buddy2));
    }

    @Test
    public void removeBuddyInfo() {
        buddies.addBuddyInfo(buddy1);
        buddies.addBuddyInfo(buddy2);
        buddies.removeBuddyInfo(buddy1);
        assertFalse(buddies.getBuddies().contains(buddy1));
        assertTrue(buddies.getBuddies().contains(buddy2));
    }

    @Test
    public void printAddressBook() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //first empty arraylist
        buddies.removeAllBuddies();

        buddies.addBuddyInfo(buddy3);
        buddies.printAddressBook();

        String expected = "Printing Address Book of size 1" + System.lineSeparator() + "Name: Rebecca; Number: 333333" + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void removeAllBuddies() {
        buddies.removeAllBuddies();
        assertEquals(buddies.getBuddies().size(), 0);
    }
}