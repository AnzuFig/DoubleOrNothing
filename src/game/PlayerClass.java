package game;

public class PlayerClass implements Player {

	int balance;
	
	public PlayerClass() {
		balance = 500;
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
