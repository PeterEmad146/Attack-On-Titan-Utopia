package game.engine.exceptions;

/**
 * 5.3 Milestone 1
 * A class representing an exception that can occur whenever the player tries
 * to buy a weapon without having enough resources. 
 * This class is a subclass of GameActionException.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class InsufficientResourcesException extends GameActionException{

	// class attributes
	private static final String MSG = "Not enough resources, resources provided = ";
	private int resourcesProvided; // an intenger representing the current availabel resources.
	
	// constructors
	/**
	 * Initializes an instance of an InsufficientResourcesException by calling the constructor of the super class
	 * with a message including MSG as well as the resourcesProvided and setting the resourcesProvided with the given parameeter.
	 * @param resourcesProvided
	 */
	public InsufficientResourcesException (int resourcesProvided) {
		super(MSG + resourcesProvided);
		this.resourcesProvided = resourcesProvided;
	}
	
	/**
	 * Initializes an instance InsufficientResourcesException by calling the constructor of teh super class with a customized message 
	 * and setting the resourcesProvided with given parameter.
	 * @param message to be displayed.
	 * @param resourcesProvided 
	 */
	public InsufficientResourcesException (String message, int resourcesProvided) {
		super(message);
		this.resourcesProvided = resourcesProvided;
	}
	
	// methods
	public int getResourcesProvided() {
		return resourcesProvided;
	}
	public void setResourcesProvided(int resourcesProvided) {
		this.resourcesProvided = resourcesProvided;
		
	}
	
	
}
