import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BattleEngine {
	private static final String POKEDEX_FILE = "Pokedex.txt";
	private static final String MOVES_FILE = "Moves.txt";
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Pokemon Battle Simulator!");
		Scanner kb = new Scanner(System.in);
		System.out.println("What is your name Player 1?");
		//String p1name = kb.nextLine();
		System.out.println("What is your name Player 2?");
		//String p2name = kb.nextLine();
		System.out.println("Generating random Pokemon for each player...");
		Scanner pokeInput;
		try {
			pokeInput = new Scanner(new File(POKEDEX_FILE));
			pokeInput.useDelimiter(";|\n");
			while(pokeInput.hasNext()) {
				Pokemon p1 = new Pokemon();
				p1.setName(pokeInput.next());
				p1.setHp(pokeInput.nextInt());
				p1.setAttack(pokeInput.nextInt());
				p1.setDefense(pokeInput.nextInt());
				p1.setSpecialAttack(pokeInput.nextInt());
				p1.setSpecialDefense(pokeInput.nextInt());
				p1.setSpeed(pokeInput.nextInt());
				p1.setT1(PokeType.valueOf(pokeInput.next()));
				p1.setT2(PokeType.valueOf(pokeInput.next()));
				System.out.println(p1);
				if (pokeInput.hasNext()) {
					pokeInput.next();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			kb.close();
		}
		
		// Player p1 = new Player(p1name, new Pokemon(), new Pokemon(), new Pokemon());
		// Player p2 = new Player(p2name, new Pokemon(), new Pokemon(), new Pokemon());

	}
}
