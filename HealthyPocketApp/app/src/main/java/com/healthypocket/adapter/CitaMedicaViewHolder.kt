package com.healthypocket.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.databinding.ItemCitamedicaBinding
import com.healthypocket.model.CitaMedica


class CitaMedicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCitamedicaBinding.bind(view)

    fun renderCita(citamedicaModel: CitaMedica) {
        binding.tvCitaMedicaNombre.text = citamedicaModel.nombre
        binding.tvCitaMedicaGasto.text = citamedicaModel.gastos
    }
}