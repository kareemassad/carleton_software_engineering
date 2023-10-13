import java.util.*;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight,
 * etc.)
 * 
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {
        HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
    }

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
        cards = new ArrayList<Card>();
        for (String s : c.split(" ")) {
            cards.add(new Card(s));
        }
    }

    protected List<Card.Rank> getRanks() {
        // return a list of the ranks in the hand
        List<Card.Rank> ranks = new ArrayList<Card.Rank>();
        for (Card c : cards) {
            ranks.add(c.getRank());
        }
        return ranks;
    }

    /**
     * @returns true if the hand has n cards of the same rank
     *          e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for
     *          n=1 and n=4
     */
    protected boolean hasNKind(int n) {
        // get all ranks then check how often each rank appears
        // base case
        if (n == 0)
            return false;
        if (n == 1)
            return false;
        List<Card.Rank> ranks = getRanks();
        for (Card.Rank r : ranks) {
            if (Collections.frequency(ranks, r) == n) {
                return true;
            }
        }
        return false;

    }

    /**
     * Optional: you may skip this one. If so, just make it return False
     * 
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
        return false;
    }

    /**
     * @returns true if the hand is a straight
     */
    public boolean isStraight() {
        // get all ranks then check if they are consecutive
        List<Card.Rank> ranks = getRanks();
        Collections.sort(ranks);
        // add cases for ace low and ace high
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i).ordinal() + 1 != ranks.get(i + 1).ordinal()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
        // get all suits then check if they are all the same
        List<Card.Suit> suits = new ArrayList<Card.Suit>();
        for (Card c : cards) {
            suits.add(c.getSuit());
        }
        return Collections.frequency(suits, suits.get(0)) == suits.size();
    }

    @Override
    public int compareTo(Hand h) {
        // hint: delegate!
        // and don't worry about breaking ties
        return kind().compareTo(h.kind());
    }

    /**
     * This method is already implemented and could be useful!
     * 
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush())
            return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4))
            return Kind.FOUR_OF_A_KIND;
        else if (hasNKind(3) && hasNKind(2))
            return Kind.FULL_HOUSE;
        else if (isFlush())
            return Kind.FLUSH;
        else if (isStraight())
            return Kind.STRAIGHT;
        else if (hasNKind(3))
            return Kind.THREE_OF_A_KIND;
        else if (isTwoPair())
            return Kind.TWO_PAIR;
        else if (hasNKind(2))
            return Kind.PAIR;
        else
            return Kind.HIGH_CARD;
    }

    public static void main(String[] args) {
        Hand h = new Hand("TD TC TH TC 7D");
        System.out.println(h.kind());

        List<Card.Rank> ranks = h.getRanks();
        System.out.println(ranks);

        System.out.println(h.hasNKind(1));
        System.out.println(h.hasNKind(2));
        System.out.println(h.hasNKind(3));
        System.out.println(h.hasNKind(4));

        System.out.println(h.isFlush());

    }
}
