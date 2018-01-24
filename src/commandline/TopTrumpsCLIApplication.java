package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import game.*;
import log.*;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	private final static String deckFileName = "StarCitizenDeck.txt";
	private static Log logFile;
	
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// create scanner object for input in console
		Scanner scanner = new Scanner(System.in);
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			boolean playAGame = false;
			boolean wantsStatisitics = false;
			boolean requireInput = true;
			String input = "";
			// ask user to play game or get statistics
			System.out.println("Hello there. Do you want to play a game or see statistics?"
					+ "\n If you want to play a game, type in 'g' or 's' for statistics."
					+ "\n Thank you.");
			while(requireInput)
			{
				// get input
				try{
					input = scanner.nextLine();
					if(input.equals("g")|| input.equals("s"))
						requireInput = false;
					else
						System.err.println("Your input isn't valid. Please try again.");
				}
				catch(InputMismatchException e)
				{
					System.err.println("Your input wasn't valid. Please try again.");
				}
			}
			// if play a game was chosen
			if(input.equals("g"))
			{
				Card[] deck = initializeDeck();
				System.out.println("So you wanna play  a game? \n"
						+ "Please tell us with how many opponents you want to play"
						+ " by giving us a number between 1 and 4");
				requireInput=true;
				int numOfPlayer = 0;
				while(requireInput)
				{
					try
					{
						numOfPlayer = scanner.nextInt();
					
						if(numOfPlayer >=1 && numOfPlayer <= 4)
							requireInput = false;
						else
							System.err.println("Your input is"
									+ " either to small or too big. "
									+ "Please try again.");
					}
					catch(InputMismatchException e)
					{
						System.err.println("Your input is not be an integer at all. "
								+ "Please try again.");
					}
				}
				
				Game game = new Game(numOfPlayer, deck);
				logFile = new Log(game);
				logFile.writeDeck(true);
				while(game.getNumOfActivePlayers() > 1)
				{
					// start round
					// by getting a category
					String category = "";
					if(game.isCurrentChooserHuman())
					{
						requireInput = true;
						while(requireInput) {
							System.out.println("It is your turn to choose a category. \n"
									+ "You have: " + game.getCurrentPlayer().getPile().getTopCard().getName()
									+ "\n Your categories and their respective values are the following: \n"
									+ cardInfoToString(game.getCurrentPlayer().getPile().getTopCard())
									+ "Please indicate your chosen category by typing its name.");
							category = scanner.nextLine();
							if(game.getCurrentPlayer().getPile().getTopCard().hasCategory(category))
							{
								requireInput = false;
							}
							else
							{
								System.err.println("Your input does not match any of the given categories."
										+ " \n Please be aware of case sensitivity");
							}
						}
					}
					else // get category from AI player if he is current player
					{
						category = game.getCurrentPlayer().selectAttribute();
						System.out.println("The selected category by player "
								+ game.getCurrentPlayer().getName()
								+ " is " + category +".");
					}
					// we now have a category and can execute the round
					String roundResults = game.executeRound(category);
					
					// communicate results with user
					System.out.println("The round has been played.");
					System.out.println("The winner is: " + game.getCurrentPlayer().getName());
					System.out.println("The results of the round are the followin \n" + roundResults);
				}
				// game has finished
				System.out.println("The game has finished. \n"
						+ "the winner is: " + game.getCurrentPlayer().getName());
			}
			else if(input.equals("s"))
			{
				
			}
			
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
					
		}
		scanner.close();
		logFile.closeLog();

	}
	
	public static Card[] initializeDeck()
	{
		Card[] deck = new Card[40];
		// open the file
		try 
		{
			FileReader deckFile = new FileReader(deckFileName);
			Scanner scanner = new Scanner(deckFile);
			
			String header = scanner.nextLine();
			header = header.substring(12);
			// initialize the string of categories
			String categories[] = header.split(" ");
			
			// counter for the loop
			int counter = 0;
			
			while(scanner.hasNext())
			{
				String line = scanner.nextLine();
				String splitLine[] = line.split(" ");
				// name 
				// name is splitLine[0];
				// five values
				int[] values = new int[5];
				for(int i = 1; i < splitLine.length; i++)
					values[i-1] = Integer.parseInt(splitLine[i]);
				// create a card
				Card tempCard = new Card(splitLine[0], categories, values);
				// give that card to the deck
				deck[counter] = tempCard;
				counter++;
			}
			
			scanner.close();
			deckFile.close();
		}		
		
		catch(IOException e)
		{
			System.err.println("Problem when trying to create deck.");
		}
		
		return deck;
	}

	private static String cardInfoToString(Card card) {
		String output = "";
		StringBuilder cardInfo = new StringBuilder(output);
		HashMap<String, Integer> cardCategories = card.getCategories(); 
		for (Map.Entry<String, Integer> entry: cardCategories.entrySet()) {
			cardInfo.append(entry.getKey() + " " + entry.getValue() + "\n");
		}
		output = cardInfo.toString();
		return output;
	}
}
