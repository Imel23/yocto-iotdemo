#!/usr/bin/env python3
"""
IoT Sensor Agent - MQTT Publisher
Simulates temperature/humidity sensor and publishes to MQTT broker
"""

import paho.mqtt.client as mqtt
from paho.mqtt.enums import CallbackAPIVersion
import random
import os
import time
import json
import socket

# sensor simulation
def read_sensor():
    """Simulate reading temperature and humidity"""
    temp = 20 + random.uniform(-2, 2)
    humidity = 50 + random.uniform(-5, 5)
    return temp, humidity

def on_connect(client, userdata, flags, reason_code, properties):
    if reason_code == 0:
        print("Connected successfully")
    else:
        print(f"Connection failed: {reason_code}")

if __name__ == "__main__":
    client = mqtt.Client(callback_api_version=CallbackAPIVersion.VERSION2)
    client.on_connect = on_connect

    hostname = socket.gethostname()
    MQTT_BROKER = os.getenv("MQTT_BROKER", "localhost")
    MQTT_PORT = int(os.getenv("MQTT_PORT", 1883))
    MQTT_TOPIC = os.getenv("MQTT_TOPIC", f"devices/{hostname}/telemetry")

    while True:
        try:
            client.connect(MQTT_BROKER, MQTT_PORT, 60)
            break
        except Exception as e:
            print(f"Connection failed: {e}, retrying in 5s...")
            time.sleep(5)
        
    client.loop_start()

    while True:
        temp, humidity = read_sensor()
        payload = json.dumps({
            "timestamp": time.time(),
            "temperature": round(temp, 2),
            "humidity": round(humidity, 2)
        })
        client.publish(MQTT_TOPIC, payload, qos=1)
        print(f"Published: {payload}")
        time.sleep(5)
