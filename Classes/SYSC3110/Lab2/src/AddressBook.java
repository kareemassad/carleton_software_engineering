import java.util.*;

public class AddressBook {

    static ArrayList<BuddyInfo> list = new ArrayList<>();

    public static void addBuddy(BuddyInfo buddy_info_obj) {
        list.add(buddy_info_obj);
    }

    public static void removeBuddy(BuddyInfo buddy_info_obj) {
        list.remove(buddy_info_obj);
    }

    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(buddy);
        //BuddyInfo marge = new BuddyInfo();
        //BuddyInfo homer = new BuddyInfo("Homer", "742 Evergreen Terrace, Springfield", "555-1111" );
        //BuddyInfo bart = new BuddyInfo("Bart", "742 Evergreen Terrace, Springfield", "555-2222" );
        //BuddyInfo lisa = new BuddyInfo("Lisa", "742 Evergreen Terrace, Springfield", "555-3333" );
        //BuddyInfo maggie = new BuddyInfo("Maggie", "742 Evergreen Terrace, Springfield", "555-4444" );

        // NEW BRANCH
        //addBuddy(marge);
        //addBuddy(homer);
        //System.out.println(list);
        //removeBuddy(homer);
        //System.out.println(list);
        //list.add(homer);
        //list.add(bart);
        //list.add(marge);
        //list.add(lisa);
        //list.add(maggie);
        //System.out.println(list);
        //System.out.println(marge.getName());
        }
    }
