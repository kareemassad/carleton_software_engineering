
/**
 * A (playing) card is a rank and a suit.
 * 
 * @author babak 
 * @version 0.0
 */
public class Card {
    public enum Rank {DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
    public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}
    
    public static Rank getRankFromAbbrev(char c) {
        //using char instead of String to avoid using switch with strings (unavailable on early java)

        // one could use a lookup map inside the enum instead. 
        // opting for fewer advanced java concepts instead
        if (Character.isDigit(c)) {
            int irank = Character.digit(c, 10); //convert to int
            if (irank == 0) return Rank.TEN; //or raise error?
            if (irank == 1) return Rank.ACE;
            return Rank.values()[irank-2];
        }

        switch (c) {
            case 'T': return Rank.TEN;
            case 'J': return Rank.JACK;
            case 'Q': return Rank.QUEEN;
            case 'K': return Rank.KING;
            case 'A': return Rank.ACE;
            default: throw new IllegalArgumentException("No such rank!");
        }
    }
    
    public static Suit getSuitFromAbbrev(char c) {
        switch (c) {
            case 'C': return Suit.CLUBS;
            case 'D': return Suit.DIAMONDS;
            case 'H': return Suit.HEARTS;
            case 'S': return Suit.SPADES;
            default: throw new IllegalArgumentException("No such suit!");
        }
    }
    private final Rank rank;
    private final Suit suit;
    /**
     * Create a card given a string shordhand formatted as:
     * "TD" should create a TEN of DIAMONDS, etc.
     */
    public Card(String s) {
        this(getRankFromAbbrev(s.charAt(0)), getSuitFromAbbrev(s.charAt(1)));
    }
    
    public Card(Rank r, Suit s) {
        rank = r;
        suit = s;
    }
    
    public Rank getRank() {
        return rank;
    }
    
    public Suit getSuit() {
        return suit;
    }
        
}




