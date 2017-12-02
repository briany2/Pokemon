
public class Pokemon {
	int hp;
	
	int attack;
	
	int defense;
	
	int speed;
	
	int specialAttack;
	
	int specialDefense;
	
	String name;
	
	String change;
	
	public Pokemon() {
		// Empty Constructor
	}
	
	public Pokemon(String identification) {
		if (identification.equals("Bulbasaur")){
			this.hp = 45;
			this.attack = 49;
			this.defense = 49;
			this.speed = 45;
			this.specialAttack = 65;
			this.specialDefense = 65;
			this.name = "Bulbasaur";
		}
		
	}
	
}