# Git

##ressources

* [Site officiel](http://git-scm.com)
* [Try git](http://try.github.com/)
* [Pro-git](http://git-scm.com/book)
* [A visual git reference](http://marklodato.github.com/visual-git-guide/index-en.html)
* [Git - The simple guide](http://rogerdudler.github.com/git-guide/)
* [Github help](https://help.github.com/)
* [Ma configuration](https://github.com/alexcp/gitconfig)


Commencez par [installez git](http://git-scm.com/downloads)

## Commandes de base
Ensuite, ouvrez le git bash si vous êtes sur windows ou ouvrez un terminal si vous êtes sur mac ou linux.

On va créer un nouveau dossier et on va s'y diriger dans un terminal.

`pwd` vous indique le répertoire courant, `cd` permet de vous dirigez vers un répertoire différent et la commande
`mkdir` vous permet de créer un nouveau dossier vide.

Pour créer un nouveau répertoire git dans le dossier courrant, exécuté la commande
`git init`

On va créer un fichier readme.md `touch readme.md` et on va y inscrire `Labo git` sur la première ligne.
L'extension `.md` signifie Markdown, un format textuel qui peut être converti en html et qui est reconnu par github.

Avec la commande status, on peut voir l'état actuel de notre repository
`git status`

On va ajouter notre fichier readme au 'stage' comme ceci:
`git add readme.md`

Dans le 'stage' il y a tout les fichiers / modifications qui seront inclues dans le prochain commit.

Si on refais la commande git status, les fichiers en vert représentent les fichiers qui vont être ajouté à notre prochain commit.
`git status`

On va créer notre premier commit avec la commande git commit, on peut ajouter un message avec l'éditeur vim par défaut ou directement a la ligne de commande
`git commit -m "Nouveau projet"`

On va créer un repository sur github pour notre projet.
Pour envoyer notre commit sur github nous allons utiliser les 2 commandes suivantes :
`git remote add origin https://github.com/:auteur/:nom_du_projet.git`
`git push -u origin master`

On va ajouter un peu de style a notre readme. On va ajouter '#' au début de notre première ligne pour indiquer qu'il s'agit du titre de notre document.

La commande diff nous indique les modifications apportées à notre fichier.
`git diff`

On ajoute le fichier avec la commande add
`git add readme.md`

On fait notre nouveau commit et on envoie les changements sur github

    ```bash
      git commit -m "ajouter une balise titre"
      git push
    ```

Il est préférable de créer des branches pour travailler et seulement appliquer les changements testés et terminer à notre branche master.
Pour créer une branche dev on utilise la commande 
`git branch dev`

Pour vous déplacer dans celle-ci. Utiliser la commande checkout.
`git checkout dev`

Lorsque vous êtes prêt à appliquer vos changements utilisé les 2 commandes suivantes:

          ```bash
            git checkout master
            git merge dev
          ```

### Quoi faire si une erreur ce produit?

* Pour modifier le dernier commit, faîtes vos modifications et au lieu d'utiliser la commande `git commit` on utilise plutôt `git commit --ammend`
* Pour récupérer un fichier supprimer/modifier par un commit vous pouvez utiliser la commande `git checkout sha -- nom_du_fichier`
* Pour retourné à un certain commit dans l'historique vous pouvez utilisez `git checkout sha` ou sha est l'identifiant numérique du commit.
* Si vous voulez retourné a ce commit et annulé les modifications après ce commit, utilisez `git reset --hard sha` Attention vous perdez les commits suivant celui indiqué

### Conseils

* Apprenez à utilisez git depuis la ligne de commande
* Faites __beaucoup__ de commits
* Faites des petits commits qui changent une seule fonctionnalité
* Pour choisir les changement à inclure dans un fichier, utilisé la commande `git add nom_du_fichier -p`
* Travaillez dans des branches autres que le _master_ et faites vos 'merge' seulement quand votre code fonctionne parfaitement dans votre branche
* Faites toujours un pull avant de commencer à travailler
* Faites des push régulièrement pour que votre équipe sache ce que vous avez fait
* Faites un `git diff` ou un `git diff --cached` avant de faire un commit pour consulter les modification en détails
* Utilisez Git sur __TOUT__ vos projets

