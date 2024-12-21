package com.test

import android.os.Build
import android.content.pm.ServiceInfo

import android.content.Intent
import com.facebook.react.HeadlessJsTaskService
import com.facebook.react.bridge.Arguments
import com.facebook.react.jstasks.HeadlessJsTaskConfig

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

class MyTaskService : HeadlessJsTaskService() {
  override fun onCreate() {
    super.onCreate()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channelId = "channel_id"
      val channel = NotificationChannel(
        channelId,
        "Foreground Service Channel",
        NotificationManager.IMPORTANCE_DEFAULT
      )

      val manager = getSystemService(NotificationManager::class.java)
      manager?.createNotificationChannel(channel)

      val notification = NotificationCompat.Builder(this, channelId)
        .setContentTitle("Service is Running")
        .setContentText("Foreground Service in Action")
        .setSmallIcon(R.drawable.ic_notification)
        .build()

      startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
    }
  }

  override fun getTaskConfig(intent: Intent): HeadlessJsTaskConfig? {
    return intent.extras?.let {
      HeadlessJsTaskConfig(
        "DataRequest",
        Arguments.fromBundle(it),
        5000, // timeout for the task
        true // optional: defines whether or not the task is allowed in foreground.
        // Default is false
      )
    }
  }
}
