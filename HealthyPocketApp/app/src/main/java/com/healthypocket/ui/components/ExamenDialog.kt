package com.healthypocket.ui.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.healthypocket.R
import com.healthypocket.databinding.DialogAgregarExamenBinding
import com.healthypocket.dto.ExamenDTO
import com.healthypocket.model.Examen

class ExamenDialog(
    private val onSubmitClickListener: (String, Number, String) -> Unit
) : DialogFragment() {
    private lateinit var binding: DialogAgregarExamenBinding
    val exmApiService = ExamenDTO()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAgregarExamenBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.agregarExamenEnviarBtn.setOnClickListener {

            val nombreexm = binding.etNombreEx.text.toString()
            val gastoexm = binding.etGastosExamen.text.toString()
            val descripexm = binding.etDescipcionExamen.text.toString()
            if(gastoexm.isEmpty()) {
                binding.etGastosExamen.error = "Gasto requerido"
                binding.etGastosExamen.requestFocus()
                return@setOnClickListener
            }else if(nombreexm.isEmpty()){
                    binding.etNombreEx.error = "Nombre requerido"
                    binding.etNombreEx.requestFocus()
                    return@setOnClickListener
            }else if(descripexm.isEmpty()) {
                binding.etDescipcionExamen.error = "Descripcion requerido"
                binding.etDescipcionExamen.requestFocus()
                return@setOnClickListener
            }else{
                val newExamen = Examen(
                    nombreexamen = binding.etNombreEx.text.toString(),
                    gastosexamen = binding.etGastosExamen.text.toString(),
                    descripcion = binding.etDescipcionExamen.text.toString(),
                )
                exmApiService.postExamen(newExamen) {
                    if (it?.nombreexamen != null && it?.gastosexamen != null) {
                        Log.d("JEJE?:", "JEJE")
                    } else {
                        Log.d("JEJEnt?:", "JEJEnt")
                    }
                }
                refreshFrag()
                onSubmitClickListener.invoke(
                    binding.etNombreEx.text.toString(),
                    binding.etGastosExamen.text.toString().toDouble(),
                    binding.etDescipcionExamen.text.toString()
                )
                dismiss()
            }
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
    fun refreshFrag(){
        Toast.makeText(requireContext(),"Agregado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.action_refresh_self,arguments,
            NavOptions.Builder().setPopUpTo(R.id.action_refresh_self,true).build())
    }
}