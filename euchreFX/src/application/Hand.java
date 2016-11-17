package application;

import java.util.ArrayList;

/**
 * <h1>Hand</h1>
 * <p>
 * This class contains the attributes of a Euchre player's
 * hand.
 * 
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */

public class Hand {

    /** An ArrayList of Cards that makes up the users hand each round. */
    private ArrayList<Card> theHand = new ArrayList<Card>();

    /**
     * Default constructor for class, replenishes hand, called after each round
     * of play.
     *
     * @param crnt is the parameter.
     */
    public Hand(final Deck crnt) {
        replenishHand(crnt);
    }

    /**
     * Gets the Card at the parameter index.
     *
     * @param index is the parameter.
     * @return Card
     */
    public final Card get(final int index) {
      if (theHand.size() > index) {
        return theHand.get(index);
      } else {
    	  return null;
      }
    }
    
    /**
     * This is a getter for the size of the ArrayList theHand. 
     * @return int The hand's size.
     */
    public final int getSize() {
    	return theHand.size();
    }
    
    /**
     * This method adds Card's to the ArrayList theHand at the
     * passed index value.
     * 
     * @param index Index for the ArrayList in theHand.
     * @param c is the Card to be added to the ArrayList the Hand.
     */
    public final void set(final int index, final Card c) {
    	theHand.set(index, c);
    }

    /**
     * Adds 5 new Cards from the Deck crnt to the this Hand.
     * 
     * @param crnt Is the the Deck that Card's are being taken
     * 				from.
     */
    public final void replenishHand(final Deck crnt) {
    	final int maxSize = 5;
        //empty hand
    	theHand.clear();
    	//refill hand
        for (int i = 0; i < maxSize; i++) {
            theHand.add(crnt.getCardAt(0));
            crnt.removeCardAt(0);
        }
    }

    /**
     * Returns the combined value of all cards in this Hand.
     * 
     * @return int This is the estimated "effectiveness" value for this
     * 				hand.
     */
    public final int getHandValue() {
        int value = 0;
        for (int i = 0; i < theHand.size(); i++) {
            value += get(i).getCardValue();
        }
        return value;
    }

    /**
     * Returns the value of the high Card in this Hand.
     * 
     * @return int value per the Card's value.
     */
    public final int getHighCard() {
        int value = 0;
        for (int i = 0; i < theHand.size(); i++) {
            if (get(i).getCardValue() > value) {
                value = get(i).getCardValue();
            }
        }
        return value;
    }
    
    /**
     * Returns the value of the lowest Card in this Hand.
     * 
     * @return int value per the Card's value.
     */
    public final int getLowCard() {
      Card lowCard = get(0);
      int lowCardPos = 0;
      for (int i = 0; i < theHand.size(); i++) {
          if (get(i).getCardValue() < lowCard.getCardValue()) {
              lowCard = get(i);
              lowCardPos = i;
          }
      }
      return lowCardPos;
  }
    
    /**
     * This method removes the card at index in theHand.
     * 
     * @param index is the Card's index to remove.
     */
    public final void removeCardAt(final int index) {
    	theHand.remove(index);
    }

    /**
     * Appends a string which describes the players Hand.
     * 
     * @return String handDesc
     */
    @Override
    public final String toString() {
        StringBuilder handDesc = new StringBuilder(theHand.size());
        for (int i = 0; i < theHand.size(); i++) {
            handDesc.append(" " + theHand.get(i).toString());
        }
        return handDesc.toString();

    }
}
