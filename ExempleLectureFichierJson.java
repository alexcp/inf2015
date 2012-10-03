package javaapplication2;

import net.sf.json.*;
import java.io.*;

/**
 *
 * @author alex
 */
public class FichierJson {
    private JSONObject jsonObj;
       
    public FichierJson(String nomDuFichier) {
        jsonObj = conversionDuFichierEnJson(nomDuFichier);
    }
 
    public static JSONObject conversionDuFichierEnJson(String nomDuFichier) {
        String     contenuDuFichier = leFichierEnString(nomDuFichier);
        JSONObject documentEnJson   = (JSONObject) JSONSerializer.toJSON(contenuDuFichier);

        return documentEnJson;
    }
    
    public static String leFichierEnString(String nomDuFichier) {       
        byte[]          bufferDeLaTailleDuFichier = new byte[(int) new File(nomDuFichier).length()];
        
        try{
            FileInputStream inputStream               = new FileInputStream(nomDuFichier);
            inputStream.read(bufferDeLaTailleDuFichier);
        }catch(Exception e){
            System.out.println("Une exception est levé");
        }
        return new String(bufferDeLaTailleDuFichier);
    }
    
    public String obtenirLaValeurDe(String valeur){
        return jsonObj.getString(valeur);
    }
    
}
