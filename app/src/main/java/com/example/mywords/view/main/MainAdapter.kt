package com.example.mywords.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.model.data.DataModel

class MainAdapter(
    private var onItemClickListener: OnItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerViewItemViewHolder>() {


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

    inner class RecyclerViewItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener {
                    onItemClickListener.getData(data)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun getData(data: DataModel)
    }

}


