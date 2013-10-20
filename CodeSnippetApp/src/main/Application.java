package main;

import model.Snippet;
import model.SnippetManager;
import db.*;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test();	
	}

	public static void test() {
	    SnippetManager manager  = SnippetManager.getInstance();
		Snippet newSnippet = new Snippet("Blah blah", "Java");
		newSnippet.setSnippetText("class SecurityUtils {  private static final byte[] salt = { }");
		// test adding a snippet to the array and the database
		manager.addSnippet(newSnippet);
	}
}
