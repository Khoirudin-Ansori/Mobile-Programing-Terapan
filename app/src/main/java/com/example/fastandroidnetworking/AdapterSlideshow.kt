package com.example.fastandroidnetworking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterSlideshow(val SlideshowList: ArrayList<Slideshow>): RecyclerView.Adapter<AdapterSlideshow.ViewHolder>() {
    override fun onBindViewHolder(holder: AdapterSlideshow.ViewHolder, position: Int) {

        val slideshow: Slideshow=SlideshowList[position]
        holder?.judul?.text = slideshow.judul
        holder?.url?.text = slideshow.url

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSlideshow.ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_slideshow, parent, false)
        return AdapterSlideshow.ViewHolder(v)

    }


    override fun getItemCount(): Int {

        return SlideshowList.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val judul = itemView.findViewById(R.id.judul) as TextView
        val url= itemView.findViewById(R.id.url) as TextView




    }
}