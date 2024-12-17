package com.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.modules.core.DeviceEventManagerModule

import android.util.Log

class CustomBroadcastReceiver: BroadcastReceiver() {
  companion object {
    var reactContext: ReactApplicationContext? = null
  }

  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.action
    val data = intent.getStringExtra("data")

    // data request
    if (action == "com.test.SEND_DATA_REQUEST") {
      Log.d("CustomBroadcastReceiver", "Received broadcast with action: $action and data: $data")

      val reactContext = Companion.reactContext

      if (reactContext == null) {
        Log.d("CustomBroadcastReceiver", "ReactContext is null, cannot send event.")
        
        return
      }

      if (!reactContext.hasActiveCatalystInstance()) {
        Log.d("CustomBroadcastReceiver", "React Native is not ready yet.")
        
        return
      }

      sendEvent(reactContext, "onRequestReceived", data)
    } else {
      Log.d("CustomBroadcastReceiver", "Received unknown broadcast with action: $action")
    }
  }

  private fun sendEvent(reactContext: ReactApplicationContext, eventName: String, data: String?) {
    Log.d("CustomBroadcastReceiver", "Worked!")

    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(eventName, data)
  }
}
