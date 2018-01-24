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
		if (connection != null) {
			System.out.println("Connection successful");
		}
		else {
			System.err.println("Failed to make connection!");
		}
	}

	
	/**
	 * disconnect from the database
	 */
	public void disconnectFromDatabase() {
		try {
			connection.close();
			System.out.println("Connection closed");
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed � SQL exception");
		}
	}
	
	/**
	 * Executes an sql-statement and returns the result
	 * @param sqlstatement String 
	 * @param attribute String
	 * @return
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
	 * @param sqlStatement String
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
	 * write the number of draws to the database
	 */
	public void writeNumDraws() {
		
		//create a query to insert number of draws
        String query = "UPDATE toptrumps.gamestats SET numofdraws = "+ game.getNumOfDraws()+" WHERE gameid = "+ currentGameID +";";
        updateTable(query);
	}

	/**
	 * write the number of rounds to the database
	 */
	public void writeNumRounds() {
		//create a query to insert number of rounds
        String query = "UPDATE toptrumps.gamestats SET numofrounds = "+ game.getNumOfRounds()+" WHERE gameid = "+ currentGameID +";";
        updateTable(query);
	}
	
	/**
	 * write the winner of a game to the database
	 */
	public void writeWinner() {
		
	}
	
	/**
	 * write the the rounds won by each player into the database
	 */
	public void writeRoundsWonByPlayers() {
		
	}
	
	/**
	 * Get the total amount of games played
	 * @return String contains number of games
	 */
	public String readTotalNumGames() {
		String query = "SELECT COUNT(gameid) FROM toptrumps.gamestats;";
		//TODO figure out what I need to pass
		return readFromDatabase(query, "count");
	}
	
	/**
	 * Get the amount of games won by the player
	 * @return
	 */
	public String readHumanWins() {
		String query = "SELECT COUNT(gameid) FROM toptrumps.gamestats WHERE gamestats.gamewinner = 1;";
		return readFromDatabase(query, "count");
	}
	
	/**
	 * Get the amount of games won by the computer
	 * @return String
	 */
	public String readAIWins() {
		String query = "SELECT COUNT(gameid) FROM TopTrumps.gamestats WHERE gamestats.gamewinner > 1";
		return readFromDatabase(query, "count");
	}

	/**
	 * Get the average amount of draws in a game
	 * @return String
	 */
	public String readAverageDraw() {
		String query = "SELECT AVG(numofdraws) FROM TopTrumps.gamestats;";
		return readFromDatabase(query, "avg");
	}
	
	/**
	 * Get the highest number of rounds played
	 * @return String
	 */
	public String readMaxRoundNumber() {
		String query = "SELECT MAX(numofrounds) FROM TopTrumps.gamestats;";
		return  readFromDatabase(query, "max");
	}

	/**
	 * Setter for the game object
	 * @param game Game object
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
	public static void main(String[] args) {
		Database db = new Database();
		System.out.println("games played: " + db.readTotalNumGames());
		System.out.println("max rounds: " + db.readMaxRoundNumber());
		System.out.println("average draw: " + db.readAverageDraw());
		System.out.println("computer wins: " + db.readAIWins());
		System.out.println("human wins: " + db.readHumanWins());
		db.disconnectFromDatabase();
	}
}
