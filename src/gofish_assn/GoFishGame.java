package gofish_assn;

import java.util.ArrayList;

public class GoFishGame {
	private ArrayList<Player> players;
	Deck d;

	public GoFishGame(String[] names) {
		players = new ArrayList<Player>();
		for(String name : names)
			players.add(new Player(name));
		d = new Deck();
		d.shuffle();
	}
}

