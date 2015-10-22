******************************************* DOCUMENTATION INTERFACE JAVA **************************************

I) Lancement du programme :

- Lancez le fichier jar ou depuis eclipse.
Par d�faut le programme se lance sur le client webSocket.

- Une fois connect� au serveur python, le client envoi automatiquement le message "Initialisation"
qui permet d'avoir en retour la chaine JSON avec la structure de la carte � afficher.

- Cette chaine JSON est analys�e afin de r�cup�rer les diff�rents �l�ments dans plusieurs objets 
(classes r�f�rentes dans le package structure.map).

- Avec ces objets ont peut dessiner la carte au moyen d'un "paintComponent" qui repr�sente l'artiste.
Ce dernier fait appel aux diff�rents "Drawers" afin de dessiner � tour de r�les les diff�rents �l�ments
de la map (Artiste et Drawers dans le package hud).

- Une fois la carte dessin�e, on se met en attente des message "CabInfo" pour afficher la position du taxi.


II) Fonctionnement du programme


Le programme est un client websocket communiquant avec un serveur. Dans un premier temps, le client re�oit
la carte (Vertices, streets and Bridges). Ces informations sont transmises au moyen d'une trame JSON.

La trame JSON est ensuite analyser pour stocker toutes les informations dans un objet.

On utilise cet objet pour afficher la carte dans l'interface graphique.

Une fois la carte affich�e, on se met en attente de trame de type "CabInfo" afin de mettre � jour
la position du taxi.

Durant cette boucle d'attente on peut �galement envoyer des message de type "CabRequest" en cliquant
sur la carte. On envoi alors le vertex le plus proche du clic.

Librairies :

Jetty : http://download.eclipse.org/jetty/
WebSocket : http://mvnrepository.com/artifact/org.cometd.java