package model;


/**
 * a snippet contains the business logic for code snippets it is the main model class of the application
 * 
 * @author anguslong
 * 
 */
public class Snippet {

    private int snippetID;
    private String snippetTitle;
    private String snippetText;
    private String language;

    /** no arg constructor */
    public Snippet() {

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

    public void setSnippetID(int snippetID) {
	this.snippetID = snippetID;
    }

    public int getSnippetID() {
	return snippetID;
    }

    public String toString() {
	return "ID: " + getSnippetID() + "\n" + "Title: " + getSnippetTitle() + "\n" + "Language: "
		+ getLanguage() + "\n";
    }

}
