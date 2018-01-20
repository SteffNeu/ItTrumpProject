package database;


import java.sql.*;

/**
 * 
 * Class to handle database interactions and requests
 * @author Tom Decke
 *
 */
public class Database {
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
			System.out.println("Connection could not be closed – SQL exception");
		}
	}
	
	/**
	 * Executes an sql-statement and returns the result
	 * @param sqlstatement String 
	 * @param attribute String
	 * @return
	 */
	private String readFromDatabase(String sqlstatement, String attribute) {

		private Game game;
		//reserve space for the result
		String result = "";
		//create a statement
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlstatement);
			//TODO review this -can there be several result sets?
			//TODO What about aggregates
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
	 * Execute a sql statement to insert values into the database
	 * @param sqlStatement String
	 */
	private void insertIntoTable(String sqlStatement) {

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

	public void writeNumDraws() {
		
	}

	public void writeNumRounds() {
		
	}
	
	public void writeWinner() {
		
	}
	
	public void writeRoundsWonByPlayers() {
	
	}
	
	public String readTotalNumGames() {
		return "";
	}
	
	public String readHumanWins() {
		return "";
	}
	
	public String readAIWins() {
		return "";
	}

	public String readAverageDraw() {
		return "";
	}
	
	public String readMaxRoundNumber() {
		return "";
	}

	public void setGame() {
		
	}
}
