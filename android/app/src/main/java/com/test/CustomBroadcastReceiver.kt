package com.test

import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.os.Build
import android.content.Intent

import android.database.sqlite.SQLiteDatabase

import com.facebook.react.HeadlessJsTaskService

import android.util.Log

class CustomBroadcastReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.action
    val data = intent?.getStringExtra("data") ?: "default_data"

    if (action == "com.test.SEND_DATA_REQUEST") {
      Log.d("CustomBroadcastReceiver", "Received broadcast with action: $action and data: $data")

      // Чтение данных из базы данных SQLite (AsyncStorage)
      val storedValue = readFromAsyncStorage(context)

      // Логирование прочитанных данных
      Log.d("CustomBroadcastReceiver", "Data from AsyncStorage: $storedValue")

      val senderAction = "com.nextshift.SEND_DATA"

      // Отправка данных обратно через broadcast
      val intent = Intent(senderAction).apply {
        putExtra("data", storedValue)
        setPackage("com.nextshift")
      }

      context.sendBroadcast(intent) // Отправляем Broadcast

      Log.d("CustomBroadcastReceiver", "Broadcast response sent")
    }
  }

  private fun readFromAsyncStorage(context: Context): String? {
    val databasePath = context.getDatabasePath("RKStorage").absolutePath
    val database: SQLiteDatabase

    try {
      // Открываем базу данных SQLite
      database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY)

      // Выполняем запрос для получения значения по ключу
      val cursor = database.rawQuery("SELECT value FROM catalystLocalStorage WHERE key = ?", arrayOf("persist:root"))
      var value: String? = null

      if (cursor.moveToFirst()) {
        value = cursor.getString(0) // Получаем значение из столбца "value"
      }

      cursor.close()
      database.close()

      return value
    } catch (e: Exception) {
      Log.e("AsyncStorageReader", "Error reading from AsyncStorage database", e)
      return null
    }
  }
}
