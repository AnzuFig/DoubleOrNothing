package game;

public class GameClass implements Game {

	int potAmount;
	int winCounter;
	
	public GameClass() {
		potAmount = 0;
		winCounter = 0;
	}

	@Override
	public boolean setPot(int amount) {
		return false;
	}

	@Override
	public boolean betStart(int startAmount) {
		return false;
	}

	@Override
	public void bet() {
	}

	@Override
	public boolean potIsEmpty() {
		return false;
	}

	@Override
	public int getCurrentPot() {
		return potAmount;
	}
	
	

}
