package com.healthypocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.R
import com.healthypocket.model.Examen
import com.healthypocket.model.Medicamento

class ExamenAdapter(private var examenList: List<Examen>) :
    RecyclerView.Adapter<ExamenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ExamenViewHolder(layoutInflater.inflate(R.layout.item_examen, parent, false))
    }

    override fun onBindViewHolder(holder: ExamenViewHolder, position: Int) {
        val item = examenList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return examenList.size
    }

    fun setData(listexm: List<Examen>){
        examenList = listexm
        notifyDataSetChanged()
    }

    fun getexmID(position: Int): String? = examenList[position]._id
}
