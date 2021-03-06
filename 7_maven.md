#Utilisation de Maven

## Convertir un projet Ant sous Netbeans pour Maven

Premièrement, il faut installer le plugin Maven pour Netbeans.
Dirigez vous vers Tools->Plugins->Available Plugins et séléctionné Maven dans la liste.

### Structure d'un projet Maven

Un projet Maven doit obligatoirement respecter cette structure.

```
projet
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- org
    |           `-- inf2015
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- org
                `-- inf2015
                    `-- app
                        `-- AppTest.java
```

### pom.xml

Le fichier pom est le fichier de configuration d'un projet Maven.
Voici un exemple de fichier pom, je conseil de l'utiliser comme base.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.inf2015.app</groupId>
  <artifactId>App</artifactId>
  <version>1.0</version>
  <packaging>jar</packaging>

  <name>Exemple fichier Pom</name>
  <url>http://maven.apache.org</url>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>2.3.2</version>
          <configuration>
            <source>1.7</source>
            <target>1.7</target>
            <compilerArgument></compilerArgument>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>

</project>
```

### Conversion du projet
Une fois le fichier créer, il serait maintenant un bon moment pour faire une commit.
Fermer le projet dans Netbeans.
Créer les dossiers selon la structures d'un projet Maven.
Copier les fichier dans les dossiers correspondant.
Supprimer tout les dossiers et fichiers maintenant inutiles. (D'ou l'importance de faire un commit avant cette étape)

Tester si tout fonctionne avec la commande `mvn package`

## Couverture de test
[Lien pour installer le plugin](http://plugins.netbeans.org/plugin/38945/unit-tests-code-coverage-plugin-updated-for-netbeans-7-0)

Installer le plugin dans Netbeans et ajoutez ceci dans votre fichier pom.xml
```xml
<reporting>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>cobertura-maven-plugin</artifactId>
      <version>2.4</version>
    </plugin>
  </plugins>
</reporting>
```

Faites un clique droit sur votre projet, choisissez Code Coverage > Show Reports.. et Run All Test
