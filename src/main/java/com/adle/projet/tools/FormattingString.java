package com.adle.projet.tools;

public class FormattingString {

    /**
     * Function to format the string to a model
     * 
     * @param string
     *            string to format
     * @return string formatted
     */
    public String Formatting( String string ) {
        String newForm = string.substring( 0, 1 ).toUpperCase()
                + string.substring( 1 ).toLowerCase();
        return newForm;
    }

}
