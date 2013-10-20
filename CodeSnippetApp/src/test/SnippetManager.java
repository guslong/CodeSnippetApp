package test;

import java.util.ArrayList;

import model.Snippet;

/** singleton object
 * stores the list of snippets in ArrayList
 */

public class SnippetManager {

	private static SnippetManager snippetManager = null;
	private ArrayList<Snippet> snippets;

	private SnippetManager() {
		snippets = new ArrayList<>();
	}

	public static SnippetManager getInstance() {
		if (snippetManager == null) {
			snippetManager = new SnippetManager();
		}
		return snippetManager;
	}

	public ArrayList<Snippet> getSnippets() {
		return snippets;
		
	}
	
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
