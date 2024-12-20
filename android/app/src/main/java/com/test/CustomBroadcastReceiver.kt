package com.test

import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.content.Intent

import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.modules.core.DeviceEventManagerModule

import com.facebook.react.HeadlessJsTaskService

import android.util.Log

class CustomBroadcastReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.action
    val data = intent?.getStringExtra("data") ?: "default_data"

    if (action == "com.test.SEND_DATA_REQUEST") {
      Log.d("CustomBroadcastReceiver", "onReceive")
      Log.d("CustomBroadcastReceiver", data)

      val service = Intent(context, MyTaskService::class.java)
      val bundle = Bundle()

      bundle.putString("data", data)

      service.putExtras(bundle)

      context.startService(service)

      HeadlessJsTaskService.acquireWakeLockNow(context)
    }
  }
}
