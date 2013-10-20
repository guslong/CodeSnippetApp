package model;

import java.util.ArrayList;


/** singleton object
 * stores the list of snippets in ArrayList
 */

public class SnippetManager {

	/** the static member for the snippetManager, singleton */
	private static SnippetManager snippetManager = null;
	
	/** stores the snippets in an ArrayList */
	private ArrayList<Snippet> snippets;

	/** private constructor */
	private SnippetManager() {
		snippets = new ArrayList<>();
	}

	/** gets an instance of the snippet manager
	 * if one already exists, return the existing object
	 * (singleton pattern) */
	
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
	public void addSnippet(Snippet snippet) {
		snippets.add(snippet);
	}
	
	//tester method to print out the snippets to sysout
	public void printAll() {
		for (Snippet snippet : snippets) {
			System.out.println(snippet.toString());
		}
	}

}
