package com.example.todo_barato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class VentaAdapter(private val listaVentas: List<Venta>) :
    RecyclerView.Adapter<VentaAdapter.VentaViewHolder>() {

    class VentaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvTipo: TextView = view.findViewById(R.id.tvTipo)
        val tvPrecioUnit: TextView = view.findViewById(R.id.tvPrecioUnit)
        val tvSubtotal: TextView = view.findViewById(R.id.tvSubtotal)
        val tvIgv: TextView = view.findViewById(R.id.tvIgv)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
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
        holder.tvTipo.text = venta.tipo

        holder.tvPrecioUnit.text = String.format(Locale.getDefault(), "P. unitario: S/ %.2f x %d", venta.precio, venta.cantidad)
        holder.tvSubtotal.text = String.format(Locale.getDefault(), "Subtotal: S/ %.2f", venta.subtotal)
        holder.tvIgv.text = String.format(Locale.getDefault(), "IGV (18%%): S/ %.2f", venta.igv)
        holder.tvTotal.text = String.format(Locale.getDefault(), "Total: S/ %.2f", venta.total)
    }

    override fun getItemCount(): Int = listaVentas.size
}