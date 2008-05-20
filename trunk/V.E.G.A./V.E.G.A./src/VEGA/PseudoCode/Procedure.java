/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.pseudocode;

import java.util.ArrayList;

/**
 *
 * @author w_jossey
 */
/**
 * 
 * @author w_jossey
 */
public class Procedure {

    private ArrayList<String> pseudoCodeLineArray = new ArrayList<String>();
    private String title;

    /**
     * 
     * @param title
     */
    public Procedure(String title) {
        this.title = title;
    }

    /**
     * 
     * @param line
     */
    public void appendLine(String line) {
        pseudoCodeLineArray.add(line);
    }

    /**
     * 
     * @param line
     * @return
     */
    public String getLineWithoutMarker(int line) {
        if (line > 0) {
            return pseudoCodeLineArray.get(line - 1);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param line
     * @return
     */
    public String getLineWithMarker(int line) {
        if (line > 0) {
            return pseudoCodeLineArray.get(line - 1) + "\t<--";
        } else {
            return null;
        }
    }

    /**
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param lineMarker If parameter is <= -1, then no line marker is used.
     * @return
     */
    public String toString(int lineMarker) {
        if (lineMarker < pseudoCodeLineArray.size()) {
            if (lineMarker > -1) {
                //Print with line marker
                String returnString = "";
                returnString += getTitle() + "\n";
                for (int i = 0; i < pseudoCodeLineArray.size(); i++) {
                    if (lineMarker - 1 == i) {
                        returnString += (i + 1) + ".  " + getLineWithMarker(i + 1) + "\n";
                    } else {
                        returnString += (i + 1) + ".  " + getLineWithoutMarker(i + 1) + "\n";
                    }
                }
                return returnString;

            } else {
                //Print without a line marker
                String returnString = "";
                for (int i = 0; i < pseudoCodeLineArray.size(); i++) {
                    returnString += (i + 1) + ".  " + getLineWithoutMarker(i + 1) + "\n";
                }
                return returnString;
            }
        }else{
            return null;
        }

    }

    @Override
    public String toString() {
        return toString(-1);
    }
}
