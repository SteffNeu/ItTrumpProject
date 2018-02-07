package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import game.*;
import database.*;
import commandline.*;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	/** TODO comment */
	Game game;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/stats")
	/**
	 * Get the information from the database and format them so that they can be processed
	 * @return String containing key-value pairs with information from the database
	 */
	public String getStatistics(){
		Database db = new Database();
		String[] rs = db.getStatistics();
		db.disconnectFromDatabase();
		String stats = String.format("{ \"games\":\"%s\", \"aiWins\":\"%s\", \"humanWins\":\"%s\", \"avgDraws\":\"%s\", \"maxRounds\":\"%s\"}", rs[0],rs[1],rs[2],rs[3],rs[4]);
		return stats;
	}

	@GET
	@Path("/game")
	public String GameOnline(@QueryParam("3") String numOfPlayers) throws IOException
	{
		String returnString = "";
		//StringBuilder sb = new StringBuilder(returnString);
		
		game = new Game(getDeck());
		game.startGame(Integer.parseInt(numOfPlayers));
		
		returnString = "The game will start now with "
				+ game.getNumOfActivePlayers() + " players. Have fun.";
		
		return returnString;
	}
	
	@GET
	@Path("/game/isCurrentHuman")
	public String isCurrentHuman() throws JsonProcessingException
	{
		String val = "{\"curHuman\":\"";
		if(this.game.isCurrentChooserHuman())
			val +=  "1\"}";
		else
			val +=  "0\"}";
		System.out.println(val);
		return val;
	}
	
	@GET
	@Path("/game/executeRoundHuman")
	public String executeRoundHuman(@QueryParam("category") String category) throws IOException
	{	
		String roundResult = this.executeRound(category);
		return roundResult;	
	}
	

	@GET
	@Path("/game/executeRoundAI")
	public String executeRoundAI()
	{
		String roundResult = this.executeRound(game.getCurrentPlayer().selectAttribute());
		System.out.println(roundResult);
		return roundResult; //TODO object formatting
	}
	
	@GET
	@Path("/game/isHumanPlaying")
	public String isHumanPlaying(){
		String isHumanPlaying = "{\"humanplaying\":";
		if (game.isHumanPlaying())
			isHumanPlaying += "true}";
		else
			isHumanPlaying += "false}";
		
		return isHumanPlaying;
	}
	
	private String executeRound(String category) {
		
		
		game.executeRound(category);
		ArrayList<Player> players = game.getActivePlayers();
		
		// get top cards of each player
		StringBuilder roundResult = new StringBuilder("{"); //TODO formatting in object
		for(int i = 0; i < players.size(); i++)
		{	
			roundResult.append("\"" + players.get(i).getName() + "\":\"" + players.get(i).getPile().getTopCard().getName() + "\", ");	
		}
		roundResult.append("\"activePlayer\":\""+game.getCurrentPlayer().getName()+"\", ");
				
		roundResult.append("\"roundnumber\":\"" + Integer.toString(game.getNumOfRounds()) + "\", ");
				
		roundResult.append("\"communalcardnumber\":\"" + Integer.toString(game.getCommunalPile().getNumOfCards()) + "\", ");
		
		// get the number of cards for each player
		for (Player player : players)
		{
			roundResult.append("\"" + player.getName() + "cards\":\"");
			roundResult.append(player.getPile().getNumOfCards() + "\", ");
		}
		
		String isHumanPlaying = "\"humanplaying\":\"";
		if (game.isHumanPlaying())
			isHumanPlaying += "1\", ";
		else
			isHumanPlaying += "0\", ";
		roundResult.append(isHumanPlaying);
		
		String isGameOver = "\"gameover\":\"";
		if (players.size() == 1) {
			isGameOver += "1\"}";
			Database db = new Database();
			db.setGame(game);
			db.writeInfoToDatabase();
			db.disconnectFromDatabase();
		}
		else {
			isGameOver += "0\"}";
		}
		roundResult.append(isGameOver);
		
		return roundResult.toString()  ;			
	}
	
	public Card[] getDeck()
	{
		return TopTrumpsCLIApplication.initializeDeck();
	}
}
