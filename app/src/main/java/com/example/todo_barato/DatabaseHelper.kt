package com.example.todo_barato

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "Todo_Barato.db"
        const val DB_VERSION = 1
    }

    private val dbPath = context.getDatabasePath(DB_NAME).path

    fun copyDatabase() {
        val dbFile = context.getDatabasePath(DB_NAME)

        // TEMPORAL mientras desarrollas: borra la copia vieja para forzar
        // que siempre tome la versión más reciente del .db en assets
        if (dbFile.exists()) {
            dbFile.delete()
        }

        dbFile.parentFile?.mkdirs()
        context.assets.open("databases/$DB_NAME").use { input ->
            FileOutputStream(dbFile).use { output ->
                input.copyTo(output)
            }
        }
    }

    fun getDatabase(): SQLiteDatabase {
        copyDatabase()
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    override fun onCreate(db: SQLiteDatabase?) {}
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}