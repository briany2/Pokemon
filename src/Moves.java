
public class Moves {
	private String moveType;
	private String name;
	private PokeType t;
	private int damage;
	private int accuracy;
	private PokeCondition condition;
	private String description;
	/**
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
		super();
		this.moveType = moveType;
		this.name = name;
		this.t = t;
		this.damage = damage;
		this.accuracy = accuracy;
		this.condition = condition;
		this.description = description;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PokeType getT() {
		return t;
	}
	public void setT(PokeType t) {
		this.t = t;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public PokeCondition getCondition() {
		return condition;
	}
	public void setCondition(PokeCondition condition) {
		this.condition = condition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
