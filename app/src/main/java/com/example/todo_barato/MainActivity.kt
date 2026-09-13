package com.example.todo_barato

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.getDatabase()

        // --- datos de prueba (temporal) ---
        db.execSQL(
            "INSERT INTO Ventas (codigo, nombre, precio, cantidad, tipo, fecha_de_venta) VALUES (?, ?, ?, ?, ?, ?)",
            arrayOf("XYZ001", "Laptop Gamer HP", 2500.0, 1, "Factura", "2026-07-20")
        )
        db.execSQL(
            "INSERT INTO Ventas (codigo, nombre, precio, cantidad, tipo, fecha_de_venta) VALUES (?, ?, ?, ?, ?, ?)",
            arrayOf("XYZ002", "Teclado Logitech", 70.0, 1, "Boleta", "2026-08-14")
        )
        // --- fin datos de prueba ---

        val listaVentas = mutableListOf<Venta>()
        val cursor = db.rawQuery("SELECT * FROM Ventas", null)
        while (cursor.moveToNext()) {
            listaVentas.add(
                Venta(
                    codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
                    cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad")),
                    tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo")),
                    fechaVenta = cursor.getString(cursor.getColumnIndexOrThrow("fecha_de_venta"))
                )
            )
        }
        cursor.close()
        db.close()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVentas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VentaAdapter(listaVentas)
    }
}