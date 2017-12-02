
public class Pokemon {
	int hp;
	int attack;
	int defense;
	int speed;
	int specialAttack;
	int specialDefense;
	
	String name;
	
	public Pokemon() {
		// Empty Constructor
	}
	
	public Pokemon(String identification) {
		if (identification.equals("Venasaur")){//Venusaur	80	82	83	80	100
			this.hp = 80;
			this.attack = 82;
			this.defense = 83;
			this.speed = 80;
			this.specialAttack = 100;
			this.specialDefense = 100;
			this.name = "Venasaur";
		}
		
	}
	
}