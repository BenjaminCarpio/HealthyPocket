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
import com.healthypocket.databinding.DialogAgregarMedicinaBinding
import com.healthypocket.dto.MedicamentoDTO
import com.healthypocket.model.Medicamento

class MedicamentoDialog(

    private val onSubmitClickListener: (String, String) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogAgregarMedicinaBinding
    val medsApiService = MedicamentoDTO()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAgregarMedicinaBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.agregarMedicinaEnviarBtn.setOnClickListener {

            val nombremed = binding.etNombreMed.text.toString()

            if(nombremed.isEmpty()){
                binding.etNombreMed.error = "Nombre requerido"
                binding.etNombreMed.requestFocus()
                return@setOnClickListener
            }else{
                refreshFrag()
                val newMedicamento = Medicamento(
                    nombremedicamento = binding.etNombreMed.text.toString(),
                    horamedicamento = binding.timePickerMed.hour.toString() + ":" + binding.timePickerMed.minute.toString()
                )
                medsApiService.postMed(newMedicamento){
                    if(it?.nombremedicamento !=null && it?.horamedicamento != null){
                        Log.d("JEJE?:", "JEJE")
                    }else{
                        Log.d("JEJEnt?:", "JEJEnt")             }
                }
            }




            onSubmitClickListener.invoke(
                binding.etNombreMed.text.toString(),
                binding.timePickerMed.hour.toString() + ":" + binding.timePickerMed.minute.toString()
            )
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
    fun refreshFrag(){
        Toast.makeText(requireContext(),"Agregado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.menuPrincipalFragment,arguments,
            NavOptions.Builder().setPopUpTo(R.id.menuPrincipalFragment,true).build())
    }
}