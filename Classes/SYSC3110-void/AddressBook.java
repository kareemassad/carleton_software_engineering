
import java.util.*;

public class AddressBook {

    static ArrayList<BuddyInfo> list = new ArrayList<>();

    private void addBuddy(BuddyInfo buddy){
        list.add(buddy);
    }

    private void removeBuddy(BuddyInfo buddy){
        list.remove(buddy);
    }

    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo();
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(buddy);
    }
}
