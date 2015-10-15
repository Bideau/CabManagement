#include <LiquidCrystal.h>
 
LiquidCrystal lcd(8, 9, 4, 5, 6, 7); 
 
// define some values used by the panel and buttons
int lcd_key     = 0;
int adc_key_in  = 0;
 
#define btnRIGHT  0
#define btnUP     1
#define btnDOWN   2
#define btnLEFT   3
#define btnSELECT 4
#define btnNONE   5


void initAllPins(){
  for(int i=4;i<10;i++){
    pinMode(i,OUTPUT);
  }
}
 
int read_LCD_buttons(){               // read the buttons
    adc_key_in = analogRead(0);       // read the value from the sensor 
    
    if (adc_key_in > 1000) return btnNONE; 
 
    if (adc_key_in < 50)   return btnRIGHT;  
    if (adc_key_in < 250)  return btnUP; 
    if (adc_key_in < 450)  return btnDOWN; 
    if (adc_key_in < 650)  return btnLEFT; 
    if (adc_key_in < 850)  return btnSELECT;  
 
    return btnNONE;                // when all others fail, return this.
}
 
void setup(){
   initAllPins();
   lcd.begin(16, 2);               // start the library
   lcd.setCursor(0,0);             // set the LCD cursor   position 
   lcd.print("Push the buttons");  // print a simple message on the LCD
}
  
void loop(){
   lcd.setCursor(9,1);             // move cursor to second line "1" and 9 spaces over
   lcd.print(millis()/1000);       // display seconds elapsed since power-up
 
   lcd.setCursor(0,1);             // move to the begining of the second line
   lcd_key = read_LCD_buttons();   // read the buttons
 
   switch (lcd_key){               // depending on which button was pushed, we perform an action
 
       case btnRIGHT:{             //  push button "RIGHT" and show the word on the screen
            lcd.print("RIGHT ");
            break;
       }
       case btnLEFT:{
             lcd.print("LEFT   "); //  push button "LEFT" and show the word on the screen
             break;
       }    
       case btnUP:{
             lcd.print("UP    ");  //  push button "UP" and show the word on the screen
             break;
       }
       case btnDOWN:{
             lcd.print("DOWN  ");  //  push button "DOWN" and show the word on the screen
             break;
       }
       case btnSELECT:{
             lcd.print("SELECT");  //  push button "SELECT" and show the word on the screen
             break;
       }
       case btnNONE:{
             lcd.print("NONE  ");  //  No action  will show "None" on the screen
             break;
       }
   }
}
