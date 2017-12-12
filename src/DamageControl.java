import java.util.Random;

public class DamageControl {
	public Pokemon attacker;
	public Pokemon defender;
	public Moves move;
	Random rand = new Random();
	public boolean fightEnded;
	public int afflictionCount = 0;

	DamageControl(Pokemon attacker, Pokemon defender, Moves move) {
		this.attacker = attacker;
		this.defender = defender;
		this.move = move;
		fightEnded = false;
	}

	public int dealPhysicalDamage(Pokemon attacker, Pokemon defender, Moves move) {
		int n = rand.nextInt(100 + 1);
		if (n > move.getAccuracy()) {
			System.out.println(attacker.getTrainer() + "'s " + attacker + "'s attack missed!");
			return 0;
		}
		int damage = ((18 * move.getDamage() * attacker.getAttack() / defender.getDefense()) / 50 + 2);
		if (n < 10) {
			System.out.println("Critical Hit!");
			damage = damage * 2; //changed this value to 2 from 3 because of balance and how it actually works
		}
		int effective = isEffective(move, defender);
		if (effective == -1) {
			System.out.println("It's not very effective...");
			damage = damage / 2;
		} else if (effective == 1) {
			System.out.println("It's super effective!!!");
			damage = damage * 2;
		} else if (effective == 2) {
			System.out.println(defender.getTrainer() + "'s " +defender.getName() + " is immune! It doesn't take damage!");
			damage = 0;
		}
		this.fightEnded = true; //sets the fightended boolean to true for the next reading of status check
		return damage;
	}

	public int dealSpecialDamage(Pokemon attacker, Pokemon defender, Moves move) {
		int n = rand.nextInt(100 + 1);
		if (n > move.getAccuracy()) {
			System.out.println(attacker.getTrainer() + "'s " + attacker + "'s attack missed!");
			return 0;
		}
		int damage = ((18 * move.getDamage() * attacker.getSpecialAttack() / defender.getSpecialDefense()) / 50 + 2);
		if (n < 10) {
			System.out.println("Critical Hit!");
			damage = damage * 3;
		}
		int effective = isEffective(move, defender);
		if (effective == -1) {
			System.out.println("It's not very effective...");
			damage = damage / 2;
		} else if (effective == 1) {
			System.out.println("It's super effective!!!");
			damage = damage * 2;
		} else if (effective == 2) {
			System.out.println(
					defender.getTrainer() + "'s " + defender.getName() + " is immune to " + move.getT() + "type moves! It doesn't take damage!");
			damage = 0;
		}
		defender.setHp(defender.getHp() - damage);
		System.out.println(defender.getTrainer() + "'s " + defender.getName() + " took " +damage + " damage!\n");
		this.fightEnded = true; //sets the fightended boolean to true for the next reading of status check
		return damage;
	}

	public int tryStatusMove(Pokemon attacker, Pokemon defender, Moves move) {
		if (hitCheck(attacker, defender, move)) {
			if (defender.getCondition() == PokeCondition.NONE) { // applying status to a pokemon without a current status
																	// 
				defender.setCondition(move.getCondition()); // setting new condition to afflicted pokemon
				System.out.println(defender.getName() + " has been " + move.getCondition() + "ed!");
				return 0; // returns 0 dmg
			} else {
				System.out.println(defender.getName() + " has already been afflicted with " + defender.getCondition()+ "!");// trying
																								
				this.fightEnded = true; //sets the fightended boolean to true for the next reading of status check
				return 0;
			}

		} else {
			System.out.print(attacker + "'s attack missed!");
			return 0;
		}
	}

	public boolean hitCheck(Pokemon attacker, Pokemon defender, Moves move) { // accuracy calculator
		int randomNumber = (int) (Math.random() * 100);
		if (randomNumber <= move.getAccuracy()) {
			return true;
		} else {
			return false;
		}
	}

