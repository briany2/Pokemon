
public class Player {
	private String name;
	private Pokemon[] pokemonList = new Pokemon[6];

	public Player() {

	}

	public Player(String name, Pokemon[] pokemonList) {
		this.name = name;
		this.pokemonList = pokemonList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pokemon[] getPokemonList() {
		return pokemonList;
	}

	public void setPokemonList(Pokemon[] pokemonList) {
		this.pokemonList = pokemonList;
	}
}
