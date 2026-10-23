package NAR.MasTerblackjack;

import java.util.*;

public class Game {
    static Scanner sc = new Scanner(System.in);

    Side player;
    Side dealer;
    ArrayList<Card> allCards;
    String filePath;

    public Game(String filePath){
        this.filePath = filePath;
        player = new Side();
        dealer = new Side();
    }

    public void initGame(){
        System.out.println("INITIALIZING GAME");
        allCards = Card.loadCardsFromFile(this.filePath, 4);
        Collections.shuffle(allCards);
        player.receiveCard(getCard(false));
        player.receiveCard(getCard(false));
        dealer.receiveCard(getCard(false));
        dealer.receiveCard(getCard(true));
    }


    public void game(){
        initGame();
        System.out.println("DONE");
        printGameCards();
        if(player.natural){
            System.out.println("NATURAL! YOU WIN!");
            return;
        }else if(dealer.natural){
            System.out.println("Dealer got NATURAL! YOU LOSE!");
            return;
        }

        while(!player.bust && !dealer.bust && !player.natural && !dealer.natural){
            System.out.print("Hit or Stand [H/S]: ");
            String input = sc.next();
            while(input.length() != 1 || input.toLowerCase().charAt(0) != 'h' && input.toLowerCase().charAt(0) != 's'){
                System.out.println("INVALID INPUT!");
                System.out.print("Hit or Stand [H/S]: ");
                input = sc.next();
            }
            char inputC = input.toLowerCase().charAt(0);
            if(inputC == 'h'){
                Card newCard = getCard(false);
                System.out.println("YOU CHOSE HIT! You Receive: " + newCard);
                player.receiveCard(newCard);
            }
            printGameCards();
            dealer.openClosedCard();
            printGameCards();
            if(player.bust || dealer.bust || player.natural || dealer.natural){
                break;
            }
            if(dealer.total > player.total){
                System.out.printf("DEALER: %d > YOU: %d\nYOU LOSE!\n", dealer.total, player.total);
                break;
            }
            if(dealer.total == player.total){
                System.out.println("ITS A DRAW, THE GAME WILL CONTINUE!");
                continue;
            }
            System.out.printf("DEALER: %d < YOU: %d\nYOU WIN!\n", dealer.total, player.total);
            break;
        }

        if(player.bust){
            System.out.println("You BUST! You Lose!");
        }else if(dealer.bust){
            System.out.println("Dealer BUST! You WIN!");
        }
    }

    public Card getCard(boolean close){
        Card card = allCards.getLast();
        card.opened = !close;
        allCards.removeLast();
        return card;
    }

    public void printGameCards(){
        System.out.println("==================");
        System.out.println("DEALER's CARDS: ");
        dealer.printCardsInHand();
        System.out.println();
        System.out.println("YOUR CARDS");
        player.printCardsInHand();
        System.out.println("==================");
    }
}