	public int getDamage(Pokemon attacker, Pokemon defender, Moves move) { // calculates damage and status changes,
																			// potentially changes defender's
																			// stats/hp/etc. has methods that print
																			// appropriate narration.
		if (move.getMoveType().equals("PHYSICAL")) {
			return dealPhysicalDamage(attacker, defender, move);
		} else if (move.getMoveType().equals("SPECIAL")) {
			return dealSpecialDamage(attacker, defender, move);
		} else {
			return tryStatusMove(attacker, defender, move);
		}
	}

	public void statusCheck() { // calls condition-specific methods for each condition for each Pokemon, needs to go into battleengine
		PokeCondition attackCondition = attacker.getCondition();
		PokeCondition defendCondition = defender.getCondition();
		switch (attackCondition) {
		case BURN:
			burnConsequences(attacker);
			break;

		case SLEEP:
			sleepConsequences(attacker);
			break;

		case POISON:
			poisonConsequences(attacker);
			break;

		case PARALYSIS:
			paralysisConsequences(attacker);
			break;

		case FREEZE:
			freezeConsequences(attacker);
			break;

		case FLINCH:
			flinchConsequences(attacker);
			break;

		case NONE:
			noConsequences(attacker);
			break;

		default:
			break;
		}
		switch (defendCondition) {
		case BURN:
			burnConsequences(defender);
			break;

		case SLEEP:
			sleepConsequences(defender);
			break;

		case POISON:
			poisonConsequences(defender);
			break;

		case PARALYSIS:
			paralysisConsequences(defender);
			break;

		case FREEZE:
			freezeConsequences(defender);
			break;

		case FLINCH:
			flinchConsequences(defender);
			break;

		case NONE:
			noConsequences(defender);
			break;

		default:
			break;
		}

	}

	public Pokemon priorityCheck(Pokemon pokemon1, Pokemon pokemon2) { // to be used in battle engine to determine the faster pokemon, and determine attack order
		//method for checking if they
	
		if (pokemon1.getWillLoseNextTurn()) {  //how do i select a certain condition to test if it equals the pokemon's condition?
			lostTurnReason(pokemon1);
		}
		if (pokemon2.getWillLoseNextTurn()) {
			lostTurnReason(pokemon2);
		}
		if (pokemon1.getWillLoseNextTurn() && pokemon2.getWillLoseNextTurn()) {
			pokemon1.setWillLoseNextTurn(false);
			pokemon2.setWillLoseNextTurn(true);
			return null;
		}
		
		if (pokemon1.getWillLoseNextTurn()) {
			return pokemon2;
		}
		if (pokemon2.getWillLoseNextTurn()) {
			return pokemon1;
		}
		
		if (pokemon1.getSpeed() >= pokemon2.getSpeed()) { //case where both are normal
			return pokemon1;
		} else {
			return pokemon2;
		}
		
	}
	
	public void lostTurnReason(Pokemon afflicted) { //returns a string describing why a pokemon lost it's turn based on status.
		if (afflicted.getCondition() == PokeCondition.SLEEP) {
			System.out.print(afflicted.getTrainer()+"'s "+afflicted.getName()+" is fast asleep.");
			return;
		}
		if (afflicted.getCondition() == PokeCondition.PARALYSIS) {
			System.out.print(afflicted.getTrainer()+"'s "+afflicted.getName()+" is paralyzed! It can't move!");
			return;
		}
		if (afflicted.getCondition() == PokeCondition.FREEZE) {
			System.out.print(afflicted.getTrainer()+"'s "+afflicted.getName()+" is frozen solid!");
			return;
		} else {
			System.out.print(afflicted.getTrainer()+"'s "+afflicted.getName()+" flinched!");
			return;
		}
		
		
	}

	public void burnConsequences(Pokemon afflicted) { // target has burn, will burn until death as burn heals aren't in
														// the game.
		if (fightEnded == false) {
			return;
		} else {
			// burn damage
			afflicted.setHp(afflicted.getHp()-10);
			System.out.println(afflicted.getTrainer() + "'s " + afflicted.getName() + " is hurt by it's burn!");
			return;
		}
	}

