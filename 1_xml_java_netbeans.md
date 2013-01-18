# XML, Java et Netbeans

Le XML est souvent utiliser dans l'API de site web, il est parcontre de plus en plus remplacé par le json.

On va créer un programme qui va nous donner les derniers tweets d'une utilisateur sur twitter.

Les informations sur l'API de twitter son disponible [ici](https://dev.twitter.com/docs)

L'application que nous allons créer va prendre en argument le nom d'un usager sur twitter et afficher ses 5 derniers tweets.

## Nouveau projet sous Netbeans

Cliquez sur File -> Java -> Java Application
Entrez les détails du projet et cliquez sur Finish

## Arguments

On peu passé des arguments à un programme Java quand on l'exécute depuis la ligne de commande.
Les arguments sont passés a notre main. Par défauts, ils se retrouvent dans un array de String nommé args.

Avec netbeans, on peut définir ces arguments dans les propriétés de notre projet sous l'onglet Run.

Pour exécuter un projet netbeans depuis la ligne de commande, d'abord, faites un Clean Build.
Diriger vous ensuite dans le dossier de votre projet et exécuter `java -jar dist/nom_du_projet.jar`

## Le Main

La méthode main d'un programme Java prends un tableau de String en arguments.
Ce tableau contient les arguments qu'on passe au programme lors de l'éxecution depuis la ligne de commande.

Nous allons commencer par créer une méthode constuireUrl pour construire l'url depuis lequel nous allons récuperer les données.
Voici l'url en question: [https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=alexcp_&count=5](https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=alexcp_&count=5)

    public static String constuireUrl(String screenName){
        String url = "https://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
        url += screenName;
        url += "&count=5";
        return url;
    }

Nous allons assigné url retourné à une variable dans notre main comme ceci.

    public static void main(String[] args) {
        String url = constuireUrl(args[0]);
    }

## Lecture du xml

Pour la lecture du xml, on va se créer une classe DocumentXml avec une variable d'instance privée de type Document.
Nous devons également importer 2 bibliotheques.

      import org.w3c.dom.*;
      import javax.xml.parsers.*;

      public class DocumentXml {
          private Document document;
      }

La variable document va contenir une interprétation du fichier xml. Pour faire ça, on peu utiliser la méthode [DocumentBuilder.parse(url)](http://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html#parse(java.lang.String))
(Cette méthode peu également prendre un fichier ou un inputstream en paramêtre)

Pour pouvoir utiliser cette méthode nous devons créer un instance de DocumentBuilder, mais pour ce faire, nous devons utiliser un [DocumentBuilderFactory](http://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilderFactory.html#newDocumentBuilder())

Je vais créer un méthode qui va me retourner le document interpreté comme ceci:

      private Document obtenirLeContenuDeLurl(String url) throws Exception{
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder  builder = factory.newDocumentBuilder();
          return builder.parse(url);
      }

Veuillez notez que je ne gère pas les exceptions.

Je peu maintenant appeler cette méthode depuis mon constructeur.

    public DocumentXml(String url) throws Exception{
        this.document = obtenirLeContenuDeLurl(url);
    }

Le contenu des tweets ce trouve dans la balise "text", le fichier xml est formatté comme ceci:

    <statuses type="array">
      <status>
        ...
        <text>backbone.js 0.9.9 releassed http://t.co/kBQn2C0g</text>
        ...
      </status>
      <status>
        ...
        <text>CheckIO is a videogame you play by writing Python. http://t.co/PV634Cz8</text>
        ...
      </status>
      ...
    </statuses>

On va commencer par créer une méthode pour récupérer tout les "status".

    public NodeList obtenirLesElements(String nomDesElements){
        return document.getElementsByTagName(nomDesElements);
    }

Cette méthode nous retourne un "NodeListe", une liste de balise.
Pour chaque élément dans la liste, nous allons appeller la méthode obtenirTexteDeLElement.
Comme ceci:

    DocumentXml xml = new DocumentXml(url);
    NodeList liste = xml.obtenirLesElements("status");
    for(int i=0; i < liste.getLength();i++){
      System.out.println(xml.obtenirTexteDeLElement(liste.item(i),"text"));
    }

La méthode obtenirTexteDeLElement va prendre en paramètre un "Node" et un String qui représente la balise que l'on cherche.

    public String obtenirTexteDeLElement(Node parent,String nomElement){
        String resultat = null;

        //Liste des balises sous la balise parent.
        NodeList liste = parent.getChildNodes();

        for(int i=0; i < liste.getLength(); i++){

            //Compare le nom de la balise avec le nom de l'élement que l'on recherche
            if(liste.item(i).getNodeName().equals(nomElement)){
                resultat = liste.item(i).getTextContent();
            }
        }

        return resultat;
    }

Vous pouvez consulter le code [ici](https://github.com/alexcp/inf2015--twitter-api).
