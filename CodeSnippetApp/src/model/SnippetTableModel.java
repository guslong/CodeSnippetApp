package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SnippetTableModel extends AbstractTableModel {

    ArrayList<Snippet> snippetsList;
    
    public SnippetTableModel(ArrayList<Snippet> snippets) {
	snippetsList = snippets;
    }
    
    @Override
    public int getRowCount() {
	return snippetsList.size();
    }

    @Override
    public int getColumnCount() {
	// title
	// language
	// date
	return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	if (columnIndex == 0) {
	    return snippetsList.get(rowIndex).getSnippetTitle();
	}
	if (columnIndex == 1) {
	    return snippetsList.get(rowIndex).getLanguage();
	}
	if (columnIndex == 2) {
	    return snippetsList.get(rowIndex).getSQLDate().toString();
	}
	return null;
    }
    
    public String getColumnName(int column) {
	switch (column) {
	case 0: return "Title";
	case 1: return "Language";
	case 2: return "Date created";
	default:
	    return null;
	}
    }

}
