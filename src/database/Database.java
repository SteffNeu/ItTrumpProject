package database;

import game.Game;
import java.sql.*;

/**
 * 
 * Class to handle database interactions and requests
 * @author Tom Decke
 *
 */
public class Database {

	// current game
	private Game game; 
	//Id of the current game
	private int currentGameID;
	//define connection for the database
	private Connection connection = null;

	/**
	 * Constructor; creates a connection to the database
	 */
	public Database() {
		String database = "m_17_2354160d";
		String username = "m_17_2354160d";
		String password = "2354160d";

		try {
			connection =
					DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" +
							database,username, password);
		}
		catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
	}
	/**
	 * disconnect from the database
	 */
	public void disconnectFromDatabase() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed – SQL exception");
		}
	}

	/**
	 * Executes an sql-statement and returns the result
	 * @param String sql-statement to be executed 
	 * @param String attribute of interest
	 * @return String with desired result
	 */
	private String readFromDatabase(String sqlstatement, String attribute) {

		//reserve space for the result
		String result = "";
		//create a statement
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlstatement);
			//iterate through results
			while (rs.next()) {  
				//retrieve the attribute of interest
				result = rs.getString(attribute);
			}

		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + sqlstatement);
		}

		return result;

	}

	/**
	 * Execute a sql statement to write values into the database
	 * @param String sql-statement to be executed
	 */
	private void updateTable(String sqlStatement) {

		//create statement
		Statement stmt = null;
		try {
			//update the database
			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStatement);
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + sqlStatement);
		}
	}
	
	/**
	 * Get a String containing the formatted statistics
	 * @return String containing the statistics
	 */
	public String[] getStatistics() {
		String[] statistics = {readTotalNumGames(),readAIWins(), readHumanWins(), readAverageDraw(), readMaxRoundNumber()}; 
		return statistics;
	}

	/**
	 * Create a game entity and write the required information to the database
	 */
	public void writeInfoToDatabase() {
		createGameEntry();
		writeNumDraws();
		writeNumRounds();
		writeRoundsWonByPlayers();
		writeWinner();
	}

	/**
	 * Creates a new game entity 
	 * The ID is created based on the biggest former ID 
	 * The draw and round counter are initialised to 0
	 * All remaining values are initialised to null
	 */
	private void createGameEntry() {
		//get the highest id from the database and increment it
		String query = "SELECT MAX(gameid) FROM toptrumps.gamestats;";
		currentGameID = Integer.parseInt(readFromDatabase(query, "max"))+1;

		//create a new game entity
		query = "INSERT INTO toptrumps.gamestats VALUES ("+currentGameID+",0,0,null,null,null,null,null,null)";
		updateTable(query);
	}

	/**
	 * Write the number of draws to the database
	 */
	private void writeNumDraws() {
		//create a query to insert number of draws
		String query = "UPDATE toptrumps.gamestats SET numofdraws = "+ game.getNumOfDraws()+" WHERE gameid = "+ currentGameID +";";
		updateTable(query);
	}

	/**
	 * Write the number of rounds to the database
	 */
	private void writeNumRounds() {
		//create a query to insert number of rounds
		String query = "UPDATE toptrumps.gamestats SET numofrounds = "+ game.getNumOfRounds()+" WHERE gameid = "+ currentGameID +";";
		updateTable(query);
	}

	/**
	 * Write the winner of a game to the database
	 */
	private void writeWinner() {
		String query ="UPDATE toptrumps.gamestats SET gamewinner = " + game.getCurrentPlayer().getID()+ " WHERE gameid = "+ currentGameID +";";
		updateTable(query);
	}

	/**
	 * Write the the rounds won by each player into the database
	 */
	private void writeRoundsWonByPlayers() {
		String[] roundsWBP = game.getRoundsWonPerPlayer();
		String query = String.format("UPDATE toptrumps.gamestats SET humanrounds = %s, ai1rounds = %s, ai2rounds = %s, ai3rounds = %s, ai4rounds = %s  WHERE gameid = %d;", roundsWBP[0],roundsWBP[1],roundsWBP[2],roundsWBP[3],roundsWBP[4],currentGameID);
		updateTable(query);
	}

	/**
	 * Get the total amount of games played
	 * @return String contains number of games
	 */
	private String readTotalNumGames() {
		String query = "SELECT COUNT(gameid) FROM toptrumps.gamestats;";
		return readFromDatabase(query, "count");
	}

	/**
	 * Get the amount of games won by the player
	 * @return String number of games won by human player
	 */
	private String readHumanWins() {
		String query = "SELECT COUNT(gameid) FROM toptrumps.gamestats WHERE gamestats.gamewinner = 1;";
		return readFromDatabase(query, "count");
	}

	/**
	 * Get the amount of games won by the computer
	 * @return String number of games won by computer
	 */
	 private String readAIWins() {
		String query = "SELECT COUNT(gameid) FROM TopTrumps.gamestats WHERE gamestats.gamewinner > 1";
		return readFromDatabase(query, "count");
	}

	/**
	 * Get the average amount of draws in a game rounded to two decimal digits
	 * @return String number of average draws
	 */
	private String readAverageDraw() {
		String query = "SELECT AVG(numofdraws) FROM TopTrumps.gamestats;";
		double tmp = Double.parseDouble(readFromDatabase(query, "avg"));
		return String.format("%.2f", tmp);
	}

	/**
	 * Get the highest number of rounds played
	 * @return String highest game number
	 */
	private String readMaxRoundNumber() {
		String query = "SELECT MAX(numofrounds) FROM TopTrumps.gamestats;";
		return  readFromDatabase(query, "max");
	}

	/**
	 * Setter for the game object
	 * @param game Game-object
	 */
	public void setGame(Game game) {
		this.game = game;
	}
}