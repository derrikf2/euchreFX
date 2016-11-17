package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 * <h1>Hand Test</h1>
 * <p> This class tests the Hand class.
 * 
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */
public class HandTest {
	/** index used for Hand. */
	private int index;
	
	/** A known hand value. */
	private static final int HAND_VALUE = 15;
	
	/** Max hand size. */
	private static final int MAX_HAND_SIZE = 5;
	
	/**
	 * This method tests the set/get Card at method in the Hand class.
	 * It instantiates a Deck, Card, Hand sets a Card in the Hand, and
	 * verifies it at the specified index.
	 */
	@Test
	public final void testGetAndSetCardAt() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		Card c1 = new Card(Suit.CLUBS, Rank.NINE, false);
		h1.set(index, c1);
		assertEquals("Getter and setter work", c1, h1.get(index));
	}
	
	/**
	 * This method tests the getHandValue method in Hand. It 
	 * instantiates a deck, and a hand, and verifies the Hand's value.
	 */
	@Test
	public final void testGetHandValue() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		
		d1.setTrump(Suit.HEARTS);
		h1.set(index, new Card(Suit.CLUBS, Rank.NINE, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.TEN, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.JACK, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.QUEEN, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.KING, false));
		assertEquals("Get hand value works", HAND_VALUE, h1.getHandValue());
	}
	
	/**
	 * This method tests the getHighCard method in Hand. It instantiates
	 * a Deck, and a Hand, and it fills the hand, then attempts to get the 
	 * Hand's high Card
	 */
	@Test
	public final void testGetHighCard() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		d1.setTrump(Suit.HEARTS);
		h1.set(index, new Card(Suit.CLUBS, Rank.NINE, false));
		index--;
		h1.set(index, new Card(Suit.CLUBS, Rank.TEN, false));
		index--;
		h1.set(index, new Card(Suit.CLUBS, Rank.JACK, false));
		index--;
		h1.set(index, new Card(Suit.CLUBS, Rank.QUEEN, false));
		index--;
		h1.set(index, new Card(Suit.CLUBS, Rank.KING, false));
		assertEquals("Get high card works", index, h1.getHighCard());
	}
	
	/**
	 * This method tests the getSize method of the Hand class. It
	 * instantiates a Deck, and a Hand and checks the size of the
	 * Hand.
	 */
	@Test
	public final void testGetSize() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		assertEquals("Works for a full hand", MAX_HAND_SIZE, h1.getSize());
		h1.removeCardAt(index);
		assertEquals("Works for a removed card", 
				MAX_HAND_SIZE - 1, h1.getSize());
	}
	
	/**
	 * This method tests the removeCardAt method of the Hand class.
	 * It instantiates a Deck, a Hand, and removes a card from the Hand.
	 */
	@Test
	public final void testRemoveCardAt() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		Card c1 = h1.get(index);
		h1.removeCardAt(index);
		boolean b = (c1.getRank() != h1.get(index).getRank()
				|| c1.getSuit() != h1.get(index).getSuit());
		assertTrue("Remove card works", b);
	}
	
	/**
	 * This method tests the toString method of the Hand class.
	 * It instantiates a Deck, and a Hand. It manually 
	 * sets Cards to each available index in the Hand. and then compares
	 * the string description.
	 */
	@Test
	public final void testToString() {
		Deck d1 = new Deck();
		Hand h1 = new Hand(d1);
		index = 0;
		h1.set(index, new Card(Suit.CLUBS, Rank.NINE, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.TEN, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.JACK, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.QUEEN, false));
		index++;
		h1.set(index, new Card(Suit.CLUBS, Rank.KING, false));
		boolean b = h1.toString().equals(" The NINE of CLUBS The TEN of CLUBS "
				+ "The JACK of CLUBS The QUEEN of CLUBS The KING of CLUBS");
		assertTrue("To string works", b);
	}
}