COMPILER:
	jar cvmf MANIFEST.MF BatNav.jar *.class
	java -jar BatNav.jar

Question 1 :
- j'ai décidé de rajouter un attribut taille afin de pouvoir y accéder facilement lors de l'affichage
de la grille (celle-ci entre en compte pour itérer sur le tableau)
- j'ai suivi la charte graphique proposée dans l'énoncé
- j'ai créé une méthode pour changer l'état d'une cellule à la main pour vérifier le bon fonctionnement
- le code de print n'est pas encore très bon mais je vais le diviser en plus petites fonctions plus spécifiques

- puisque pour l'instant il n'est pas prévu de créer des classes filles à Board, j'ai tout mis en private pour
bien contrôler les élémenst que nous pouvons changer dans la classe
	=> on peut tout obtenir
	=> on ne peut modifier que le nom

Exercice 2 :
- puisque nous ferons des classes filles, la classe abstraite a des attributs protégés et non privés

- L'orientation des navires étant un ensemble fini, on peut représenter cette information par le biais d'un
enum
- Pour remédier au problème du nombre de fichiers on peut passer par un jar