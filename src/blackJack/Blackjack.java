/*
 *
 */
package blackJack;

import java.util.Scanner;


/**
 *
 * @author Mark
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to Big Papa Blackjack!");
        System.out.println("Must be legal age to play");
        System.out.println("Are you over the legal age? 1 for Yes, 2 for No");
        Scanner input = new Scanner(System.in);
        int choice1 = input.nextInt();

        if (choice1 == 1) {
            System.out.println("Legal Age verified -- Initializing Sweet and Cool game");
        } else if (choice1 == 2) {
            System.out.println("Sorry, you must be legal age to proceed, this program will now terminate.");
            System.exit(0);
        }
        int prizeCash = 100;
        CardDeck cardDeck = new CardDeck();
        CardDeck playerDeck = new CardDeck();
        CardDeck dealerDeck = new CardDeck();
        cardDeck.generateDeck();
        cardDeck.shuffle();

        while (prizeCash > 0) {
            System.out.println("Enter amount you wish to bet! You currently have $" + prizeCash);
            int playerBet = input.nextInt();
            boolean endRound = false;
            if (playerBet > prizeCash) {
                System.out.println("You cannot bet more than your maximum value.");
                break;
            }

            System.out.println("Dealing cards");
            playerDeck.draw(playerDeck);
            playerDeck.draw(playerDeck);

            dealerDeck.draw(playerDeck);
            dealerDeck.draw(playerDeck);
            while (true) {

                System.out.println("Your Hand:" + playerDeck.toString());
                System.out.println("Your hand is currently valued at: " + playerDeck.cardsValue());
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [hidden]");
                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = input.nextInt();
                if (response == 1) {
                    playerDeck.draw(playerDeck);
                    System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
             
                     if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        prizeCash -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                //Stand
                if (response == 2) {
                    break;
                }
            }
            System.out.println("Dealer Cards: " + dealerDeck.toString());

            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
                System.out.println("Dealer Wins!");
                prizeCash -= playerBet;
                endRound = true;
            }
            while (dealerDeck.cardsValue() < 17 && !endRound == false) {
                dealerDeck.draw(playerDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }

            System.out.println("Dealers hand value: " + dealerDeck.cardsValue());
            if (dealerDeck.cardsValue() > 21 && !endRound) {
                System.out.println("Dealer Busts. You win!");
                prizeCash += playerBet;
                endRound = true;
            }
            if (dealerDeck.cardsValue() == playerDeck.cardsValue() && !endRound) {
                System.out.println("Push.");
                endRound = true;
            }
            if (playerDeck.cardsValue() > dealerDeck.cardsValue() && !endRound) {
                System.out.println("You win the hand.");
                prizeCash += playerBet;
                endRound = true;
            } else if (endRound == false) {
                System.out.println("Dealer wins.");
                prizeCash -= playerBet;
            }

            playerDeck.moveAllToDeck(playerDeck);
            dealerDeck.moveAllToDeck(playerDeck);
            System.out.println("End of Hand.");
        }
        //Game is over
        System.out.println("Game over! You lost all your money. :(");
        System.exit(0);
    }

}
