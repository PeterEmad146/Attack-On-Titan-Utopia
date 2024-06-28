package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

/**
 * 4.10 Milestone 1 -- 2.2 Milestone 2
 * A class representing the WallTraps available in the game.
 * This class is a subclass of the Weapon class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class WallTrap extends Weapon{

	// class attributes
	public static final int WEAPON_CODE = 4;
	
	// constructors
	public WallTrap(int baseDamage) {
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGained = 0;
		if(!laneTitans.isEmpty()) {
			Titan t = laneTitans.remove();
			if(t.hasReachedTarget())
				resourcesGained += this.attack(t);
			if(!t.isDefeated())
				laneTitans.add(t);
		}
		return resourcesGained;
	}

}
