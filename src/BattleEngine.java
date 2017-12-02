import java.util.Scanner;

public class BattleEngine {
	public static void main(String[] args) {
		System.out.println("Welcome to the Pokemon Battle Simulator!");
		Scanner kb = new Scanner(System.in);
		System.out.println("What is your name Player 1?");
		String p1name = kb.nextLine();
		System.out.println("What is your name Player 2?");
		String p2name = kb.nextLine();
		System.out.println("");
	}
}
