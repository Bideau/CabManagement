Andro�d :

All the following programs were build for Android 4.2 and + for a compatibility with a maximum of devices.

The main program consist to build a connection with a Rasberry Pi server (using websockets) and to exchange informations with it.
First of all, the main program receive an area (with vertices, bridges and streets) and paint it.
Then, the server send sometimes the cab's position and the client paint it on the map.
If the map is touched by the user, the client will send a request to the server with the coordinates.




Galileo :

The program connect a Galileo with a Rasberry Pi with an ethernet connection (using websockets) and exchange data with it.
The Galileo show all server information on it's own LCD screen.
An accepted/declined message can be send to the server with Left/Right button to accept/reject a cabRequest.


Python server :

To use the librairy Simple-Websocket-server you need to go on the folder simple-websocket-server-master/
And execute the folling command has super user 'python setup.py install'
Then you can run you program and use the lib
The lib is distrubued under the MIT licence
Installion of the paquet python-netaddr


Java interface :

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


