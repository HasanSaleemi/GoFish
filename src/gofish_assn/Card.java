package gofish_assn;

public class Card {
	/**
	 * Enum representing a suit of a card.
	 */
	public enum Suits {club, diamond, heart, spade}
	/**
	 * Card rank names. The first value is the lowest rank and the next values are the special ranks in order.
	 */
	private static String[] rankNames = {"A", "J", "Q", "K"};
	/**
	 * Maximum value of the highest rank card.
	 */
	final static int TOP_RANK = 13;
	/**
	 * Minimum value of the lowest rank card.
	 */
	final static int LOW_RANK = 1;
	/**
	 * The difference between the top rank and the number of special rank cards.
	 */
	private final static int RANK_DIF = TOP_RANK - rankNames.length + 1;

	/**
	 * The rank number of the card.
	 */
	private int rank;
	/**
	 * The suit of the card.
	 */
	private Suits suit;

	/**
	 * Default card constructor creates a card of the lowest suit and rank.
	 */
	public Card() {
		rank = LOW_RANK;
		suit = Suits.values()[0];
	}
	/**
	 * Create a specific card.
	 * @param r The rank value.
	 * @param s The suit enum.
	 */
	public Card(int r, Suits s) {
		rank = r;
		suit = s;
	}
	/**
	 * @deprecated
	 * @param r The rank value.
	 * @param s The suit.
	 */
	public Card(int r, char s) {
		this(r, toSuit(s));
	}


	/**
	 * @param c Card to compare.
	 * @return Cards are equal in suit and rank.
	 */
	public boolean equals(Card c){
		return (this.rank == c.rank && this.suit == c.suit);
	}

	/**
	 * @deprecated
	 * @param c Suit as a character.
	 * @return Suit enum.
	 */
	private static Suits toSuit(char c) {
		switch (c) {
			case 'c':
				return Suits.club;
			case 'd':
				return Suits.diamond;
			case 'h':
				return Suits.heart;
			case 's':
				return Suits.spade;
			default:
				return Suits.spade;
		}
	}

	/**
	 * @return Card's suit to string.
	 */
	private String suitToString() {
		return "" + suit.toString().charAt(0);
	}
	/**
	 * @return Card's rank to string.
	 */
	public String rankToString() {
		if(rank == LOW_RANK)
			return rankNames[0];
		else if(rank > LOW_RANK && rank <= RANK_DIF)
			return Integer.toString(rank);
		else if(rank > RANK_DIF && rank <= TOP_RANK)
			return rankNames[rank - RANK_DIF];
		else
			return null;
	}
	/**
	 * @return Representation of the Card as a string.
	 */
	public String toString() {
		return rankToString() + "" + suitToString();
	}

	/**
	 * @return Card's rank.
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @return Card's suit enum.
	 */
	public Suits getSuit() {
		return suit;
	}
}
