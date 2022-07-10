package com.healthypocket.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.DeslizarParaAccionMed
import com.healthypocket.ExamenViewModel
import com.healthypocket.R
import com.healthypocket.adapter.ExamenAdapter
import com.healthypocket.databinding.FragmentAgregandoCitaBinding
import com.healthypocket.dto.CitasDTO
import com.healthypocket.dto.ExamenDTO
import com.healthypocket.model.CitaMedica
import com.healthypocket.model.Examen
import com.healthypocket.ui.components.ExamenDialog


class AgregandoCitaFragment : Fragment() {

    private var _binding: FragmentAgregandoCitaBinding? = null
    private val binding: FragmentAgregandoCitaBinding
        get() = _binding!!
    val citasdto = CitasDTO()

    private lateinit var vm: ExamenViewModel
    private lateinit var adapter: ExamenAdapter
    private var examenData = mutableListOf<Examen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[ExamenViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgregandoCitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExamenAdapter(examenData)
        binding.recyclerExamen.layoutManager = LinearLayoutManager(activity)
        binding.recyclerExamen.adapter = adapter

        vm.dataexm.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
        adapter.notifyDataSetChanged()
        vm.getExamenes()

        binding.cancelarCitanuevaBtn.setOnClickListener {
            regresarMenu()
        }


        //Agregar Cita medica Mandar los datos
        binding.agregarCitanuevaBtn.setOnClickListener {
            val nombre = binding.etNombreCita.text.toString()
            val medico = binding.etMedicoCita.text.toString()
            val incapacidad = binding.etIncapacidadCita.text.toString()
            val clinicahospital = binding.etHospitalCita.text.toString()
            val gastos = binding.etGastosCita.text.toString()
            val descripcion = binding.etDescripcionCita.text.toString()
            if (nombre.isEmpty()) {
                binding.etNombreCita.error = "Nombre Requerido"
                binding.etNombreCita.requestFocus()
                return@setOnClickListener
            } else if (medico.isEmpty()) {
                binding.etMedicoCita.error = "Medico Requerido"
                binding.etMedicoCita.requestFocus()
                return@setOnClickListener
            } else if (incapacidad.isEmpty()) {
                binding.etIncapacidadCita.error = "Incapacidad Requerido"
                binding.etIncapacidadCita.requestFocus()
                return@setOnClickListener
            } else if (clinicahospital.isEmpty()) {
                binding.etHospitalCita.error = "Hospital Requerido"
                binding.etHospitalCita.requestFocus()
                return@setOnClickListener
            } else if (gastos.isEmpty()) {
                binding.etGastosCita.error = "Gasto Requerido"
                binding.etGastosCita.requestFocus()
                return@setOnClickListener
            } else if (descripcion.isEmpty()){
                binding.etDescripcionCita.error = "Descripcion Requerido"
                binding.etDescripcionCita.requestFocus()
                return@setOnClickListener
            }else{
                refreshAgregarCitaFrag()
                val newCita = CitaMedica(
                    nombre = binding.etNombreCita.text.toString(),
                    medico = binding.etMedicoCita.text.toString(),
                    incapacidad = binding.etIncapacidadCita.text.toString(),
                    descripcion = binding.etDescripcionCita.text.toString(),
                    clinicahospital = binding.etHospitalCita.text.toString(),
                    gastos = binding.etGastosCita.text.toString()
                )
                citasdto.postCitas(newCita) {
                    if (it?.nombre != null && it?.medico != null) {
                        Log.d("JEJE?:", "JEJE!!")
                    } else {
                        Log.d("JEJEnt?:", "JEJEnt")
                    }
                }
            }
        }

        //Agregar examen btn que abre el modal
        binding.agregarExamenBtn.setOnClickListener {
            ExamenDialog { nombremex: String, gastoex: Number, descex: String, ->
                Toast.makeText(
                    requireContext(),
                    "Datos seleccionados: $nombremex and $gastoex and $descex",
                    Toast.LENGTH_SHORT
                ).show()
            }.show(parentFragmentManager, "dialogaddex")
        }

        val DeslizarParaAccionExm = object : DeslizarParaAccionMed(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val dto = ExamenDTO()
                val adiosexm = adapter.getexmID(position)
                dto.deleteExamen(adiosexm.toString())
                binding.recyclerExamen.adapter?.notifyItemRemoved(position)
                adapter.notifyDataSetChanged()
                refreshBorrarFrag()
            }
        }
        val itemTouchHelper = ItemTouchHelper(DeslizarParaAccionExm)
        itemTouchHelper.attachToRecyclerView(binding.recyclerExamen)
    }
    fun refreshBorrarFrag(){
        Toast.makeText(requireContext(),"Eliminado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.action_refresh_self,arguments,
            NavOptions.Builder().setPopUpTo(R.id.action_refresh_self,true).build())
    }

    fun regresarMenu(){
        findNavController().navigate(
            R.id.menuPrincipalFragment,arguments,
            NavOptions.Builder().setPopUpTo(R.id.menuPrincipalFragment,true).build())

    }

    fun refreshAgregarCitaFrag() {
        Toast.makeText(requireContext(), "Agregado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.menuPrincipalFragment,arguments,
            NavOptions.Builder().setPopUpTo(R.id.menuPrincipalFragment,true).build())
    }
}