package gofish_assn;

import java.util.ArrayList;

public class Player {
	/**
	 * Player's hand.
	 */
	private ArrayList<Card> hand = new ArrayList<Card>();
	/**
	 * List of books the player has collected.
	 */
	private ArrayList<Card[]> book = new ArrayList<Card[]>();
	private String name;

	/**
	 * Creates a new player.
	 * @param name Name of player.
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Swaps a card from one player's deck to another's.
	 * @param from Player to take card from.
	 * @param c The card to take.
	 * @param to Player to give the card to.
	 */
	public static void swapCards(Player from, Card c, Player to){
		Card take = from.removeCardFromHand(c);
		to.addCardToHand(take);
	}

	/**
	 * Chooses a card from the player's hand.
	 * @return The chosen card.
	 */
	public Card chooseCardFromHand() {
		return hand.get((int)Math.floor(Math.random() * (hand.size() - 1) + 0.5));
	}

	/**
	 * Returns a card from the player's hand with the same rank as the input card.
	 * @param c The input card.
	 * @return A card with the same rank or null.
	 */
	public Card rankInHand(Card c) {
		int theRank = c.getRank();
		for(Card check: hand){
			if(theRank == check.getRank())
				return check;
		}
		return null;
	}

	/**
	 * Adds a card to the end of the player's hand.
	 * @param c The card to add.
	 */
	public void addCardToHand(Card c) {
		hand.add(c);
	}

	/**
	 * Removes a card from the player's hand.
	 * @param c The card to remove.
	 * @return The removed card.
	 */
	private Card removeCardFromHand(Card c) {
		Card retCard = null;
		for(Card check: hand){
			if(c.equals(check)){
				retCard = check;
				hand.remove(check);
				break;
			}
		}
		return retCard;
	}

	/**
	 * @return String representation of the player's current hand.
	 */
	public String handToString() {
		String s = "";
		for(Card check: hand){
			s = s + check.toString() + " ";
		}
		return s;
	}

	/**
	 * @return String representation of the player's collected books.
	 */
	public String bookToString() {
		String s = "";
		for(Card[] check: book){
			s = s + "[" + check[0].toString() + ", " + check[1].toString() + "] ";
		}
		return s;
	}

	/**
	 * @return Player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Size of player's hand.
	 */
	public int getHandSize() {
		return hand.size();
	}

	/**
	 * @return How many books the player has collected.
	 */
	public int getBookSize() {
		return book.size();
	}

	/**
	 * Checks player's current hand for any pairs of cards. If a pair is found, removes the pair from hand and adds it into the player's collection of books.
	 */
	public void checkHandForBook(){
		boolean found = false;
		int[] theInds = new int[2];

		for(int hold = 0; hold < hand.size(); hold++){
			for(int check = 0; check < hand.size(); check++){
				if(hold != check && hand.get(check).getRank() == hand.get(hold).getRank()){
					theInds[0] = hold;
					theInds[1] = check;
					found = true;
					break;
				}
			}
			if(found)
				break;
		}

		if(found){
			Card[] theBook = new Card[2];
			theBook[0] = hand.get(theInds[0]);
			theBook[1] = hand.get(theInds[1]);
			book.add(theBook);

			hand.remove(theInds[0]);
			hand.remove(theInds[1] - 1);

			checkHandForBook();
		}
	}

	/*
	//Here are som ideas for additional functionality that you may want/need
	//OPTIONAL
	// comment out if you decide to not use it
	//Does the player have a card with the same rank as c in her hand?
	public boolean rankInHand(Card c) {
		return false; //stubbed
	}

	//uses some strategy to choose one card from the player's
	//hand so they can say "Do you have a 4?"
	public Card chooseCardFromHand() {
		Card c = new Card();

		return c;
	}

	//Does the player have the card c in her hand?
	public boolean cardInHand(Card c) {
		return false; //stubbed
	}


	//OPTIONAL
	// comment out if you decide to not use it
	//Does the player have a card with the same rank as c in her hand?
	//e.g. will return true if the player has a 7d and the parameter is 7c

	public boolean sameRankInHand(Card c) {
		return false; //stubbed
	}*/
}
