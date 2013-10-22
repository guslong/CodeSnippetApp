package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Snippet;
import model.SnippetManager;

public class AddSnippetDialog extends JDialog {

    public class InputListener implements DocumentListener {

	/**
	 * this method is called when data is entered into the fields. Checks to see if all fields are completed, then
	 * enables the add button
	 * 
	 * @param e
	 *            the document event
	 */

	@Override
	public void insertUpdate(DocumentEvent e) {
	    if (txtSnippetTitle.getDocument().getLength() > 0
		    && txtSnippetLang.getDocument().getLength() > 0
		    && txtAreaSnippetText.getDocument().getLength() > 0) {
		addButton.setEnabled(true);
		
	    }
	}

	/**
	 * this method is called when data is remvoed from the fields. If any field is blank
	 * the add button is disabled.
	 * 
	 * @param e
	 *            the document event
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
	    if (txtSnippetTitle.getDocument().getLength() == 0
		    || txtSnippetLang.getDocument().getLength() == 0
		    || txtAreaSnippetText.getDocument().getLength() == 0) {
		addButton.setEnabled(false);

	    }

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	    /** Empty implementation. Method necessary for implementation of DocumentListener */

	}

    }

    private JTextField txtSnippetTitle = new JTextField(32);
    private JTextField txtSnippetLang = new JTextField(16);
    private JTextArea txtAreaSnippetText;
    private JButton addButton;
    private JButton cancelButton;

    public AddSnippetDialog(final JFrame owner) {
	// set the dialog title and size
	super(owner, "Add Snippet", true);
	setSize(420, 300);
	setLayout(new BorderLayout());

	// Create the north panel which contains the title and language
	JPanel north = new JPanel();
	north.setLayout(new GridLayout(2, 2));
	JLabel labelTitle = new JLabel("Title");
	JLabel labelLang = new JLabel("Language");
	north.add(labelTitle);
	north.add(labelLang);
	north.add(txtSnippetTitle);
	north.add(txtSnippetLang);

	// Create the center panel which contains the snippet text field
	JPanel center = new JPanel();
	txtAreaSnippetText = new JTextArea(10, 34);
	JScrollPane scrollPane = new JScrollPane(txtAreaSnippetText);
	center.add(scrollPane);

	// Create the south panel, which contains the buttons
	JPanel south = new JPanel();
	addButton = new JButton("Add");
	cancelButton = new JButton("Cancel");
	addButton.setEnabled(false);
	south.add(addButton);
	addButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent aEvent) {
		SnippetManager manager = SnippetManager.getInstance();
		
		// get the input from the fields and create a new Snippet object
		Snippet newSnippet = new Snippet();
		newSnippet.setSnippetTitle(txtSnippetTitle.getText());
		newSnippet.setLanguage(txtSnippetLang.getText());
		newSnippet.setSnippetText(txtAreaSnippetText.getText());
		
		// insert the record into the database
		manager.addSnippet(newSnippet);
		
		dispose(); // get rid of the dialog
	    }
	});

	south.add(cancelButton);
	cancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent aEvent) {
		dispose();
	    }
	});

	// add document listener to the three fields
	txtSnippetTitle.getDocument().addDocumentListener(new InputListener());
	txtSnippetLang.getDocument().addDocumentListener(new InputListener());
	txtAreaSnippetText.getDocument().addDocumentListener(new InputListener());

	// add the panels to the container panel
	Container contentPane = getContentPane();
	contentPane.add(north, BorderLayout.NORTH);
	contentPane.add(center, BorderLayout.CENTER);
	contentPane.add(south, BorderLayout.SOUTH);

	setVisible(true);
    }
}
