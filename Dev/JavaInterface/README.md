*********************************** DOCUMENTATION INTERFACE JAVA **************************************

I) Pour lancer le programme :

- Lancez le fichier jar ou depuis eclipse.
Par défaut le programme se lance sur le client webSocket.

- Une fois connecté au serveur python, le client envoi automatiquement le message "Initialisation"
qui permet d'avoir en retour la chaine JSON avec la structure de la carte à afficher.

- Cette chaine JSON est analysée afin de récupérer les différents éléments dans plusieurs objets 
(classes référentes dans le package structure.map).

- Avec ces objets ont peut dessiner la carte au moyen d'un "paintComponent" qui représente l'artiste.
Ce dernier fait appel aux différents "Drawers" afin de dessiner à tour de rôles les différents éléments
de la map (Artiste et Drawers dans le package hud).

- Une fois la carte dessinnée, on se met en attente des message "CabInfo" pour afficher la position du taxi.


II) Fonctionnement du programme



