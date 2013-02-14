# JUnit

[junit.org](http://junit.org)

JUnit nous permet de verifier le résultat d'une méthode avec différent outils.

Typiquement, un test compare le résultat obtenu par l'exécution d'une méthode avec le résultat attendu à l'aide d'une assertion.

### Format d'un test

      ```java
        @Test
        public void testCalculerTotal(){
          Commande commande = new Commande(60);
          assertEquals(60,commande.calculerTotal());
        }
      ```

### Préfixes
Avec JUnit4 les méthodes sont toujours précédé d'une entête.

On préfixe les tests avec `@Test`

Les préfixes `@Before` et `@After` font exécuter la méthode avant ou après chaque tests.

On peut aussi utiliser `@BeforeClass` et `@AfterClass` pour exécuter la méthode avant ou après tout les tests.
Ces commandes sont utiles si nous devons créer des objets ou effectuer certaines opÃ©rations avant un test.

On peut ignorer un test avec `@Ignore`, ceci est très utiles lorsqu'on n'a pas le temps de terminer un test.

### Assertion

Une assertion compare un résultat obtenu avec le résultat prévu

Exemples les plus communs:

* `assertEquals(expected,actual)`, égalité entre 2 valeurs.
* `assertArrayEquals(expected,actual)`, égalité entre 2 tableau.
* `assertTrue(result)` ou `assertFalse(result)` boolean
* `assertNull(result)` ou `assertNotNull(result)` null

### Exceptions

Si nous voulons tester qu'une exception est bien lancée, on le fait avec le paramètre "expected" du préfixe test.
      ```java
        @Test(expected = Exception.class)
        public void methodeException(){
          objet.methode(0);
        }
      ```

[exercice](https://github.com/alexcp/inf2015-junit4)
