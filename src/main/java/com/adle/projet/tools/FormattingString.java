package com.adle.projet.tools;

public class FormattingString {

    public String Formatting( String string ) {
        String newForm = string.substring( 0, 1 ).toUpperCase()
                + string.substring( 1 ).toLowerCase();
        return newForm;
    }

}
