int wait = 50;

#define L1 8
#define L2 9
#define R1 12
#define R2 13
#define LPower 10
#define RPower 11

void setup() {
  pinMode(L1, OUTPUT);
  pinMode(L2, OUTPUT);
  pinMode(R1, OUTPUT);
  pinMode(R2, OUTPUT);
  pinMode(LPower, OUTPUT);
  pinMode(RPower, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    wait = Serial.read();
    int value = wait & 127;
    if (wait & 128) {
      //right wheel
      if (value < 50) {
        analogWrite(RPower, ((50 - value) * 255) / 50);
        digitalWrite(R1, LOW);
        digitalWrite(R2, HIGH);
      } else {
        analogWrite(RPower, ((value - 50) * 255) / 50);
        digitalWrite(R1, HIGH);
        digitalWrite(R2, LOW);
      }
    } else {
      //left wheel
      if (value < 50) {
        analogWrite(LPower, ((50 - value) * 255) / 50);
        digitalWrite(L1, HIGH);
        digitalWrite(L2, LOW);
      } else {
        analogWrite(LPower, ((value - 50) * 255) / 50);
        digitalWrite(L1, LOW);
        digitalWrite(L2, HIGH);
      }
    }
    Serial.write(wait);
  }
}
