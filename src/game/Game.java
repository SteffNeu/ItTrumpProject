package game;

import java.util.*;

/**
 * Game 
 * @author Stephanie
 *
 */
public class Game 
{
	/** deck of cards of length 40 */
	private Card[] deck;
	/** players in the game*/
	private ArrayList<Player> activePlayers;
	/** players not in game anymore */
	private ArrayList<Player> inactivePlayers;
	/** count of the rounds played */
	private int numOfRounds;
	/** id of current chooser in round */
	private int currentChooser;
	private int numOfDraws;
	/** communal pile for draws */
	private Pile communalPile;
	/** content of the current round  TODO maybe delete */
	private ArrayList<String> roundContent;
	
	/** 
	 * constructor
	 */
	public Game(int numberOfPlayers, Card[] deckOfCards)
	{
		numOfRounds = 0;
		numOfDraws = 0;
		// initiate deck of cards and shuffle
		deck = deckOfCards;
		shuffleDeck();
		// create the players
		createPlayers(numberOfPlayers);
		// distribute cards between the player
		distributeCards();
		// choose first player
		currentChooser=chooseFirstPlayer();
		
	}
	/**
	 * gets number of active players
	 * @return integer of number of active players
	 */
	public int getNumOfActivePlayers()
	{
		return activePlayers.size();
	}
	/**
	 * get communal pile
	 * @return pile that is communal pile
	 */
	public Pile getCommunalPile()
	{
		return communalPile;
	}
	/**
	 * get deck
	 * @return card array that is deck
	 */
	public Card[] getDeck()
	{
		return deck;
	}
	/** 
	 * gets number of rounds
	 * @return integer  num of draws
	 */
	public int getNumOfRounds()
	{
		return numOfRounds;
	}
	/**
	 * gets number of draws
	 * @return integer num of draws
	 */
	public int getNumOfDraws()
	{
		return numOfDraws;
	}
	/** 
	 * gets list of all active players
	 * @return arraylist<player> all active players
	 */
	public ArrayList<Player> getActivePlayers()
	{
		return activePlayers;
	}
	
	public void shuffleDeck() 
	{
	    // TODO Matt help pleeeaazzze collections.shuffle(arraystoList(
	}
	// TODO test this shit
	public void distributeCards()
	{
		for(int i = 0, j = 0; i < deck.length; i++, j++)
		{
			if(j > activePlayers.size())
				j = 0;
			activePlayers.get(i).getPile().addCard(deck[i]);
		}
	}
	// TODO test this shit
	public void createPlayers(int numOfPlayers)
	{
		// TODO let Tom check code
		int ID = 1;
		HumanPlayer pl1 = new HumanPlayer(ID);
		activePlayers.add(pl1);
		for(int i = 0; i < numOfPlayers; i++)
		{
			ID++;
			AIPlayer plAI = new AIPlayer(ID);
			activePlayers.add(plAI);
		}
	}
	/** 
	 * chooses the first player of the game
	 * @return integer giving the player by naming an index of activePlayers
	 */
	public int chooseFirstPlayer()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((activePlayers.size() - 0) + 1) + 0;
		
		return activePlayers.get(randomNum).getID();
	}
	
	public String executeRound(String category)
	{
		// get the id of the winner
		int winner = calculateRoundWinner(category);
		// create String for results
		String results = category;
		for(int i = 0; i < activePlayers.size(); i++)
			results = ";" + Integer.toString(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(category));
		// handle the piles
		if(winner != 0)
			handleDraw();
		else
			updatePiles(winner);
		// check if all players are still valid to keep playing
		checkForElimination();
		// 
		updateNumOfRounds();
		
		if(winner != 0)
			currentChooser = winner;
		
		return results;
	}
	public void handleDraw()
	{
		updateNumOfDraws();
		for(int i = 0; i < activePlayers.size(); i++)
		{
			communalPile.addCard(activePlayers.get(i).getPile().getTopCard());
			activePlayers.get(i).getPile().removeTopCard();
		}
	}
	public void updatePiles(int winner)
	{
		//get index of winner
		int winnerIndex = 0;
		for(int i = 0; i < activePlayers.size(); i++)
			if(activePlayers.get(i).getID()==winner)
			{
				winnerIndex = i;
				break;
			}
		for(int i = 0; i < activePlayers.size(); i++)
		{
			activePlayers.get(winnerIndex).getPile().addCard(activePlayers.get(i).getPile().getTopCard());
			activePlayers.get(i).getPile().removeTopCard();
		}
		if(communalPile.getNumOfCards() > 0)
		{
			activePlayers.get(winnerIndex).getPile().addPile(communalPile);
			communalPile.removeAllCards();
		}
	}
	
	public void checkForElimination()
	{
		for(int i = 0; i < activePlayers.size(); i++)
			if(activePlayers.get(i).getPile().getNumOfCards() == 0)
				killPlayer(i);
	}
	
	public void killPlayer(int index) {
		inactivePlayers.add(activePlayers.get(index));
		activePlayers.remove(index);
	}
	
	public void updateNumOfRounds()
	{
		numOfRounds++;
	}
	
	public int calculateRoundWinner(String ctgry)
	{
		int tempMax = 0;
		int tempID = 0;
		boolean isDraw = false;
		for (int i = 0; i < activePlayers.size(); i++)
		{
			if(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry) == tempMax)
			{
				isDraw = true;
			}
			else if(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry) > tempMax)
			{
				tempMax = activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry);
				tempID = activePlayers.get(i).getID();
				if(isDraw == true)
					isDraw = false;
			}
		}
		if(isDraw == true)
			return 0;
		else
			return tempID;
	}
	/**
	 * get the identity of the active player
	 * @return boolean if true is human
	 */
	public boolean isCurrentChooserHuman() 
	{
		return activePlayers.get(currentChooser).isHuman();
	}
	/**
	 * increments numOfDraws by one
	 */
	public void updateNumOfDraws()
	{
		numOfDraws++;
	}
}
