COMPILER:
	javac -d . *.java
	java -jar BatNav.jar

Question 1 :
- j'ai décidé de rajouter un attribut taille afin de pouvoir y accéder facilement lors de l'affichage
de la grille (celle-ci entre en compte pour itérer sur le tableau)
- j'ai suivi la charte graphique proposée dans l'énoncé
- j'ai créé une méthode pour changer l'état d'une cellule à la main pour vérifier le bon fonctionnement
- le code de print n'est pas encore très bon mais je vais le diviser en plus petites fonctions plus spécifiques

- puisque pour l'instant il n'est pas prévu de créer des classes filles à Board, j'ai tout mis en private pour
bien contrôler les éléments que nous pouvons changer dans la classe
	=> on peut tout obtenir
	=> on ne peut modifier que le nom

Exercice 2 :
- puisque nous ferons des classes filles, la classe abstraite a des attributs protégés et non privés

- L'orientation des navires étant un ensemble fini, on peut représenter cette information par le biais d'un
enum
- Pour remédier au problème du nombre de fichiers on peut passer par un jar

Exercice 3 :
- les indices de position des navires commencent dans le code à 0, mais l'utilisateur devra y accéder
en commençant par 1, il faudra donc faire une translation lors des différents appels de fonction
- si la valeur (position + longueur) d'un navire mène en dehors de la grille de jeu il y aura une erreur
il faut donc lever une erreur et faire replacer le navire
- si deux navires se chevauchent il faut interdire le placement du nouveau navire (levée d'erreur aussi)

Exercice 5 :
- pour éviter gérer cet état illégal, nous faisons en sorte que addStrike appelle la méthode du navire
uniquement si le booléen isStruck est à l'état faux

Question 6 :
- si on appelle sendHit deux fois sur la même position d'un navire, un message sera généré et le joueur devra indiquer une place légale
- hasShip(x,y) renvoie true lorsque le navire en (x,y) a été coulé. Cependant, puisque nous ne pouvons pas taper 2 fois au même endroit cela ne pose pas de soucis.
