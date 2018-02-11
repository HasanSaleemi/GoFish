package gofish_assn;

public class Main {
	public static void main(String args[]) {
		Deck d = new Deck();
		d.shuffle();

		Player bob = new Player("Bob");
		Player bill = new Player("Bil");

		for(int i = 0; i < 7; i++){
			bob.addCardToHand(d.dealCard());
			bill.addCardToHand(d.dealCard());
		}
		bob.checkHandForBook();
		bill.checkHandForBook();

		/*while(d.getSize() > 0 || bob.getHandSize() > 0 || bill.getHandSize() > 0) {
			if (bob.getHandSize() == 0) {
				if (d.getSize() > 0)
					bob.addCardToHand(d.dealCard());
			} else {
				Card pickBob = bob.chooseCardFromHand();
				Card checkBill = bill.rankInHand(pickBob);
				if (checkBill != null) {
					Player.swapCards(bill, checkBill, bob);
				} else {
					bill.addCardToHand(d.dealCard());
				}
			}
			bill.checkHandForBook();
			bob.checkHandForBook();

			System.out.println(bob.getName() + ": " + bob.handToString() + "\n\tBOOKS: " + bob.bookToString());
			System.out.println(bill.getName() + ": " + bill.handToString() + "\n\tBOOKS: " + bill.bookToString());
			System.out.println();

			if(bill.getHandSize() == 0){
				if(d.getSize() > 0)
					bill.addCardToHand(d.dealCard());
			} else {
				Card pickBill = bill.chooseCardFromHand();
				Card checkBob = bob.rankInHand(pickBill);
				if (checkBob != null) {
					Player.swapCards(bob, checkBob, bill);
				} else {
					bob.addCardToHand(d.dealCard());
				}
			}
			bill.checkHandForBook();
			bob.checkHandForBook();

			System.out.println(bob.getName() + ": " + bob.handToString() + "\n\tBOOKS: " + bob.bookToString());
			System.out.println(bill.getName() + ": " + bill.handToString() + "\n\tBOOKS: " + bill.bookToString());
			System.out.println();
		}*/
	}
}






