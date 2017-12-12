
/**
 * This is the Moves class which defines and implements what a Move is.
 * 
 * @author Brian Yu and Jason Harrold
 */
public class Moves {
	/**
	 * The move type of the move. This can be either Status, Special, or Physical
	 */
	private String moveType;
	/**
	 * The name of the move.
	 */
	private String name;
	/**
	 * The type of the move.
	 */
	private PokeType t;
	/**
	 * The initial damage of the move.
	 */
	private int damage;
	/**
	 * The accuracy of the move.
	 */
	private int accuracy;
	/**
	 * The condition that the move can set.
	 */
	private PokeCondition condition;
	/**
	 * The description of the move.
	 */
	private String description;

	/**
	 * Empty constructor.
	 */
	public Moves() {

	}

	/**
	 * This constructor defines what a move is.
	 * 
	 * @param moveType
	 * @param name
	 * @param t
	 * @param damage
	 * @param accuracy
	 * @param condition
	 * @param description
	 */
	public Moves(String moveType, String name, PokeType t, int damage, int accuracy, PokeCondition condition,
			String description) {
		this.moveType = moveType;
		this.name = name;
		this.t = t;
		this.damage = damage;
		this.accuracy = accuracy;
		this.condition = condition;
		this.description = description;
	}

	/**
	 * Gets the move type of the move. This can be Status, Special, or Physical.
	 * 
	 * @return the moveType.
	 */
	public String getMoveType() {
		return moveType;
	}

	/**
	 * Sets the move type of the move. This can be Status, Special, or Physical.
	 * 
	 * @param moveType
	 */
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	/**
	 * Gets the name of the move.
	 * 
	 * @return the move's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the move.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type of the move.
	 * 
	 * @return the move's type.
	 */
	public PokeType getT() {
		return t;
	}

	/**
	 * Sets the type of the move.
	 * 
	 * @param t
	 */
	public void setT(PokeType t) {
		this.t = t;
	}

	/**
	 * Gets the damage of the move.
	 * 
	 * @return the move's damage.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the damage of the move.
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Gets the accuracy of the move.
	 * 
	 * @return the move's accuracy.
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * Sets the accuracy of the move.
	 * 
	 * @param accuracy
	 */
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * Gets the condition that the move can set.
	 * 
	 * @return the condition
	 */
	public PokeCondition getCondition() {
		return condition;
	}

	/**
	 * Sets the condition that the move can set.
	 * 
	 * @param condition
	 */
	public void setCondition(PokeCondition condition) {
		this.condition = condition;
	}

	/**
	 * Gets the description of the move.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the move.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " | Type: " + t + " Damage:" + damage + " Accuracy:" + accuracy + " | " + description + "\n";
	}
}
