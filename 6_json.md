# JSON et Java

Le code que nous avons fait en classe est disponible [ici](https://github.com/alexcp/labo_json)

## directives

On va utiliser [l'api de github](http://developer.github.com/) dans un programme Java.
L'api de github transmet du JSON.

Le programme va nous retourner l'information d'un utilisateur de github

Pour trouver un utilisateur avec l'api de github, on fait une requête avec ce format:
`https://api.github.com/legacy/user/search/:username`

Par exemple pour mon compte:
`https://api.github.com/legacy/user/search/alexcp`

La librairie JSON utilisé est [JSON-lib](http://json-lib.sourceforge.net)
[Javadoc de la librairie](http://json-lib.sourceforge.net/apidocs/jdk15/index.html)

----

### Requête HTTP
Pour récupérer la réponse de notre requête, nous allons avoir besoin de 3 bibliothèques.
```java
import java.net.URL;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
```
Nous allons ouvrir un InputStream à l'adresse spécifiée avec l'URL, ensuite Apache Common IOUtils nous permet de convertir le InputStream en String.

On va se créer une méthode dans le main (pour l'instant) pour effectuer le tout.
```java
    public static String obtenirLesDonneesDeLUrl(String url){
        String result = "";
        try{
            InputStream request = new URL(url).openStream();
            result = IOUtils.toString(request,"UTF-8");
        }catch(Exception e){
            System.out.println("Erreur, impossible de lire le contenu de l'url.");
        }
        return result;
    }
```
Cette méthode prend une URL en String et nous retourne la réponse en String.

Pour le moment, on va l'utiliser comme ceci dans notre Main
```java
  String url = "https://api.github.com/legacy/user/search/alexcp";
  String donnees = obtenirLesDonneesDeLUrl(url); 
```

## Classe pour notre JSON
Nous allons créer une Classe pour lire le Json.
```java
import net.sf.json.*;
public class DocumentJson {
    // variable d'instance
    private JSONObject jsonObj;
       
    // constructeur
    public DocumentJson(String contenu) {
        jsonObj = JSONObject.fromObject(contenu);
    }

    // retourne le String de de la cle
    public String obtenirLaValeurDe(String cle){
        return jsonObj.getString(cle);
    }
}
```

On peut utiliser notre classe de le main comme ceci:
```java
public static void main(String[] args) {
        String url = "https://api.github.com/legacy/user/search/alexcp";
        String donnees = obtenirLesDonneesDeLUrl(url); 
        DocumentJson json = new DocumentJson(donnees);
        System.out.println(json.valeurDe("users"));
}
```

