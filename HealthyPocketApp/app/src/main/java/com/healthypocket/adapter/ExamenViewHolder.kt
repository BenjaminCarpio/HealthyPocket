package com.healthypocket.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.databinding.ItemExamenBinding
import com.healthypocket.model.Examen

class ExamenViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemExamenBinding.bind(view)

    fun render(examenModel: Examen) {
        binding.tvExamenNombre.text = examenModel.nombreexamen
        binding.tvExamenGasto.text = examenModel.gastosexamen.toString()
    }
}
