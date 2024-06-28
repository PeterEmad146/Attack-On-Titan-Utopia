package game.engine.titans;

/**
 * 4.13 Milestone 1 -- 2.4 Milestone 2
 * A class representing the TitanRegistry, which is a place to store the titan's information
 * that was read from the csv file for later use.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class TitanRegistry {

	// class attributes
	private final int code; // an integer representing the type of titan.
	private int baseHealth;
	private int baseDamage;
	private int heightInMeters;
	private int speed; // distance moved per turn
	private int resourcesValue; // resources gained by defeating it
	private int dangerLevel;
	
	// constructors
	public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed,
			int resourcesValue, int dangerLevel) {
		
		super();
		this.code = code;
		this.baseHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.speed = speed;
		this.resourcesValue = resourcesValue;
		this.dangerLevel = dangerLevel;
	}

	// methods
	public int getCode() {
		return code;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public int getHeightInMeters() {
		return heightInMeters;
	}

	public int getSpeed() {
		return speed;
	}

	public int getResourcesValue() {
		return resourcesValue;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	/**
	 * The titan object will be spawned at the input distance
	 * @param distanceFromBase
	 * @return an object of the relevant type of titans based on the registry code attribute.
	 */
	public Titan spawnTitan (int distanceFromBase) {
		Titan t = null;
		switch (this.getCode()) {
			case 1: t = new PureTitan(this.baseHealth, this.baseDamage, this.heightInMeters, distanceFromBase, this.speed, this.resourcesValue, this.dangerLevel); break;
			case 2: t = new AbnormalTitan(this.baseHealth, this.baseDamage, this.heightInMeters, distanceFromBase, this.speed, this.resourcesValue, this.dangerLevel); break;
			case 3: t = new ArmoredTitan(this.baseHealth, this.baseDamage, this.heightInMeters, distanceFromBase, this.speed, this.resourcesValue, this.dangerLevel); break;
			case 4: t = new ColossalTitan(this.baseHealth, this.baseDamage, this.heightInMeters, distanceFromBase, this.speed, this.resourcesValue, this.dangerLevel); break;
			default: break;
		}
		return t;
	}
}