	public void sleepConsequences(Pokemon afflicted) { // needs to disable attacking for a turn, then reallow attacking

		if (this.afflictionCount == 2) {
			// reenable turn
			afflicted.setCondition(afflicted.getInitialCondition());
			afflicted.setWillLoseNextTurn(false);
			System.out.println(afflicted.getTrainer() + "'s " + afflicted.getName() + " woke up!");
			this.afflictionCount = 0;
			return;
		} else if (this.afflictionCount == 1) {
			int random = (int)Math.random()*100;
			if(random > 66){
				afflicted.setCondition(afflicted.getInitialCondition());
				afflicted.setWillLoseNextTurn(false);
				System.out.println(afflicted.getTrainer() + "'s " + afflicted.getName() + " woke up!");
				this.afflictionCount = 0;
				return;
			}
		} else {
			// disable turn
			afflicted.setWillLoseNextTurn(true); //should print that the afflicted is asleep.
			afflictionCount += 1;
			return;
		}

	}

	public void poisonConsequences(Pokemon afflicted) { // will be poisoned until death. switching out should stop the
														// damage, like in the games.
		if (fightEnded == false) {
			return;
		} else {
			// poison damage
			afflicted.setHp(afflicted.getHp()-15);
			System.out.println(afflicted.getTrainer() + "'s " + afflicted.getName() + " is hurt by poison!");
			return;
		}
	}

	public void paralysisConsequences(Pokemon afflicted) {
		if (fightEnded == false) {
			afflicted.setSpeed(afflicted.getSpeed()/2);//lowers speed
			//disable turn on a % chance 
			int random = (int)Math.random()*100;
			if (random <=25) {
				afflicted.setWillLoseNextTurn(true);
			}
			return;
		} else {
			return;
		}

	}

	public void freezeConsequences(Pokemon afflicted) { 
		if (fightEnded == false) {
			afflicted.setWillLoseNextTurn(true); //turns on will lose next turn for the priority checker
			return;
		} else {
			afflicted.setWillLoseNextTurn(false); //reenable turn at the second status check
			
			afflicted.setCondition(afflicted.getInitialCondition()); //remove freeze from pokemon
			System.out.println(afflicted.getTrainer() + "'s " + afflicted.getName() + " thawed out!");
		}
	}

	public void flinchConsequences(Pokemon afflicted) { //should be ok
		if (fightEnded == false) {
			afflicted.setWillLoseNextTurn(true); //will prevent pokemon from attacking on it's turn
			System.out.println(afflicted.getTrainer() + "'s "+afflicted.getName() + "flinched!");
			return;
		} else {
			afflicted.setWillLoseNextTurn(false); //reenable turns for afflicted pokemon
			afflicted.setCondition(afflicted.getInitialCondition());
		}
		

	}

	public void noConsequences(Pokemon unafflicted) {
		return;
	}

	public int isEffective(Moves attacker, Pokemon defender) { // -1 weak, 0 is no bonus, +1 is supereffective, +2 if
																// immune.
		PokeType attackType = attacker.getT();
		PokeType defendType = defender.getT1();
		switch (attackType) {
		case NORMAL:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 0;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 0;

			case ROCK:
				return -1;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return 0;

			case GHOST:
				return 2;
			}

		case FIRE:
			switch (defendType) {
			case NORMAL:
				return 0;
			case FIRE:
				return -1;

			case FIGHTING:
				return 0;

			case WATER:
				return -1;

			case FLYING:
				return 0;

			case GRASS:
				return 1;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 0;

			case ROCK:
				return -1;

			case ICE:
				return 1;

			case BUG:
				return 1;

			case DRAGON:
				return -1;

			case GHOST:
				return 0;
			}

		case FIGHTING:
			switch (defendType) {
			case NORMAL:
				return 1;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return -1;

			case GRASS:
				return 0;

			case POISON:
				return -1;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return -1;

			case ROCK:
				return 1;

			case ICE:
				return 1;

			case BUG:
				return -1;

			case DRAGON:
				return 0;

			case GHOST:
				return 2;
			}

		case WATER:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 1;

			case FIGHTING:
				return 0;

			case WATER:
				return -1;

			case FLYING:

			case GRASS:
				return -1;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 1;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 1;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return -1;

			case GHOST:
				return 0;
			}

