package com.example.uts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val userList: ArrayList<Penduduk>): RecyclerView.Adapter<CustomAdapter.ViewHolder>()   {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val penduduk: Penduduk=userList[position]
        holder?.textViewNama?.text = penduduk.nama
        holder?.textViewTtl?.text = penduduk.ttl
        holder?.textViewHp?.text = penduduk.hp
        holder?.textViewAlamat?.text = penduduk.alamat

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return  ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }



    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewNama = itemView.findViewById(R.id.textViewNama) as TextView
        val textViewTtl = itemView.findViewById(R.id.textViewTtl) as TextView
        val textViewHp = itemView.findViewById(R.id.textViewHp) as TextView
        val textViewAlamat = itemView.findViewById(R.id.textViewAlamat) as TextView

    }
}