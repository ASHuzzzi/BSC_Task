package com.ashu.bsctask.ui

import android.graphics.drawable.Drawable
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter by lazy {
        GuestAdapter(this)
    }
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
            Glide
                .with(this)
                .load(party.partyImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(exception: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.VISIBLE
                        imageTitleParty.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        imageTitleParty.visibility = View.VISIBLE
                        return false
                    }
                })
                .centerCrop()
                .into(imageTitleParty)

            textPartyTitle.text = party.partyName

            Glide
                .with(this)
                .load(party.hostParty.imageURL)
                .centerCrop()
                .placeholder(R.drawable.default_image_person)
                .into(imageHostParty)
            textInvite.text = resources.getString(R.string.invited, party.hostParty.name)
            adapter.guests = party.guests
            layoutError.visibility = View.GONE
        })

        viewModel.errorLiveData().observe(this, {
            layoutParty.visibility = View.GONE
            layoutTitleImage.visibility = View.GONE
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