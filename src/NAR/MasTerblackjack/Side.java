package NAR.MasTerblackjack;

import java.util.ArrayList;

public class Side {
    ArrayList<Card> cardsPut;
    int total;
    boolean bust;
    boolean natural;

    public Side(){
        cardsPut = new ArrayList<>();
        bust = false;
        natural = false;
    }

    public void updateTotal(){
        total = 0;
        for(Card card : cardsPut) {
//            System.out.println(card);
            if (!card.opened) {
                continue;
            }
            total += card.val;
            if (card.card.equals("A") && total + 11 > 21) {
                total -= 10;
            }
            if (total > 21) {
                bust = true;
            }
            if (total == 21) {
                natural = true;
            }
        }
    }
    public void printCardsInHand(){
        for(Card card : cardsPut){
            System.out.print(card + " ");
        }
        System.out.println("\nTotal: " + total);
    }


    public void receiveCard(Card card){
        cardsPut.add(card);
        updateTotal();
    }

    public void openClosedCard(){
        for(Card card : cardsPut){
            if(!card.opened){
                card.opened = true;
            }
        }
    }
}


