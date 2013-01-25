# XML, Java et Netbeans (suite)

Nous allons continuer sur ce que nous avons construit à la dernière séance.

Le programme va produire un fichier xml avec les 5 derniers tweets d'un usager comme ceci:

      ```xml
          <users>
            <user name="alexcp_">
                <tweet>text</tweet>
                <tweet>...</tweet>
                <tweet>...</tweet>
                <tweet>...</tweet>
                <tweet>...</tweet>
            </user>
          </users>
      ```

Le constructeur actuel, récupère le contenu d'un fichier xml depuis un url.
Nous avons donc besoin d'un nouveau constructeur pour initialiser un objet avec un document xml vide dans le lequel nous allons pouvoir écrire.

Nous allons créer le constructeur et extraire l'initialisation du document builder dans une méthode pour éviter de dupliquer le code.

        ```java
            public DocumentXml() throws Exception{
                this.document = nouveauDocumentBuilder().newDocument();
            }

            private Document obtenirLeContenuDeLurl(String url) throws Exception{
                return nouveauDocumentBuilder().parse(url);
            }

            private DocumentBuilder nouveauDocumentBuilder() throws Exception{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                return factory.newDocumentBuilder();
            }
        ```

Dans notre main, nous pouvons maintenant créer un nouvel objet comme ceci

          ```java
            DocumentXml nouveauDocument = new DocumentXml();
          ```

Nous devons maintenant créer l'élement racine de notre fichier xml, l'élément "users".

        ```java
          // twitter_api.java
          Element racine = nouveauDocument.nouvelleElement("users");

          // document_xml.java
          public Element nouvelleElement(String nom){
            return document.createElement(nom);
          }
        ```

Une fois l'élément nous devons l'ajouter à notre document.
Nous allons utiliser la méthode `appendChild`.

        ```java
        // twitter_api.java
        nouveauDocument.ajouterElement(racine);

        // document_xml.java
        public void ajouterElement(Element element){
            this.document.appendChild(element);
        }
        ```

Nous allons maintenant créer la balise user avec l'attribut "name" qui correspond au nom d'utilisateur.
Veuillez notez que nous utilisons la méthode `appendChild` sur l'élément racine, qui est le parent de l'élément user.

        ```java
            Element user = nouveauDocument.nouvelleElement("user");
            user.setAttribute("name", args[0]);
            racine.appendChild(user);
        ```

Pour ajouter les tweets, nous allons simplement créer un element tweet et lui assigner le contenu textuel d'un tweet.
Ici nous utilison le loop qu'on avait fait à la dernière séance.

        ```java
            for(int i=0; i < listeDeBalises.getLength();i++){
                Element tweet = nouveauDocument.nouvelleElement("tweet");
                tweet.setTextContent(xml.obtenirTexteDeLElement(listeDeBalises.item(i),"text"));
                user.appendChild(tweet);
            }
        ```

Maintenant, il nous reste seulement à sauvegarder le tout dans un fichier.
Nous allons avoir besoin d'un objet de type [Transformer](http://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Transformer.html) et de ça méthode [transform](http://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Transformer.html#transform(javax.xml.transform.Source, javax.xml.transform.Result)).
Cette classe va convertir notre document en fichier xml.

La méthode Tranform prend en paramêtre un argument [Source](http://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Source.html) et un [Result](http://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Result.html)

        ```java
        public void enregistrerSous(String nomFichier) throws Exception{

            //Nous devons utiliser un factory pour créer une nouvelle instance de transformer
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // Nous utilison l'implémentation DOMSource de l'interface Source
            Source source = new DOMSource(document);

            // Nous utilison l'implémentation StreamResult de l'interface Result
            Result result = new StreamResult(new File(nomFichier));

            // Ici on indique qu'on souhaite indenter le fichier
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            // Ici on indique qu'on veut 2 espace pour l'indentation
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            transformer.transform(source, result);
        }
        ```

On peut maintenant appeler la fonction comme ceci:
        
        ```java 
            nouveauDocument.enregistrerSous("result.xml");
        ```

Consulter le code [ici](https://github.com/alexcp/inf2015--twitter-api).

