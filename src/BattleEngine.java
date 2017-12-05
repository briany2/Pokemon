import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BattleEngine {
	private static final String POKEDEX_FILE = "Pokedex.txt";
	private static final String MOVES_FILE = "Moves.txt";

	public static void main(String[] args) {
		System.out.println("Welcome to the Pokemon Battle Simulator!");
		Scanner kb = new Scanner(System.in);
		System.out.println("What is your name Player 1?");
		String p1name = kb.nextLine();
		System.out.println("What is your name Player 2?");
		String p2name = kb.nextLine();
		System.out.println("Generating random Pokemon for each player...");
		Scanner pokeInput;
		try {
			Player player1 = new Player(p1name);
			ArrayList<String> lines = new ArrayList<String>();
			pokeInput = new Scanner(new File(POKEDEX_FILE));
			while (pokeInput.hasNextLine()) {
				lines.add(pokeInput.nextLine());
			}
			ArrayList<Integer> ex = new ArrayList<Integer>();
			for (int i = 0; i < player1.getPokemonList().size() + 1; i++) {
				int val = generateRandom(0, lines.size() - 1, ex);
				ex.add(val);
				String str = lines.get(i);
				String[] a = str.split(";");
				Pokemon p = new Pokemon();
				p.setName(a[0]);
				p.setHp(Integer.parseInt(a[1]));
				p.setAttack(Integer.parseInt(a[2]));
				p.setDefense(Integer.parseInt(a[3]));
				p.setSpeed(Integer.parseInt(a[4]));
				p.setSpecialAttack(Integer.parseInt(a[5]));
				p.setSpecialDefense(Integer.parseInt(a[6]));
				p.setT1(PokeType.valueOf(a[7]));
				p.setT1(PokeType.valueOf(a[8]));
				player1.addPokemon(p);
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

	public static int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
	    Random rand = new Random();
	    int range = end - start + 1;
	    int random = rand.nextInt(range);
	    while(excludeRows.contains(random)) {
	        random = rand.nextInt(range);
	    }
	    return random;
	}
}
