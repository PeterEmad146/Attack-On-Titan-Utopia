package game.engine.exceptions;

import java.io.IOException;

/**
 * 5.4 Milestone 1
 * A class representing an exception that can occur whenever an invalid csv is being read.
 * This class is a subclass of java's IOException class
 * @author Peter Emad, Mark Fahim, Ahmed Sheta
 *
 */
public class InvalidCSVFormat extends IOException{
	
	// class attributes
	private static final String MSG = "Invalid input detected while reading csv file, input = \n"; // a string representing the message of the exception that should be initialized to ”Invalid input detected while reading csv file, input = \n”.
	private String inputLine; // a variable representing the csv file name.
	
	// constructors
	/**
	 * Initializes an instance of a InvalidCSVFormat by calling the constructor of the super class with a message
	 * including MSG as well as the inputLine and setting the inputLine with the given parameter.
	 * @param inputLine
	 */
	public InvalidCSVFormat (String inputLine) {
		super(MSG + inputLine);
		this.inputLine = inputLine;
	}
	
	/**
	 * Initializes an instance of a InvalidCSVFormat by calling the constructor of the super class with a customized message 
	 * and setting the inputLine with the given parameter.
	 * @param message
	 * @param inputLine
	 */
	public InvalidCSVFormat (String message, String inputLine) {
		super(message);
		this.inputLine = inputLine;
	}

	// methods
	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}
}
