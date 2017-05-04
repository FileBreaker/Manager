//* FileBreaker
//*
//* V1.2
//*
//* Enero de 2017
//* Preparado por:
//*      Talleres de Electrónica y Delineación
//*      Centro de Asistencia a la Investigación
//*      Talleres de Apoyo a la Investigación
//*      Universidad Complutense de Madrid.
//*


// Includes
#include <LiquidCrystal.h>      //Se incluye la librería para el control de la pantalla LCD
#include <Bounce2.h>            //Librería Bounce2 para eliminar transitorios en botones de entrada
#include <elapsedMillis.h>      //Librería para cálculo de tiempo en loop

// Constantes
#define PIN_DIRECCION 7         //Pin de la placa con el que se controla el sentido de giro del motor
#define PIN_MOVIMIENTO 9        //Pin de la placa con el que se controla la velocidad de giro del motor
#define PIN_LED 2               //Pin de la placa al que se ha conectado el PIN_LED
#define PIN_LECTURA A0          //Pin donde se conecta la LDR para lectura del haz

#define BOTON_A 12              //Botón A conectado a E/S digital 12
#define BOTON_B 13              //Botón B conectado a E/S digital 13

// Variables globales
char cDato = 0;                 //Variable en la que se almacena lo enviado desde el ordenador
int iVelocidad = 0;             //Variable en la que se almacena la última velocidad seleccionada
int iLectura = 0;               //Variable en la que se almacena la lectura del canal de luz
int iIteracion = 0;             //Variable que almacena el numero de veces iIteraciontidas que se detecta la fresa introducida para detectar si se ha roto
int iMinimo = 1024;             //Variable donde se almacena el valor de luz con la fresa introducida en la calibracion
int iMaximo = 0;                //Variable donde se almacena el valor de luz con la fresa extraída en la calibracion
int iUmbral = 0;                //Umbral que se calculará por distancia Max Min en Calibración
int iVueltas = 0;               //Indica el número de ciclos de entrada de la fresa por experimento
unsigned int iPeriodo = 20000;  //Tiempo máximo esperable entre cambios de estado en la inserción de la fresa. Utilizado en loop
                                //Inicializamos a un tiempo mayor que el obtenido por observación en la velocidad más lenta 10% (15sg)
                                //para que no detecte rotura en el calibrado.
bool bMotorOn = false;          //Al inicio el motor está parado.
bool bCalibrando = true;        //Al inicio queremos calibrar.
bool bEstadoFresa = false;      //Booleano para detectar cambio de estado en la fresa
bool bGirando = false;          //Cuando recibo velocidad del PC, activo flag para empezar a comparar tiempos. Antes no puedo.

const int aiPeriodos[10] = {15000,6420,3950,3090,2290,1930,1770,1580,1250,1200}; // Constante (periodos en ms para cada velocidad)
                                                                                 // que usamos para cargar los valores de iPeriodo en func. motor.
#define C_INC_PERIODO 0.20      //Porcentaje en que incrementamos el periodo de una revolución para detectar rotura
#define C_UMBRAL      0.80      //Porcentaje en que incrementamos el periodo de una revolución para detectar rotura


//Instancias iniciales
LiquidCrystal lcd(10, 8, 6, 5, 4, 3);   // Inicialización del LCD 
Bounce boton_a = Bounce();              // Inicialización de Antirrebotes para boton A y B
Bounce boton_b = Bounce();
elapsedMillis emTiempo;                 // Inicialización del contador de tiempo

void motor (int iNuevaVelocidad)        //Función que cambia la velocidad de giro del motor
{
  digitalWrite(PIN_DIRECCION, HIGH);                                  //Indicamos dirección
  analogWrite (PIN_MOVIMIENTO, map(iNuevaVelocidad, 0, 100, 0, 255)); // Ponemos salida proporcional a Pololu
  if (iNuevaVelocidad==0) {
    iPeriodo = 20000;
  }
  else
  {
    iPeriodo=aiPeriodos[(iNuevaVelocidad/10)-1]*(1+C_INC_PERIODO);    //Cargamos el nuevo periodo
  }
}


