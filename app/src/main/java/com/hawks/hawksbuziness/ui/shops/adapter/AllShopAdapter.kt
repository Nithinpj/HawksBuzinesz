package com.hawks.hawksbuziness.ui.shops.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hawks.hawksbuziness.databinding.LayoutShopsBinding
import com.hawks.hawksbuziness.model.shops.Shop

class AllShopAdapter(val click:(Shop)->Unit) : RecyclerView.Adapter<AllShopAdapter.ViewHolder>() {

    var shops_list: List<Shop> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutShopsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(click,shops_list[position])
    }

    override fun getItemCount(): Int {
        return shops_list.size
    }

    class ViewHolder(val itemview: LayoutShopsBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun bindData(click: (Shop) -> Unit, shop: Shop){
            itemview.shop=shop
            itemView.setOnClickListener {
                click(shop)
            }
        }
    }

    fun bind(shops: List<Shop>) {
        shops_list=shops
    }
}