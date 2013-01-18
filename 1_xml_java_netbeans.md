# XML, Java et Netbeans

Le XML est souvent utiliser dans l'API de site web, il est parcontre de plus en plus remplacé par le json.

On va créer un programme qui va nous donner les derniers tweets d'une utilisateur sur twitter.

Les informations sur l'API de twitter son disponible [ici](https://dev.twitter.com/docs)

## Nouveau projet sous Netbeans

## Créer un projet dans Netbeans

Cliquez sur File -> Java -> Java Application
Entrez les dÃ©tails du projet et cliquez sur Finish

## Arguments

On peu passé des arguments à un programme Java quand on l'exécute depuis la ligne de commande.
Les arguments sont passés a notre main. Par défauts, ils se retrouvent dans un array de String nommé args.

Avec netbeans, on peut définir ces arguments dans les propriétés de notre projet sous l'onglet Run.

Pour exécuter un projet netbeans depuis la ligne de commande, d'abord, faites un Clean Build.
Diriger vous ensuite dans le dossier de votre projet et exécuter `java -jar dist/nom_du_projet.jar`

## Main

Pour communiquer avec l'api de twitter, il faut pouvoir envoyer des requêtes http.
On va avoir besoin des bibliothèques suivantes:

    import java.net.URL;
    import java.io.InputStream;
    import org.apache.commons.io.IOUtils;
    /**
     *
     * @author alex
     */

## Ajouter une librairie a une projet Netbeans

on va télécharger le jar dans un dossier lib à la racine de notre projet.
On va ensuite aller dans le menu properties de notre projet, et cliquer sur Add JAR/Folder sous l'onglet librairies et ajouter le contenu de lib.

----

On peu l'utiliser dans notre main comme ceci:

        String url = "https://api.twitter.com/1/users/show.xml?screen_name=alexcp_";
        String donnes = obtenirLesDonneesDeLUrl(url);

On va ajouter une méthode pour créer un url a partir de l'argument données à la ligne de commande.

     public static String construireUrl(String auteur){
          String url = "https://api.twitter.com/1/users/show.xml?screen_name=";
          url += auteur;
          return url;
      }

## Lecture du xml

Pour la lecture du xml, on va se créer une classe DocumentXml avec une variable d'instance privée de type Document.
Le type "Document" sera un DOM (Document Objet Model)

Premièrement nous allons "Parser" le fichier xml a l'url correspondant.
