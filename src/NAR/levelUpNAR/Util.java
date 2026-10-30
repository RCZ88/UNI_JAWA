package NAR.levelUpNAR;

import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in);
	public static int handleValidIntInput(int from, int to, String message) {
		int input = from - 1;
		while(input < from || input > to) {
			System.out.print(message);
			input = sc.nextInt();
		}
		return input;
	}
	public static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}
}
