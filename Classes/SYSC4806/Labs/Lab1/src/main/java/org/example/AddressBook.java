package org.example;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    static ArrayList<BuddyInfo> buddies = new ArrayList<>();

    public void addBuddyInfo(BuddyInfo buddy){
        buddies.add(buddy);
    }
    public void removeBuddyInfo(BuddyInfo buddy){
        buddies.remove(buddy);
    }

    public void removeAllBuddies(){
        buddies.clear();
    }

    public void printAddressBook(){
        System.out.println("Printing Address Book of size " + buddies.size());
        for (BuddyInfo buddy : buddies) {
            String buddyName = buddy.getName();
            String buddyNumber = buddy.getPhoneNumber();
            System.out.println("Name: " + buddyName + "; Number: " + buddyNumber);
        }
    }

}
