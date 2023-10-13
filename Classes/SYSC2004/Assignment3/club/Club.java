import java.util.ArrayList;
import java.util.Iterator;

/**
 * Store details of club memberships.
 * 
 * @author Kareem El Assad 
 * @version 2/27/2020
 */
public class Club
{
    // Define any necessary fields here ...
    private ArrayList<Membership> membersOfClub;
    
    /**
     * Constructor for objects of class Club
     */
    public Club()
    {
        // Initialise any fields here ...
        membersOfClub = new ArrayList<Membership>();
        
    }

    /**
     * Add a new member to the club's list of members.
     * @param member The member object to be added.
     */
    public void join(Membership member)
    {
        if (search(member.getName())==0) {
            membersOfClub.add(member);
            System.out.println(member.getName() + " added to the club-list.");
            
        }
        else{
            System.out.println(member.getName() + " is already on the list.");
        }
    }

    /**
     * @return The number of members (Membership objects) in
     *         the club.
     */
    public int numberOfMembers()
    {
        return membersOfClub.size();
    }

    public int search(String nameToFind){
        int nameFound = 0;
        for(Membership member : membersOfClub){
            if(nameToFind.equals(member.getName())){
                nameFound++;
            }

        }
        System.out.println(nameFound + " is the number of match(es) found for the name " + nameToFind + ".");
        return nameFound;
    }
}
