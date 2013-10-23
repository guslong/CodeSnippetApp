package model;

import java.util.HashMap;

public enum Language {

    JAVA ("java"),
    SCALA ("scala"),
    C ("c"),
    XHTML ("xhtml");

    private final String lang;
    
    Language (String lang) {
	this.lang = lang;
    }
    
    public String toString() {
	return lang;
    }
}
