package com.example.todo_barato

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.getDatabase()

        // --- insertar dato de prueba (solo por ahora) ---
        db.execSQL(
            "INSERT INTO Ventas (codigo, nombre, precio, cantidad, tipo, fecha_de_venta) VALUES (?, ?, ?, ?, ?, ?)",
            arrayOf("XYZ001", "Laptop Gamer HP", 2500, 1, "Factura", "2026-07-20")
        )

        // --- prueba de conexión (consulta) ---
        val cursor = db.rawQuery("SELECT * FROM Ventas", null)
        Log.d("DB_TEST", "Filas encontradas: ${cursor.count}")
        while (cursor.moveToNext()) {
            val codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
            Log.d("DB_TEST", "Código: $codigo")
        }
        cursor.close()
        db.close()
    }
}