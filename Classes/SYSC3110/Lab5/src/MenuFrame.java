import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener{

    private AddressBook addressBook;
    private BuddyInfo buddyInfo;

    JMenuBar menuBar;
    JMenu addressBookMenu;
    JMenu buddyInfoMenu;
    JMenuItem createItem;
    JMenuItem nameItem;
    JMenuItem removeItem;

    DefaultListModel listModel = new DefaultListModel();
    JList contacts = new JList(listModel);

    public MenuFrame(){

        //Set frame properties
        this.setTitle("Address Book");
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Creates the menu bar
        addressBookMenu = new JMenu("Address Book");
        buddyInfoMenu = new JMenu("Buddy Info");

        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        //Creates the items to go into the menu bar
        createItem = new JMenuItem("Create");
        nameItem = new JMenuItem("Add");
        removeItem = new JMenuItem("Remove");

        addressBookMenu.add(createItem);
        buddyInfoMenu.add(nameItem);
        buddyInfoMenu.add(removeItem);

        createItem.addActionListener(this);
        nameItem.addActionListener(this);
        removeItem.addActionListener(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(createItem)){
            DefaultListModel<BuddyInfo> newListModel = new DefaultListModel<>();
            listModel = newListModel;
            contacts.setModel(listModel);
        }

        if(e.getSource().equals(nameItem)){
            String name = JOptionPane.showInputDialog("Name");
            String address = JOptionPane.showInputDialog("Address");
            String phone = JOptionPane.showInputDialog("Phone Number");

            BuddyInfo temp = new BuddyInfo(name,address,phone);
            listModel.addElement(temp);
        }

        if(e.getSource().equals(removeItem)){
            int index = Integer.parseInt(JOptionPane.showInputDialog("Please choose the person"))-1;
            listModel.remove(index);
        }
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}
