package application;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <h1>Game</h1>
 * <p> This is the class where all the action happens
 * most of the hard game logic is within this class.
 * 
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarrett Swales
 * @version 1.0
 * @since 2016 Fall
 */


public class Game implements Serializable {
	/** The maximum number of played cards. */
	private static final int MAX_PLAYED_CARDS = 4;
	
	/** The number of times a turn changes per round. */
	private static final int TURN_CHANGES = 3;
	
	/** The maximum number of cards in a player's hand. */
	private static final int MAX_CARDS_IN_HAND = 5;
	
	/** A base trump in hand ratio, used for AI trump selection. */
	private static final double BASE_TRUMP_RATIO = 0.25;
	
	/** The games Deck of Cards. */
	private Deck theDeck;
	
	/** Used to store the Hands of each player. */
	//private ArrayList<Card> cards = new ArrayList<Card>();
	private Card[] cards = new Card[MAX_PLAYED_CARDS];
	
	/** Each player's Hand. */
	private Hand p1, p2, p3, p4;
	
	/** Used to iterate around the table and 
	 * designate who's turn it is.*/
	private int turnCounter;
	
	/** The flipped Card after dealing all cards. */
	private Card upCard;
	
	/** The trump Suit for any given round. */
	private Suit trump;
	
	/** The score for team 1. */
	private int team1Score;
	
	/** The score for team 2. */
	private int team2Score;
	
	/** The game's overall score. */
	private int gameScore;
	
	/** Game type, true if two-player game is in effect */
	private boolean isTwoPlayer;

	/**
	 * This is the Game default constructor for the Game class,
	 * it sets the attributes for the Game class for a new Game. 
	 */
	public Game() {
	    // constructor starts new game
	    turnCounter = 0;
	    theDeck = new Deck();
	    upCard = theDeck.getCardAt(0);
	    theDeck.removeCardAt(0);
	    p1 = new Hand(theDeck);
	    p2 = new Hand(theDeck);
	    p3 = new Hand(theDeck);
	    p4 = new Hand(theDeck);
	}
	
	/**
	 * This method adds the selected card to the Array cards and removes it
	 * from the player's Hand, and then calls nextTurn() to set the turn to
	 * the next player.
	 * 
	 * @param crnt is the current player's Hand.
	 * @param index is the index of the selected card.
	 */
	public final void playCard(final Hand crnt, final int index) {
	    cards[0] = crnt.get(index);
	    crnt.removeCardAt(index);
	    nextTurn();
	}
	
	 /**
	  * This sets the turn to the next player.
	  */
	 public final void nextTurn() {
		 if (turnCounter == TURN_CHANGES) {
			 turnCounter = 0;
		 } else {
			 turnCounter++;
		 }
	 }
	 
	 /**
	  * Getter for the turn counter, turnCounter.
	  * @return the index of the player whose turn it is.
	  */
	 public final int getTurn() {
	   return turnCounter;
	 }
	 
	 /**
	  * Setter for the current players turn.
	  * @param turn the index of the player whose turn it is.
	  */
	 public final void setTurn(final int turn) {
	   turnCounter = turn;
	 }
	 
	 /**
	  * This plays a card from the AI players Hand.
	  * @param index the index of the AIs Card to be played.
	  */
	 public final void playCardAI(final int index) {
	   cards[index] = getPlayerHand(index).get(0);
	   getPlayerHand(index).removeCardAt(0);
	   nextTurn();
	 }
	 
	 /**
	  * Shell for R2 method.
	  */
	 public final void selectTrumpAI() {
	   
	 }
	 
