package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;

/**
 * 4.18 Milestone 1 -- 2.6 Milestone 2
 * A class representing the WeaponFactory, which is used to store the available weapons
 * that can be purchased during each turn.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class WeaponFactory {

	// class attributes
	private final HashMap<Integer, WeaponRegistry> weaponShop;
	
	// constructors
	public WeaponFactory () throws IOException {
		super();
		this.weaponShop = DataLoader.readWeaponRegistry();
	}
	
	// methods
	// getters and setters
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return this.weaponShop;
	}
	
	/**
	 * A method that takes avaiable resources and weapon code as inputs to purchase
	 * the specified weapon from the weaponShop indicated by the code if there are available resources.
	 * @param resources available to purchase the weapon.
	 * @param weaponCode for the weapon to be purchased.
	 * @return a FactoryResponse containing the weapon and the remaining resources after
	 * deducting the weapon's price in case of a successful purchase.
	 * @throws InsufficientResourcesException
	 */
	public FactoryResponse buyWeapon (int resources, int weaponCode) throws InsufficientResourcesException {
		
		// gets the weapon based on its code from the registry
		WeaponRegistry wr = weaponShop.get(weaponCode);
		if(resources < wr.getPrice())
			throw new InsufficientResourcesException(resources);
		
		// define the parameters for the FactoryResponse object
		int remainingResources = resources - wr.getPrice();
		Weapon weapon = wr.buildWeapon();
		
		return new FactoryResponse(weapon, remainingResources);
	}
	
	/**
	 * A method that adds a WeaponRegistry with the given parameters to the weaponShop with its code.
	 * @param code of weapon
	 * @param price of weapon
	 */
	public void addWeaponToShop (int code, int price) {
		WeaponRegistry wr = new WeaponRegistry(code, price);
		this.weaponShop.put(code, wr);
	}
	
	/**
	 * A method that adds a WeaponRegistry with the given parameters to the weaponShop with its code.
	 * @param code of weapon
	 * @param price of weapon
	 * @param damage of weapon
	 * @param name of weapon
	 */
	public void addWeaponToShop (int code, int price, int damage, String name) {
		WeaponRegistry wr = new WeaponRegistry(code, price, damage, name);
		this.weaponShop.put(code, wr);
	}
	
	/**
	 * A method that adds a WeaponRegistry with the given parameters to the weaponShop with its code.
	 * @param code of weapon
	 * @param price of weapon
	 * @param damage of weapon
	 * @param name of weapon
	 * @param minRange of weapon
	 * @param maxRange of weapon
	 */
	public void addWeaponToShop (int code, int price, int damage, String name, int minRange, int maxRange) {
		WeaponRegistry wr = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
		this.weaponShop.put(code, wr);
	}
}
