package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BuddyInfo {


    @Id
    private Integer id = null;

    private String name;
    private String phoneNumber;




    @ManyToOne
    private AddressBook addressBook;

    public void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
        if (addressBook != null && !addressBook.getBuddies().contains(this)) {
            addressBook.getBuddies().add(this);
        }
    }
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public BuddyInfo(Integer id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public BuddyInfo(){
        this.id = 0;
        this.name="default";
        this.phoneNumber="000000000";
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuddyInfo buddyInfo = (BuddyInfo) o;
        return id.equals(buddyInfo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
