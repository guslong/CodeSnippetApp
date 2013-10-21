package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db.DatabaseManager;

public class AddSnippetDialog extends JDialog {

    private JTextField txtSnippetTitle = new JTextField(32);
    private JTextField txtSnippetLang = new JTextField(16);
    private JTextArea txtAreaSnippetText = new JTextArea();
    private JButton addButton;
    private JButton cancelButton;

    public AddSnippetDialog(final JFrame owner) {
	// set the dialog title and size
	super(owner, "Add Snippet", true);
	setSize(280, 150);
	setVisible(true);
	
	// Create the center panel which contains the input fields
	JPanel center = new JPanel();

	// Create the south panel, which contains the buttons
	JPanel south = new JPanel();
	addButton = new JButton("Add");
	cancelButton = new JButton("Cancel");
	addButton.setEnabled(false);
	south.add(addButton);
	addButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent aEvent) {

		// TODO get manager and insert the record into the database

		dispose(); // get rid of the dialog
	    }
	});

	south.add(cancelButton);
	cancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent aEvent) {
		dispose();
	    }
	});

	
	// add the panels to the container panel
	Container contentPane = getContentPane();
	contentPane.add(center, BorderLayout.CENTER);
	contentPane.add(south, BorderLayout.SOUTH);
    }
}
