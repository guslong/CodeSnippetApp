package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DatabaseManager;

/**
 * singleton object stores the list of snippets in ArrayList and manages everything to do with the snippets including
 * writing and retrieving them from the DB
 */

public class SnippetManager {

    /** the database manager to enable this object to communicate with the database */
    DatabaseManager dbm;

    /** the static member for the snippetManager, singleton */
    private static SnippetManager snippetManager = null;

    /** stores the snippets in an ArrayList */
    private ArrayList<Snippet> snippets = null;

    /**
     * private constructor initialises the dbm and attempts to get the snippets from the db
     */
    private SnippetManager() {

	// get an instance of the db manager
	dbm = new DatabaseManager();

	// get the existing snippets from the database
	snippets = getAllFromDB();
	if (snippets == null) {
	    // if null, create a blank arraylist
	    snippets = new ArrayList<Snippet>();
	}
	// otherwise the snippets ArrayList has been initialised by the DB
    }

    /**
     * returns an instance of the snippet manager. If one already exists, return the existing object (singleton pattern)
     */
    public static SnippetManager getInstance() {
	if (snippetManager == null) {
	    snippetManager = new SnippetManager();
	}
	return snippetManager;
    }

    /** @return the ArrayList of snippets */
    public ArrayList<Snippet> getSnippets() {
	return snippets;
    }

    /**
     * adds a snippet to the arraylist and to the database
     * 
     * @param snippet
     *            the snippet to add
     */
    public void addSnippet(Snippet snippet) {
	snippets.add(snippet);

	// build an insert query
	String query = "insert into " + DatabaseManager.TABLE_NAME
		+ "(SNIPPET_TITLE, SNIPPET_TEXT, SNIPPET_LANG) values " + "('"
		+ snippet.getSnippetTitle() + "' ," + " '" + snippet.getSnippetText() + "' ,"
		+ " '" + snippet.getLanguage() + "');";

	dbm.doInsertQuery(query);
    }

    // reconstruct all the snippets from the database, to be called on construction of snippet manager
    // if there is nothign in DB, create a new arraylist and return that
    public ArrayList<Snippet> getAllFromDB() {

	ArrayList<Snippet> results = new ArrayList<>();

	String query = "select * from " + DatabaseManager.TABLE_NAME;
	System.out.println(query);
	// loop through the rs and construct objects
	try {
	    ResultSet resultSet = dbm.doGetQuery(query);
	    while (resultSet.next()) {
		Snippet newSnippet = new Snippet();

		// get the id
		newSnippet.setSnippetID(resultSet.getInt("ID"));
		// get the title
		newSnippet.setSnippetTitle(resultSet.getString("SNIPPET_TITLE"));
		// get the text
		newSnippet.setSnippetText(resultSet.getString("SNIPPET_TEXT"));
		// get the language
		newSnippet.setLanguage(resultSet.getString("SNIPPET_LANG"));
		// add the object to the array
		results.add(newSnippet);
	    }
	    return results;
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return null; // return null if there was no success
    }

    // tester method to print out the snippets to sysout
    public void printAll() {
	for (Snippet snippet : snippets) {
	    System.out.println(snippet.toString());
	}
    }

}
