package com.example.ungdungbanhang.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ungdungbanhang.data.Product
import com.example.ungdungbanhang.databinding.FragmentHomeBinding
import com.example.ungdungbanhang.databinding.SpecialRvItemBinding

class SpecialProductsApdater: RecyclerView.Adapter<SpecialProductsApdater.SpecialProductsViewHoslder>() {
    inner class SpecialProductsViewHoslder(private  val binding: SpecialRvItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imageSpecialRvItem)
                tvSpecialProductName.text = product.name
                tvSpecialPrdouctPrice.text = product.price.toString()
            }
        }
    }

    val diffCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductsViewHoslder {
        return  SpecialProductsViewHoslder(
            SpecialRvItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SpecialProductsViewHoslder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }
}