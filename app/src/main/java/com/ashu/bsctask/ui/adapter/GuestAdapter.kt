package com.ashu.bsctask.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.bsctask.R
import com.ashu.bsctask.domain.entities.Person
import com.bumptech.glide.Glide

class GuestAdapter(val parentActivity: Activity): RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    var guests: List<Person> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val guestName = itemView.findViewById<TextView>(R.id.textGuestName)
        private val guestImage = itemView.findViewById<ImageView>(R.id.imageGuestImage)

        fun bind(guest: Person) {
            guestName.text = guest.name
            Glide
                .with(parentActivity)
                .load(guest.imageURL)
                .centerCrop()
                .placeholder(R.drawable.default_image_person)
                .into(guestImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_guest, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(guests[position])
    }

    override fun getItemCount(): Int = guests.size
}