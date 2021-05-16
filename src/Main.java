import java.util.Scanner;

import exceptions.NotEnoughBalanceException;
import game.Game;
import game.GameClass;
import game.Player;
import game.PlayerClass;

public class Main {
	
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
		Player p = new PlayerClass();
		Command command = getCommand(in);
		while(!command.equals(Command.EXIT)) {
			switch(command) {
				case HELP:
					execHelp();
					break;
				case DOUBLE:
					execDouble(in, game, p);
					break;
				default:
					System.out.println(UNKNOWN_COMMAND);
			}
			command = getCommand(in);
		}
		System.out.println(EXIT_MESSAGE);
		in.close();
	}

	private static void execDouble(Scanner in, Game game, Player p) {
		if(game.potIsEmpty()) {
			try {
				in.nextLine();
				System.out.println("Pot is empty. Insert bet amount: ");
				int startAmount = in.nextInt();
				if(game.betStart(startAmount, p)) {
					System.out.printf("Doubled!! Pot: %d\n", game.getCurrentPot());
				}
				else {
					System.out.println("Failed...");
				}
				
			}
			catch(NotEnoughBalanceException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			if(game.bet()) {
				System.out.printf("Doubled!! Pot: %d\n", game.getCurrentPot());
			}
			else {
				System.out.println("Failed...");
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
