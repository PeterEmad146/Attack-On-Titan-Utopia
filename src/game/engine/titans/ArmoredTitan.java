package game.engine.titans;

/**
 * 4.4 Milestone 1 -- 2.1 Milestone 2
 * A class representing the Armored Titans avaiable in the game.
 * This class is a subclass of the Titan class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class ArmoredTitan extends Titan{

	// class attributes
	public static final int TITAN_CODE = 3;
	
	// constructor
	public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

	@Override
	/**
	 * Only takes quarter of the intended damage when attacked.
	 */
	public int takeDamage(int damage) {
		return super.takeDamage((int)(damage*0.25));
	}
	
}
