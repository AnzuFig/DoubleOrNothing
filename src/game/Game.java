package game;

public interface Game {
	boolean setPot(int amount);
	boolean betStart(int startAmount);
	void bet();
	boolean potIsEmpty();
	int getCurrentPot();
}
