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
	/** true if last round was a draw */
	private boolean lastRoundDraw;
	
	/** 
	 * constructor
	 */
	public Game(Card[] deckOfCards)
	{
		// initialize variables
		numOfRounds = 0;
		numOfDraws = 0;
		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<Player>();
		communalPile = new Pile();
		lastRoundDraw = false;
		// initiate deck of cards and shuffle
		deck = deckOfCards;

	}
	/**
	 * calls the methods needed to start a game
	 * @param numberOfPlayers
	 */
	public void startGame(int numberOfPlayers)
	{
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
	 * gets the player that is currently choosing the categories
	 * @return Player that current chooser
	 */
	public Player getCurrentPlayer()
	{
		for(int i = 0; i < activePlayers.size(); i++)
		{
			if(activePlayers.get(i).getID() == currentChooser)
				return activePlayers.get(i);
		}
		return null;
	}
	/** 
	 * gets list of all active players
	 * @return Arraylist<player> all active players
	 */
	public ArrayList<Player> getActivePlayers()
	{
		return activePlayers;
	}
	/**
	 * deck is shuffled into a random order
	 */
	public void shuffleDeck() 
	{
		Random rand = new Random();

		int index = deck.length - 1;
		while (index > 0) 
		{
			int r = rand.nextInt(index + 1);

			Card tempSwap = deck [index];
			deck [index] = deck [r];
			deck [r] = tempSwap;

			index --;
		}
	}
	/**
	 * distributes the deck between the players one by one
	 */
	public void distributeCards()
	{
		for(int i = 0, j = 0; i < deck.length; i++, j++)
		{
			if(j >= activePlayers.size())
				j = 0;
			activePlayers.get(j).getPile().addCard(deck[i]);
		}
	}
	/**
	 * initializes the players for the game, assigning names and adding them to the list of active players
	 * @param numOfPlayers integer specifying the amount of AI players desired
	 */
	public void createPlayers(int numOfPlayers)
	{
		int ID = 1;
		String nameHuman = "human";
		HumanPlayer pl1 = new HumanPlayer(ID, nameHuman);
		activePlayers.add(pl1);
		String nameAI = "ai1";
		for(int i = 0; i < numOfPlayers; i++)
		{
			ID++;
			AIPlayer plAI = new AIPlayer(ID, nameAI);
			activePlayers.add(plAI);
			nameAI = nameAI.substring(0, 2) + Integer.toString(ID);
		}
	}
	/** 
	 * chooses the first player of the game
	 * @return integer giving the player by naming an index of activePlayers
	 */
	public int chooseFirstPlayer()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(activePlayers.size());
		
		return activePlayers.get(randomNum).getID();
	}
	/**
	 * calls all functions and sets all variables necessary for the procedure of executing a round
	 * @param category string containing the chosen category for the round
	 * @return String containing information about the players and their category value
	 */
	public String executeRound(String category)
	{
		// get the id of the winner
		int winner = calculateRoundWinner(category);
		// create StringBuilder for results
		StringBuilder results = new StringBuilder("");
		for(int i = 0; i < activePlayers.size(); i++)
		{	
			results.append(activePlayers.get(i).getName() 
			+ ": "
			+ "with Card " + activePlayers.get(i).getPile().getTopCard().getName()
			+ " with value "
			+ Integer.toString(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(category))
			+ "\n");
		}
		
		// handle the piles
		if(winner == 0)
		{
			handleDraw();
			if(winner == 0)
				results.append("There was a draw.  The communal pile contains "
						+ Integer.toString(communalPile.getNumOfCards()) + " cards. \n");
			updateNumOfDraws();
			lastRoundDraw = true;
		}
		else
		{
			updatePiles(winner);
			lastRoundDraw = false;
			// sets the current chooser to last winner
			currentChooser = winner;
			// increments winners rounds won
			this.getCurrentPlayer().increaseRoundsWon();
		}
		// check if all players are still valid to keep playing
		checkForElimination();
		// update number of rounds
		updateNumOfRounds();
				
		return results.toString();
	}
	/**
	 * takes first card of all players and appends them to the communal pile
	 */
	public void handleDraw()
	{
		for(int i = 0; i < activePlayers.size(); i++)
		{
			communalPile.addCard(activePlayers.get(i).getPile().getTopCard());
			activePlayers.get(i).getPile().removeTopCard();
		}
	}

	/**
	 * takes first card of all players and appends them to the winners pile
	 * if the communal pile contains cards they are also appended to the winners pile
	 */
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
		// redistribute cards to winner
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
	/**
	 * gets information if last round was a draw
	 * @return boolean, true = last round was a draw
	 */
	public boolean lastRoundWasDraw()
	{
		return lastRoundDraw;
	}
	/**
	 * checks if any players have lost in the round and checks that current chooser is an active player
	 */
	public void checkForElimination()
	{
		for(int i = 0; i < activePlayers.size(); i++)
			if(activePlayers.get(i).getPile().getNumOfCards() == 0)
			{
				if(activePlayers.get(i).getID()== currentChooser)
				{
					killPlayer(i);
					currentChooser = activePlayers.get(0).getID();
				}
				else
					killPlayer(i);	
			}
	}
	/**
	 * puts given player at index into the inactive player list and removes them from the active player list
	 * @param index integer giving index of player position in active player list
	 */
	public void killPlayer(int index) {
		inactivePlayers.add(activePlayers.get(index));
		activePlayers.remove(index);
	}
	/**
	 * updates number of rounds played so far
	 */
	public void updateNumOfRounds()
	{
		numOfRounds++;
	}
	/**
	 * calculates the winner of the round by finding the highest value
	 * @param ctgry String containing category name of the round
	 * @return integer of winners ID or 0 when draw
	 */
	public int calculateRoundWinner(String ctgry)
	{
		int tempMax = 0;
		int tempID = 0;
		boolean isDraw = false;
		// searching for max
		for (int i = 0; i < activePlayers.size(); i++)
		{
			// checking for draws
			if(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry) == tempMax)
			{
				isDraw = true;
			}
			else if(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry) > tempMax)
			{
				tempMax = activePlayers.get(i).getPile().getTopCard().getValueAtCategory(ctgry);
				tempID = activePlayers.get(i).getID();
				if(isDraw)
					isDraw = false;
			}
		}
		if(isDraw)
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
		int winnerIndex = 0;
		for(int i = 0; i < activePlayers.size(); i++)
			if(activePlayers.get(i).getID()==currentChooser)
			{
				winnerIndex = i;
				break;
			}
		return activePlayers.get(winnerIndex).isHuman();
	}
	/**
	 * increments numOfDraws by one
	 */
	public void updateNumOfDraws()
	{
		numOfDraws++;
	}
	/**
	 * gets rounds each player has won
	 * @return String[] containing the number of games won by each player
	 */
	public String[] getRoundsWonPerPlayer(){
		
		//initialize empty array
		String[] roundsWBP = {null, null, null, null, null};
		
		//iterate through all players
		for (Player p : inactivePlayers) {
			//map the id to a position in the array and add the amount of rounds won
			roundsWBP[p.getID()-1] = Integer.toString(p.getRoundsWon());
		}
		Player wp = activePlayers.get(0);
		roundsWBP[wp.getID()-1] = Integer.toString(wp.getRoundsWon());
		return roundsWBP;
		
	}
	/**
	 * checks if human is still an active player
	 * @return boolean if true human still active player
	 */
	public boolean isHumanPlaying()
	{
		if(activePlayers.get(0).isHuman())
			return true;
		else 
			return false;
	}
}
