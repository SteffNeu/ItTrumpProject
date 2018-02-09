package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String isCurrentHuman()	{
		String val = "{\"curHuman\":";
		if(this.game.isCurrentChooserHuman())
			val +=  "true}";
		else
			val +=  "false}";
		System.out.println(val);
		return val;
	}
	
	@GET
	@Path("/game/isHumanPlaying")
	public String isHumanPlaying()	{
		String val = "{\"humanplaying\":";
		if(this.game.isHumanPlaying())
			val +=  "true}";
		else
			val +=  "false}";
		System.out.println(val);
		return val;
	}
	
	@GET
	@Path("/game/setUpGameDisplay")
	public String setUpGameDisplay()
	{
		String roundResult = this.getUpdateInfoString();
		return roundResult; //TODO object formatting
	}
	
	@GET
	@Path("/game/executeRoundHuman")
	public String executeRoundHuman(@QueryParam("category") String category) throws IOException
	{	
		game.executeRound(category);
		game.checkForElimination();
		String roundResult = this.getUpdateInfoString();
		return roundResult;	
	}
	
	@GET
	@Path("/game/executeRoundAI")
	public String executeRoundAI()
	{
		game.executeRound(game.getCurrentPlayer().selectAttribute());
		game.checkForElimination();
		String roundResult = this.getUpdateInfoString();
		return roundResult; //TODO object formatting
	}

	@GET
	@Path("/game/getActivePlayers")
	public String getActivePlayers() throws JsonProcessingException {
		ArrayList<String> playerNames = new ArrayList();
		ArrayList<Player> players = game.getActivePlayers();
		for (Player p : players) {
			playerNames.add(p.getName());
		}
		System.out.println(oWriter.writeValueAsString(playerNames));
		return oWriter.writeValueAsString(playerNames);
	}

	
	@GET
	@Path("/game/getCategory")
	public String getCategory(@QueryParam("cat") String catRef){
		String key ="{\"key\":\"";
		Card card = game.getCurrentPlayer().getPile().getTopCard();
		HashMap<String, Integer> cardCategories = card.getCategories();
		int i = 1;
		for (Map.Entry<String, Integer> entry: cardCategories.entrySet()) {
			if(i == Integer.parseInt(catRef)) {
				key += entry.getKey();
			}
			
			i++;
		}
		System.out.println(key);
		return key += "\"}";
	}

	
	@GET
	@Path("/game/getTopCards")
	public String getTopCards() {
		
		ArrayList<Player> players = game.getActivePlayers();
		
		StringBuilder topCardInfo = new StringBuilder("{");
		for(int i = 0; i < players.size(); i++)
		{	
			Card topCard = players.get(i).getPile().getTopCard();
			topCardInfo.append("\"" + players.get(i).getName() + "\":{\"cardName\":\"" + topCard.getName() + "\", ");	
			
			HashMap<String, Integer> cardCategories = topCard.getCategories(); 
			int j = 1;
			for (Map.Entry<String, Integer> entry: cardCategories.entrySet()) {
				topCardInfo.append("\"category" + j + "\":\"" + entry.getKey() + " " + entry.getValue() + "\",");
				j ++;
			}
			topCardInfo.deleteCharAt(topCardInfo.length() - 1);
			topCardInfo.append("},");
		}
		topCardInfo.deleteCharAt(topCardInfo.length() - 1);
		topCardInfo.append("}");
		return topCardInfo.toString();
	}
	
	@GET
	@Path("/game/getNumCards")
	public String getNumCards() {
		ArrayList<Player> players = game.getActivePlayers();
		StringBuilder roundResult = new StringBuilder("{");
		for (Player player : players){
			roundResult.append("\"" + player.getName() + "cards\":\"");
			roundResult.append(player.getPile().getNumOfCards() + "\", ");
		}
		roundResult.deleteCharAt(roundResult.length()-2);
		roundResult.deleteCharAt(roundResult.length()-1);
		roundResult.append("}");
		return roundResult.toString();
	}
	
	private String getUpdateInfoString() {
		
		
		ArrayList<Player> players = game.getActivePlayers();

		StringBuilder roundResult = new StringBuilder("{");
		
		roundResult.append("\"activePlayer\":\""+game.getCurrentPlayer().getName()+"\", ");
				
		roundResult.append("\"roundnumber\":\"" + Integer.toString(game.getNumOfRounds()) + "\", ");
				
		roundResult.append("\"communalcardnumber\":\"" + Integer.toString(game.getCommunalPile().getNumOfCards()) + "\", ");
		
		// get the number of cards for each player
		for (Player player : players)
		{
			roundResult.append("\"" + player.getName() + "cards\":\"");
			roundResult.append(player.getPile().getNumOfCards() + "\", ");
		}
		
		roundResult.append("\"numofdraws\":\"" + game.getNumOfDraws() + "\",");
		
		String isHumanPlaying = "\"humanplaying\":";
		if (game.isHumanPlaying())
			isHumanPlaying += "true, ";
		else
			isHumanPlaying += "false, ";
		roundResult.append(isHumanPlaying);
		
		String isGameOver = "\"gameover\":";
		if (players.size() == 1) {
			isGameOver += "true}";
//			Database db = new Database();
//			db.setGame(game);
//			db.writeInfoToDatabase();
//			db.disconnectFromDatabase();
		}
		else {
			isGameOver += "false}";
		}
		roundResult.append(isGameOver);
		return roundResult.toString()  ;			
	}

	
	
	public Card[] getDeck()
	{
		return TopTrumpsCLIApplication.initializeDeck();
	}
	
}
