package com.example.firsttest

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import com.example.firsttest.ui.theme.FirstTestTheme


class MainActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstTestTheme {

                TemperatureSensor()
                Navigation(viewModel)

            }

        }
    }

    private fun show_notification() {

        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, "basic id")
            .setContentText("Temperature is over 30 :)")
            .setContentTitle("Temperature notification")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(1, notification)
    }


    private var tempSensor: Sensor? = null
    private fun TemperatureSensor() {

        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        }

        val temperatureSensorEventListener = object : SensorEventListener {

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                //not needed
            }

            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                    val temperature = event.values[0]

                    //Log.d("temperature", "current temp: " + temperature)
                    if (temperature > 30) {
                        show_notification()
                    }

                }
            }
        }
        sensorManager.registerListener(
            temperatureSensorEventListener,
            tempSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

    }
}
