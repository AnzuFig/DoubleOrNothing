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
	private static final String HELP_MESSAGE = "%s - %s\n";
	private static final String EXIT_MESSAGE = "Thank you for playing!";
	private static final String UNKNOWN_COMMAND = "Unknown Command...";
	
	private enum Command {                          
		DOUBLE("Bets on doubling the pot. (Must insert ammount if pot is 0!)"),
		WITHDRAW("Cashes out."), 
		BALANCE("Checks current balance."), HELP("Runs help command."),
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
	}
	
	private static void runCommands() {
		Scanner in = new Scanner(System.in);
		Game game = new GameClass();
		Command command = Command.UNKNOWN;
		while(!command.equals(Command.EXIT) && !(game.getPlayerBalance() == 0 && game.potIsEmpty())) {
			command = getCommand(in);
			switch(command) {
				case HELP:
					execHelp();
					break;
				case DOUBLE:
					execDouble(in, game);
					break;
				case WITHDRAW:
					execWithdraw(game);
					break;
				case BALANCE:
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
				System.out.println(POT_IS_EMPTY);
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

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		}
		catch(IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}

}
