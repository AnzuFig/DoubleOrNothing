package game;

import exceptions.NotEnoughBalanceException;

public class GameClass implements Game {

	private static final double WIN_CHANCE = 0.5; // From 0.0 to 1.0 (Percentage)
	
	int potAmount;
	int winCounter;
	
	public GameClass() {
		potAmount = 0;
		winCounter = 0;
	}

	@Override
	public void setPot(int amount) {
		
	}

	@Override
	public boolean betStart(int startAmount, Player p) throws NotEnoughBalanceException {
		boolean won;
		if(p.getBalance() >= startAmount) {
			p.removeBalance(startAmount);
			if(winsBet()) {
				potAmount = startAmount * 2;
				winCounter += 1;
				won = true;
			}
			else {
				potAmount = 0;
				winCounter = 0;
				won = false;
			}
			return won;
		}
		else {
			throw new NotEnoughBalanceException();
		}
		
	}

	@Override
	public boolean bet() {
		boolean won;
		if(winsBet()) {
			potAmount *= 2;
			winCounter += 1;
			won = true;
		}
		else {
			potAmount = 0;
			winCounter = 0;
			won = false;
		}
		return won;
	}

	@Override
	public boolean potIsEmpty() {
		return potAmount == 0;
	}

	@Override
	public int getCurrentPot() {
		return potAmount;
	}

	@Override
	public void cashOut(Player p) {
		p.addBalance(potAmount);
		potAmount = 0;
	}
	
	// -- Private Methods --
	
	private boolean winsBet() {
		return Math.random() < WIN_CHANCE;
	}
	
	

}
