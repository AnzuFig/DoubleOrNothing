package game;

public class PlayerClass implements Player {

	int balance;
	
	public PlayerClass() {
		balance = 0;
	}

	@Override
	public void addBalance(int amount) {
		balance += amount;
	}

	@Override
	public void removeBalance(int amount) {
		balance -= amount;
	}

}
