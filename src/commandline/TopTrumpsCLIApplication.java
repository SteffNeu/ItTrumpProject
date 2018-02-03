package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import game.*;
import log.*;
import database.*;

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
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// create scanner object for input in console
		Scanner scanner = new Scanner(System.in);
		
		// Loop until the user wants to exit the game
		mainLoop:
		while (!userWantsToQuit) {
			boolean playAGame = false;
			boolean wantsStatisitics = false;
			boolean requireInput = true;
			String input = "";
			// ask user to play game or get statistics
			System.out.println("Hello there. Do you want to play a game or see statistics?"
					+ "\n If you want to play a game, type in 'g' or 's' for statistics and press 'Enter'."
					+ "\n Thank you.");
			while(requireInput)
			{
				// get input
				try
				{
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
						String tempStringNumOfPlayer = scanner.nextLine();
						
						numOfPlayer = Integer.parseInt(tempStringNumOfPlayer);
					
						if(numOfPlayer >=1 && numOfPlayer <= 4)
							requireInput = false;
						else
							System.err.println("Your input is"
									+ " either to small or too big. "
									+ "Please try again.");
					}
					catch(InputMismatchException| NumberFormatException e)
					{
						System.err.println("There was a problem while reading in your input. Are you sure you entered a number? "
								+ "Please try again.");
					}
				}
				requireInput=true;
				while(requireInput)
				{
					try
					{
						System.out.println("We are all set up. Are you ready to start? If so, please press 'Enter'.");
						String tempStringNumOfPlayer = scanner.nextLine();
											
						if(tempStringNumOfPlayer.equals(""))
							requireInput = false;
						else
							System.err.println("Sorry, we couldn't handle your input, please press 'Enter' if you want to continue. ");
					}
					catch(InputMismatchException| NumberFormatException e)
					{
						System.err.println("There was a problem while reading in your input. Are you sure you entered a number? "
								+ "Please try again.");
					}
				}
				
				Game game = new Game(deck);
				if (writeGameLogsToFile) // create log and write the initialised deck
				{
					logFile = new Log(game);
					logFile.writeDeck(false);
				}
								
				game.startGame(numOfPlayer);
				if (writeGameLogsToFile) // write the shuffled deck and players hands to log
				{
					logFile.writeDeck(true);
					logFile.writePlayersDecks();
				}
				
				boolean humanStillPlaying = true;
				while(game.getNumOfActivePlayers() > 1)
				{
					if (writeGameLogsToFile) // write all top cards to log
					{
						logFile.writeRoundNumber(game.getNumOfRounds());
						logFile.writeCardsInPlay();
					}
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
					}

					if (writeGameLogsToFile) // write chosen category to log file
						logFile.writeCategorySelected(category);	
					if(humanStillPlaying)
						System.out.println("The selected category by player "
								+ game.getCurrentPlayer().getName()
								+ " is " + category +".");
					
					// we now have a category and can execute the round
					String roundResults = game.executeRound(category);
					if (writeGameLogsToFile) // write players hands and communal pile after the round is played
					{			
						logFile.writePlayersDecks();
						logFile.writeCommunalPile();
					}
					
					if(game.isHumanPlaying())
					{
						// communicate results with user + 
						System.out.println("The round has been played.");
						if(game.lastRoundWasDraw())
							System.out.println("There was no winner.");			
						else
							System.out.println("The winner is: " + game.getCurrentPlayer().getName());
						System.out.println("The results of the round are the following: \n" + roundResults);
						System.out.print("Cards left: ");
						for(Player p : game.getActivePlayers())
							System.out.print(p.getName()+ ", " + p.getPile().getNumOfCards()+ "; " );

						System.out.println("\nThis was round number " + Integer.toString(game.getNumOfRounds())
								+ "\nIf you are ready for the next round \n"
								+ "please press enter. \n"
								+ "If you want to end this game. Type 'exit'.");
						requireInput = true;
						while(requireInput)
						{
							// get input
							try{
								input = scanner.nextLine();
								if(input.equals("exit"))
								{
									requireInput = false;
									break mainLoop;
								}
								else if(input.equals(""))
								{
									System.out.println("--------------------------------------------------");
									requireInput = false;
								}
								else
									System.err.println("Your input isn't valid. Please try again.");
							}
							catch(InputMismatchException e)
							{
								System.err.println("Your input wasn't valid. Please try again.");
							}
						}
					}
					else
					{
						if (humanStillPlaying)
							System.out.println("\n You have lost the game.");

						humanStillPlaying=false;
					}
					// check again because of that weird mistake TODO delete
					game.checkForElimination();
				}
				// game has finished
				System.out.println("The game has finished. \n"
						+ "the winner is: " + game.getCurrentPlayer().getName());
				System.out.println("There were " + Integer.toString(game.getNumOfRounds()) + " rounds played.");
				System.out.println("There were " + Integer.toString(game.getNumOfDraws()) + " draws.");
				
				if (writeGameLogsToFile) // write winner to the logfile
				{
					logFile.writeWinner(game.getCurrentPlayer());
				}
				
				// give game to database
				Database db = new Database();
				db.setGame(game);
				db.writeInfoToDatabase();
				db.disconnectFromDatabase();
			}
			else if(input.equals("s"))
			{
				//display the statistics
				Database database = new Database();
				String[] dbContent = database.getStatistics();
				System.out.println("Total number of games: "+dbContent[0]);
				System.out.println("Computer wins: "+dbContent[1]);
				System.out.println("Human wins: "+dbContent[2]);
				System.out.println("Average number of draws: "+dbContent[3]);
				System.out.println("Maximum round number: "+dbContent[4]);
				database.disconnectFromDatabase();
			}
			// make quiting statement
			System.out.println("You can now choose to exit the program. \n"
					+ "Should you desire to exit the program please type 'exit'. \n"
					+ "Should you want to continue type anything else.");
			requireInput = true;
			while(requireInput)
			{
				// get input
				try{
					input = scanner.nextLine();
					if(input.equals("exit"))
					{
						requireInput = false;
						userWantsToQuit=true;
					}
					else
					{
						requireInput = false;
					}
				}
				catch(InputMismatchException e)
				{
					System.err.println("Your input wasn't valid. Please try again.");
				}
			}
			
			
					
		}
		System.out.println("Goodybye and thank you for participating. \n"
				+ "Love, the IT Crowd.");
		scanner.close();
		
		if (writeGameLogsToFile) {
			logFile.closeLog();
		}
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
