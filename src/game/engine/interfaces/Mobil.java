package game.engine.interfaces;

/**
 * 2.3 Milestone 1 -- 1.3 Milestone 2
 * Interface containing the methods available to all objects that has mobility (i.e. can move) within the game.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public interface Mobil {

	// Methods
	// getters and setters
	public int getDistance (); // A getter method that retrieves the Mobil's distance from its target
	public void setDistance (int distance); // A setter method that changes the Mobil's distance from its target to the input value.
	public int getSpeed (); // A getter method that retrieves the Mobil's movement speed.
	public void setSpeed (int speed); // A setter method that changes the Mobil's movement speed to the input value.
	
	/**
	 * A method that cheks if a mobil has arrived at the intended target.
	 * @return true if reached, false otherwise.
	 */
	public default boolean hasReachedTarget () {
		return this.getDistance() <= 0 ? true : false;
	}
	
	/**
	 * A method that moves a mobil by changing it's distance. 
	 * @return true if the target was reached, otherwise false.
	 */
	public default boolean move () {
		this.setDistance(this.getDistance() - this.getSpeed());
		return this.hasReachedTarget();
	}
}
