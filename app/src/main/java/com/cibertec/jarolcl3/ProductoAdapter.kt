package com.cibertec.jarolcl3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cibertec.jarolcl3.databinding.ItemProductoBinding
import com.cibertec.jarolcl3.model.Producto

//3 implementar los metodos del adaptador
//Indicar de donde sacaras la data
class ProductoAdapter constructor(private var productos:List<Producto> = emptyList(), var onItemView:(Producto) ->Unit)
    : Adapter<ProductoAdapter.ViewHolder>()
     {

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        private val binding : ItemProductoBinding = ItemProductoBinding.bind(itemView)

        fun bind(producto:Producto) {
            binding.tvProducto.text = producto.producto
            binding.tvDescripcion.text = producto.descripcion
            binding.tvStock.text = "${producto.stock}"
            binding.imgView.setOnClickListener {
                onItemView(producto)
        }
        }

    }

    fun updateList(productos:List<Producto>){
        this.productos = productos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_producto,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = productos[position]
        holder.bind(user)
    }
}