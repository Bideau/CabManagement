// include the library code:
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(8, 9, 4, 5, 6, 7);

void setup() {
  // init all pins
  for(int i=4;i<10;i++){
    pinMode(i,OUTPUT);
  }
  
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);

  // print the test message
  lcd.print("THIS IS A TEST !");
}

void loop() {
  // set the cursor to column 0, line 1
  // (note: line 1 is the second row, since counting begins with 0):
  lcd.setCursor(0, 1);

  lcd.print("Time: ");
  lcd.setCursor(6, 1);
  // print the number of seconds since reset:
  lcd.print(millis() / 1000);
}
