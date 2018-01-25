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
		activePlayers = new ArrayList<Player>();
		inactivePlayers = new ArrayList<Player>();
		communalPile = new Pile();
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
	 * @return arraylist<player> all active players
	 */
	public ArrayList<Player> getActivePlayers()
	{
		return activePlayers;
	}
	
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
	// TODO test this shit
	public void distributeCards()
	{
		for(int i = 0, j = 0; i < deck.length; i++, j++)
		{
			if(j >= activePlayers.size())
				j = 0;
			activePlayers.get(j).getPile().addCard(deck[i]);
		}
	}
	// TODO test this shit
	public void createPlayers(int numOfPlayers)
	{
		// TODO let Tom check code
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
			nameAI = nameAI.substring(0, 2);
			nameAI += Integer.toString(ID-1);		}
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
	
	public String executeRound(String category)
	{
		// get the id of the winner
		int winner = calculateRoundWinner(category);
		// create StringBuilder for results
		StringBuilder results = new StringBuilder("");
		for(int i = 0; i < activePlayers.size(); i++)
			results.append(activePlayers.get(i).getName() 
			+ ": "
			+ "with Card " + activePlayers.get(i).getPile().getTopCard().getName()
			+ " with value "
			+ Integer.toString(activePlayers.get(i).getPile().getTopCard().getValueAtCategory(category))
			+ "\n");
		// handle the piles
		if(winner == 0)
			handleDraw();
		else
		{
			updatePiles(winner);
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
	
	//return winner
	// return get all players 
	/**
	 * 
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
		return roundsWBP;
		
	}
}
