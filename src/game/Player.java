package game;
/**
 * class making an abstract player
 * @author Stephanie
 *
 */
public abstract class Player 
{
	protected int ID;
	protected Pile hand;
	protected int roundsWon;
	protected boolean human;
	protected String name;
	/**
	 * constructor
	 */
	public Player(int identification, String name)
	{
		ID = identification;
		this.name = name;
		hand = new Pile();
		roundsWon = 0;
	}
	/**
	 * gets ID
	 * @return integer containing ID
	 */
	public int getID()
	{
		return ID;
	}
	/**
	 * get rounds won
	 * @return integer rounds won
	 */
	public int getRoundsWon()
	{
		return roundsWon;
	}
	/**
	 * gets name of the player
	 * @return String containing name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * increases the rounds won by one
	 */
	public void increaseRoundsWon()
	{
		roundsWon++;
	}
	/**
	 * confirms if player is human
	 * @return boolean if true player is human
	 */
	public boolean isHuman()
	{
		return human;
	}
	
	/**
	 * @return Pile the hand of the player
	 */
	public Pile getPile() {
		return hand;
	}
	/**
	 * choosing the best attribute in the round
	 * @return String of a category
	 */
	public abstract String selectAttribute();
	
}
