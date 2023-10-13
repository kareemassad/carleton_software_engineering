package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo( 0,"Kareem", "111111");
        BuddyInfo buddy2 = new BuddyInfo( 1,"Katey", "222222");
        BuddyInfo buddy3 = new BuddyInfo( 2,"Stefan", "333333");

        addressBook.addBuddyInfo(buddy1);
        addressBook.addBuddyInfo(buddy2);
        addressBook.addBuddyInfo(buddy3);

        addressBook.printAddressBook();
    }
}