import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
		Scanner moveInput;
		try {
			Player player1 = new Player(p1name);
			Player player2 = new Player(p2name);
			ArrayList<String> pokeLines = new ArrayList<String>();
			ArrayList<String> moveLines = new ArrayList<String>();

			pokeInput = new Scanner(new File(POKEDEX_FILE));
			while (pokeInput.hasNextLine()) {
				pokeLines.add(pokeInput.nextLine());
			}
			moveInput = new Scanner(new File(MOVES_FILE));
			while (moveInput.hasNextLine()) {
				moveLines.add(moveInput.nextLine());
			}
			ArrayList<Integer> ex = new ArrayList<Integer>();
			for (int i = 0; i < player1.getPokemonList().size() + 1; i++) {
				int val = generateRandom(0, pokeLines.size() - 1, ex);
				ex.add(val);
				String str = pokeLines.get(val);
				Pokemon p = new Pokemon();
				for (int j = val * 4; j < (1 + val) * 4; j++) {
					String[] move = moveLines.get(j).split(";|\n");
					Moves m = new Moves();
					m.setMoveType(move[0]);
					m.setName(move[1]);
					m.setT(PokeType.valueOf(move[2]));
					m.setDamage(Integer.parseInt(move[3]));
					m.setAccuracy(Integer.parseInt(move[4]));
					m.setCondition(PokeCondition.valueOf(move[5]));
					m.setDescription(move[6]);
					p.addMove(m);
				}
				String[] a = str.split(";");
				p.setName(a[0]);
				p.setHp(Integer.parseInt(a[1]));
				p.setAttack(Integer.parseInt(a[2]));
				p.setDefense(Integer.parseInt(a[3]));
				p.setSpeed(Integer.parseInt(a[4]));
				p.setSpecialAttack(Integer.parseInt(a[5]));
				p.setSpecialDefense(Integer.parseInt(a[6]));
				p.setT1(PokeType.valueOf(a[7]));
				p.setT2(PokeType.valueOf(a[8]));
				p.printMoves();
				player1.addPokemon(p);
			}
			ex.clear();
			for (int i = 0; i < player2.getPokemonList().size() + 1; i++) {
				int val = generateRandom(0, pokeLines.size() - 1, ex);
				ex.add(val);
				String str = pokeLines.get(val);
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
				p.setT2(PokeType.valueOf(a[8]));
				player2.addPokemon(p);
			}
			System.out.println(player1.getName() + player1.getPokemonList());
			System.out.println(player2.getName() + player2.getPokemonList());
			System.out.println("Sending out random Pokemon for each player...");
			System.out.println(player1.getName() + " has sent out " + player1.getPokemonList().get(0) + "!");
			System.out.println(player2.getName() + " has sent out " + player2.getPokemonList().get(0) + "!");

			boolean selected = true;
			while (selected) {
				printTurnOptions(player1);
				int choice = 0;
				try {
					choice = kb.nextInt();
				} catch (InputMismatchException exception) {
					System.out.println("Integers only, please.");
					kb.next();
				}
				printTurn(player1, choice);
				if (choice == 1) {
					int choice2 = 0;
					try {
						choice2 = kb.nextInt();
					} catch (InputMismatchException exception) {
						System.out.println("Integers only, please.");
						kb.next();
					}
				}
				if (choice == 2) {
					int choice2 = 0;
					try {
						choice2 = kb.nextInt();
					} catch (InputMismatchException exception) {
						System.out.println("Integers only, please.");
						kb.next();
					}
				}
			}

			printTurnOptions(player2);
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
		while (excludeRows.contains(random)) {
			random = rand.nextInt(range);
		}
		return random;
	}

	public static void printTurnOptions(Player play) {
		System.out.println(play.getName() + ", choose an action!");
		System.out.println("1. Fight");
		System.out.println("2. Switch");
		System.out.println("3. Print Pokemon List");
		System.out.println("4. Get Alive Pokemon");
		System.out.println("5. Get Current Pokemon");
	}

	public static boolean printTurn(Player play, int choice) {
		switch (choice) {
		case 1:
			printFightOptions(play);
			break;
		case 2:
			printSwitch(play);
			break;
		case 3:
			play.printPokemonList();
			break;
		case 4:
			play.printPokemonList();
			printAlive(play);
			break;
		case 5:
			getCurrentPokemon(play);
		default:
			break;
		}
		return true;
	}

	public static void printFightOptions(Player play) {
		System.out.println(play.getName() + ", choose a move for " + play.getPokemonList().get(0) + "!");
		play.getPokemonList().get(0).printMoves();
	}

	public static void printSwitch(Player play) {
		System.out.println(play.getName() + ", choose a Pokemon to send out!");
	}

	public static void printAlive(Player play) {
		int count = 0;
		for (int i = 0; i < play.getPokemonList().size(); i++) {
			if (play.getPokemonList().get(i).getHp() > 0) {
				System.out.println(play.getPokemonList().get(i));
				count++;
			}
		}
		System.out.println("You have " + count + " alive Pokemon.");
	}

	public static void getCurrentPokemon(Player play) {
		System.out.println(play.getPokemonList().get(0));
	}
}
