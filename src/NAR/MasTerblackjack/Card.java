package NAR.MasTerblackjack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Card {
    String card;
    String suit;
    int val;
    boolean opened;

    public Card(String card, int val, String suit) {
        this.card = card;
        this.val = val;
        this.suit = suit;
        this.opened = false;
    }

    public static ArrayList<Card> loadCardsFromFile(String filePath, int deckCount){
        Path path = Path.of(filePath);
        ArrayList<Card>cards = new ArrayList<>();
        try {
            for(int i = 0 ; i <deckCount; i++){
                List<String> lines= Files.readAllLines(path);
                lines.removeFirst();
                for(String line : lines){
//                    System.out.println(line);
                    String[] items = line.split(",");
                    Card c = new Card(items[0], Integer.parseInt(items[1]), items[2]);
                    cards.add(c);
                }
            }
        } catch (IOException e) {
            System.out.printf("Failed to Read Card Data!\n%s\n", e.getMessage());
        }
        return cards;
    }

    @Override
    public String toString(){
        if(!opened) return String.format("[??] -> ??");
        return String.format("[%s%c] -> %d", card, suit.charAt(0), val);
    }
}

