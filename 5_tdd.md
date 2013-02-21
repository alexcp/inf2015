# Test Driven Development

## Librairies

Nous allons utiliser 2 librairies qui facilitent l'écriture de test.

## Mockito

[Mockito](http://code.google.com/p/mockito/) permet de créer des "Mock".

Un Mock est un "faux objet" qui remplace un objet et ces fonctionnalité le temps d'un test.
Un Stub est une "fausse méthode".

### Pourquoi?

* Un test unitaire devrait tester _une seule_ fonctionnalité.
* Les tests doivent être exécuté rapidement, on peu simuler les opérations couteuses avec des mock.
* Simple / Rapide  à  utiliser.

### Utilisation

Pour utiliser Mockito, ajouter le fichier jar a votre projet et dans l'entête de vos test ajouter:
    
```java
      import static org.mockito.Mockito.*;
```

Pour créer un mock, vous pouvez faire comme ceci.

```java
      //Mock d'un objet de la classe Client
      Client client = mock(Client.class);
```

Pour ajouter un Stub d'une methode a notre Mock Object on utilise les mÃ©thode when().then...

```java
      //Mock d'un objet de la classe Client
      Client client = mock(Client.class);

      //Stub de la methode getNom()
      when(client.getNom()).thenReturn("alex");
```

[Téléchargé la Dernière version](http://code.google.com/p/mockito/downloads/detail?name=mockito-all-1.9.5.jar&can=2&q=)

## Hamcrest

[Hamcrest](https://github.com/hamcrest/JavaHamcrest) ajoute des "matcher" a junit.
Ils permettent d'écrire des test de façon plus expressive et plus lisible.

On peut facilement utiliser les matchers avec la méthode `assertThat`

Pour utiliser les matcher de hamcrest, ajouter dans l'entête:

```java
        import static org.hamcrest.MatcherAssert.*;
        import static org.hamcrest.Matchers.*;
```

Voici quelque exemple de Matcher:

```java
      assertThat(object, is(otherObject));
      // ou
      assertThat(object, is(equalTo(otherObject)));
      // equivalent de
      assertEquals(object, otherObject);
```


```java
      String string = "Les tests sont plus facile à lire."

      assertThat(string, containsString("tests"));
      assertThat(string, not(containsString("difficile")));
```