void lcd_velocidad(int iNuevaVelocidad) //Función que indica la nueva velocidad
{

  lcd.setCursor(0, 1);                                                //Pinta la velocidad seleccionada, para evitar pausas, un solo print
  switch (iNuevaVelocidad)
  {                   
    case 0:  lcd.print("  Velocidad:   0% "); break;
    case 10: lcd.print("  Velocidad:  10% "); break;
    case 20: lcd.print("  Velocidad:  20% "); break;
    case 30: lcd.print("  Velocidad:  30% "); break;
    case 40: lcd.print("  Velocidad:  40% "); break;
    case 50: lcd.print("  Velocidad:  50% "); break;
    case 60: lcd.print("  Velocidad:  60% "); break;
    case 70: lcd.print("  Velocidad:  70% "); break;
    case 80: lcd.print("  Velocidad:  80% "); break;
    case 90: lcd.print("  Velocidad:  90% "); break;
    case 100: lcd.print("  Velocidad: 100% "); break;
  }
}


void lcd_umbrales(int iMin, int iLec,int iMax)                        //Función para indicar los umbrales en pantalla
{
      lcd.setCursor(1,3);
      lcd.print(iMin);
      lcd.print("    ");
      lcd.setCursor(6,3);
      
      if (iLec==0){
        lcd.print("-------");
        
      }
      else
      {
        lcd.print("- ");
        lcd.print(iLec);
        lcd.print("    ");
      }
      
      lcd.setCursor(13,3);
      lcd.print("- ");
      lcd.print(iMax);
      lcd.print("    ");
}

void setup()                        //Funcion setup, ejecutada al inicio una sóla vez.
{
  lcd.begin(20, 4);                 // Configuración del tipo de LCD: Columnas, líneas
  Serial.begin(9600);               // Configuración de la velocidad del puerto serie.
  pinMode(PIN_LED, OUTPUT);         //PIN_LED declarado como salida
  digitalWrite(PIN_LED, HIGH);      //PIN_LED encendido
  pinMode(BOTON_A, INPUT_PULLUP);   //BOTON_A con pullup interno activado
  pinMode(BOTON_B, INPUT_PULLUP);   //BOTON_B con pullup interno activado
  boton_a.attach(BOTON_A);          //Unimos una instancia del antirrebotes al boton_A
  boton_b.attach(BOTON_B);          //Unimos una instancia del antirrebotes al boton_B
  boton_a.interval(5);              //Fijamos el tiempo de desacople en 5ms al boton_A...
  boton_b.interval(5);              //...y el boton_B
  
  lcd.setCursor(0, 0);              //Escritura de presentación del LCD
  lcd.print("    FileBreakeR     ");
  lcd.setCursor(0, 1);
  lcd.print("  V1.2 Enero 2017   ");
  lcd.setCursor(0, 4);
  lcd.print("U.Complutense Madrid");
  delay(1000);
  lcd.clear();

  lcd.setCursor(0, 0);              //Inicia secuencia de calibración del LCD
  lcd.print("    Calibracion     ");
  lcd.setCursor(0, 1);
  lcd.print("Boton A: Girar/Parar");
  lcd.setCursor(0, 2);
  lcd.print(" Boton B: Terminar  ");

  while (bCalibrando) {
    boton_a.update();               //Actualizamos el antirrebotes.
    boton_b.update();

    if (boton_a.fell()) {           //En el flanco de bajada del botón A
      if(bMotorOn==false) {         //Si el motor estaba parado...
        motor(10);                  //...arranca al mínimo
      }
      else                          //Si estaba encendido, lo paro.
      {
        motor(0);
      }
      
      if (bMotorOn) {               //Actualizamos el flag.
        bMotorOn=false;
      }
      else
      {
        bMotorOn=true;
      }
      
    }

    if (boton_b.fell()) {           //Han pulsado el botón B (flanco bajada)
      motor(0);                     //Se para el motor
      bMotorOn=false;
      bCalibrando=false;            //Marcamos salida del bucle
    }

    //Código para acumular máximo y mínimo:

    iLectura = analogRead(PIN_LECTURA);

    if (iLectura<iMinimo) {
      iMinimo=iLectura;
    }

    if (iLectura>iMaximo) {
      iMaximo=iLectura;
    }

    if (bMotorOn==true){
      lcd_umbrales(iMinimo, iLectura, iMaximo);
    }
  }

  iUmbral=iMinimo+((iMaximo-iMinimo)*C_UMBRAL); //Calculamos el umbral de detección para loop
  lcd.clear();
  lcd.setCursor(0, 1);                          //Pasamos el control a loop
  lcd.print(" Esperando comando");
  lcd_umbrales(iMinimo, 0, iMaximo);            //Imprimo sólo máximo y mínimo

  iLectura =  analogRead(PIN_LECTURA);          //Dejamos en consonancia el booleano de estado de la fresa

  if (iLectura < iUmbral)                       //Fresa introducida
  {
    lcd.setCursor(9,3);                         //Indicamos el estado introducido de la fresa
    lcd.print("__");
    bEstadoFresa=true;                
  }
  else
  {
    lcd.setCursor(9,3);                         //Indicamos el estado introducido de la fresa
    lcd.print("^^");    
    bEstadoFresa=false;               
  }

  emTiempo=0;                                   //Inicializamos el contador de tiempo.

}

