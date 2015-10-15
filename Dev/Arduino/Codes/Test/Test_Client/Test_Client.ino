#include <SPI.h>
#include <Ethernet.h>

byte mac[] = {  0x98, 0x4F, 0xEE, 0x05, 0x37, 0xE3 }; // My mac address
String serverIP = "192.168.1.78";
int Port = 8080;
IPAddress server(192,168,1,78); // Server IP
IPAddress ip(192, 168, 1, 177);  // My IP

EthernetClient client;

void setup() {
  Ethernet.begin(mac,ip);
 // Open serial communications and wait for port to open:
  Serial.begin(9600);
   while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }

  // give a second to initialize:
  delay(1000);
  Serial.println("connecting...");

  // if you get a connection, report back via serial:
  if (client.connect(server, Port)) {
    Serial.println("connected");

    // Send Handshake
    String newLine = "\r\n";
    String handshake = "GET /Service HTTP/1.1" + newLine
    + "Host: " + serverIP + ":" + Port + newLine
    + "Connection: Upgrade" + newLine
    + "Upgrade: websocket" + newLine
    + "Origin: arduinoClient" + newLine
    + "Sec-WebSocket-Key: " + newLine
    + "Sec-WebSocket-Version: 13";
    client.println("Hello!");
    client.println(handshake);
    client.println();
  } 
  else {
    // if you didn't get a connection to the server:
    Serial.println("connection failed");
  }
}

void loop()
{
  // if there are incoming bytes available 
  // from the server, read them and print them:
  if (client.available()) {
    char c = client.read();
    Serial.print(c);
  }

  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();

    // do nothing forevermore:
    for(;;)
      ;
  }
}

