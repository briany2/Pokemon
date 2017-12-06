import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

	public Player(String name) {
		this.name = name;
	}

	public Player(String name, ArrayList<Pokemon> pokemonList) {
		this.name = name;
		this.pokemonList = pokemonList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Pokemon> getPokemonList() {
		return pokemonList;
	}

	public void setPokemonList(ArrayList<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}
	
	public void addPokemon(Pokemon p) {
		if (pokemonList.size() < 6) {
			pokemonList.add(p);
		}
	}
	
	public void printPokemonList() {
		System.out.println("List of " + this.name + "'s Pokemon.");
		for (int i = 0; i < pokemonList.size(); i++) {
			System.out.println(i + ". " + pokemonList.get(i).getName());
		}
	}
}
