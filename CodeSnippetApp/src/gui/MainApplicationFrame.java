package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainApplicationFrame extends JFrame {

    public MainApplicationFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setPreferredSize(new Dimension(800,600));
	setVisible(true);
	initGUI();
	pack();
    }

    private void initGUI() {
	// need a menu
	
	// need a list
	
	// need a scroll view for the snippet text

	// just for testing
	new AddSnippetDialog(this);
    }
    
}