	 /**
	  * This method affords an AI player the ability to call trump during
	  * the trump call rounds.
	  * 
	  * @param hand is the Hand of the AI whose turn is is to call trump.
	  * @param faceUpCard is the Card that is turned face up.
	  */
	 public final void orderUpAI(final Hand hand, final Card faceUpCard) {
		 Suit upCardSuit = faceUpCard.getSuit();
		 int numInSuit = 0;
		 for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
			 if (hand.get(i).getSuit() == upCardSuit) {
				 numInSuit++;
			 }
		 }
		 if (numInSuit >= 2) {
			 setTrump(upCardSuit);
			 hand.set(hand.getLowCard(), faceUpCard);
			 setTrump(faceUpCard.getSuit());
		 }
	 }
	 
	 /**
	  * This method helps the AI determine how to select trump.
	  * 
	  * @param hand is the Hand of the AI player.
	  * @return Suit of the selected trump suit.
	  */
	 public final Suit selectTrumpAI(final Hand hand) {
		 int cValue, sValue, hValue, dValue, highest;
		 setTrump(Suit.CLUBS);
		 cValue = hand.getHandValue();
		 setTrump(Suit.SPADES);
		 sValue = hand.getHandValue();
		 setTrump(Suit.HEARTS);
		 hValue = hand.getHandValue();
     	 setTrump(Suit.DIAMONDS);
     	 dValue = hand.getHandValue();
     
     	highest = (Math.max(cValue, 
     			Math.max(sValue, Math.max(hValue, dValue))));
     	if (Math.random() < BASE_TRUMP_RATIO) {
     		return null;
     	} else if (highest == cValue) {
     		return Suit.CLUBS;
     	} else if (highest == sValue) {
     		return Suit.SPADES;
     	} else if (highest == hValue) {
     		return Suit.HEARTS;
     	} else {
     		return Suit.DIAMONDS;
     	}
	 }
	 
	 /**
	  * Getter for the Hand of the the player at the passed index
	  * value.
	  * 
	  * @param playerIndex the index of the player whose Hand to get.
	  * @return Hand is the players Hand at playerIndex.
	  */
	 public final Hand getPlayerHand(final int playerIndex) {
		 if (playerIndex == 0) {
			 return p1;
		 } else if (playerIndex == 1) {
			return p2;
		 } else if (playerIndex == 2) {
			return p3;
		 } else { 
			return p4;
		 }
	 }
	 
	 /**
	  * This method clears the Array cards, which holds the played Cards
	  * for the round.
	  */
	 public final void clearTable() {
		 Arrays.fill(cards, null);
	 }
	
	/**
	 * This method updates the score depending on who took the round.
	 */
	 public final void updateScore() {
		 if (roundTaker() == 0 || roundTaker() == 2) {
			 team1Score++;
		 } else {
			 team2Score++;
		 }
		nextTurn();
	 }
	
	 /**
	  * This method compares each player's played card, and determines
	  * who won the hand.
	  * @return the index of the highest valued played card.
	  */
	 public final int roundTaker() {
		 int highestCardValue = 0;
		 int taker = 0;
		 for (int i = 0; i < MAX_PLAYED_CARDS; i++) {
			 if (cards[i].getCardValue() > highestCardValue) {
				 highestCardValue = cards[i].getCardValue();
				 taker = i;
			 }
		 }
		 return taker;
	 }
	 
	 /**
	  * Getter for upCard, the face up card.
	  * @return the face up Card.
	  */
	 public final Card getUpCard() {
		 return upCard;
	 }
	 
	 /**
	  * Getter for team1's score.
	  * @return the score of team1.
	  */
	 public final int getScore1() {
		 return team1Score;
	 }
	 
	 /**
	  * Getter for team2's score.
	  * @return the score of team2.
	  */
	 public final int getScore2() {
		 return team2Score;
	 }
	 
	 /**
	  * Getter for gameScore.
	  * @return the game's score.
	  */
	 public final int getGameScore() {
		 return gameScore;
	 }
	 
	 /**
	  * This method adds points to the round winning team's game
	  * score.
	  */
	public final void scoreRound() {
		if (getScore1() > getScore2()) {
			gameScore++;
		} else {
		  gameScore--;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTwoPlayer() {
		return isTwoPlayer;
	}

	/**
	 * 
	 * @param isTwoPlayer
	 */
	public void setIsTwoPlayer(boolean isTwoPlayer) {
		this.isTwoPlayer = isTwoPlayer;
	}
	
	/**
	 * This method sets any Card of the trump suit 
	 * in the players Hand to True.
	 * 
	 * @param s is the Suit to be trump.
	 */
	public final void setTrump(final Suit s) {
		trump = s;
		Hand currentHand;
		for (int i = 0; i < MAX_PLAYED_CARDS; i++) {
			currentHand = getPlayerHand(i);
			for (int j = 0; j < MAX_CARDS_IN_HAND; j++) {
				if (currentHand.get(j).getSuit() == s) {
					currentHand.get(j).setTrump(true);
				}
			}
		}
	}
	
	/**
	 * This method returns the Suit of the trump suit.
	 * 
	 * @return Suit is the trump suit.
	 */
	public final Suit getTrump() {
		return trump;
	}
	
	/**
	 * This method sets upCard to null, in effect removing it.
	 */
	public final void removeUpCard() {
		upCard = null;
	}
	
	/**
	 * This method returns true when the round is complete
	 * and false otherwise.
	 * 
	 * @return true when the round is complete.
	 */
	public final boolean roundComplete() {
		boolean done = true;
		for (int i = 0; i < MAX_PLAYED_CARDS; i++) {
			if (cards[i] == null) {
				done = false;
			}
		}
		return done;
	}

	/**
	 * This method returns a Card at the passed value, index.
	 * 
	 * @param index the index of the array cards, (the played cards).
	 * @return the Card at the passed index.
	 */
	public final Card getPlayedCard(final int index) {
		return cards[index];
	}
}