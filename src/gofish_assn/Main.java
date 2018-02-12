package gofish_assn;

public class Main {
	public static void main(String args[]) {
		String[] playerNames = {"Bob", "Bill", "Mary", "Sue", "Joe", "Mark", "Lewis", "William", "Chris", "Gage"};
		//String[] playerNames = {"Bob", "Bill"};
		GoFishGame newGame = new GoFishGame(playerNames);

		while(!newGame.gameEnded)
			newGame.DoTurn();

		System.out.println("GAME OVER!");
	}
}






