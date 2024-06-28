package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

/**
 * 4.8 Milestone 1 -- 2.2 Milestone 2
 * A class representing the Sniper Cannons available in the game.
 * This class is a subclass of the Weapon class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class SniperCannon extends Weapon{

	// class attributes
	public static final int WEAPON_CODE = 2;
	
	// constructors
	public SniperCannon(int baseDamage) {
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0; // returned at the end of the method
		if(!laneTitans.isEmpty()) {
			Titan t = laneTitans.remove();
			resourcesGained += this.attack(t);
			if(!t.isDefeated())
				laneTitans.add(t);
		}
		return resourcesGained;
	}

}
