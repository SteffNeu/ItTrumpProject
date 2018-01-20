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
	/**
	 * constructor
	 */
	public Player(int identification)
	{
		ID = identification;
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
		return human;
	}
	
	/**
	 * @return Pile the hand of the player
	 */
	public Pile getPile() {
		return hand;
	}
	
}
