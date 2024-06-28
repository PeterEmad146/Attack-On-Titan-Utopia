package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.interfaces.Attacker;
import game.engine.titans.Titan;

/**
 * 4.6 Milestone 1 -- 2.2 Milestone 2
 * A class representing the weapons available in the game.
 * A weapon should be able to perform an attack.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public abstract class Weapon implements Attacker{

	// class attributes
	private final int baseDamage; // an integer representing the amount of damage a weapon can inflict.
	
	// constructors
	public Weapon (int baseDamage) {
		super();
		this.baseDamage = baseDamage;
	}
	
	// methods
	@Override
	public int getDamage() {
		return this.baseDamage;
	}
	
	/**
	 * A method that performs a certain damage to all titans on a lane. Any defeated titan should be removed.
	 * @param laneTitans
	 * @return the value of resources gained if any of the attackees got defeated.
	 */
	public abstract int turnAttack (PriorityQueue<Titan> laneTitans);
	
}
