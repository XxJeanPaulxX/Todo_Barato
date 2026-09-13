package com.example.todo_barato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VentaAdapter(private val listaVentas: List<Venta>) :
    RecyclerView.Adapter<VentaAdapter.VentaViewHolder>() {

    class VentaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venta, parent, false)
        return VentaViewHolder(view)
    }

    override fun onBindViewHolder(holder: VentaViewHolder, position: Int) {
        val venta = listaVentas[position]
        holder.tvNombre.text = venta.nombre
        holder.tvCodigo.text = "Código: ${venta.codigo}"
        holder.tvPrecio.text = "S/ ${venta.precio}"
    }

    override fun getItemCount(): Int = listaVentas.size
}