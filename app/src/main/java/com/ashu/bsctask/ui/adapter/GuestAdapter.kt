package com.ashu.bsctask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.bsctask.R
import com.ashu.bsctask.domain.entities.Person

class GuestAdapter(): RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    var guests: List<Person> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val guestName = itemView.findViewById<TextView>(R.id.textGuestName)

        fun bind(guest: Person) {
            guestName.text = guest.name
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