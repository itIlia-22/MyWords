package com.example.mywords.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.model.data.DataModel
import com.example.mywords.utils.convertMeaningsToString
import kotlinx.android.synthetic.main.activity_main_recycler_view.view.*

class MainAdapter(
    private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MainAdapter.RecyclerViewItemViewHolder>() {
    private var data: List<DataModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewItemViewHolder {
        return RecyclerViewItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recycler_view, parent, false) as View
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerViewItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class RecyclerViewItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = data.text
                itemView.description_textview_recycler_item.text =
                    convertMeaningsToString(data.meanings!!)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onItemClickListener.onItemClick(listItemData)
    }

    interface OnItemClickListener {
        fun onItemClick(data: DataModel)
    }
}


