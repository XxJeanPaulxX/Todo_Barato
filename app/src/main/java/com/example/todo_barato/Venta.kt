package com.example.todo_barato

data class Venta(
    val codigo: String,
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val tipo: String,
    val fechaVenta: String
) {
    val subtotal: Double get() = precio * cantidad
    val igv: Double get() = subtotal * 0.18
    val total: Double get() = subtotal + igv
}