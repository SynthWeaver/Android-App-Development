package com.example.reminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.terwijn.shoppinglistkotlin.Product


class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val etName: TextView = itemView.findViewById(android.R.id.text1)
        private val etAmount: TextView = itemView.findViewById(android.R.id.text1)


        fun bind(product: Product) {
            etName.text = product.name
            etAmount.text = product.quantity.toString()
        }

    }
}
