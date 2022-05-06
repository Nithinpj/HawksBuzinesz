package com.hawks.hawksbuziness.ui.settings.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.model.places.Data
import com.hawks.hawksbuziness.model.shops.Shop

class PlaceAdapter(val name:String,val list: ArrayList<Data>,val click:(Data)->Unit): RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview.rootView) {
        var place=itemview.findViewById<TextView>(R.id.place)
        val radio_place=itemview.findViewById<RadioButton>(R.id.radio_place)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ViewHolder {

        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.place_row,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PlaceAdapter.ViewHolder, position: Int) {

        holder.place.text=list[position].place

        if (!name.isNullOrEmpty()){
            if (list[position].place.equals(name)){
                holder.radio_place.isChecked=true
            }
        }
        holder.radio_place.setOnClickListener {
            click(list[position])
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

}