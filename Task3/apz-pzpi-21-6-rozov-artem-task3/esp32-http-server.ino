#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

const char* ssid = "Wokwi-GUEST";
const char* password = "";
const char* serverUrl = "https://933b-178-165-27-143.ngrok-free.app/Vehicles/UpdateLocation/1";

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");
  randomSeed(analogRead(0));
}

void loop() {
  String randomString = "RandomString" + String(random(1000));
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    http.begin(serverUrl);

    http.addHeader("Content-Type", "application/json");

    DynamicJsonDocument doc(256);
    doc["newLocation"] = randomString;
    char buffer[256];
    serializeJson(doc, buffer);
    String jsonData = String(buffer);

    Serial.println("Sending PUT request to: " + String(serverUrl));
    Serial.println("Data: " + jsonData);

    int httpResponseCode = http.PUT(jsonData);

    if (httpResponseCode > 0) {
      Serial.print("HTTP Response code: ");
      Serial.println(httpResponseCode);
    } else {
      Serial.print("Error code: ");
      Serial.println(httpResponseCode);
    }

    http.end();
  } else {
    Serial.println("Error in WiFi connection");
  }

  delay(10000);
}
