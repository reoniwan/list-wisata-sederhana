package com.frozenproject.sanggauborneo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frozenproject.sanggauborneo.R
import com.frozenproject.sanggauborneo.model.PlaceModel

class ListPlaceAdapter (internal var context: Context,
                        internal  var placeList: List<PlaceModel>):
    RecyclerView.Adapter<ListPlaceAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_place_item, parent, false))
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(placeList.get(position).image)
            .into(holder.place_image!!)


        holder.place_name!!.setText(placeList.get(position).name)
        holder.place_address!!.setText(placeList.get(position).address)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var place_name:TextView?=null
        var place_address:TextView?=null
        var place_image:ImageView?=null

       init {
           place_name = itemView.findViewById(R.id.txt_place_name) as TextView
           place_address = itemView.findViewById(R.id.txt_place_address) as TextView
           place_image = itemView.findViewById(R.id.img_place_image) as ImageView
       }

    }
}