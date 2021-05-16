package game;

import exceptions.NotEnoughBalanceException;

public interface Game {
	void setPot(int amount);
	boolean betStart(int startAmount, Player p) throws NotEnoughBalanceException;
	boolean bet();
	boolean potIsEmpty();
	int getCurrentPot();
	void cashOut(Player p);
}
