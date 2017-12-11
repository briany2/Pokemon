import java.util.ArrayList;

public class Pokemon {
	private String name;
	private String trainer;

	private int hp;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;

	private boolean willLoseNextTurn; // boolean that tells my conditions whether or not this pokemon is set to lose
										// it's next turn.

	private PokeType t1;
	private PokeType t2;

	private PokeCondition condition;
	private PokeCondition initialCondition; // need this to be allowed.

	private ArrayList<Moves> moveList = new ArrayList<Moves>();

	public Pokemon() {

	}

	/**
	 * @param name
	 * @param description
	 * @param hp
	 * @param attack
	 * @param defense
	 * @param specialAttack
	 * @param specialDefense
	 * @param speed
	 * @param t1
	 * @param t2
	 * @param condition
	 */
	public Pokemon(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			PokeType t1, PokeType t2, PokeCondition p1) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.t1 = t1;
		this.t2 = t2;
		this.condition = p1;
		this.initialCondition = p1; // i need this to be set to NONE so i can reset an afflicted pokemon's condition
									// to NONE when it's condition wares off.
	}

	public boolean getWillLoseNextTurn() { // boolean that tells my condition methods if this pokemon is set to lose
											// it's next turn.
		return this.willLoseNextTurn;
	}

	public void setWillLoseNextTurn(boolean trueOrFalse) {
		this.willLoseNextTurn = trueOrFalse;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainerName) {
		this.trainer = trainerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public PokeType getT1() {
		return t1;
	}

	public void setT1(PokeType t1) {
		this.t1 = t1;
	}

	public PokeType getT2() {
		return t2;
	}

	public void setT2(PokeType t2) {
		this.t2 = t2;
	}

	public ArrayList<Moves> getMoveList() {
		return moveList;
	}

	public void setMoveList(ArrayList<Moves> moveList) {
		this.moveList = moveList;
	}

	public void addMove(Moves m) {
		if (moveList.size() < 4) {
			moveList.add(m);
		}
	}

	public void printMoves() {
		for (int i = 0; i < moveList.size(); i++) {
			System.out.print(i + ". " + this.getMoveList().get(i).toString());
		}
	}

	public PokeCondition getCondition() {
		return condition;
	}

	public PokeCondition getInitialCondition() {
		return initialCondition;
	}

	public void setCondition(PokeCondition condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return name;
	}

}