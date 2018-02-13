package gofish_assn;

import java.util.ArrayList;

public class Main {
	public static void main(String args[]) {
		//String[] playerNames = {"Bob", "Bill", "Mary", "Sue", "Joe", "Mark", "Lewis", "William", "Chris", "Gage"};
		String[] playerNames = {"Bob", "Bill", "Mary", "Sue", "Joe", "Mark"};
		//String[] playerNames = {"Bob", "Bill"};
		GoFishGame newGame = new GoFishGame(playerNames);

		while(!newGame.gameEnded)
			newGame.DoRound();

		ArrayList<Player> winners = new ArrayList<Player>();
		int theMax = -1;
		for(Player checkMax: newGame.players){
			if(checkMax.getBookSize() > theMax)
				theMax = checkMax.getBookSize();
		}
		for(Player checkMax: newGame.players){
			if(checkMax.getBookSize() == theMax)
				winners.add(checkMax);
		}

		System.out.print("GAME OVER! Player");
		if(winners.size() == 1){
			System.out.println(" " + winners.get(0).getName().toUpperCase() + " won with " + theMax + " books!");
		} else if(winners.size() == 2){
			System.out.println("s " + winners.get(0).getName().toUpperCase() + " and " + winners.get(1).getName().toUpperCase() + " tied with " + theMax + " books!");
		} else {
			System.out.print("s ");
			for(int i = 0; i < winners.size(); i++){
				if(i == winners.size() - 1)
					System.out.print("and " + winners.get(i).getName().toUpperCase());
				else
					System.out.print(winners.get(i).getName().toUpperCase() + ", ");
			}
			System.out.println(" tied with " + theMax + " books!");
		}
	}
}






