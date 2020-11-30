package com.ashu.bsctask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashu.bsctask.R
import com.ashu.bsctask.ui.adapter.GuestAdapter
import com.ashu.bsctask.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter = GuestAdapter()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initRecyclerView() {
        recyclerView = recyclerGuests
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.partyLiveData().observe(this, { party ->
            layoutParty.visibility = View.VISIBLE
            textPartyTitle.text = party.partyName
            textInvite.text = resources.getString(R.string.invited, party.hostParty.name)
            adapter.guests = party.guests
            layoutError.visibility = View.GONE
        })
        viewModel.errorLiveData().observe(this, {
            layoutParty.visibility = View.GONE
            layoutError.visibility = View.VISIBLE
            textError.text = it
        })
        viewModel.getParty()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}