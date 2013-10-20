package model;

import java.util.ArrayList;

import db.DatabaseManager;

/**
 * singleton object stores the list of snippets in ArrayList
 */

public class SnippetManager {

	/**
	 * the database manager to enable this object to communicate with the
	 * database
	 */
	DatabaseManager dbm;

	/** the static member for the snippetManager, singleton */
	private static SnippetManager snippetManager = null;

	/** stores the snippets in an ArrayList */
	private ArrayList<Snippet> snippets;

	/** private constructor */
	private SnippetManager() {
		snippets = new ArrayList<>(); // TODO in future this will initialise the
										// arraylist from the database
		dbm = new DatabaseManager(); // get an instance of the db manager
	}

	/**
	 * gets an instance of the snippet manager if one already exists, return the
	 * existing object (singleton pattern)
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

	/** adds a snippet to the arraylist */
	// TODO and add to the database
	public void addSnippet(Snippet snippet) {
		snippets.add(snippet);

		// build a query to enable to insert into database
		String query = "insert into " + DatabaseManager.TABLE_NAME
				+ "(SNIPPET_TITLE, SNIPPET_TEXT, SNIPPET_LANG) values " + "('"
				+ snippet.getSnippetTitle() + "' ," + " '"
				+ snippet.getSnippetText() + "' ," + " '"
				+ snippet.getLanguage() + "');";
		dbm.doInsertQuery(query);
	}

	// tester method to print out the snippets to sysout
	public void printAll() {
		for (Snippet snippet : snippets) {
			System.out.println(snippet.toString());
		}
	}

}
