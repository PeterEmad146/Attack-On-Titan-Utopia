package game.engine.interfaces;

/**
 * 2.2 Milestone 1 -- 1.2 Milestone 2
 * Interface containing the methods available to all attackers within the game.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public interface Attacker {

	// Methods
	// getters
	public int getDamage(); // A getter method that retrieves the damage value that the attacker inflicts.
	
	/**
	 * A method that inflict damage to the target.
	 * @param target
	 * @return the value of gained resources in case the attackee was defeated
	 */
	public default int attack (Attackee target) {
		return target.takeDamage(this.getDamage());
	}
}
