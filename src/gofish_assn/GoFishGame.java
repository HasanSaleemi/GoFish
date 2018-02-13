package gofish_assn;

import java.util.ArrayList;

public class GoFishGame {
    private ArrayList<Player> players;
    private Deck deck;
    private int turnNum;

    boolean gameEnded;

    public GoFishGame(String[] names) {
        players = new ArrayList<Player>();
        for(String name : names)
            players.add(new Player(name));
        deck = new Deck();
        deck.shuffle();

        int sizeCheck = players.size();
        if(sizeCheck == 2){
            for (Player player : players) {
                for (int x = 0; x < 7; x++) {
                    player.addCardToHand(deck.dealCard());
                }
            }
        } else if (sizeCheck > 2 && sizeCheck <= 10){
            for (Player player : players) {
                for (int x = 0; x < 5; x++) {
                    player.addCardToHand(deck.dealCard());
                }
            }
        }
        for (Player player : players) {
            player.checkHandForBook();
        }
        gameEnded = false;
        turnNum = 0;
    }

    private void DrawCardForPlayer(Player player){
        if (deck.getSize() > 0) {
            Card draw = deck.dealCard();
            player.addCardToHand(draw);
            System.out.println("\t" + player.getName() + " drew " + draw.toString() + ".");
        } else {
            System.out.println("\t" + player.getName() + " can't draw from an empty deck!");
        }
    }

    public void DoTurn(){
        if(gameEnded)
            return;

        System.out.println("ROUND #" + ++turnNum + " (" + deck.getSize() + ")");
        int isEmpty = 0;

        for (Player player: players) {
            String extra = "";
            if(player.getHandSize() == 0 && deck.getSize() == 0)
                extra = extra +" (FINISHED)";
            System.out.println("\t" + player.getName().toUpperCase() + extra + "\n\t\tHAND = " + "(" + player.getHandSize() + ") " + player.handToString() + "\n\t\tBOOK = " + "(" + player.getBookSize() + ") " + player.bookToString());
        }
        System.out.println("--------");
        for (Player player: players) {
            if(player.getHandSize() == 0){
                if(deck.getSize() > 0){
                    System.out.println(player.getName().toUpperCase() + "'S TURN");
                    DrawCardForPlayer(player);
                } else {
                    isEmpty++;
                }
            } else {
                System.out.println(player.getName().toUpperCase() + "'S TURN");
                while(true) {
                    if(player.getHandSize() == 0) {
                        System.out.println("\t" + player.getName() + "'s hand is empty!");
                        DrawCardForPlayer(player);
                        break;
                    }

                    ArrayList<Player> pickRand = new ArrayList<Player>();
                    for (Player insertPlayer: players) {
                        if(insertPlayer.getHandSize() > 0 && insertPlayer != player){
                            pickRand.add(insertPlayer);
                        }
                    }
                    if(pickRand.size() == 0)
                        break;
                    Player randomPlayerPicked = pickRand.get((int)Math.floor((pickRand.size() - 1) * Math.random() + 0.5));

                    Card randCard = player.chooseCardFromHand();
                    System.out.println("\t" + "> " + player.getName() + ": " + randomPlayerPicked.getName() + ", do you have any " + randCard.rankToString() + "'s?");
                    Card checkForCard = randomPlayerPicked.rankInHand(randCard);
                    if (checkForCard != null) {
                        System.out.println("\t" + "> " + randomPlayerPicked.getName() + ": " + "fuck");
                        Player.swapCards(randomPlayerPicked, checkForCard, player);
                        player.checkHandForBook();
                        System.out.println("\t" + player.getName() + " took " + randomPlayerPicked.getName() + "'s " + checkForCard.toString() + "!");
                    } else {
                        System.out.println("\t" + "> " + randomPlayerPicked.getName() + ": " + "Go fish.");
                        DrawCardForPlayer(player);
                        player.checkHandForBook();
                        break;
                    }
                }
            }
        }
        System.out.println();

        if(isEmpty == players.size())
            gameEnded = true;
    }
}

