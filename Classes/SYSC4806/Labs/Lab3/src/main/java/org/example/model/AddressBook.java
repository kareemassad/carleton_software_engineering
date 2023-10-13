package org.example.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class AddressBook {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public AddressBook() {
        this.buddies = new ArrayList<>();
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void addBuddyInfo(BuddyInfo buddy){

        buddies.add(buddy);
    }

    public void setBuddies(List<BuddyInfo> listb){
        buddies=listb;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
