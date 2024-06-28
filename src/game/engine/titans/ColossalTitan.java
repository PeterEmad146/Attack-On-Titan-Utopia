package game.engine.titans;

/**
 * 4.5 Milestone 1 -- 2.1 Milestone 2
 * A class representing the ArmoredTitans available in the game.
 * This class is a subclass of the Titan class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class ColossalTitan extends Titan{

	// class attributes
	public static final int TITAN_CODE = 4;

	// constructors
	public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

	@Override
	/**
	 * Speed (distance moved) increases by 1 after every movement action.
	 */
	public boolean move() {
		boolean result = super.move();
		this.setSpeed(this.getSpeed() + 1);
		return result;
	}

}
