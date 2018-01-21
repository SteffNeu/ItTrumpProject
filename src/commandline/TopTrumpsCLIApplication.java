package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import game.*;

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
	
	
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			boolean playAGame = false;
			boolean wantsStatisitics = false;
			
			System.out.println("Hello User. Do you want to play a game or see statistics?");
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
			Card[] deck = initializeDeck();
			
			System.out.println(deck);			
			
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

}
