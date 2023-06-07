package com.eganathan.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationSample(val context: Context, var title: String, var description: String) {

    val channelId = "P1"
    val channelName = "PANNAI_NF"
    val notificationManager =
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: NotificationCompat.Builder

    fun triggerNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

        notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(androidx.core.R.drawable.notification_bg_low_normal)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(description)
        notificationBuilder.addAction(R.drawable.ic_launcher_foreground,"OPEN",pendingIntent)
        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())

    }

}