package application;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * <h1> CardTest </h1>
 * <p> This class tests the Card class.
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */

public class CardTest {
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
	
	/**
	 * This is a test for the Suit getter method. instantiates 
	 * a card for each possible Suit, and then checks that the suit
	 * of each card matches.
	 */
	@Test
	public final void testSuitGetter() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.SPADES, Rank.QUEEN, false);
		Card c3 = new Card(Suit.HEARTS, Rank.ACE, false);
		Card c4 = new Card(Suit.DIAMONDS, Rank.TEN, true);
		assertEquals("Works for clubs", Suit.CLUBS, c1.getSuit());
		assertEquals("Works for spades", Suit.SPADES, c2.getSuit());
		assertEquals("Works for hearts", Suit.HEARTS, c3.getSuit());
		assertEquals("Works for diamonds", Suit.DIAMONDS, c4.getSuit());
	}
	
	/**
	 * This is a test for the Rank getter method. instantiates 
	 * a Card for each possible Rank, and then checks that the Rank
	 * of each Card matches.
	 */
	@Test
	public final void testRankGetter() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.CLUBS, Rank.TEN, false);
		Card c3 = new Card(Suit.CLUBS, Rank.JACK, false);
		Card c4 = new Card(Suit.CLUBS, Rank.QUEEN, false);
		Card c5 = new Card(Suit.CLUBS, Rank.KING, false);
		Card c6 = new Card(Suit.CLUBS, Rank.ACE, false);
		assertEquals("Works for nines", Rank.NINE, c1.getRank());
		assertEquals("Works for tens", Rank.TEN, c2.getRank());
		assertEquals("Works for jacks", Rank.JACK, c3.getRank());
		assertEquals("Works for queens", Rank.QUEEN, c4.getRank());
		assertEquals("Works for kings", Rank.KING, c5.getRank());
		assertEquals("Works for aces", Rank.ACE, c6.getRank());
	}
	
	/**
	 * This is a test for the color getter method. instantiates 
	 * a Card for each possible Suit, and then checks that the Suit
	 * color of each Card matches.
	 */
	@Test
	public final void testColorGetter() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.SPADES, Rank.NINE, false);
		Card c3 = new Card(Suit.HEARTS, Rank.NINE, false);
		Card c4 = new Card(Suit.DIAMONDS, Rank.NINE, false);
		assertEquals("Works for clubs", 0, c1.getColor());
		assertEquals("Works for clubs", 0, c2.getColor());
		assertEquals("Works for clubs", 1, c3.getColor());
		assertEquals("Works for clubs", 1, c4.getColor());
	}
	
	/**
	 * This is a test for the value getter method. instantiates 
	 * a Card for a variety of values, and then checks that the value
	 * of each card matches.
	 */
	@Test
	public final void testValueGetter() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.CLUBS, Rank.TEN, false);
		Card c3 = new Card(Suit.CLUBS, Rank.JACK, false);
		Card c4 = new Card(Suit.CLUBS, Rank.QUEEN, false);
		Card c5 = new Card(Suit.CLUBS, Rank.KING, false);
		Card c6 = new Card(Suit.CLUBS, Rank.ACE, false);
		Card c7 = new Card(Suit.CLUBS, Rank.NINE, true);
		Card c8 = new Card(Suit.CLUBS, Rank.TEN, true);
		Card c9 = new Card(Suit.CLUBS, Rank.JACK, true);
		Card c10 = new Card(Suit.CLUBS, Rank.QUEEN, true);
		Card c11 = new Card(Suit.CLUBS, Rank.KING, true);
		Card c12 = new Card(Suit.CLUBS, Rank.ACE, true);
		Card c13 = new Card(Suit.SPADES, Rank.JACK, false);
		Deck d1 = new Deck();
		d1.setTrump(Suit.HEARTS);
		assertEquals("Works for nines not trump", 
				NINE_NO_TRUMP, c1.getCardValue());
		assertEquals("Works for tens not trump", 
				TEN_NO_TRUMP, c2.getCardValue());
		assertEquals("Works for jacks not trump", 
				JACK_NO_TRUMP, c3.getCardValue());
		assertEquals("Works for queens not trump", 
				QUEEN_NO_TRUMP, c4.getCardValue());
		assertEquals("Works for kings not trump", 
				KING_NO_TRUMP, c5.getCardValue());
		assertEquals("Works for aces not trump", 
				ACE_NO_TRUMP, c6.getCardValue());
		d1.setTrump(Suit.CLUBS);
		assertEquals("Works for nines trump", NINE_TRUMP, c7.getCardValue());
		assertEquals("Works for tens trump", TEN_TRUMP, c8.getCardValue());
		assertEquals("Works for jacks trump", JACK_R_BOWER, c9.getCardValue());
		assertEquals("Works for queens trump", QUEEN_TRUMP, c10.getCardValue());
		assertEquals("Works for kings trump", KING_TRUMP, c11.getCardValue());
		assertEquals("Works for aces trump", ACE_TRUMP, c12.getCardValue());
		d1.setTrump(Suit.CLUBS);
		assertEquals("Works for left bower", JACK_L_BOWER, c13.getCardValue());
	}
	
	/**
	 * This is a test for the tremp setter method. instantiates 
	 * 4 Card's for two different suits, sets trump to true/false for 
	 * each suit and then checks that the trump value of each card 
	 * matches.
	 */
	@Test
	public final void testSetTrump() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.SPADES, Rank.TEN, true);
		Card c3 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c4 = new Card(Suit.SPADES, Rank.TEN, true);
		c1.setTrump(true);
		c2.setTrump(false);
		c3.setTrump(false);
		c4.setTrump(true);
		assertEquals("Works from false to true", true, c1.getTrump());
		assertEquals("Works from true to false", false, c2.getTrump());
		assertEquals("Works from false to false", false, c3.getTrump());
		assertEquals("Works from true to true", true, c4.getTrump());
	}
	
	/**
	 * This method tests the toString method of the Card class. It 
	 * instantiates 4 Cards and then checks that the string of each
	 * is correct.
	 */
	@Test
	public final void testToString() {
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		Card c2 = new Card(Suit.SPADES, Rank.ACE, false);
		Card c3 = new Card(Suit.HEARTS, Rank.QUEEN, false);
		Card c4 = new Card(Suit.DIAMONDS, Rank.JACK, false);
		assertEquals("Nine of clubs", "The NINE of CLUBS", c1.toString());
		assertEquals("Ace of spades", "The ACE of SPADES", c2.toString());
		assertEquals("Queen of hearts", "The QUEEN of HEARTS", c3.toString());
		assertEquals("Jack of diamonds", "The JACK of DIAMONDS", c4.toString());
	}
}