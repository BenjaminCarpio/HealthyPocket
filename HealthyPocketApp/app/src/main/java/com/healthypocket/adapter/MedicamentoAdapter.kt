package com.healthypocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.R
import com.healthypocket.model.Medicamento

class MedicamentoAdapter(private var medicamentoList: List<Medicamento>) :
    RecyclerView.Adapter<MedicamentoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MedicamentoViewHolder(
            layoutInflater.inflate(
                R.layout.item_medicamento,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val item = medicamentoList[position]

        holder.render(item)
    }

    override fun getItemCount(): Int {
        return medicamentoList.size
    }

    fun setData(listmed: List<Medicamento>){
        medicamentoList = listmed
        notifyDataSetChanged()
    }

    fun getmedID(position: Int): String? = medicamentoList[position]._id


}