
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AddressBook extends DefaultListModel<BuddyInfo> {

    static ArrayList<BuddyInfo> list;

    public AddressBook(){
        list = new ArrayList<>();
    }
    private void addBuddy(BuddyInfo buddy){

        if(buddy != null){
            list.add(buddy);
        }
    }

    private void removeBuddy(BuddyInfo buddy){
        list.remove(buddy);
    }

    private BuddyInfo removeBuddyByIndex(int index){
        if(index >= 0 && index < list.size()){
            return list.remove(index);
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Address Book");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            AddressBook addressBook = new AddressBook();

            JList<BuddyInfo> buddyList = new JList<>(addressBook);

            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(buddyList), BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();

            JMenu addressBookMenu = new JMenu("AddressBook");
            JMenuItem newItem = new JMenuItem("New");
            JMenuItem saveItem = new JMenuItem("Save");

            addressBookMenu.add(newItem);
            addressBookMenu.add(saveItem);

            //now buddy info
            JMenu buddymenu = new JMenu("BuddyInfo");
            JMenuItem addItem = new JMenuItem("Add");
            JMenuItem removeItem = new JMenuItem("Remove");
            buddymenu.add(addItem);
            buddymenu.add(removeItem);

            addItem.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(frame, "Enter Buddy name: ");
                String address = JOptionPane.showInputDialog(frame, "Enter Buddy Address: ");
                String phoneNumber = JOptionPane.showInputDialog(frame, "Enter Buddy Number: ");

                BuddyInfo newBuddy = new BuddyInfo(name, address, phoneNumber);
                addressBook.addElement(newBuddy);
            });
            removeItem.addActionListener(e -> {
                BuddyInfo selectBuddy = buddyList.getSelectedValue();
                if(selectBuddy != null){
                    addressBook.removeElement(selectBuddy);
                }
            });

            menuBar.add(addressBookMenu);
            menuBar.add(buddymenu);
            frame.setJMenuBar(menuBar);

            frame.setSize(500,500);
            frame.setVisible(true);
        });

        // BuddyInfo buddy = new BuddyInfo();
        // AddressBook addressBook = new AddressBook();
        // addressBook.addBuddy(buddy);
        // addressBook.removeBuddyByIndex(0);
    }
}