		case FLYING:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 1;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 1;

			case POISON:
				return 0;

			case ELECTRIC:
				return -1;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 0;

			case ROCK:
				return -1;

			case ICE:
				return 0;

			case BUG:
				return 1;

			case DRAGON:
				return 0;

			case GHOST:
				return 0;
			}

		case GRASS:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return -1;

			case FIGHTING:
				return 0;

			case WATER:
				return 1;

			case FLYING:
				return -1;

			case GRASS:
				return -1;

			case POISON:
				return -1;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 1;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 1;

			case ICE:
				return 0;

			case BUG:
				return -1;

			case DRAGON:
				return -1;

			case GHOST:
				return 0;
			}

		case POISON:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 1;

			case POISON:
				return -1;

			case ELECTRIC:
				return 0;

			case GROUND:
				return -1;

			case PSYCHIC:
				return 0;

			case ROCK:
				return -1;

			case ICE:
				return 0;

			case BUG:
				return 1;

			case DRAGON:
				return 0;

			case GHOST:
				return -1;
			}

		case ELECTRIC:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 1;

			case FLYING:
				return 1;

			case GRASS:
				return -1;

			case POISON:
				return 0;

			case ELECTRIC:
				return -1;

			case GROUND:
				return 2;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 0;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return -1;

			case GHOST:
				return 0;
			}

		case GROUND:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 1;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return 2;

			case GRASS:
				return -1;

			case POISON:
				return 1;

			case ELECTRIC:
				return 1;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 1;

			case ICE:
				return 0;

			case BUG:
				return -1;

			case DRAGON:
				return 0;

			case GHOST:
				return 0;
			}

		case PSYCHIC:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 1;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 0;

			case POISON:
				return 1;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return -1;

			case ROCK:
				return 0;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return 0;

			case GHOST:
				return 0;
			}

		case ROCK:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 1;

			case FIGHTING:
				return -1;

			case WATER:
				return 0;

			case FLYING:
				return 1;

			case GRASS:
				return 0;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return -1;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 0;

			case ICE:
				return 1;

			case BUG:
				return 1;

			case DRAGON:
				return 0;

			case GHOST:
				return 0;
			}

		case ICE:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return -1;

			case FLYING:
				return 1;

			case GRASS:
				return 1;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 1;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 0;

			case ICE:
				return -1;

			case BUG:
				return 0;

			case DRAGON:
				return 1;

			case GHOST:
				return 0;
			}

		case BUG:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return -1;

			case FIGHTING:
				return -1;

			case WATER:
				return 0;

			case FLYING:
				return -1;

			case GRASS:
				return 1;

			case POISON:
				return 1;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 1;

			case ROCK:
				return 0;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return 0;

			case GHOST:
				return -1;
			}

		case DRAGON:
			switch (defendType) {
			case NORMAL:
				return 0;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 0;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 0;

			case ROCK:
				return 0;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return 1;

			case GHOST:
				return 0;
			}

		case GHOST:
			switch (defendType) {
			case NORMAL:
				return 2;

			case FIRE:
				return 0;

			case FIGHTING:
				return 0;

			case WATER:
				return 0;

			case FLYING:
				return 0;

			case GRASS:
				return 0;

			case POISON:
				return 0;

			case ELECTRIC:
				return 0;

			case GROUND:
				return 0;

			case PSYCHIC:
				return 2;

			case ROCK:
				return 0;

			case ICE:
				return 0;

			case BUG:
				return 0;

			case DRAGON:
				return 0;

			case GHOST:
				return 1;
			}
		default:
			return 0;
		}
	}
}
