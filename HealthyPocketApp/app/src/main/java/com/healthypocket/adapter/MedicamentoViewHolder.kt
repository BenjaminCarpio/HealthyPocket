package com.healthypocket.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.R
import com.healthypocket.databinding.ItemMedicamentoBinding
import com.healthypocket.model.Medicamento

class MedicamentoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMedicamentoBinding.bind(view)

    fun render(medicamentoModel: Medicamento) {
        binding.tvMedicamentoNombre.text = medicamentoModel.nombremedicamento
        binding.tvMedicamentoHora.text = medicamentoModel.horamedicamento
    }
}