import java.util.ArrayList;

/**
 * This is the Player Class which defines the Player Object.
 * 
 * @author Brian Yu and Jason Harrold
 */
public class Player {
	/**
	 * The name of the player.
	 */
	private String name;
	/**
	 * The List of Pokemon that the player has.
	 */
	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

	/**
	 * Default Player Constructor
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Player Constructor with full parameters.
	 * 
	 * @param name
	 * @param pokemonList
	 */
	public Player(String name, ArrayList<Pokemon> pokemonList) {
		this.name = name;
		this.pokemonList = pokemonList;
	}

	/**
	 * Gets the name of the player.
	 * 
	 * @return the player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the list of Pokemon that a player has.
	 * 
	 * @return the list of Pokemon.
	 */
	public ArrayList<Pokemon> getPokemonList() {
		return pokemonList;
	}

	/**
	 * Sets the list of Pokemon that a player has.
	 * 
	 * @param pokemonList
	 */
	public void setPokemonList(ArrayList<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}

	/**
	 * Adds a Pokemon to the player list.
	 * 
	 * @param p
	 */
	public void addPokemon(Pokemon p) {
		if (pokemonList.size() < 6) {
			pokemonList.add(p);
		}
	}

	/**
	 * Prints the list of Pokemon that a player has.
	 */
	public void printPokemonList() {
		System.out.println("List of " + this.name + "'s Pokemon.");
		for (int i = 0; i < pokemonList.size(); i++) {
			System.out.println(i + ". " + pokemonList.get(i).getName());
		}
	}
}
