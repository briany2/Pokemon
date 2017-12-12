import java.util.Random;

public class DamageControl {
	public Pokemon attacker;
	public Pokemon defender;
	public Moves move;
	Random rand = new Random();

	public DamageControl(Pokemon attacker, Pokemon defender, Moves move) {
		this.attacker = attacker;
		this.defender = defender;
		this.move = move;
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
		return damage;
	}

	public boolean hitCheck(Pokemon attacker, Pokemon defender, Moves move) { // accuracy calculator
		int randomNumber = (int) (Math.random() * 100);
		if (randomNumber <= move.getAccuracy()) {
			return true;
		} else {
			return false;
		}
	}

	public int getDamage(Pokemon attacker, Pokemon defender, Moves move) {
		if (move.getMoveType().equals("PHYSICAL")) {
			return dealPhysicalDamage(attacker, defender, move);
		} else {
			return dealSpecialDamage(attacker, defender, move);
		}
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
