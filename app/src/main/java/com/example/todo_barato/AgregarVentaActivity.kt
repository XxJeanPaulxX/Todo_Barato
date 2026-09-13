package com.example.todo_barato

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AgregarVentaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_venta)

        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPrecio = findViewById<EditText>(R.id.etPrecio)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val rbBoleta = findViewById<RadioButton>(R.id.rbBoleta)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val codigo = etCodigo.text.toString().trim()
            val nombre = etNombre.text.toString().trim()
            val precioTexto = etPrecio.text.toString().trim()
            val cantidadTexto = etCantidad.text.toString().trim()
            val fecha = etFecha.text.toString().trim()
            val tipo = if (rbBoleta.isChecked) "Boleta" else "Factura"

            if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() ||
                cantidadTexto.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val precio = precioTexto.toDoubleOrNull()
            val cantidad = cantidadTexto.toIntOrNull()

            if (precio == null || cantidad == null) {
                Toast.makeText(this, "Precio o cantidad inválidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DatabaseHelper(this)
            val db = dbHelper.getDatabase()
            db.execSQL(
                "INSERT INTO Ventas (codigo, nombre, precio, cantidad, tipo, fecha_de_venta) VALUES (?, ?, ?, ?, ?, ?)",
                arrayOf(codigo, nombre, precio, cantidad, tipo, fecha)
            )
            db.close()

            Toast.makeText(this, "Venta guardada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}