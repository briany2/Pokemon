
public class DamageControl {
	public Pokemon attacker;
	public Pokemon defender;
	public Moves move;

	public void dealPhysicalDamage(Pokemon attacker, Pokemon defender) {
		int damage = ((18 * move.getDamage() * attacker.getAttack() / defender.getDefense()) / 50 + 2);
		defender.setHp(defender.getHp() - damage);
		System.out.println(damage);
	}

	public void dealSpecialDamage(Pokemon attacker, Pokemon defender) {
		int damage = ((18 * move.getDamage() * attacker.getSpecialAttack() / defender.getSpecialDefense()) / 50 + 2);
		defender.setHp(defender.getHp() - damage);
		System.out.println(damage);
	}
}
