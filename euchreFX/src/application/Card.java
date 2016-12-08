package application;

import java.io.Serializable;

/**
 * <h1>Card</h1>
 * <p>
 * This is the card class. Each Deck is comprised 
 * of Card objects. Card uses the Enum Classes Suit
 * and Rank. It's color is used described by a binary
 * integer (color).
 * 
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */

public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** A round effectiveness score for non-trump nine. */
	public static final int NINE_NO_TRUMP = 1;
	
	/** A round effectiveness score for non-trump ten. */
	public static final int TEN_NO_TRUMP = 2;
	
	/** A round effectiveness score for non-trump jack. */
	public static final int JACK_NO_TRUMP = 3;
	
	/** A round effectiveness score for non-trump queen. */
	public static final int QUEEN_NO_TRUMP = 4;
	
	/** A round effectiveness score for non-trump king. */
	public static final int KING_NO_TRUMP = 5;
	
	/** A round effectiveness score for non-trump ace. */
	public static final int ACE_NO_TRUMP = 10;
	
	/** A round effectiveness score for trump nine. */
	public static final int NINE_TRUMP = 12;
	
	/** A round effectiveness score for trump ten. */
	public static final int TEN_TRUMP = 15;
	
	/** A round effectiveness score for the left bower. */
	public static final int JACK_L_BOWER = 31;
	
	/** A round effectiveness score for the right bower. */
	public static final int JACK_R_BOWER = 35;
	
	/** A round effectiveness score for trump queen. */
	public static final int QUEEN_TRUMP = 20;
	
	/** A round effectiveness score for trump king. */
	public static final int KING_TRUMP = 25;
	
	/** A round effectiveness score for trump ace. */
	public static final int ACE_TRUMP = 30;
	
	/** This Card's suit. */
	private final Suit suit;

	/** This Card's rank. */
	private final Rank rank;

	// 0 for black, 1 for red
	/** This Card's color. */
	private int color;

	/** Trump true = yes, false = no. */
	private boolean trump;

	/**
	 * This is the constructor for all Card class objects, the parameters suit,
	 * rank, trump are used to set the private.
	 *	 
	 * @param cardSuit is this Cards (Enum) Suit.
	 * @param cardRank is this Cards (Enum) Rank.
	 * @param cardTrump is this Card's trump value, true for trump.
	 */
	public Card(final Suit cardSuit, final Rank cardRank, 
			final boolean cardTrump) {
		this.rank = cardRank;
		this.suit = cardSuit;
		this.trump = cardTrump;

		if (suit == Suit.CLUBS || suit == Suit.SPADES) {
			color = 0;
		} else {
			color = 1;
		}
	}

	/**
	 * This method returns this Card's rank.
	 * 
	 * @return rank this Card's rank.
	 */
	public final Rank getRank() {
		return rank;
	}

	/**
	 * This method returns true if this Card's suit is trump, false if not.
	 * 
	 * @return trump is true when this Card's suit is trump.
	 */
	public final boolean getTrump() {
		return trump;
	}

	// calculates value of cards based on trump suit
	/**
	 * This method returns a "effectiveness value" of the card. The
	 * "effectiveness" is a metric for the Card's value in the current round,
	 * generally for AI purposes.
	 *	 
	 * @return int The card's estimated value in the current round.
	 */
	public final int getCardValue() {
		
		
		if (!trump) {
			switch (rank) {
				case NINE:
					return NINE_NO_TRUMP;
				case TEN:
					return TEN_NO_TRUMP;
				case JACK:
					if (color == Deck.getTrumpColor()) {
						return JACK_L_BOWER;
					} else {
						return JACK_NO_TRUMP;
					}
				case QUEEN:
					return QUEEN_NO_TRUMP;
				case KING:
					return KING_NO_TRUMP;
				case ACE:
					return ACE_NO_TRUMP;
				default:
					return 0;
			}
		} else {
			switch (rank) {
				case NINE:
					return NINE_TRUMP;
				case TEN:
					return TEN_TRUMP;
				case QUEEN:
					return QUEEN_TRUMP;
				case KING:
					return KING_TRUMP;
				case ACE:
					return ACE_TRUMP;
				case JACK:
					return JACK_R_BOWER;
				default:
					return 0;
			}
		}
	}

	// returns a string telling the value and suit of a card

	/**
	 * @return String
	 */
	@Override
	public final String toString() {
		String desc = "The " + rank + " of " + suit;
		return desc;
	}

	/**
	 * This method sets the trump field for this Card.
	 * 
	 * @param suitTrump
	 *          Is true, when this Card's suit is trump.
	 */
	public final void setTrump(final boolean suitTrump) {
		this.trump = suitTrump;
	}

	/**
	 * This method returns the field for this Card's Suit.
	 * 
	 * @return Suit Is this Card's Suit.
	 */
	public final Suit getSuit() {
		return suit;
	}

	/**
	 * This method returns an integer (binary), that represents this 
	 * Card's color (0 is black, 1 is red). Used to identify the 
	 * rounds Left Bower.
	 * 
	 * @return int Is 0 when Suit color is black, 1 when Suit color is red.
	 */
	public final int getColor() {
		return color;
	}
}
