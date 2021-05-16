package exceptions;

public class PotIsEmptyException extends Exception {

	private static final String MESSAGE = "The pot is empty.";
	
	public PotIsEmptyException() {
		super(MESSAGE);
	}

}
