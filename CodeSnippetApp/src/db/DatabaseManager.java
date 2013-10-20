package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	/** A connection to a database */
	private Connection conn;
	/** An executable SQL statement */
	private Statement stmt;
	/** The result of an executed SQL statement */
	private ResultSet rset;
	/** the JDBC connection URI */
	private static final String DB_CONN = "jdbc:mysql://localhost:3306/";
	/** the database */
	public static final String DB_NAME = "addressBookDB";
	
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Glasn0st!";
	
	public static final String TABLE_NAME = "snippet";

	public DatabaseManager() { // the constructor for the database manager
		// Connect to database and execute the SQL commands for creating and
		// initializing the Snippet table.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Found driver...");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load JDBC/ODBC driver.");
			e.printStackTrace();
			return;
		}

		try {
			// Connect to the database.
			conn = DriverManager.getConnection(DB_CONN + DB_NAME,
					USERNAME, PASSWORD);

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // Create a Statement
			
			// Execute the creation and initialization of table query
			DatabaseMetaData aboutDB = conn.getMetaData();
			String[] tableType = { "TABLE" };
			ResultSet rs = aboutDB.getTables(null, null, TABLE_NAME, tableType);
			if (!inspectForTable(rs, TABLE_NAME)) {
				// there is no table-- call the initTable() method to create one
				System.out.println("No table called " + TABLE_NAME + " found. Creating new one.");
				String[] SQL = initTable(); 
				for (int i = 0; i < SQL.length; i++) {
					stmt.execute(SQL[i]);
				}
			}
			System.out.println("Connected...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* doGetQuery */
	/**
	 * Executes the select query specified.
	 * 
	 * <pre>
	 * PRE:  Connection to database has been established. Query is assigned and is a simple
	 *       select statement against the table.
	 * POST: The query is executed.
	 * </pre>
	 * 
	 * @param query
	 *            a simple select query against the table
	 */
	public void doGetQuery(String query) { 
		try {
			rset = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* doInsertQuery */
	/**
	 * Executes an insert statement, specified by query.
	 * 
	 * <pre>
	 * PRE:  Connection to database has been established. Query is assigned and is a simple
	 *       insert statement into the Listings table.
	 * POST: The query is executed.
	 * </pre>
	 * 
	 * @param query
	 *            a simple insert query into the Listings table
	 */
	public void doInsertQuery(String query) { 
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* getResultSet */
	/**
	 * Returns the current value of the ResultSet instance
	 * 
	 * <pre>
	 * PRE:  True
	 * POST: ResultSet instance value is returned, its value remains the same as upon entry.
	 * </pre>
	 */
	public ResultSet getResultSet() { // a new method that will let the GUI get
										// the resultSet to manipulate it
		return rset;
	}

	/* close */
	/**
	 * Closes opened Statements and the Connection.
	 * 
	 * <pre>
	 * PRE:  Connection to database has been established. Statement has been created. Listings is a table in the db
	 * POST: If remove is true, table Listings is dropped, otherwise it is preserved.  Open Connection and Statement are closed
	 * </pre>
	 * 
	 * @param remove
	 *            boolean to specify if the table Listings should be dropped or
	 *            not.
	 */
	public void close(boolean remove) { // closes all open connections
		try {
			if (remove) {
				stmt.execute("drop table snippet;");
				System.out.println("Dropping table snippet");
				stmt.close();
				conn.close();
			} else {
				System.out.println("Saving table snippet");
			}
		} catch (SQLException e) {
			System.out.println("\n*** SQLException caught ***\n");
			e.printStackTrace();
		}
	}

	/* inspectForTable */
	/**
	 * Determines if a table exists in the db.
	 * 
	 * <pre>
	 * PRE:  Connection to database has been established. rs is not null.
	 * POST: Table has not been changed, but its presence is verified (or not).
	 * </pre>
	 * 
	 * @param rs
	 *            ResultSet from DatabaseMetaData query about existing Tables
	 * @param tableName
	 *            String identifying the table in question
	 */
	private boolean inspectForTable(ResultSet rs, String tableName)
			throws SQLException { // exception will be caught when method is
									// used
		int i;
		ResultSetMetaData rsmd = rs.getMetaData(); // Get the ResultSetMetaData
													// to use for the column
													// headings
		int numCols = rsmd.getColumnCount(); // Get the number of columns in the
												// result set

		boolean more = rs.next();
		while (more) { // Get each row, fetching until end of the result set
			for (i = 1; i <= numCols; i++) {
				if (rsmd.getColumnLabel(i) == "TABLE_NAME") // Loop through each
															// row, getting the
															// column data
															// looking for
															// Tables
					if (rs.getString(i).equals(tableName)) // If the column is
															// the TABLE_NAME,
															// is it the one we
															// are looking for?
					{
						System.out.println("Found one that equals "
								+ rs.getString(i));
						return true;
					}
			}
			System.out.println("");
			more = rs.next(); // Fetch the next result set row
		}
		return false;
	}

	/* initListingsTable */
	/**
	 * Creates the Listings table and initializes it with some records. This
	 * method connects to the MySQL database at
	 * jdbc:mysql://sql.useractive.com:3306. It then creates a table called
	 * Listings and initializes the table with some arbitrary records.
	 * 
	 * <pre>
	 * PRE: True  
	 * POST: SQL String is created for the initial population of a table named Listings.
	 * </pre>
	 */
	private String[] initTable() {
		// Create a new table
		String[] SQL = {
				"create table " + TABLE_NAME + " ("
						+ "ID int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
						+ "SNIPPET_TITLE  varchar (64),"
						+ "SNIPPET_TEXT text,"
						+ "SNIPPET_LANG varchar(16)"
						+ ");"
		};
		return SQL;
	}
}
