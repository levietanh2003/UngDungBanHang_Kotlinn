package com.example.ungdungbanhang.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ungdungbanhang.data.Product
import com.example.ungdungbanhang.databinding.BestDealsRvItemBinding
import com.example.ungdungbanhang.databinding.ProductRvItemBinding
import java.text.NumberFormat
import java.util.*

class BestProudctApdapter: RecyclerView.Adapter<BestProudctApdapter.BestProductViewHolder>() {


    inner class BestProductViewHolder( private val binding: ProductRvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f  - it
                    val priceAffterOffer = remainingPricePercentage * product.price
                    //tvNewPrice.text = "$ ${String.format("%.2f",priceAffterOffer)}"
                    tvNewPrice.text = formatTienTeVietNam(priceAffterOffer.toDouble())
                    // gach ngang gia da giam
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if(product.offerPercentage == null)
                    tvNewPrice.visibility = View.VISIBLE
                //tvPrice.text = "$ ${product.price}"
                tvPrice.text = formatTienTeVietNam(product.price.toDouble())
                tvName.text = product.name
            }
        }
    }
    private val diffCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // doi tien te
    fun formatTienTeVietNam(giaTienTrongVND: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.currency = Currency.getInstance("VND")
        return numberFormat.format(giaTienTrongVND)
    }
}