package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * <h1>Deck</h1>
 * <p> The is the Deck class, it is used to hold the 
 * collection of cards used during game play. The cards
 * are stored in an ArrayList, theDeck.
 * 
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */
public class Deck {

    /** The trump suit for the round. */
    private Suit trump;

    /** Binary value of the trump suits color. */
    private static int trumpColor;

    /** Seed time. */
    private long seed = System.nanoTime();

    /** theDeck, an AL of valid, playable cards for the euchre game. */
    private ArrayList<Card> theDeck = new ArrayList<Card>();

    // creates a new Euchre deck of 24 cards
    /**
     * Constructor, replenishes the deck and shuffles on instantiation.
     */
    public Deck() {
        replenishDeck();
        shuffle();

    }

    // randomizes the order of the cards in the deck
    /**
    * This method shuffles the Deck.
    */
    public final void shuffle() {
        Collections.shuffle(theDeck, new Random(seed));

    }

    /**
     * This method is essentially a getter for a Card at a 
     * specified index.
     * 
     * @param index is the index of the Deck.
     * @return Card at index.
     */
    public final Card getCardAt(final int index) {
        return theDeck.get(index);
    }

    /**
     * This method removes a Card from the Deck.
     * 
     * @param index index of the Card to be removed.
     */
    public final void removeCardAt(final int index) {
        theDeck.remove(index);
    }

    /**
     * This method replenishes the deck.
     */
    public final void replenishDeck() {
    	final int numSuits = 4,
    			numRanks = 6;
        theDeck.clear();
        for (int s = 0; s < numSuits; s++) {
            for (int f = 0; f < numRanks; f++) {
                theDeck.add(new Card(Suit.values()[s], 
                		Rank.values()[f], false));
            }
        }

    }

    /**
     * Getter for the current size of the Deck.
     * @return int the size of the Deck.
     */
    public final int getCurrentSize() {
        return theDeck.size();
    }

    /**
     * Setter for trump.
     * @param t is the parameter.
     */
    public final void setTrump(final Suit t) {
        if (t == Suit.CLUBS || t == Suit.SPADES) {
            trumpColor = 0;
        } else {
            trumpColor = 1;
        }
        this.trump = t;
        
        for (int i = 0; i < getCurrentSize(); i++) {
            if (theDeck.get(i).getSuit() == t) {
                theDeck.get(i).setTrump(true);
            }
        }
    }

    /**
     * Getter for trump.
     * @return Suit is the set trump suit.
     */
    public final Suit getTrump() {
        return trump;
    }

    /**
     * Getter for trump color.
    * @return int 0=black 1=red.
    */
    public static int getTrumpColor() {
        return trumpColor;
    }
}
