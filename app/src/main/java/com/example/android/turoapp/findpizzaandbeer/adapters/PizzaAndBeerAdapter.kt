package com.example.android.turoapp.findpizzaandbeer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.turoapp.R
import com.example.android.turoapp.findpizzaandbeer.models.Business
import java.util.*

class PizzaAndBeerAdapter(var data: ArrayList<Business> = ArrayList()) : RecyclerView.Adapter<PizzaAndBeerAdapter.PizzaAndBeerViewHolder>(){


    class PizzaAndBeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val businessName = view.findViewById<TextView>(R.id.business_name)
        val businessImage = view.findViewById<ImageView>(R.id.business_image)
        val businessRating = view.findViewById<TextView>(R.id.business_rating)
        val businessLocation = view.findViewById<TextView>(R.id.business_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaAndBeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.business_container,parent,false)
        return PizzaAndBeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaAndBeerViewHolder, position: Int) {
        //Using data binding will allow for a better way to concatenate the values of the strings
        val business = data[position]
        holder.apply {
            businessName.text = "Name: ${business.name}"
            businessLocation.text = "Location: ${business.location}"
            businessRating.text = "Rating: ${business.rating}"
            Glide.with(businessImage).load(business.image_url).into(businessImage)
        }
        //load image url
    }

    override fun getItemCount(): Int {
        return data.size
    }
}