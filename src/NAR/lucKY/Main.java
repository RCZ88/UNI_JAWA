package NAR.lucKY;

import java.util.Scanner;

public class Main {
    static Mine currentMine;
    static final Scanner sc = new Scanner(System.in);
    static void main() {
        currentMine = new Mine(8);
        currentMine.loadMineFromFile("");
        int row = sc.nextInt();
        int col = sc.nextInt();
        currentMine.doAction(col, row);
    }

    static Player getPlayer(){
        return currentMine.getPlayer();
    }
}
