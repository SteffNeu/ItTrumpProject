package log;

import java.util.*;
import game.*;
import java.io.*;

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
	}
	
	/**
	 * Writes the entire contents of the deck after checking
	 * whether the deck is shuffled or not
	 * @param shuffled boolean
	 */
	public void writeDeck(boolean shuffled) {
		
		StringBuilder deckLog = new StringBuilder("");
		if (shuffled) 
			deckLog.append("Shuffled deck: /n");
		else 
			deckLog.append("Initialised deck before shuffling: /n");
		
		Card [] deck = game.getDeck();
		
		for (int i = 0; i < deck.length; i ++) {
			deckLog.append(cardInfoToString(deck [i]));
		}		
		writeLog(deckLog);
	}
	
	/**
	 * Writes the contents of each players' hand of cards
	 */
	public void writePlayersDecks() {
		ArrayList<Player> activePlayers = game.getActivePlayers();
		StringBuilder playerInfo = new StringBuilder("Player's hands: /n");
		
		for (Player player : activePlayers) {
			playerInfo.append(player.getName() + "/n");
			ArrayList<Card> playersCards = player.getPile().getCards();
			for (Card card : playersCards) {
				playerInfo.append(cardInfoToString(card));
			}		
		}		
		writeLog(playerInfo);
	}
	
	/**
	 * writes the contents of the communal pile when cards are added
	 * after checking if the pile has had cards added
	 */
	public void writeCommunalPile(boolean cardsAdded) { 
		ArrayList<Card> communalCards = game.getCommunalPile().getCards();
		StringBuilder pileInfo = new StringBuilder("Communal pile ");
		
		if (cardsAdded) {
			pileInfo.append("contains: /n");
			for (Card card : communalCards) {
				pileInfo.append(cardInfoToString(card));
			}
		}
		else {
			pileInfo.append("is empty./n");
		}
		
		writeLog(pileInfo);
	}
	
	/**
	 * writes the top card of each player's pile 
	 */
	public void writeCardsInPlay() {
		StringBuilder cardsInPlayInfo = new StringBuilder("Current cards in play: /n");
		for (Player player : game.getActivePlayers()) {
			cardsInPlayInfo.append(player.getName() + ": ");
			Card playersTopCard = player.getPile().getTopCard();
			cardsInPlayInfo.append(cardInfoToString(playersTopCard));
		}		
		writeLog(cardsInPlayInfo);	
	}
	
	public void writeCategorySelected() { //UNFINISHED
		StringBuilder categoryInfo = new StringBuilder("Category selected: ");
		
	}
	
	public void writeWinner() {
		
	}
	
	public void closeLog()
	{
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
		return cardInfo.toString() + "/n";
	}
	
	
	private void writeLog(StringBuilder info) {
		try {
			logWriter.write(info.toString());
		}
		catch (IOException e) {}		
	}
	
}


