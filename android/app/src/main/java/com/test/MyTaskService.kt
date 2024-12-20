package com.test

import android.content.Intent
import android.util.Log
import com.facebook.react.HeadlessJsTaskService
import com.facebook.react.bridge.Arguments
import com.facebook.react.jstasks.HeadlessJsTaskConfig

class MyTaskService : HeadlessJsTaskService() {
  override fun getTaskConfig(intent: Intent): HeadlessJsTaskConfig? {
    Log.d("MyTaskService", "Test")

    return intent.extras?.let {
      HeadlessJsTaskConfig(
        "dataRequest",
        Arguments.fromBundle(it),
        5000, // timeout for the task
        true // optional: defines whether or not the task is allowed in foreground.
        // Default is false
      )
    }
  }
}
