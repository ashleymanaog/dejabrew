package com.example.dejabrew


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

class AdapterClass (val dataList : ArrayList<DataClass>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>(){

    override fun onCreateViewHolder(p0 : ViewGroup, p1 : Int): ViewHolderClass {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.activity_profile_layout, p0, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0 : ViewHolderClass, p1 : Int) {
        var currentItem = dataList[p1]
        p0.rvImage.setImageResource(currentItem.dataImage)
        p0.rvTitle.text = currentItem.dataTitle

    }

    class ViewHolderClass (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var rvImage : ImageView = itemView.findViewById(R.id.recyclerImage)
        var rvTitle : TextView = itemView.findViewById(R.id.recyclerTitle)

    }
}