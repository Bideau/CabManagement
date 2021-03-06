#include <aJSON.h>
#include <LiquidCrystal.h>
#include <WebSocketClient.h>
#include "Arduino.h"
#include <Ethernet.h>
#include <SPI.h>

LiquidCrystal lcd(8, 9, 4, 5, 6, 7); 

// Panel and buttons values
int lcdKey     = 0;
int adc_key_in  = 0;
boolean cabBusy = false;
#define btnRIGHT  0
#define btnUP     1
#define btnDOWN   2
#define btnLEFT   3
#define btnSELECT 4
#define btnNONE   5

// All connection values
byte mac[] = { 0x98, 0x4F, 0xEE, 0x05, 0x37, 0xE3 };
IPAddress ip(192, 168, 2, 10); // Ip address
IPAddress dnServer(192, 168, 2, 1); // Dns
IPAddress gateway(192, 168, 2, 2); // Gateway
IPAddress subnet(255, 255, 255, 0); // Subnet
char server[] = "192.168.2.2";
char rejected[] = "{\"answer\":\"false\"}";
char accepted[] = "{\"answer\":\"true\"}";
int port = 8000;
WebSocketClient client;

int customers;
boolean boolCabState = true;
String cabState;
int distance;
aJsonObject *answer;

/*
 * Cab status LCD refresher
 */
void setCabStatus(){
  // Customers
  lcd.setCursor(0,0);
  lcd.print(customers);

  // State
  lcd.setCursor(4,0);
  if(boolCabState == true){
    lcd.print("Free");
  }else{
    lcd.print("Busy");
  }

  // Distance
  lcd.setCursor(10,0);
  lcd.print(distance);
  lcd.setCursor(11,0);
  lcd.print("Km");

  // Buttons
  lcd.setCursor(0,1);
  if(boolCabState == true){
    lcd.print("Accept");
    lcd.setCursor(10,1);
    lcd.print("Reject");
  }else{
    lcd.print("                ");
  }
}

/*
 * LCD settings
 */
void initLCD(){
  // Set all pins at output
  for(int i=4;i<10;i++){
    pinMode(i,OUTPUT);
  }
  // Start LCD library.
  lcd.begin(16, 2); 
  // Init LCD cursor
  lcd.setCursor(0,0);
  Serial.println("End of LCD init");
}

/*
 * Initialization and connection to the server
 */
void webSocketInitializer(){
  Ethernet.begin(mac, ip, dnServer, gateway, subnet);
  client.connect(server, port);
  client.onOpen(onOpen);
  client.onMessage(onMessage);
  client.onError(onError);
  Serial.println("End of Web init");
}

/*
 * Callback function when a connection is set.
 */
void onOpen(WebSocketClient client) {
  Serial.println("Connected");
  client.send("Galileo connected");
}

/*
 * Callback function when a message is received.
 */
void onMessage(WebSocketClient client, char* message) {
  Serial.println("Message received");
  Serial.print("Received: "); Serial.println(message);
  
  // Parse JSON
  aJsonObject* jsonObject = aJson.parse(message);
  aJsonObject* numberOfClients = aJson.getObjectItem(jsonObject , "numberOfClients");
  aJsonObject* cabstatus = aJson.getObjectItem(jsonObject , "cabStatus");
  aJsonObject* distanceTravelled = aJson.getObjectItem(jsonObject , "distanceTravelled");
  customers = numberOfClients->valueint;
  
  cabState = cabstatus->valuestring;
  if(cabState.equals("free")){
    boolCabState = true;
  }else{
    boolCabState = false;
  }
  
  
  distance = distanceTravelled->valueint;

  
  Serial.print("Values received: ");
  Serial.print(customers);
  Serial.print(cabState);
  Serial.print("->");
  Serial.print(boolCabState);
  Serial.print(distance);
  
  
  // Refresh LCD with new information
  setCabStatus();
}

/*
 * Callback function when an error came.
 */
void onError(WebSocketClient client, char* message) {
  Serial.println("An error occured");
  Serial.print("ERROR: "); Serial.println(message);
}

/*
 * Read buttons signals
 */
int read_LCD_buttons(){
  // Read the value from the sensor 
  adc_key_in = analogRead(0);
    
  if (adc_key_in > 1000) return btnNONE; 
  if (adc_key_in < 50)   return btnRIGHT;  
  if (adc_key_in < 250)  return btnUP; 
  if (adc_key_in < 450)  return btnDOWN; 
  if (adc_key_in < 650)  return btnLEFT; 
  if (adc_key_in < 850)  return btnSELECT;  

  // If all other fail
  return btnNONE;                
}

/*
 * Galileo setting function (run once).
 */
void setup() {
  initLCD();  
  Serial.begin(9600);
  Serial.println("setup()");
  webSocketInitializer();
}

/*
 * Galileo loop function.
 */
void loop() {
  client.monitor();
  //if(boolCabState == false){
    lcdKey = read_LCD_buttons();
    switch (lcdKey){
      case btnRIGHT:{
        Serial.println("RIGHT BUTTON -> Reject");
        // Transform decline into JSON
        //answer=aJson.createObject();
        //aJson.addItemToObject(answer, "answer", aJson.createItem("false"));

        // Send decline
        //client.send(answer->valuestring);
        client.send(rejected);
        break;
      }
      case btnLEFT:{
        
        Serial.println("LEFT BUTTON -> Accept");
        // Transform accept into JSON
        //answer=aJson.createObject();
        //aJson.addItemToObject(answer, "answer", aJson.createItem("true"));
        
        // Send accept
        //client.send(answer->valuestring);
        client.send(accepted);
        break;
      }
      default:{
        // Do nothing
        break;
      }
    }
 // }
}
