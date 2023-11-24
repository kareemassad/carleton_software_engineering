//import model.AddressBook;
//import model.BuddyInfo;
//import view.AddressBookView;

import javax.swing.*;
import java.io.IOException;

public class AddressBookController {
    private AddressBookView view;
    private AddressBook addressBook;

    public AddressBookController(AddressBook addressBook, AddressBookView view) {
        this.addressBook = addressBook;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddItem().addActionListener(e -> addBuddy());
        view.getRemoveItem().addActionListener(e -> removeBuddy());
        view.getExportItem().addActionListener(e-> exportAddressBook());
        view.getImportItem().addActionListener(e -> importAddressBook());
    }

    private void importAddressBook() {
        String fileName = JOptionPane.showInputDialog(view, "Enter file name to import from: ");
        if(fileName != null){
            addressBook.importAddressBook(fileName);
            view.refreshBuddyList(addressBook.getList());
        }
    }

    private void addBuddy() {
        String name = JOptionPane.showInputDialog(view, "Enter Buddy Name: ");
        String address = JOptionPane.showInputDialog(view, "Enter Buddy Address: ");
        String phoneNumber = JOptionPane.showInputDialog(view, "Enter Buddy Number: ");

        if (name != null && address != null && phoneNumber != null) {
            BuddyInfo newBuddy = new BuddyInfo(name, address, phoneNumber);
            addressBook.addBuddy(newBuddy);
            view.addBuddy(newBuddy);
        }
    }

    private void removeBuddy() {
        BuddyInfo selectedBuddy = view.getSelectedBuddy();
        if (selectedBuddy != null) {
            addressBook.removeBuddy(selectedBuddy);
            view.removeBuddy(selectedBuddy);
        }
    }

    private void exportAddressBook(){
        String fileName = JOptionPane.showInputDialog(view, "Enter file name to export: ");
        if(fileName != null){
            addressBook.save(fileName);
        }
    }
}
