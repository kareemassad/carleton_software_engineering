import java.util.*;
/**
 * Poker distributes the hands and can determine the winner
 * 
 * @author babak 
 * @version 0.0
 */
public class Poker {

    private Collection<Hand> hands;

    /**
     * Create a new game of poker (empty at first)
     */
    public Poker(){
        hands = new ArrayList<Hand>();
    }

    /**
     * Add a new hand
     */
    public void addHand(Hand h) {
        hands.add(h);
    }
    
    /**
     * @return the best hand 
     */
    public Hand bestHand(){
       return Collections.max(hands);
    }
}
