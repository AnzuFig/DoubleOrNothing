package exceptions;

@SuppressWarnings("serial")
public class NotEnoughBalanceException extends Exception {
	private static final String MESSAGE = "Not enough balance to make that bet.";
	
	public NotEnoughBalanceException(){
		super(MESSAGE);
	}
}
