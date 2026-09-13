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