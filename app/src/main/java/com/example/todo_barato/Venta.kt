package com.example.todo_barato

data class Venta(
    val codigo: String,
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val tipo: String,
    val fechaVenta: String
)