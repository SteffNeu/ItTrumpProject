import java.io.*;

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
	// TODO maybe discuss to make one human player
	// and another object for all AI players?
	private Player[] players;
	private int numOfPlayers;
	private int numOfRounds;
	private Pile communalPile;
	// DATABASE HERE TODO
	
	private String logFileName = "logFile.txt";
	
	private FileWriter logFile;
	/**
	 * default constructor
	 */
	public Game() 
	{
		numOfRounds = 0;		
	}
	/** 
	 * constructor
	 * TODO find out what is already given at what needs to be initialized
	 */
	public Game(int numberOfPlayers)
	{
		numOfRounds = 0;
	}
	
	public void shuffleDeck() 
	{
		// no need for variable in header, we have deck as instant variable
	}
	
	public void distributeCards(Player[] playerArray)
	{
		
	}
	
	public void executeRound()
	{
		
	}
	
	public void writeToDatabase()
	{
		// called in execute Round
	}
	
	public void calculateRoundWinner()
	{
		// called in execute Round
	}
	
	public Player getMaxAttribute()
	{ // isn't this the same as the one above?
		return null;
	}
	
	public void displayStatus() {}
	
	public void displayRoundResult() {}
	
	// there are more functions, but i think we should go over them again
	
}
