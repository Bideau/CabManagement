*********************************** DOCUMENTATION INTERFACE JAVA **************************************

I) Pour lancer le programme :

- Lancez le fichier jar ou depuis eclipse.
Par d�faut le programme se lance sur le client webSocket.

- Une fois connect� au serveur python, le client envoi automatiquement le message "Initialisation"
qui permet d'avoir en retour la chaine JSON avec la structure de la carte � afficher.

- Cette chaine JSON est analys�e afin de r�cup�rer les diff�rents �l�ments dans plusieurs objets 
(classes r�f�rentes dans le package structure.map).

- Avec ces objets ont peut dessiner la carte au moyen d'un "paintComponent" qui repr�sente l'artiste.
Ce dernier fait appel aux diff�rents "Drawers" afin de dessiner � tour de r�les les diff�rents �l�ments
de la map (Artiste et Drawers dans le package hud).

- Une fois la carte dessinn�e, on se met en attente des message "CabInfo" pour afficher la position du taxi.


II) Fonctionnement du programme



