package game.engine.exceptions;

/**
 * 5.1 Milestone 1
 * A class representing a generic exception that can occur during game play.
 * These exceptions arise from any invalid action that is performed. 
 * This class is a subclass of java's Exception class.
 * No objects of type GameActionException can be instantiated.
 * This class has two subclasses: InvalidLaneException and InsufficientResourcesException.
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public abstract class GameActionException extends Exception{
	
	// constructors
	/**
	 * Initialize an instance of a GameActionException by calling the constructor of the super class.
	 */
	public GameActionException () {
		super();
	}
	
	/**
	 * Initializes an instance of a GameActionException by calling the constructor of the super class with a customized message.
	 * @param message to be displayed.
	 */
	public GameActionException (String message) {
		super(message);
	}

}
