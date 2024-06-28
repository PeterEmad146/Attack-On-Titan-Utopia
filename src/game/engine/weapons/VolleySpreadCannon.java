package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

/**
 * 4.9 Milestone 1 -- 2.2 Milestone 2
 * A class representing the VolleySpreadCannons available in the game.
 * This class is a subclass of the Weapon class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class VolleySpreadCannon extends Weapon{

	// class attributes
	public static final int WEAPON_CODE = 3;
	private final int minRange;
	private final int maxRange;
	
	// constructors
	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}
	
	// methods
	// getters and setters
	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0; // returned at the end of the method
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while (!laneTitans.isEmpty()) {
			Titan t = laneTitans.remove();
			if(t.getDistance() >= this.getMinRange() && t.getDistance() <= this.getMaxRange()) {
				resourcesGained += this.attack(t);
			}
			if(!t.isDefeated())
				temp.add(t);
		}
		while(!temp.isEmpty())
			laneTitans.add(temp.remove());
		return resourcesGained;
	}
}
