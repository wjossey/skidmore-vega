/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.pseudoCode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author w_jossey
 */
public class PseudoCode {
    HashMap<String, Procedure> procedureHash = new HashMap<String, Procedure>();
    private String title = "";
    
    public PseudoCode(String algorithmTitle){
        title = algorithmTitle;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void addProcedure(Procedure p){
        procedureHash.put(p.getTitle(), p);
    }
    
    public Procedure[] getProcedures(){
        Procedure[] procedureArray = new Procedure[procedureHash.size()];
        
        Collection<Procedure> procedureCollection = procedureHash.values();
        int i = 0;
        Iterator<Procedure> iterator = procedureCollection.iterator();
        while(iterator.hasNext()){
            procedureArray[i] = (Procedure) iterator.next();
        }
        return procedureArray;
    }
    
    public String getLine(int line, String procedureTitle){
        if(procedureHash.containsKey(procedureTitle)){
            Procedure p = (Procedure) procedureHash.get(procedureTitle);
            return p.getLineWithMarker(line);            
        }else{
            return null;
        }
    }
    
    public String getPseudoCodeWithLineMarker(int line, String procedureTitle){
        if(procedureHash.containsKey(procedureTitle)){
            Procedure p = (Procedure) procedureHash.get(procedureTitle);
            return p.toString(line);
        }else{
            return null;
        }
    }
    
    public String getPseudoCodeWithoutLineMarker(String procedureTitle){
        if(procedureHash.containsKey(procedureTitle)){
            Procedure p = (Procedure) procedureHash.get(procedureTitle);
            return p.toString();
        }else{
            return null;
        }
        
    }
    
    public String getPseudoCodeWithoutLineMarker(){
        String returnString = "";
        returnString += getTitle() + "\n";
        Procedure[] proceduresArray = getProcedures();
        for(int i = 0; i < proceduresArray.length; i++){
            returnString += proceduresArray[i].toString();
        }
        return returnString;
    }
    
}
