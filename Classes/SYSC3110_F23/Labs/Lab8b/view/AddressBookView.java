//import model.BuddyInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddressBookView {
    private JFrame frame;
    private JList<BuddyInfo> buddyList;
    private DefaultListModel<BuddyInfo> listModel;
    private JMenuItem newItem, saveItem, addItem, removeItem, exportItem, importItem;

    public AddressBookView() {
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listModel = new DefaultListModel<>();
        buddyList = new JList<>(listModel);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(buddyList), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu addressBookMenu = new JMenu("AddressBook");
        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        exportItem = new JMenuItem("Export");
        importItem = new JMenuItem("Import");

        addressBookMenu.add(newItem);
        addressBookMenu.add(saveItem);
        addressBookMenu.add(exportItem);
        addressBookMenu.add(importItem);

        JMenu buddymenu = new JMenu("BuddyInfo");
        addItem = new JMenuItem("Add"); // Initialize class-level field
        removeItem = new JMenuItem("Remove"); // Initialize class-level field
        buddymenu.add(addItem);
        buddymenu.add(removeItem);

        menuBar.add(addressBookMenu);
        menuBar.add(buddymenu);
        frame.setJMenuBar(menuBar);



        frame.setSize(500,500);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void removeBuddy(BuddyInfo buddy){
        listModel.removeElement(buddy);
    }

    public void addBuddy(BuddyInfo buddy){
        listModel.addElement(buddy);
    }

    public BuddyInfo getSelectedBuddy(){
        return buddyList.getSelectedValue();
    }

    // Getters for menu items
    public JMenuItem getNewItem() {
        return newItem;
    }

    public JMenuItem getSaveItem() {
        return saveItem;
    }

    public JMenuItem getAddItem() {
        return addItem;
    }

    public JMenuItem getRemoveItem() {
        return removeItem;
    }

    public JMenuItem getExportItem(){
        return exportItem;
    }

    public JMenuItem getImportItem(){
        return importItem;
    }

    public void refreshBuddyList(List<BuddyInfo> buddies) {
        listModel.clear();

        for(BuddyInfo buddy: buddies){
            listModel.addElement(buddy);
        }
    }
}
