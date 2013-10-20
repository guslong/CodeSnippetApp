package test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import model.Snippet;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;


public class _TestSnippet {

	private SnippetManager manager;

	@Before
	public void setUp() {
		manager = SnippetManager.getInstance();
	}
	
	@Test
	public void newSnippetObject() {
		Snippet snip = new Snippet("Title", "Java");
		assertNotNull(snip);
		//System.out.println(snip.getSnippetID());
	}
	
	@Test
	public void getSnippetManager() {
		// must return the same object, as it is a singleton
	
		SnippetManager manager2 = SnippetManager.getInstance();
		assertEquals(manager, manager2);		
	}
	
	@Test
	public void getSnippetsFromManagerSingleton() {
		
		ArrayList<Snippet> snippetsList = manager.getSnippets();
		assertNotNull(snippetsList);
	}
	
	@Test
	public void printSnippets() {
		Snippet a = new Snippet("Boo", "Java");
		manager.addSnippet(a);
		manager.printAll();
		assertThat(a.getLanguage(), equalTo("Java"));	
		
	}
	
	
}
