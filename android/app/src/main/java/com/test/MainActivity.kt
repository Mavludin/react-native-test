package com.test

import android.os.Bundle
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

    // Лог перед добавлением слушателя
    Log.d("MainActivity", "Adding ReactInstanceEventListener")

    val reactInstanceManager = reactNativeHost.reactInstanceManager

    reactInstanceManager.addReactInstanceEventListener(object : ReactInstanceManager.ReactInstanceEventListener {
      override fun onReactContextInitialized(reactContext: ReactContext) {
        Log.d("MainActivity", "ReactContext Initialized")

        handleReactContext(reactContext)
      }
    })

    // Лог перед проверкой существующего контекста
    Log.d("MainActivity", "Checking if ReactContext has started creating")

    // Если контекст уже существует, сразу используем его
    if (reactInstanceManager.hasStartedCreatingInitialContext()) {
      Log.d("MainActivity", "ReactContext already initialized")

      reactInstanceManager.currentReactContext?.let {
        handleReactContext(it)
      }
    }
  }

  private fun handleReactContext(reactContext: ReactContext) {
    if (reactContext == null) {
      Log.d("MainActivity", "ReactContext is null in handleReactContext")
      
      return
    }

    if (reactContext is ReactApplicationContext) {
      CustomBroadcastReceiver.reactContext = reactContext

      val intentFilter = IntentFilter("com.test.SEND_DATA_REQUEST")
      registerReceiver(CustomBroadcastReceiver(), intentFilter)
  
      Log.d("MainActivity", "ReactContext assigned to CustomBroadcastReceiver")
    } else {
      Log.d("MainActivity", "ReactContext is not ReactApplicationContext")
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    unregisterReceiver(CustomBroadcastReceiver())
  }
}