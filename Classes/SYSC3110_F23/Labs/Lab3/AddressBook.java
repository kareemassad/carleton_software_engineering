
import java.util.*;

public class AddressBook {

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
        BuddyInfo buddy = new BuddyInfo();
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddyByIndex(0);
    }
}
