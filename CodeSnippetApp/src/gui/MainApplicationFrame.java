package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jsyntaxpane.DefaultSyntaxKit;

import model.Snippet;
import model.SnippetManager;
import model.SnippetTableModel;

public class MainApplicationFrame extends JFrame {

    public static final String APPNAME = "Code Library";

    JMenuBar menuBar;
    JButton add = new JButton("+"); // add a snippet
    JButton rem = new JButton("-"); // remove a snippet
    JLabel space = new JLabel(" ");

    /** Provides methods for displaying a SQL result set in a JTable */
    private SnippetTableModel tblModel;
    /** Used to display the SQL result set in a cell format */
    private JTable table;
    /** A scrollable view for the SQL result set */
    private JScrollPane scrollPane;

    private SnippetManager snippetManager;
    ArrayList<Snippet> snippets;
    private JEditorPane codeEditor;

    public MainApplicationFrame() {

	// get the snippetmanager singleton object
	snippetManager = SnippetManager.getInstance();

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setPreferredSize(new Dimension(800, 600));
	initGUI(); // just call this method to make it more readable what is going on here
	pack();
	setVisible(true);
    }

    private void initGUI() {
	
	// initalise the syntax formatting kit
	DefaultSyntaxKit.initKit();
	
	// need a menu
	setJMenuBar(makeMenu());

	// left side panel
	JPanel leftSidePanel = new JPanel();
	leftSidePanel.setBackground(new Color(60, 60, 60));

	// JPanel top left (the add and remove buttons)
	JPanel topLeftPanel = new JPanel();
	topLeftPanel.add(add);
	topLeftPanel.add(space);
	topLeftPanel.add(rem);

	// right side main panel (just a container)
	JPanel rightSidePanel = new JPanel();
	rightSidePanel.setLayout(new BorderLayout());

	// right side NORTH (will be the search bar)
	JPanel searchBarPanel = new JPanel();
	JButton testButton = new JButton("Search");
	searchBarPanel.add(testButton);
	// right side CENTER (will be the listview)
	JPanel listViewPanel = new JPanel();

	// get all of the snippets
	snippets = snippetManager.getSnippets();

	// build the table, add to a scrollpane and add to panel
	tblModel = new SnippetTableModel(snippets);
	table = new JTable(tblModel);
	scrollPane = new JScrollPane(table);
	listViewPanel.add(scrollPane);

	// add a list selection listener to the table
	table.getSelectionModel().addListSelectionListener(new SnippetListSelectionListener());

	// right side SOUTH (will be a JEditorPane)
	JPanel editorPanel = new JPanel();
	editorPanel.setBackground(new Color(20, 20, 20));

	codeEditor = new JEditorPane();
	JScrollPane scrPane = new JScrollPane(codeEditor);
	// resize the codeEditor
	codeEditor.setPreferredSize(new Dimension(600, 250));
	editorPanel.add(scrPane);

	// add the top left panel to the CENTER of the leftSidePanel
	leftSidePanel.add(topLeftPanel);

	rightSidePanel.add(searchBarPanel, BorderLayout.NORTH);
	rightSidePanel.add(listViewPanel, BorderLayout.CENTER);
	rightSidePanel.add(editorPanel, BorderLayout.SOUTH);
	// add the two main panels to the content
	getContentPane().add(leftSidePanel, BorderLayout.WEST);
	getContentPane().add(rightSidePanel, BorderLayout.CENTER);
    }



    /**
     * creates a JMenuBar with "File" menu and "Quit" menu item If Quit is selected, a dialog asks the user if they want
     * to save the database state.
     * 
     * @return the JMenuBar
     */
    public JMenuBar makeMenu() {
	JMenuBar menu = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem itemQuit = new JMenuItem("Quit");
	itemQuit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	fileMenu.add(itemQuit);
	menu.add(fileMenu);
	return menu;
    }

    // inner listener class for the list selection
    public class SnippetListSelectionListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    
	    if (e.getValueIsAdjusting())
		return;
	    
	    // get the selected row from the table
	    int index = table.getSelectedRow();

	    // set the language of the selected snippet
	    String langCode = snippets.get(index).getLanguage();
	    codeEditor.setContentType("text/"+langCode );
	    // set the text of the code editor to the text from that object in that row
	    codeEditor.setText((snippets.get(index).getSnippetText()));
	}
    }

}
