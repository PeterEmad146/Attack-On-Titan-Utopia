package game.engine.exceptions;

/**
 * 5.2 Milestone 1
 * A class representing an exception that can occur whenever the player tries to buy
 * a weapon in a destroyed or non existent Lane.
 * This class is subclass of GameActionException class.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class InvalidLaneException extends GameActionException{

	// class attributes
	private static final String MSG = "Action done on an invalid lane";
	
	// constructors
	/**
	 * Initializes an instance of a InvalidLaneException by calling the constructor of the super class with MSG.
	 */
	public InvalidLaneException () {
		super(MSG);
	}
	
	/**
	 * Initlializes an instance of a InvalidLaneException by calling the constructor of the super class with a customized message.
	 * @param message
	 */
	public InvalidLaneException (String message) {
		super(message);
	}
	
}
