import java.util.ArrayList;

public class AddressBook extends MenuFrame{

    private ArrayList<BuddyInfo> buddyList;

    public AddressBook(){
        this.buddyList = new ArrayList<BuddyInfo>();
    }

    public void addBuddy(BuddyInfo buddy){
        buddyList.add(buddy);
    }

    public BuddyInfo removeBuddy(int index){
        if(index >= 0 && index < buddyList.size()){
            return buddyList.remove(index);
        }
        return null;
    }

}


