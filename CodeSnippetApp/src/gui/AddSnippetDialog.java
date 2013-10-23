package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jsyntaxpane.DefaultSyntaxKit;
import model.Language;
import model.Snippet;
import model.SnippetManager;

public class AddSnippetDialog extends JDialog {


    public Language language;
    


    private JTextField txtSnippetTitle = new JTextField(32);
    private JTextField txtSnippetLang = new JTextField(16);
    private JTextArea txtAreaSnippetText;
    private JButton addButton;
    private JButton cancelButton;
    private JEditorPane codeEditor;
    JComboBox<Language> comboBoxLang;
    
    public AddSnippetDialog(final JFrame owner) {
	// set the dialog title and size
	super(owner, "Add Snippet", true);
	setSize(428, 400);
	setLayout(new BorderLayout());
	
	// initalise the syntax formatting kit
	DefaultSyntaxKit.initKit();
	
	// Create the north panel which contains the title and language
	JPanel north = new JPanel();
	north.setLayout(new GridLayout(2, 2));
	JLabel labelTitle = new JLabel("Title");
	JLabel labelLang = new JLabel("Language");
	
	//create the languages combo box and a listener that sets the language
	comboBoxLang = new JComboBox<Language>();
	for (Language l : Language.values()) {
	    comboBoxLang.addItem(l);
	}
	comboBoxLang.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String langSelected = comboBoxLang.getSelectedItem().toString();
		System.out.println(langSelected);		
	    }
	    
	});
	
	north.add(labelTitle);
	north.add(labelLang);
	north.add(txtSnippetTitle);
	north.add(comboBoxLang);

	// Create the center panel which contains the code editor
	JPanel center = new JPanel();
	codeEditor = new JEditorPane();
	JScrollPane scrPane = new JScrollPane(codeEditor);	
	//resize the codeEditor
	codeEditor.setPreferredSize(new Dimension(400, 250));
	// needs to set the content type depending on the language selection TODO
	
	String langCode = "text/" + Language.JAVA.toString();
	System.out.println(langCode);
	codeEditor.setContentType(langCode);	
	// just a test TODO
        codeEditor.setText("public static void main(String[] args) {\n}");    
	center.add(scrPane);

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

	// add the panels to the container panel
	Container contentPane = getContentPane();
	contentPane.add(north, BorderLayout.NORTH);
	contentPane.add(center, BorderLayout.CENTER);
	contentPane.add(south, BorderLayout.SOUTH);

	setVisible(true);
    }
    
    //LISTENERS
    
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
		    ) {
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
		    ) {
		addButton.setEnabled(false);

	    }

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	    /** Empty implementation. Method necessary for implementation of DocumentListener */

	}

    }
    


}
