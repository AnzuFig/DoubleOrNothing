package game;

public class PlayerClass implements Player {

	private static final int STARTING_BALANCE = 500;
	int balance;
	
	public PlayerClass() {
		balance = STARTING_BALANCE;
	}

	@Override
	public void addBalance(int amount) {
		balance += amount;
	}

	@Override
	public void removeBalance(int amount) {
		balance -= amount;
	}

	@Override
	public int getBalance() {
		return balance;
	}

}
