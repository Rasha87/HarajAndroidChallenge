package com.example.harajtask.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.data.HarajData
import com.example.harajtask.utils.DateManger
import com.example.harajtask.utils.LanguageHelper
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.app_bar_main.*

class DetailsActivity : AppCompatActivity() {
     companion object {
        val HARAJDATA: String = "data"
    }
   lateinit var  harajData : HarajData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_main)

        LanguageHelper.arabicLanguage(this)
        setupUI()
    }
    fun setupUI()
    {
        search.visibility = View.GONE
        harajData = intent.getSerializableExtra(HARAJDATA) as HarajData
        Glide.with(detailsThumbImage.context)
            .load(harajData.thumbURL)
            .into(detailsThumbImage)
        txtViewDetailsTitle.text = harajData.title
        val date = DateManger.getDate(harajData.date!!)
        txtViewDetailsDate.text = date
        txtViewDetailsLocation.text = harajData.city
        txtViewDetailsUser.text = harajData.username
        txtViewDetailsBody.text = harajData.body
        share.setOnClickListener { shareTitle() }

     }
    fun shareTitle()
    {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, harajData.body)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}