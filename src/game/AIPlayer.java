package game;

/**
 * Class to create an AI-Player
 * Inherits from the Player.java class
 * 
 * @author Stephanie Neubauer
 * 
 */
public class AIPlayer extends Player
{
	/**
	 * default constructor to create an AI-player
	 */
	public AIPlayer(int identification)
	{
		super(identification);
		human = false;
	}
	
	/** 
	 * @return String containing the category with the highest value  
	 */
	public String selectAttribute() {
		return hand.getTopCard().getHighestCategory();
	}

}
