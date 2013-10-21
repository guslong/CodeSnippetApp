package model;

import java.sql.Date;
import java.sql.Timestamp;


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
    private java.util.Date dateCreated;

    /** no arg constructor */
    public Snippet() {
	dateCreated = new java.util.Date();
	
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

    // converts the date to sql datetime for compatibility with the mysql database
    public Timestamp getSQLDate() {
	Timestamp sqldate = new java.sql.Timestamp(dateCreated.getTime()); 
	return sqldate;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String toString() {
	return "ID: " + getSnippetID() + "\n" + "Title: " + getSnippetTitle() + "\n" + "Language: "
		+ getLanguage() + "\n";
    }

}
