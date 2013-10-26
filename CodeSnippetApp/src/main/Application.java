package main;

import gui.MainApplicationFrame;

import javax.swing.JFrame;

public class Application {

    public static void main(String[] args) {

	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		test();
	    }

	});

    }

    public static void test() {
	JFrame appFrame = new MainApplicationFrame();
	// new AddSnippetDialog(appFrame);
    }
}
