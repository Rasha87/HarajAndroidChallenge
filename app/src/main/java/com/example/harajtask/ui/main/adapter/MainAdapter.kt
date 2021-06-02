package com.example.harajtask.ui.main.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.data.HarajData
import com.example.harajtask.utils.DateManger
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.round_imageview.view.*

class MainAdapter(
   private val harajDataList: ArrayList<HarajData>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
     class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(harajData: HarajData, position: Int) {
            itemView.txtViewTitle.text =  harajData.title
            val date = DateManger.getDate(harajData.date!!)
            itemView.txtViewDate.text = String.format(itemView.context.getString(R.string.since),date)
            itemView.txtViewCommentCount.visibility = View.VISIBLE
            if (harajData.commentCount==0)  itemView.txtViewCommentCount.visibility = View.GONE
            itemView.txtViewCommentCount.text = harajData.commentCount.toString()
            itemView.txtViewLocation.text = harajData.city
            itemView.txtViewUser.text = harajData.username
            if (position %2 == 0 ) itemView.setBackgroundColor(itemView.context.getColor(R.color.list_bg_color)) else itemView.setBackgroundColor(itemView.context.getColor(R.color.white_bg_color))
            Glide.with(itemView.thumbImage.context)
                .load(harajData.thumbURL)
                .into(itemView.thumbImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = harajDataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(harajDataList[position],position)

    fun addData(list: List<HarajData>) {
        harajDataList.addAll(list)
    }

}