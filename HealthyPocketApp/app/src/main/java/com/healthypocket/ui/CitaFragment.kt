package com.healthypocket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.CitasMedicasViewModel
import com.healthypocket.DeslizarParaAccionMed
import com.healthypocket.R
import com.healthypocket.adapter.CitaMedicaAdapter
import com.healthypocket.databinding.FragmentCitaBinding
import com.healthypocket.dto.CitasDTO
import com.healthypocket.dto.MedicamentoDTO
import com.healthypocket.model.CitaMedica


class CitaFragment : Fragment() {

    private var _binding: FragmentCitaBinding? = null
    private val binding: FragmentCitaBinding
        get() = _binding!!

    private lateinit var  vm: CitasMedicasViewModel
    private lateinit var adapter: CitaMedicaAdapter

    private var citaData = mutableListOf<CitaMedica>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[CitasMedicasViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CitaMedicaAdapter(citaData)
        binding.recyclerCitaMedica.layoutManager = LinearLayoutManager(activity)
        binding.recyclerCitaMedica.adapter = adapter

        vm.datacita.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
        adapter.notifyDataSetChanged()
        vm.getcitasmedicas()

        val DeslizarParaAccionCita = object : DeslizarParaAccionMed(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val dto = CitasDTO()
                val adioscita = adapter.getmedID(position)
                dto.deleteCita(adioscita.toString())
                binding.recyclerCitaMedica.adapter?.notifyItemRemoved(position)
                adapter.notifyDataSetChanged()
                refreshborrarFrag()
            }
        }

        val itemTouchHelper = ItemTouchHelper(DeslizarParaAccionCita)
        itemTouchHelper.attachToRecyclerView(binding.recyclerCitaMedica)

        binding.agregarCitaBtn.setOnClickListener{
           Navigation.findNavController(view).navigate(R.id.navigate_to_agregar_cita)
        }
    }

    fun refreshborrarFrag(){
        Toast.makeText(requireContext(),"Eliminado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.menuPrincipalFragment,arguments,
            NavOptions.Builder().setPopUpTo(R.id.menuPrincipalFragment,true).build())
    }
}