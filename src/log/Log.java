package log;

import java.util.*;
import game.*;
import java.io.*;
/**
 * class object to manage the log file in the command line mode
 * @author Matthew Wilson
 *
 */


public class Log {
	
	/**game being played*/
	private Game game;
	
	private FileWriter logWriter;
	/** name of the output file*/
	private String outFile = "toptrumps.log";
	
	/**
	 * constructor
	 * @param g - game being played 
	 */
	public Log(Game g) {
		game = g;
		try {
			logWriter = new FileWriter(outFile);
		}
		catch (IOException e) {}
		String title = drawDelineator() + "     *** Top Trumps Test Log ***\r\n" + drawDelineator();
		writeLog(title);
	}
	
	/**
	 * Writes the entire contents of the deck after checking
	 * whether the deck is shuffled or not
	 * @param shuffled boolean
	 */
	public void writeDeck(boolean shuffled) {
		
		StringBuilder deckInfo = new StringBuilder(drawDelineator());
		if (shuffled) 
			deckInfo.append("Shuffled deck: \r\n\r\n");
		else 
			deckInfo.append("Initialised deck before shuffling: \r\n\r\n");
		
		Card [] deck = game.getDeck();
		
		for (int i = 0; i < deck.length; i ++) {
			deckInfo.append(cardInfoToString(deck [i]));
		}		
		writeLog(deckInfo.toString());
	}
	
	/**
	 * Writes the contents of each players' hand of cards
	 */
	public void writePlayersDecks() 
	{
		ArrayList<Player> activePlayers = game.getActivePlayers();
		StringBuilder playerInfo = new StringBuilder(drawDelineator() + "Players' hands:\r\n\r\n");
		
		for (Player player : activePlayers) {
			playerInfo.append(player.getName() + ":\r\n");
			ArrayList<Card> playersCards = player.getPile().getCards();
			for (Card card : playersCards) {
				playerInfo.append(cardInfoToString(card));
			}
			playerInfo.append("\r\n");
		}		
		writeLog(playerInfo.toString());
	}
	
	/**
	 * writes the current round number to the log file
	 * @param round integer
	 */
	public void writeRoundNumber(int round) 
	{
		String roundHeader = drawDelineator() + "          *** Round " + (round + 1) + " ***\r\n" + drawDelineator();
		writeLog(roundHeader);
	}
	
	/**
	 * writes the contents of the communal pile when cards are added
	 * after checking if the pile has had cards added
	 */
	public void writeCommunalPile() { 
		ArrayList<Card> communalCards = game.getCommunalPile().getCards();
		StringBuilder pileInfo = new StringBuilder(drawDelineator() + "Communal pile ");
		
		if (communalCards.isEmpty()) {
			pileInfo.append("is empty.\r\n\r\n");
		}
		else {
			pileInfo.append("now contains: \r\n");
			for (Card card : communalCards) {
				pileInfo.append(cardInfoToString(card));
			}
		}	
		writeLog(pileInfo.toString() + "\r\n");
	}
	
	/**
	 * writes the top card of each player's pile 
	 */
	public void writeCardsInPlay() {
		StringBuilder cardsInPlayInfo = new StringBuilder(drawDelineator() + "Current cards in play: \r\n");
		for (Player player : game.getActivePlayers()) {
			cardsInPlayInfo.append(player.getName() + ": ");
			Card playersTopCard = player.getPile().getTopCard();
			cardsInPlayInfo.append(cardInfoToString(playersTopCard));
		}		
		writeLog(cardsInPlayInfo.toString());	
	}
	
	/**
	 * writes the category selected by the active player
	 * to the logfile 
	 * @param category
	 */
	public void writeCategorySelected(String category)
	{ 
		String categoryInfo = drawDelineator() + game.getCurrentPlayer().getName() + " selected: " + category + "\r\n";
		writeLog(categoryInfo);
	}
	
	/**
	 * writes the overall winner to the log file
	 * @param winner
	 */
	public void writeWinner(Player winner) 
	{
		writeLog(drawDelineator() + winner.getName() + " won this game of Top Trumps.\r\n");
	}
	
	/**
	 * closes the FileWriter object
	 */
	public void closeLog()
	{
		writeLog(drawDelineator());
		try {
			logWriter.close();
		}
		catch(IOException e)
		{
			System.err.println("The logFile could not be closed properly");
		}
	}
	
	/**
	 * Creates a string for a given card that contains the
	 * name of each card and all its category values 
	 * @param card
	 * @return String - card details on one line
	 */
	private String cardInfoToString(Card card) {
		StringBuilder cardInfo = new StringBuilder(card.getName());
		HashMap<String, Integer> cardCategories = card.getCategories(); 
		for (Map.Entry<String, Integer> entry: cardCategories.entrySet()) {
			cardInfo.append(" " + entry.getValue());
		}
		return cardInfo.toString() + "\r\n";
	}
	
	/**
	 *  writes a given String to the log output file
	 * @param info
	 */
	private void writeLog(String info) {
		try {
			logWriter.write(info);
			logWriter.flush();
		}
		catch (IOException e) {
			System.err.println("Error occured while writing the log file.\r\n");
		}		
	}
	
	/**
	 * writes a line of dashes across the log file to 
	 * demarcate different sections
	 */
	private String drawDelineator()
	{
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < 38; i++)
			line.append('-');
		line.append("\r\n");
		return line.toString();
	}
	
}


