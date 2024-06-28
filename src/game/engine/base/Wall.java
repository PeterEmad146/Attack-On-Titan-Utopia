package game.engine.base;

import game.engine.interfaces.Attackee;

/**
 * 4.11 Milestone 1
 * A class representing the Walls in which a titan attacks in the game.
 * A wall is a class that gets attacked.
 * Its resources value is -1 and is not deducted from the player's resources if destroyed.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class Wall implements Attackee{

	// class attributes
	private final int baseHealth;
	private int currentHealth;
	
	// constructors
	public Wall (int baseHealth) {
		super();
		this.baseHealth = baseHealth;
		this.currentHealth = baseHealth;
	}
	
	// methods
	public int getBaseHealth() {
		return this.baseHealth;
	}
	
	@Override
	public int getCurrentHealth() {
		return this.currentHealth;
	}

	@Override
	public void setCurrentHealth(int health) {
		if(health>=0)
			this.currentHealth = health;
		else
			this.currentHealth = 0;
	}

	@Override
	public int getResourcesValue() {
		return -1;
	}
}
