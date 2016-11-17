package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
* <h1>DeckTest</h1>
* <p> This class tests the Deck class.
* 
* @author Derrik Fleming
* @author Josh T
* @author Jarrett Swales
* @version 1.0
* @since 2016 Fall
*/
public class DeckTest {
	/** Full deck size. */
	private static final int DECK_SIZE = 24;
	
	/**
	 * Tests the get and remove card at methods.
	 */
	@Test
	public final void testGetAndRemoveCardAt() {
		Deck d1 = new Deck();
		Card c1 = d1.getCardAt(0);
		d1.removeCardAt(0);
		assertNotEquals("Tests to see if card is removed", c1, d1.getCardAt(0));
	}
	
	/**
	 * Tests the replenish and shuffle deck methods.
	 */
	@Test
	public final void testReplenishAndShuffleDeck() {
		Deck d1 = new Deck();
		Deck d2 = new Deck();
		d1.replenishDeck();
		d2.replenishDeck();
		assertEquals("Replenish works the same for all decks", 
				d1.getCardAt(0).getSuit(), d2.getCardAt(0).getSuit());
		assertEquals("Replenish works the same for all decks", 
				d1.getCardAt(0).getRank(), d2.getCardAt(0).getRank());
		d1.shuffle();
		boolean b = (d1.getCardAt(0).getSuit() == d2.getCardAt(0).getSuit());
		b = (b && d1.getCardAt(0).getRank() == d2.getCardAt(0).getRank());
		assertFalse("Shuffle randomizes cards", b);
	}
	
	/**
	 * Test the get current size methods.
	 */
	@Test
	public final void testGetCurrentSize() {
		Deck d1 = new Deck();
		assertEquals("Works for a full deck", DECK_SIZE, d1.getCurrentSize());
		d1.removeCardAt(0);
		assertEquals("Works for an almost full deck", 
				DECK_SIZE - 1, d1.getCurrentSize());
	}
	
	/**
	 * Tests the setters and getters for trump.
	 */
	@Test
	public final void testSetAndGetTrump() {
		Deck d1 = new Deck();
		Deck d2 = new Deck();
		Deck d3 = new Deck();
		Deck d4 = new Deck();
		d1.setTrump(Suit.CLUBS);
		d2.setTrump(Suit.SPADES);
		d3.setTrump(Suit.HEARTS);
		d4.setTrump(Suit.DIAMONDS);
		assertEquals("Works when clubs are trump", Suit.CLUBS, d1.getTrump());
		assertEquals("Works when spades are trump", Suit.SPADES, d2.getTrump());
		assertEquals("Works when hearts are trump", Suit.HEARTS, d3.getTrump());
		assertEquals("Works when diamonds are trump", 
				Suit.DIAMONDS, d4.getTrump());
	}
	
	/**
	 * Tests the getter for trump color.
	 */
	@Test
	public final void testGetTrumpColor() {
		Deck d1 = new Deck();
		d1.setTrump(Suit.CLUBS);
		assertEquals("Works when clubs are trump", 0, Deck.getTrumpColor());
		d1.setTrump(Suit.SPADES);
		assertEquals("Works when spades are trump", 0, Deck.getTrumpColor());
		d1.setTrump(Suit.HEARTS);
		assertEquals("Works when hearts are trump", 1, Deck.getTrumpColor());
		d1.setTrump(Suit.DIAMONDS);
		assertEquals("Works when diamonds are trump", 1, Deck.getTrumpColor());
	}
}