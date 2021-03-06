import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import exceptions.NotEnoughBalanceException;
import exceptions.PotIsEmptyException;
import game.Game;
import game.GameClass;

public class Main {
	
	private static final String LOSE_MESSAGE = "You have lost all your money.";
	private static final String WITHDRAW_SUCCESS = "Congratulation! You current balance is now: %d\n";
	private static final String DOUBLE_FAILED = "Failed...";
	private static final String DOUBLE_SUCCESS = "Doubled!! Pot: %d\n";
	private static final String POT_IS_EMPTY = "Pot is empty. Insert bet amount: ";
	private static final String CHECK_BALANCE = "Your balance is: %d\n";
	private static final String HELP_MESSAGE = "%s: %s\n";
	private static final String EXIT_MESSAGE = "Thank you for playing!";
	private static final String UNKNOWN_COMMAND = "Unknown Command...";
	
	private enum Command {                          
		D("Double - Bets on doubling the pot. (Must insert ammount if pot is 0!)"),
		W("Withdraw - Cashes out."), 
		B("Balance - Checks current balance."), HELP("Lists all avalible commands and describes what each do."),
		EXIT("Exists application."), UNKNOWN("");
		
		private String description;
		
		Command(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
	
	public static void main(String[] args) {
		runCommands();
		//visualizeRNG();
	}
	
	private static void runCommands() {
		Scanner in = new Scanner(System.in);
		Game game = new GameClass();
		Command command = Command.UNKNOWN;
		while(!command.equals(Command.EXIT) && !playerLost(game)) {
			command = getCommand(in);
			switch(command) {
				case HELP:
					execHelp();
					break;
				case D:
					execDouble(in, game);
					break;
				case W:
					execWithdraw(game);
					break;
				case B:
					execBalance(game);
					break;
				default:
					System.out.println(UNKNOWN_COMMAND);
			}
		}
		System.out.println(EXIT_MESSAGE);
		in.close();
	}

	private static void execWithdraw(Game game) {
		try {
			game.cashOut();
			System.out.printf(WITHDRAW_SUCCESS, game.getPlayerBalance());
		}
		catch(PotIsEmptyException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void execBalance(Game game) {
		System.out.printf(CHECK_BALANCE, game.getPlayerBalance());
	}

	private static void execDouble(Scanner in, Game game) {
		if(game.potIsEmpty()) {
			try {
				in.nextLine();
				System.out.printf(POT_IS_EMPTY);
				int startAmount = in.nextInt();
				if(game.betStart(startAmount)) {
					System.out.printf(DOUBLE_SUCCESS, game.getCurrentPot());
				}
				else {
					System.out.println(DOUBLE_FAILED);
					if(game.getPlayerBalance() == 0) {
						System.out.println(LOSE_MESSAGE);
					}
				}
				
			}
			catch(NotEnoughBalanceException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			if(game.bet()) {
				System.out.printf(DOUBLE_SUCCESS, game.getCurrentPot());
			}
			else {
				System.out.println(DOUBLE_FAILED);
				if(game.getPlayerBalance() == 0) {
					System.out.println(LOSE_MESSAGE);
				}
			}
		}
	}

	private static void execHelp() {
		for(Command c : Command.values()) {
			if(c != Command.UNKNOWN)
			System.out.printf(HELP_MESSAGE, c, c.getDescription());
		}
	}
	
	private static boolean playerLost(Game game) {
		return game.getPlayerBalance() == 0 && game.potIsEmpty();
	}

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		}
		catch(IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}
	
	// -----For debbuging purposes-----
	
	private static void visualizeRNG() {
		
		final int TRIES = 1000;
		final int JACKPOT_NUM = 10;
		
		List<Boolean> wons = new ArrayList<Boolean>(); 
		List<Double> chances = new ArrayList<Double>(); 
		for(int i = 0; i < TRIES; i++) {
			double chance = Math.random();
			boolean won = chance > 0.5;
			wons.add(won);
			chances.add(chance);
			}
		Iterator<Double> it = chances.iterator();
		List<Double> jackpots = new ArrayList<Double>();
		int counter = 0;
		int jackCounter = 0;
		while(it.hasNext()) {
			double next = it.next();
			if(next > 0.5) {
				counter++;
				jackpots.add(next);
				if(counter >= JACKPOT_NUM) {
					counter = 0;
					jackCounter++;
					System.out.println("--------------------");
					Iterator<Double> tempIt = jackpots.iterator();
					while(tempIt.hasNext()) {
						System.out.printf("JACKPOT: %f\n", tempIt.next());
					}
					jackpots.clear();
					System.out.println("--------------------");
				}
			}
			else {
				System.out.println(next);
				counter = 0;
				jackpots.clear();
			}
		}
		System.out.printf("Jackpots: %d", jackCounter);
	}

}
