package com.healthypocket.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.MedicamentoViewModel
import com.healthypocket.R
import com.healthypocket.databinding.FragmentPerfilBinding
import com.healthypocket.databinding.ItemMedicamentoBinding
import com.healthypocket.model.Medicamento
import com.healthypocket.model.User

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = FragmentPerfilBinding.bind(view)
        fun render(userModel: User) {
            userModel.name.also { binding.etNombre.text = null }
            userModel.lastname.also { binding.etApellido.text = null }
        }
}