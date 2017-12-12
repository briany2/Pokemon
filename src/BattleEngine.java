import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
				p.setTrainer(p1name);
				player1.addPokemon(p);
			}
			ex.clear();
			for (int i = 0; i < player2.getPokemonList().size() + 1; i++) {
				int val = generateRandom(0, pokeLines.size() - 1, ex);
				ex.add(val);
				Pokemon p = new Pokemon();
				String str = pokeLines.get(val);
				String[] a = str.split(";");
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
				p.setName(a[0]);
				p.setHp(Integer.parseInt(a[1]));
				p.setAttack(Integer.parseInt(a[2]));
				p.setDefense(Integer.parseInt(a[3]));
				p.setSpeed(Integer.parseInt(a[4]));
				p.setSpecialAttack(Integer.parseInt(a[5]));
				p.setSpecialDefense(Integer.parseInt(a[6]));
				p.setT1(PokeType.valueOf(a[7]));
				p.setT2(PokeType.valueOf(a[8]));
				p.setTrainer(p2name);
				player2.addPokemon(p);
			}
			System.out.println(player1.getName() + " has sent out " + player1.getPokemonList().get(0) + "!");
			System.out.println(player2.getName() + " has sent out " + player2.getPokemonList().get(0) + "!");

			boolean playerOneTurn = true;
			while (getAllAlive(player1) && getAllAlive(player2)) {
				int choice = 0;
				int fightChoice = -1;
				int switchChoice = -1;
				int fightChoice2 = -1;
				int switchChoice2 = -1;
				while (playerOneTurn) {
					printTurnOptions(player1);
					try {
						choice = kb.nextInt();
					} catch (InputMismatchException exception) {
						System.out.println("Integers only, please.");
						kb.next();
					}
					printTurn(player1, choice);
					if (choice == 1) { // fight choice, ends turn if fight goes through
						try {
							fightChoice = kb.nextInt();
							playerOneTurn = !playerOneTurn;
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
					if (choice == 2) {
						try {
							switchChoice = kb.nextInt();
							playerOneTurn = !playerOneTurn;
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
				}
				while (!playerOneTurn) {
					printTurnOptions(player2);
					try {
						choice = kb.nextInt();
					} catch (InputMismatchException exception) {
						System.out.println("Integers only, please.");
						kb.next();
					}
					printTurn(player2, choice);
					if (choice == 1) { // fight choice, ends turn if fight goes through
						try {
							fightChoice2 = kb.nextInt();
							playerOneTurn = !playerOneTurn;
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
					if (choice == 2) {
						try {
							switchChoice2 = kb.nextInt();
							playerOneTurn = !playerOneTurn;
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
				}
				if (switchChoice != -1) {
					printSendOut(player1, switchChoice);
				}
				if (switchChoice2 != -1) {
					printSendOut(player2, switchChoice2);
				}
				if (fightChoice == -1) {
					if (fightChoice2 == -1) {
						System.out.println();
					} else {
						printFight(player2, player1, fightChoice2);
						System.out.println();
					}
				}
				if (fightChoice2 == -1) {
					if (fightChoice == -1) {
						System.out.println();
					} else {
						printFight(player1, player2, fightChoice);
						System.out.println();
					}
				}
				if (player1.getPokemonList().get(0).getSpeed() > player2.getPokemonList().get(0).getSpeed()
						&& fightChoice != -1 && fightChoice2 != -1) {
					printFight(player1, player2, fightChoice);
					if (!checkFainted(player2)) {
						printFight(player2, player1, fightChoice2);
						System.out.println();
					}
				} else if (player1.getPokemonList().get(0).getSpeed() <= player2.getPokemonList().get(0).getSpeed()
						&& fightChoice != -1 && fightChoice2 != -1) {
					printFight(player2, player1, fightChoice2);
					if (!checkFainted(player1)) {
						printFight(player1, player2, fightChoice);
						System.out.println();
					}
				}
				if (checkFainted(player1) && getAllAlive(player1)) {
					while (true) {
						try {
							printSwitch(player1);
							int replace = kb.nextInt();
							if (printSendOut(player1, replace)) {
								break;
							}
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
				} else if (checkFainted(player2) && getAllAlive(player2)) {
					while (true) {
						try {
							printSwitch(player2);
							int replace = kb.nextInt();
							if (printSendOut(player2, replace)) {
								break;
							}
						} catch (InputMismatchException exception) {
							System.out.println("Integers only, please.");
							kb.next();
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			kb.close();
		}
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
		System.out.println("4. Get Current Pokemon HP and Status");
	}

	public static boolean printTurn(Player play, int choice) { // prints information about a given player's turn
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
			getCurrentPokemon(play);
			break;
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
		for (int i = 0; i < play.getPokemonList().size(); i++) {
			System.out.println(
					i + ". " + play.getPokemonList().get(i) + " | HP: " + play.getPokemonList().get(i).getHp());
		}
	}

	public static boolean getAllAlive(Player play) {
		int count = 0;
		for (int i = 0; i < play.getPokemonList().size(); i++) {
			if (play.getPokemonList().get(i).getHp() > 0) {
				count++;
			}
		}
		System.out.println(play.getName() + " has " + count + " alive Pokemon.");
		if (count == 0) {
			System.out.println(play.getName()+ " has no usable Pokemon!"); //want to declare the other player the winner here
			return false;
		}
		return true;
	}

	public static void getCurrentPokemon(Player play) {
		System.out.println(play.getPokemonList().get(0) + " | " + play.getPokemonList().get(0).getHp());
	}

	public static boolean printFight(Player play, Player play2, int choice) {
		if (choice >= play.getPokemonList().get(0).getMoveList().size()) { //checks for valid input
			System.out.println("Invalid Number.");
			return false;
		}
		//should have both players choose their move here
		//need to call status check here (for missed turns)
		//need to call priority to return the address of the faster pokemon, then set the other address to slower pokemon.
		
		System.out.println(play.getName() + "'s " +play.getPokemonList().get(0) + " used "
				+ play.getPokemonList().get(0).getMoveList().get(choice).getName() + " on "
				+ play2.getPokemonList().get(0) + "!");
		// call damage control's damage message method for more string information
		DamageControl d = new DamageControl(play.getPokemonList().get(0), play2.getPokemonList().get(0), // constructor
																											// call
				play.getPokemonList().get(0).getMoveList().get(choice));
																							
		int getDamage = d.getDamage(play.getPokemonList().get(0), play2.getPokemonList().get(0), 
				play.getPokemonList().get(0).getMoveList().get(choice));

		int newHp = play2.getPokemonList().get(0).getHp() - getDamage; //need to call status check after this line also

		play2.getPokemonList().get(0).setHp(newHp);
		if (play2.getPokemonList().get(0).getHp() < 0) {
			play2.getPokemonList().get(0).setHp(0);
			System.out.println(play2.getPokemonList().get(0) + " has fainted!"); //i want to add a trainer name here
		}
		return true;
	}

	public static boolean printSendOut(Player play, int choice2) {
		if (choice2 >= play.getPokemonList().size()) {
			System.out.println("Invalid Number.");
			return false;
		}
		if (play.getPokemonList().get(choice2).getHp() <= 0) {
			System.out.println(play.getPokemonList().get(choice2) + " can not battle, choose another Pokemon.");
			return false;
		} else {
			System.out.println(play.getPokemonList().get(0) + ", come back!");
			System.out.println(play.getName() + " sends out " + play.getPokemonList().get(choice2).getName()+"\n");//added spacing
			Collections.swap(play.getPokemonList(), 0, choice2);
			return true;
		}
	}

	public static boolean checkFainted(Player play) {
		if (play.getPokemonList().get(0).getHp() <= 0) { // changed to less than or equal to 0
			return true;
		}
		return false;
	}
}
