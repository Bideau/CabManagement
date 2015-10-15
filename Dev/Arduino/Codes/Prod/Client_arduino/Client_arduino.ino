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
char server[] = "echo.websocket.org";
WebSocketClient client;

int customers;
boolean cabState;
int distanceTravelled;
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
  if(cabState == true){
    lcd.print("Free");
  }else{
    lcd.print("Busy");
  }

  // Distance
  lcd.setCursor(10,0);
  lcd.print(distanceTravelled);
  lcd.setCursor(11,0);
  lcd.print("Km");

  // Buttons
  lcd.setCursor(0,1);
  if(cabState == true){
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
  Ethernet.begin(mac);
  client.connect(server);
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
  int customers = numberOfClient->valueint;
  boolean cabState = cabstatus->valuebool;
  int distanceTravelled = distanceTravelled->valueint;

  
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
  client.send("Hello World!");
}

/*
 * Galileo loop function.
 */
void loop() {
  client.monitor();
  if(cabBusy == false){
    lcdKey = read_LCD_buttons();
    switch (lcdKey){
      case btnRIGHT:{
        // Transform decline into JSON
        answer=aJson.createObject();
        aJson.addItemToObject(answer, "answer", aJson.createString("false"));

        // Send decline
        client.send(anwser->valuestring);
        break;
      }
      case btnLEFT:{
        // Transform accept into JSON
        answer=aJson.createObject();
        aJson.addItemToObject(answer, "answer", aJson.createString("true"));
        
        // Send accept
        client.send(anwser->valuestring);
        break;
      }
      default:{
        // Do nothing
        break;
      }
    }
  }
}
