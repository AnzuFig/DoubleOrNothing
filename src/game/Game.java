package game;

import exceptions.NotEnoughBalanceException;

public interface Game {
	void setPot(int amount);
	boolean betStart(int startAmount) throws NotEnoughBalanceException;
	boolean bet();
	boolean potIsEmpty();
	int getCurrentPot();
	int getPlayerBalance();
	void cashOut(Player p);
}
