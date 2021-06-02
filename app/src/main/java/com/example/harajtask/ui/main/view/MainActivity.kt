package com.example.harajtask.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.R
import com.example.harajtask.data.HarajData
import com.example.harajtask.data.local.AssetHelperImpl
import com.example.harajtask.ui.base.ViewModelFactory
import com.example.harajtask.ui.main.OnClickAdapterItem
import com.example.harajtask.ui.main.adapter.MainAdapter
import com.example.harajtask.ui.main.viewmodel.MainViewModel
import com.example.harajtask.utils.AssetManger.getJsonDataFromAsset
import com.example.harajtask.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity() ,OnClickAdapterItem
{
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        if (toolbar_main != null) {
            share.visibility = View.GONE
            toolbar_main.layoutDirection = View.LAYOUT_DIRECTION_RTL
            val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer,
                toolbar_main,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            ) {
                override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                    if (item != null) {
                        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                            drawer.closeDrawer(Gravity.RIGHT)
                        } else {
                            drawer.openDrawer(Gravity.RIGHT)
                        }
                    }
                    return false
                }
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                }
            }
            drawer.addDrawerListener(toggle)
            toggle.syncState()
         }
         recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(),this)

        recyclerView.adapter = adapter

    }



    private fun setupObserver() {
        viewModel.getHarajData().observe(this, Observer
        {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { harajDataList -> renderList(harajDataList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun renderList(harajDataList: List<HarajData>) {
        adapter.addData(harajDataList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        val jsonFileString = getJsonDataFromAsset(this, "data.json")
        Log.i("data", jsonFileString.toString())
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(jsonFileString, AssetHelperImpl())
        ).get(MainViewModel::class.java)
    }

    override fun onItemClick(harjaData: HarajData) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.HARAJDATA, harjaData)
        startActivity(intent)
    }
}



