package model;

import java.util.HashMap;

public enum Language {

    JAVA ("java"),
    JSON ("json"),
    SCALA ("scala"),
    XHTML ("xhtml"),
    XML ("xml"),
    SQL ("sql")
    ;

    private final String lang;
    
    Language (String lang) {
	this.lang = lang;
    }
    
    public String toString() {
	return lang;
    }
}
