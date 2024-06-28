package game.engine.titans;

import game.engine.interfaces.*;

/**
 * 4.1 Milestone 1
 * A class representing the Titans available in the game.
 * A titan class can do the following functionalities:
 * 1. move
 * 2. attack
 * 3. gets attacked
 * 4. gets compared based on it's distance from the base/wall
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public abstract class Titan implements Attackee, Attacker, Mobil, Comparable<Titan>{

	// class attributes
	
	private final int baseHealth; // an integer representing the original titan's health when spawned.
	private int currentHealth; // an integer representing the current titan's health which by default is equal to baseHealth.
	private final int baseDamage; // an integer representing the amount of damage that a titan would cause when attacking a wall.
	private final int heightInMeters; // an integer representing the titan's height.
	private int distanceFromBase; // an integer representing the distance of a titan from the base (i.e. wall)
	private int speed; // an integer representing the speed of a titan (i.e. the distance a titan moves each turn).
	private final int resourcesValue; // an integer representing the amount of resources that are gained by defeating the titan.
	private final int dangerLevel; // an integer representing the danger level of a titan. The smaller the value, the less dangerous that titan is.
	
	// constructors
	
	/**
	 * Constructor that initializes a Titan object with the given parameters as the attributes.
	 * @param baseHealth
	 * @param baseDamage
	 * @param heightInMeters
	 * @param distanceFromBase
	 * @param speed
	 * @param resourcesValue
	 * @param dangerLevel
	 */
	public Titan (int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		super();
		this.baseHealth = baseHealth;
		this.currentHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.distanceFromBase = distanceFromBase;
		this.speed = speed;
		this.resourcesValue = resourcesValue;
		this.dangerLevel = dangerLevel;
	}
	
	// methods

	/**
	 * 
	 * @param o the titan to compare with.
	 * @return -ve value if o is closer to the base.
	 * 		   +ve value if the specified Titan is closer to the base.
	 *         zero if the two Titans are at the same distance from the base.
	 */
	public int compareTo (Titan o) {
		return this.distanceFromBase - o.distanceFromBase;
	}
	
	// getters and setters
	
	public int getBaseHealth () {
		return this.baseHealth;
	}
	
	@Override
	public int getCurrentHealth() {
		return this.currentHealth;
	}

	@Override
	public void setCurrentHealth(int health) {
		if(health >= 0)
			this.currentHealth = health;
		else
			this.currentHealth = 0;
	}

	@Override
	public int getDamage() {
		return this.baseDamage;
	}
	
	public int getHeightInMeters() {
		return this.heightInMeters;
	}
	
	@Override
	public int getDistance() {
		return this.distanceFromBase;
	}

	@Override
	public void setDistance(int distance) {
		if(distance>0)
			this.distanceFromBase = distance;
		else
			this.distanceFromBase = 0;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int getResourcesValue() {
		return this.resourcesValue;
	}
	
	public int getDangerLevel() {
		return this.dangerLevel;
	}
	
}
