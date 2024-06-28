package game.engine.interfaces;

/**
 * 2.1 Milestone 1 -- 1.1 Milestone 2
 * Description: Interface containing the methods available to all objects that gets attacked within the game.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public interface Attackee {

	// Methods
	// getters
	public int getCurrentHealth (); // A getter method that retrieves the Attackee's current health.
	public void setCurrentHealth (int health); // A setter method that changes the Attackee's current health to the input value.
	public int getResourcesValue(); // A getter method that retrieves the Attackee's resources value.
	
	/**
	 * A method that checks if thte attackee's current health has reached or fallen below 0
	 * @return true if the attackee is defeated, false otherwise.
	 */
	public default boolean isDefeated () {
		return this.getCurrentHealth() <= 0 ? true : false;
	}
	
	/**
	 * A method that inflict damage to the attackee's current health.
	 * @param damage
	 * @return the value of gained resources in case the attackee was defeated, otherwise returns 0
	 */
	public default int takeDamage (int damage) {
		if (damage > 0) {
			this.setCurrentHealth(this.getCurrentHealth() - damage);
			return this.isDefeated() ? this.getResourcesValue() : 0;
		}
		return 0;
	}
}
