package com.test

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

import android.util.Log

class BroadcastSender(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  override fun getName(): String {
    return "BroadcastSender"
  }

  @ReactMethod
  private fun sendBroadcast(data: String?) {
    val action = "com.nextshift.SEND_DATA"

    val intent = Intent(action).apply {
      putExtra("data", data)
      setPackage("com.nextshift")
    }

    reactApplicationContext.sendBroadcast(intent)
    
    Log.d("BroadcastSender", "Broadcast sent with action: $action and data: $data")
  }
}
