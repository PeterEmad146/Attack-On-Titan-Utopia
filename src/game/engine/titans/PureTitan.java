package game.engine.titans;

/**
 * 4.2 Milestone 1 -- 2.1 Milestone 2
 * A class representing the PureTitans avaiable in the game.
 * This class is a subclass of the Titan class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class PureTitan extends Titan{

	public static final int TITAN_CODE = 1;

	// constructors
	public PureTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

}
