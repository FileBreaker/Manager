/*
  Filebreaker Driver

  modified 11 Apr 2015
  by Daniel Ortega (danielortegaufano@gmail.com)
 */

// enum (constant) to declare PINS
enum { LDR_PIN = 0, BUTTON_ONOFF_PIN = 2, LED_PIN = 12, PWM_MOTOR_PIN = 3, DIR_MOTOR_PIN = 12 };

// LED
const int LED_DELAY_MS = 200;

// MOTOR
int motorSpeed = 250;

// ON-OFF BUTTON
int filebreakerState = HIGH;
int previousOnOffButtonState = LOW;

// LDR
int oscillations = 0;

enum { LDR_STATE_LOW_REACHED = 0, LDR_STATE_BETWEEN_LIMITS = 1, LDR_STATE_HIGH_REACHED = 2 };
int previousLdrState = LDR_STATE_BETWEEN_LIMITS;

const int LDR_LOW_ANALOG_LIMIT = 400;
const int LDR_HIGH_ANALOG_LIMIT = 800;

// the setup function runs once when you press reset or power the board
void setup() {
  // setting pins ...
  pinMode(LED_PIN, OUTPUT);
  pinMode(PWM_MOTOR_PIN, OUTPUT);
  pinMode(DIR_MOTOR_PIN, OUTPUT);
  pinMode(BUTTON_ONOFF_PIN, INPUT);
  
  Serial.begin(9600);
}

// the loop function runs over and over again forever
void loop() {  
  // 1. Turn debug led on every 'LED_DELAY_MS' milliseconds
  activateDebugLed(LED_DELAY_MS);

  // 2. Turn motor on (speed range: 0 to 255)
  activateMotor(motorSpeed, LOW);
  
  // 3. Read LDR
  activateLdr();
  
  // TODO: Design sensor to estimate motor rpms  
  readOnOffButton();
}

int calculateCurrentLdrState(int analogValue){  
  int state = LDR_STATE_BETWEEN_LIMITS;
  
  if(analogValue >= LDR_HIGH_ANALOG_LIMIT){
    state = LDR_STATE_HIGH_REACHED;
  } else if(analogValue <= LDR_LOW_ANALOG_LIMIT){
    state = LDR_STATE_LOW_REACHED;
  }
  
  return state;
}

void activateLdr(){
  int analogValue = analogRead(LDR_PIN);
  int currentLdrState = calculateCurrentLdrState(analogValue);
  
  if(currentLdrState != previousLdrState){
    // A transition occurred    
    if(currentLdrState == LDR_LOW_ANALOG_LIMIT){
      oscillations++;
    }
  }
  
  previousLdrState = currentLdrState;
  
  Serial.println(analogValue);
  Serial.print("oscillations: "); 
  Serial.print(oscillations);
  Serial.flush();
}

void readOnOffButton(){
  int currentOnOffButtonState = digitalRead(BUTTON_ONOFF_PIN) + readSerial();
  Serial.print("currentOnOfState: " + currentOnOffButtonState);
  
  if(currentOnOffButtonState != previousOnOffButtonState){
     // A transition occurred
     if(currentOnOffButtonState == LOW){
      // Switch is now pressed
      changeFileBreakerState();
     } else {
         // Switch is now released
     }
  }
  
  previousOnOffButtonState = currentOnOffButtonState;
}

void changeFileBreakerState(){
  if(filebreakerState == HIGH){
     filebreakerState = LOW;
  } else {
     filebreakerState = HIGH;
  }
}

// activates and deactivates debug led to ensure that the filebreaker driver is running on arduino board
void activateDebugLed(short int millis) {
  if(filebreakerState == HIGH){
    digitalWrite(LED_PIN, HIGH);
  } else {
    digitalWrite(LED_PIN, LOW);
  }
}

int readSerial(){
  if(Serial.available() < 0) return 0;
  return Serial.read();
}

// activates motor if given speed is in valid range
// TODO: Fix direction var (still not working!!)
void activateMotor(short int speed, int direction) {
  if(filebreakerState == HIGH){
    if(speed >= 0 && speed <= 255){
      analogWrite(DIR_MOTOR_PIN, direction);
      analogWrite(PWM_MOTOR_PIN, speed);
    }
  } else {
    analogWrite(PWM_MOTOR_PIN, 0);
  }
}

