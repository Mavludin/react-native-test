package com.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.json.JSONObject

import android.database.sqlite.SQLiteDatabase

import android.util.Log

class DataBroadcastReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.action
    val data = intent?.getStringExtra("data")

    if (action == Settings.sendDataRequestAction) {
      Log.d("DataBroadcastReceiver", "Received broadcast with action: $action and data: $data")

      val json = getDataFromAsyncStorage(context)

      Log.d("DataBroadcastReceiver", "Data from AsyncStorage: $json")

      val filteredJSON = filterJSONByFields(json, Settings.allowedFields)

      Log.d("DataBroadcastReceiver", "Filtered data from AsyncStorage: $filteredJSON")

      // Отправка данных обратно через broadcast
      val intent = Intent(Settings.sendDataAction).apply {
        putExtra("data", filteredJSON)
        setPackage(Settings.nextshiftPackage)
      }

      context.sendBroadcast(intent)

      Log.d("DataBroadcastReceiver", "Broadcast response sent")
    }
  }

  private fun getDataFromAsyncStorage(context: Context): String? {
    val databasePath = context.getDatabasePath("RKStorage").absolutePath
    val database: SQLiteDatabase

    return try {
      database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY)

      val cursor = database.rawQuery("SELECT value FROM catalystLocalStorage WHERE key = ?", arrayOf("persist:root"))
      var json: String? = null

      if (cursor.moveToFirst()) {
        json = cursor.getString(0)
      }

      cursor.close()
      database.close()

      json
    } catch (e: Exception) {
      Log.e("AsyncStorageReader", "Error reading from AsyncStorage database", e)

      null
    }
  }

  fun filterJSONByFields(json: String?, fields: List<String>): String? {
    return try {
      val jsonObject = JSONObject(json)
      val filteredObject = JSONObject()

      for (field in fields) {
        if (jsonObject.has(field)) {
          filteredObject.put(field, jsonObject.get(field))
        }
      }

      filteredObject.toString()
    } catch (e: Exception) {
      e.printStackTrace()
  
      null
    }
  }

}
