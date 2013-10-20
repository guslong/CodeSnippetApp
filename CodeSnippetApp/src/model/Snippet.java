package model;

import java.util.UUID;

/**
 * a snippet contains the business logic for code snippets
 * it is the main model class of the application
 * @author anguslong
 *
 */
public class Snippet {

	private UUID snippetID;
	private String snippetTitle;
	private String snippetText;
	private String language;
	
	/**
	 * constructor
	 * @param name The title of the snippet
	 * @param language The programming language
	 * 
	 */
	public Snippet(String name, String language) {
		this.snippetID = UUID.randomUUID();
		this.snippetTitle = name;
		this.language = language;
	}

	public String getSnippetTitle() {
		return snippetTitle;
	}

	public void setSnippetTitle(String snippetTitle) {
		this.snippetTitle = snippetTitle;
	}

	public String getSnippetText() {
		return snippetText;
	}

	public void setSnippetText(String snippetText) {
		this.snippetText = snippetText;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UUID getSnippetID() {
		return snippetID;
	}

	public String toString() {
		return "ID: " + getSnippetID() +"\n"
				+ "Title: " + getSnippetTitle() + "\n"
				+ "Language: " + getLanguage()  +"\n";
	}
	
}
