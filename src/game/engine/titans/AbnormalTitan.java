package game.engine.titans;

import game.engine.interfaces.Attackee;

/**
 * 4.3 Milestone 1 -- 2.1 Milestone 2
 * A class representing the AbnormalTitans avaiable in the game.
 * This class is a subclass of the Titan class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class AbnormalTitan extends Titan{

	// class attributes
	public static final int TITAN_CODE = 2;
	
	// constructor
	public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}
	
	@Override
	/**
	 * Performs the attack action on target twice per turn (if not already defeated
	 * by the first attack) instead of once.
	 */
	public int attack(Attackee target) {
		int tempResources = super.attack(target);
		if(!target.isDefeated())
			tempResources = super.attack(target);
		return tempResources;
	}

}
