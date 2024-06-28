package game.engine.weapons;

/**
 * 4.14 Milestone 1 -- 2.5 Milestone 2
 * A class representing the WeaponRegistry, which is a place to store the weapon's information
 * that was read from the csv file for later use.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class WeaponRegistry {

	// class attributes
	private final int code; // an integer representing the type of weapon.
	private int price; // an integer representing the price of the weapon.
	private int damage; // an integer representing the amount of damage a weapon can inflict.
	private String name; // a variable representing the weapon's name.
	private int minRange; // an integer representing the lower bound of a weapon's damage range.
	private int maxRange; // an integer representing the upper bound of a weapon's damage range.
	
	// constructors
	public WeaponRegistry(int code, int price) {
		super();
		this.code = code;
		this.price = price;
	}
	
	public WeaponRegistry(int code, int price, int damage, String name) {
		this (code, price);
		this.damage = damage;
		this.name = name;
	}
	
	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
		this(code, price, damage, name);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	// methods
	// getters and setters
	public int getCode() {
		return code;
	}

	public int getPrice() {
		return price;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}
	
	/**
	 * A method that returns an object of the relevant type of weapon based on the registry code attribute
	 * @return weapon
	 */
	public Weapon buildWeapon() {
		Weapon w = null;
		switch (this.getCode()) {
			case 1: w = new PiercingCannon (this.damage); break;
			case 2: w = new SniperCannon (this.damage); break;
			case 3: w = new VolleySpreadCannon (this.damage, this.minRange, this.maxRange); break;
			case 4: w = new WallTrap (this.damage); break;
			default: break;
		}
		return w;
	}
	
}
