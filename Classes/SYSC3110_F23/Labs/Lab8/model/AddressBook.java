package model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook extends DefaultListModel<BuddyInfo> {

    private List<BuddyInfo> list;

    public AddressBook() {
        list = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            list.add(buddy);
            this.addElement(buddy); // To update the DefaultListModel
        }
    }

    public void save(String fileName) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (BuddyInfo buddy : list) {
                writer.write(buddy.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Failed to save file");
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        list.remove(buddy);
        this.removeElement(buddy); // To update the DefaultListModel
    }

    public List<BuddyInfo> getList() {
        return list;
    }

    public void setList(List<BuddyInfo> list) {
        this.list = list;
    }

    public void importAddressBook(String fileName) {
        //unsure if these are needed
//        list.clear();
//        this.clear();


        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                BuddyInfo buddy = BuddyInfo.importBuddyInfo(line);
                System.out.println("read: " + buddy.toString());
                list.add(buddy);
                this.addElement(buddy);
            }
        }
        catch (IOException e){
            System.out.println("Failed to import file");
        }
    }

    
}
