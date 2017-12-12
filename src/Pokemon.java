import java.util.ArrayList;

/**
 * This is the Pokemon Class which is used to define what a Pokemon is.
 * 
 * @author Brian Yu and Jason Harrold
 */
public class Pokemon {
	/**
	 * The name of the Pokemon.
	 */
	private String name;
	/**
	 * The name of the Trainer.
	 */
	private String trainer;

	/**
	 * The HP of the Pokemon.
	 */
	private int hp;
	/**
	 * The Attack of the Pokemon.
	 */
	private int attack;
	/**
	 * The Defense of the Pokemon.
	 */
	private int defense;
	/**
	 * The Special Attack of the Pokemon.
	 */
	private int specialAttack;
	/**
	 * The Special Defense of the Pokemon.
	 */
	private int specialDefense;
	/**
	 * The Speed of the Pokemon
	 */
	private int speed;

	/**
	 * The First Type of the Pokemon.
	 */
	private PokeType t1;
	/**
	 * The Second Type of the Pokemon.
	 */
	private PokeType t2;

	/**
	 * An arraylist of the Pokemon's moves.
	 */
	private ArrayList<Moves> moveList = new ArrayList<Moves>();

	/**
	 * Empty Constructor.
	 */
	public Pokemon() {

	}

	/**
	 * Pokemon Constructor with all filled conditions.
	 * 
	 * @param name
	 * @param hp
	 * @param attack
	 * @param defense
	 * @param specialAttack
	 * @param specialDefense
	 * @param speed
	 * @param t1
	 * @param t2
	 * @param p1
	 */
	public Pokemon(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			PokeType t1, PokeType t2) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.t1 = t1;
		this.t2 = t2;
	}

	/**
	 * Gets the name of the Pokemon.
	 * 
	 * @return the Pokemon's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Pokemon.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the HP of the Pokemon.
	 * 
	 * @return the Pokemon's HP.
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Sets the HP of the Pokemon.
	 * 
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Gets the Attack of the Pokemon.
	 * 
	 * @return the Pokemon's Attack.
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Sets the Attack of the Pokemon.
	 * 
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Gets the Defense of the Pokemon.
	 * 
	 * @return the Pokemon's Defense.
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * Sets the Defense of the Pokemon.
	 * 
	 * @param defense
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * Gets the Special Attack of the Pokemon.
	 * 
	 * @return the Pokemon's Special Attack
	 */
	public int getSpecialAttack() {
		return specialAttack;
	}

	/**
	 * Sets the Special Attack of the Pokemon.
	 * 
	 * @param specialAttack
	 *            the Pokemon's Special Attack.
	 */
	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	/**
	 * Gets the Special Defense of the Pokemon.
	 * 
	 * @return the Pokemon's Special Defense.
	 */
	public int getSpecialDefense() {
		return specialDefense;
	}

	/**
	 * Sets the Special Defense of the Pokemon.
	 * 
	 * @param specialDefense
	 */
	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	/**
	 * Gets the Speed of the Pokemon.
	 * 
	 * @return the Pokemon's Speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the Pokemon's speed.
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Gets the Pokemon's first type.
	 * 
	 * @return the Pokemon's first type.
	 */
	public PokeType getT1() {
		return t1;
	}

	/**
	 * Sets the Pokemon's first type.
	 * 
	 * @param t1
	 */
	public void setT1(PokeType t1) {
		this.t1 = t1;
	}

	/**
	 * Gets the Pokemon's second type.
	 * 
	 * @return the Pokemon's second type.
	 */
	public PokeType getT2() {
		return t2;
	}

	/**
	 * Sets the Pokemon's second type.
	 * 
	 * @param t2
	 */
	public void setT2(PokeType t2) {
		this.t2 = t2;
	}

	/**
	 * Gets an array list of moves that the Pokemon has.
	 * 
	 * @return the moveList
	 */
	public ArrayList<Moves> getMoveList() {
		return moveList;
	}

	/**
	 * Sets the list of moves that a Pokemon has.
	 * 
	 * @param moveList
	 */
	public void setMoveList(ArrayList<Moves> moveList) {
		this.moveList = moveList;
	}

	/**
	 * Adds a move to the Pokemon's array list of moves.
	 * 
	 * @param m
	 */
	public void addMove(Moves m) {
		if (moveList.size() < 4) {
			moveList.add(m);
		}
	}

	/**
	 * Prints the list of moves that a Pokemon has.
	 */
	public void printMoves() {
		for (int i = 0; i < moveList.size(); i++) {
			System.out.print(i + ". " + this.getMoveList().get(i).toString());
		}
	}

	/**
	 * Gets the name of the Trainer of the Pokemon.
	 * 
	 * @return the Trainer's name.
	 */
	public String getTrainer() {
		return trainer;
	}

	/**
	 * Sets the name of the Trainer of the Pokemon.
	 * 
	 * @param trainerName
	 */
	public void setTrainer(String trainerName) {
		this.trainer = trainerName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}