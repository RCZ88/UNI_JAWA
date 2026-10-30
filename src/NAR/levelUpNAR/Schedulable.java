package NAR.levelUpNAR;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Schedulable {
	public default boolean interaction(Player player) {
		System.out.print("Enter a Password: ");
		Scanner sc = new Scanner(System.in);
		try {
			int input = sc.nextInt();
			
		}catch(InputMismatchException e) {
			return false;
		}
		player.increaseCoin();
		return true;
	}
}
