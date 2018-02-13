package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
import java.util.Random;

public class Deck {
	/**
	 * The list of cards in the deck.
	 */
	private ArrayList<Card> deck = new ArrayList<Card>();
	final int NUM_CARDS = 52;

	/**
	 * Creates a new sorted deck based on values in the Card class.
	 * @see Card
	 */
	public Deck() {
		for(int suit = Suits.values().length - 1; suit >= 0; suit--){
			for(int rank = Card.LOW_RANK; rank <= Card.TOP_RANK; rank++){
				Card newCard = new Card(rank, Suits.values()[suit]);
				deck.add(newCard);
			}
		}
	}

	/**
	 * Shuffles the deck.
	 */
	public void shuffle() {
		ArrayList<Card> newDeck = new ArrayList<Card>();
		while(deck.size() > 0){
			int randIndex = (int)Math.floor((deck.size() - 1) * Math.random() + 0.5);
			newDeck.add(deck.get(randIndex));
			deck.remove(randIndex);
		}
		deck = newDeck;
	}

	/**
	 * @deprecated
	 * Prints the deck.
	 */
	public void printDeck() {
		System.out.println(toString());
	}

	/**
	 * @return String representation of the deck.
	 */
	public String toString() {
		String s = "";
		for(int card = 0; card < deck.size(); card++){
			s = s + deck.get(card).toString() + " ";
			if((card + 1) % Card.TOP_RANK == 0)
				s = s + "\n";
		}
		return s;
	}

	/**
	 * Removes a card from the top of the deck and returns it.
	 * @return The removed card from the deck.
	 */
	public Card dealCard() {
		if(deck.size() == 0)
			return null;
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}

	/**
	 * @return The amount of cards in the deck.
	 */
	public int getSize(){
		return deck.size();
	}
}



