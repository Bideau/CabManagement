All the following programs were build for Android 4.2 and + for a compatibility with a maximum of devices.

The main program consist to build a connection with a Rasberry Pi server (using websockets) and to exchange informations with it.
First of all, the main program receive an area (with vertices, bridges and streets) and paint it.
Then, the server send sometimes the cab's position and the client paint it on the map.
If the map is touched by the user, the client will send a request to the server with the coordinates.