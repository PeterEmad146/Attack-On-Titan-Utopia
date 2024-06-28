package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

/**
 * 4.7 Milestone 1 -- 2.2 Milestone 2
 * A class representing the Piercing Cannons available in the game.
 * This class is subclass of the Weapon class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class PiercingCannon extends Weapon{

	// class attributes
	public static final int WEAPON_CODE = 1;
	
	// constructor
	public PiercingCannon(int baseDamage) {
		super(baseDamage);
	}

	@Override
	/**
	 * Attacks the closest 5 titans if they exist.
	 */
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0; // returned at the end of the method
		int k = laneTitans.size(); // gets the size of laneTitans and store it in an int variable
		PriorityQueue<Titan> temp = new PriorityQueue<>(); // temp PQ to store the titans attacked to be added again after the loop
		for(int i = 0; i < 5 && i < k; i++) {
			Titan t = laneTitans.remove();
			resourcesGained += this.attack(t);
			if(!t.isDefeated())
				temp.add(t);
		}
		while(!temp.isEmpty())
			laneTitans.add(temp.remove());
		return resourcesGained;
	}

}
