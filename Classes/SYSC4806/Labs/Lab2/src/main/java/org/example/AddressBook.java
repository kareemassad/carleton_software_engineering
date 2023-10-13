package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class AddressBook {
    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private ArrayList<BuddyInfo> buddies = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ArrayList<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void addBuddyInfo(BuddyInfo buddy){

        buddies.add(buddy);
        buddy.setAddressBook(this);
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
