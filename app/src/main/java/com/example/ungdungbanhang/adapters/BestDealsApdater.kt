package com.example.ungdungbanhang.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ungdungbanhang.data.Product
import com.example.ungdungbanhang.databinding.BestDealsRvItemBinding
import java.text.NumberFormat
import java.util.Currency

class BestDealsApdater: RecyclerView.Adapter<BestDealsApdater.BestDealsViewHolder>(){

    inner class BestDealsViewHolder( private val binding: BestDealsRvItemBinding): ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgBestDeal)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f  - it
                    val priceAffterOffer = remainingPricePercentage * product.price
                    //tvNewPrice.text = "$ ${String.format("%.2f",priceAffterOffer)}"
                    tvNewPrice.text = formatTienTeVietNam(priceAffterOffer.toDouble())
                    tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if(product.offerPercentage == null)
                    tvNewPrice.visibility = View.VISIBLE
                tvOldPrice.text = formatTienTeVietNam(product.price.toDouble())
                tvDealProductName.text = product.name
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            BestDealsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
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