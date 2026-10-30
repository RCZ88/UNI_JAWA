package NAR.levelUpNAR;

import java.util.Scanner;

public interface Hackable {
	public default boolean interaction(Player player) {
		System.out.print("Enter a Password: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		if(input.equals("admin") || input.equals("1234")) {
			player.increaseCoin();
			return true;
		}
		return false;
	}
}