void loop()
{

  if (Serial.available() > 0)          //Si hay comando remoto, se actúa
  {
    cDato = Serial.read(); 
    switch (cDato)
    {
      lcd.setCursor(0,0);             //Borramos mensaje de rotura detectada, por si es segunda ejecución después de detectar fresa rota.
      lcd.print(iUmbral); //"                    ");
      case '0': motor(0); lcd_velocidad(0); bGirando = false; break;
      case '1': motor(10); lcd_velocidad(10); bGirando = true; break;
      case '2': motor(20); lcd_velocidad(20); bGirando = true; break;
      case '3': motor(30); lcd_velocidad(30); bGirando = true; break;
      case '4': motor(40); lcd_velocidad(40); bGirando = true; break;
      case '5': motor(50); lcd_velocidad(50); bGirando = true; break;
      case '6': motor(60); lcd_velocidad(60); bGirando = true; break;
      case '7': motor(70); lcd_velocidad(70); bGirando = true; break;
      case '8': motor(80); lcd_velocidad(80); bGirando = true; break;
      case '9': motor(90); lcd_velocidad(90); bGirando = true; break;
      case 'a': motor(100); lcd_velocidad(100); bGirando = true; break;
    }
    emTiempo=0;                         //Reseteamos contador de rotura de fresa.
  }

  iLectura =  analogRead(PIN_LECTURA);  //Envio de cada medida tomada del canal de luz por el puerto serie
  // Sólo para depuración, no descomentar: Serial.println(iLectura);
  
  if (iLectura < iUmbral) // Fresa introducida
  {
    lcd.setCursor(9,3);
    lcd.print("__");
    if (bEstadoFresa==false) {
      bEstadoFresa=true;                //Indicamos el estado introducido de la fresa
      emTiempo=0;                       //Reseteamos contador de rotura de fresa.
    }
  }
  
  if (iLectura > iUmbral) // Fresa extraída
  {
    lcd.setCursor(9,3);   
    lcd.print("^^");    
    if (bEstadoFresa==true) {
      bEstadoFresa=false;               //Indicamos el estado introducido de la fresa
      emTiempo=0;                       //Reseteamos contador de rotura de fresa.
    }
  }

  if (bGirando==true) {                 //Si hemos superado el tiempo de guarda, estando en giro
    if (emTiempo > iPeriodo) {
      lcd.setCursor(0,0);               //Entonces la fresa está rota
      lcd.print("  Rotura detectada  ");
      Serial.print('r');                //Informamos al PC.
      motor(0);
      lcd_velocidad(0);
      bGirando = false;                 //Paramos el motor, pero se puede volver a emitir comando desde el PC.
    }
  }

}


