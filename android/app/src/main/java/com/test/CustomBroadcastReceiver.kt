package com.test

import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.os.Build
import android.content.Intent

import com.facebook.react.HeadlessJsTaskService

import android.util.Log

class CustomBroadcastReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.action
    val data = intent?.getStringExtra("data") ?: "default_data"

    if (action == "com.test.SEND_DATA_REQUEST") {
      Log.d("CustomBroadcastReceiver", "Received broadcast with action: $action and data: $data")

      val service = Intent(context, MyTaskService::class.java)
      val bundle = Bundle()

      bundle.putString("data", data)

      service.putExtras(bundle)

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(service)
      } else {
        context.startService(service)
      }

      HeadlessJsTaskService.acquireWakeLockNow(context)
    }
  }
}
