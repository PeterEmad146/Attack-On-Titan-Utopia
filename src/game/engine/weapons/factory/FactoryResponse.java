package game.engine.weapons.factory;

import game.engine.weapons.Weapon;

/**
 * 4.17 Milestone 1
 * A class representing the FactoryResponse, which is an object 
 * to store the weapon that was bought with the remaining resources.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class FactoryResponse {

	// class attributes
	private final Weapon weapon; // a weapon that was bought
	private final int remainingResources; // an integer representing the remaining resources after buying the weapon
	
	// constructors
	public FactoryResponse (Weapon weapon, int remainingResources) {
		super();
		this.weapon = weapon;
		this.remainingResources = remainingResources;
	}

	// methods
	public Weapon getWeapon() {
		return weapon;
	}

	public int getRemainingResources() {
		return remainingResources;
	}
	
}
