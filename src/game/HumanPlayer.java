package game;
/**
 * Class to create an AI-Player
 * Inherits from the Player.java class
 * 
 * @author Stephanie Neubauer
 *
 */
public class HumanPlayer extends Player
{
	/**
	 * default constructor to create a human player
	 */
	public HumanPlayer(int identification, String name) 
	{
		super(identification, name);
		human = true;
	}

	public String selectAttribute() {
		return null;
	}	
}
