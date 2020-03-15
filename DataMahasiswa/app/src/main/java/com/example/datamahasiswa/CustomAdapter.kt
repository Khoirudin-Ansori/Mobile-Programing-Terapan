package com.example.datamahasiswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val userList: ArrayList<Mahasiswa>): RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mahasiswa: Mahasiswa=userList[position]
        holder?.textViewName?.text = mahasiswa.name
        holder?.textViewNim?.text = mahasiswa.nim
        holder?.textViewAddress?.text = mahasiswa.address
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return  ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewNim = itemView.findViewById(R.id.textViewNim) as TextView
        val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
    }
}