package test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import model.Snippet;
import model.SnippetManager;

import org.junit.Before;
import org.junit.Test;

public class _TestSnippet {

	private SnippetManager manager;

	@Before
	public void setUp() {
		manager = SnippetManager.getInstance();
	}
	
	@Test
	public void saveNewSnippetObject() {
		Snippet snip = new Snippet();
		snip.setLanguage("PHP");
		snip.setSnippetTitle("Some php");
		snip.setSnippetText("blah ?php blah");
		manager.addSnippet(snip);

	}
	
}
