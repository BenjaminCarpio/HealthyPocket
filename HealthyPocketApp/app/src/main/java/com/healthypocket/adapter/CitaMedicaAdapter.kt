package com.healthypocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.R
import com.healthypocket.model.CitaMedica
import com.healthypocket.model.Medicamento

class CitaMedicaAdapter(private var citamedicaList: List<CitaMedica>) :
    RecyclerView.Adapter<CitaMedicaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaMedicaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CitaMedicaViewHolder(layoutInflater.inflate(R.layout.item_citamedica, parent, false))
    }

    override fun onBindViewHolder(holder: CitaMedicaViewHolder, position: Int) {
        val item = citamedicaList[position]
        holder.renderCita(item)
    }

    override fun getItemCount(): Int {
        return citamedicaList.size
    }

    fun setData(listcita: List<CitaMedica>){
        citamedicaList = listcita
        notifyDataSetChanged()
    }

    fun getmedID(position: Int): String? = citamedicaList[position]._id
}