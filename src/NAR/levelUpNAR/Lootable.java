package NAR.levelUpNAR;

import java.util.Scanner;

public interface Lootable {
	public default boolean interaction(Player player) {
		System.out.print("Enter a Lootable: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		if(input.equals("y")) {
			player.increaseCoin();
			return true;
		}
		return false;
	}
}
