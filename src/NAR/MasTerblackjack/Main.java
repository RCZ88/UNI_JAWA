package NAR.MasTerblackjack;

public class Main {
    static void main() {
        Game game = new Game("src/NAR/MasTerblackjack/blackjack_cards.csv");
        game.game();
    }
}