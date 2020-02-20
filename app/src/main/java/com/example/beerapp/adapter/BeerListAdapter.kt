package com.example.beerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.R
import com.example.beerapp.model.BeerResponse
import com.squareup.picasso.Picasso

class BeerListAdapter :
    ListAdapter<BeerResponse, BeerListAdapter.BeerViewHolder>(DIFF_CALLBACK) {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_list_entry, parent, false)
        return BeerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beerResponse = getItem(position)
        holder.txtName.text = beerResponse.name
        holder.txtValue.text = beerResponse.volume.value.toString()
        holder.txtUnit.text = beerResponse.volume.unit
        val picasso = Picasso.get()
        picasso.load(beerResponse.image_url).into(holder.imgBeer)

    }

    inner class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView = view.findViewById(R.id.txtBeerName)
        var txtUnit: TextView = view.findViewById(R.id.txtBeerUnit)
        var txtValue: TextView = view.findViewById(R.id.txtBeerValue)
        var imgBeer: ImageView = view.findViewById(R.id.imgBeerImage)


        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position), it)
                }
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(beerResponse: BeerResponse, view: View)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BeerResponse>() {

            override fun areItemsTheSame(@NonNull oldItem: BeerResponse, @NonNull newItem: BeerResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(@NonNull oldItem: BeerResponse, @NonNull newItem: BeerResponse): Boolean {
                return (oldItem.name.equals(newItem.name) && oldItem.volume.value.equals(newItem.volume.value)
                        && oldItem.volume.unit.equals(newItem.volume.value))
            }
        }
    }
}