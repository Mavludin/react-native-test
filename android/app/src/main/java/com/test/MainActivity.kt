package com.test

import android.os.Bundle
import android.content.Context
import com.facebook.react.ReactActivity
import com.facebook.react.bridge.ReactContext
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import com.facebook.react.bridge.ReactApplicationContext

import android.content.IntentFilter

import android.util.Log

import com.test.CustomBroadcastReceiver

class MainActivity : ReactActivity() {

  private val broadcastReceiver = CustomBroadcastReceiver()

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  override fun getMainComponentName(): String = "Test"

  /**
   * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
   * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
   */
  override fun createReactActivityDelegate(): ReactActivityDelegate =
      DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val intentFilter = IntentFilter("com.test.SEND_DATA_REQUEST")
    registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
  }

  override fun onDestroy() {
    super.onDestroy()

    unregisterReceiver(broadcastReceiver)
  }
}