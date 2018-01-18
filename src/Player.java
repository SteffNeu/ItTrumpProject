/**
 * class making an abstract player
 * @author Stephanie
 *
 */
public abstract class Player 
{
	private String name;
	private Pile hand;
	private int roundsWon;
	private boolean isHuman;
	/**
	 * default constructor
	 */
	public Player()
	{
		
	}
	/**
	 * constructor
	 */
	public Player(String newName, Pile newHand, boolean human)
	{
		name = newName;
		hand = newHand;
		isHuman = human;
		roundsWon = 0;
	}
	/**
	 * gets name
	 * @return string containing name
	 */
	public String getName()
	{
		return name;
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
	 * updates the pile by removing the top card and appending the new pile
	 * @param newPile pile to be appended
	 */
	public void updatePile(Pile newPile)
	{
		hand.removeTopCard();
		hand.addPile(newPile);
	}
	/**
	 * updates the pile by removing the top card
	 */
	public void updatePile()
	{
		hand.removeTopCard();
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
		return isHuman;
	}
	abstract public void selectAttribute();
	
}
