package gofish_assn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GoFishGame {
    /**
     * List of profanities a player says when their card is discovered.
     */
    private static final String[] PROFANITIES = {"fuck", "shit", "asshole"};
    /**
     * List of players playing.
     */
    public ArrayList<Player> players;
    /**
     * The game's deck.
     */
    private Deck deck;
    /**
     * Amount of rounds that have happened.
     */
    private int roundNum;
    /**
     * Output file to write results of game to.
     */
    private File outputFile;
    /**
     * File writer.
     */
    public PrintWriter output;

    /**
     * Game status.
     */
    public boolean gameEnded;

    /**
     * Makes a new Go Fish game.
     * @param names String array of the players' names (first player is first in the array).
     */
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
        roundNum = 0;

        outputFile = new File("GoFish_results.txt");
        try{
            output = new PrintWriter(outputFile);
        } catch (FileNotFoundException e) {
            output = null;
        }
    }

    /**
     * Draw a card from the top of the deck and add it to a player's hand.
     * @param player The player to draw the card for.
     */
    private void DrawCardForPlayer(Player player){
        if (deck.getSize() > 0) {
            Card draw = deck.dealCard();
            player.addCardToHand(draw);
            //System.out.println("\t" + player.getName() + " drew " + draw.toString() + ".");
            output.println("\t" + player.getName() + " drew " + draw.toString() + ".");
        } else {
            //System.out.println("\t" + player.getName() + " can't draw from an empty deck!");
            output.println("\t" + player.getName() + " can't draw from an empty deck!");
        }
    }

    /**
     * Execute a round of the game (all players take turns).
     */
    public void DoRound(){
        if(gameEnded)
            return;

        roundNum++;
        //System.out.println("ROUND #" + roundNum + " (" + deck.getSize() + ")");
        output.println("ROUND #" + roundNum + " (" + deck.getSize() + ")");
        int isEmpty = 0;

        for (Player player: players) {
            String extra = "";
            if(player.getHandSize() == 0 && deck.getSize() == 0)
                extra = extra +" (FINISHED)";
            //System.out.println("\t" + player.getName().toUpperCase() + extra + "\n\t\tHAND = " + "(" + player.getHandSize() + ") " + player.handToString() + "\n\t\tBOOK = " + "(" + player.getBookSize() + ") " + player.bookToString());
            output.println("\t" + player.getName().toUpperCase() + extra + "\n\t\tHAND = " + "(" + player.getHandSize() + ") " + player.handToString() + "\n\t\tBOOK = " + "(" + player.getBookSize() + ") " + player.bookToString());
        }
        //System.out.println("--------");
        output.println("--------");
        for (Player player: players) {
            if(player.getHandSize() == 0){
                if(deck.getSize() > 0){
                    //System.out.println(player.getName().toUpperCase() + "'S TURN");
                    output.println(player.getName().toUpperCase() + "'S TURN");
                    DrawCardForPlayer(player);
                } else {
                    isEmpty++;
                }
            } else {
                //System.out.println(player.getName().toUpperCase() + "'S TURN");
                output.println(player.getName().toUpperCase() + "'S TURN");
                while(true) { // while loop to account for a player taking multiple turns.
                    if(player.getHandSize() == 0) {
                        //System.out.println("\t" + player.getName() + "'s hand is empty!");
                        output.println("\t" + player.getName() + "'s hand is empty!");
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
                    //System.out.println("\t" + "> " + player.getName() + ": " + randomPlayerPicked.getName() + ", do you have any " + randCard.rankToString() + "'s?");
                    output.println("\t" + "> " + player.getName() + ": " + randomPlayerPicked.getName() + ", do you have any " + randCard.rankToString() + "'s?");
                    Card checkForCard = randomPlayerPicked.rankInHand(randCard);
                    if (checkForCard != null) {
                        //System.out.println("\t" + "> " + randomPlayerPicked.getName() + ": " + PROFANITIES[(int)Math.floor((PROFANITIES.length - 1) * Math.random() + 0.5)]);
                        output.println("\t" + "> " + randomPlayerPicked.getName() + ": " + PROFANITIES[(int)Math.floor((PROFANITIES.length - 1) * Math.random() + 0.5)]);
                        Player.swapCards(randomPlayerPicked, checkForCard, player);
                        //System.out.println("\t" + player.getName() + " took " + randomPlayerPicked.getName() + "'s " + checkForCard.toString() + "!");
                        output.println("\t" + player.getName() + " took " + randomPlayerPicked.getName() + "'s " + checkForCard.toString() + "!");
                        player.checkHandForBook(output);
                    } else {
                        //System.out.println("\t" + "> " + randomPlayerPicked.getName() + ": " + "Go fish.");
                        output.println("\t" + "> " + randomPlayerPicked.getName() + ": " + "Go fish.");
                        DrawCardForPlayer(player);
                        player.checkHandForBook(output);
                        break; // wrong guess = turn is over.
                    }
                }
            }
        }
        //System.out.println();
        output.println();

        if(isEmpty == players.size())
            gameEnded = true;
    }
}

