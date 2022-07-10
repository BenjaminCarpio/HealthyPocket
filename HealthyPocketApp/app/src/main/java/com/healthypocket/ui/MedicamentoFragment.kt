package com.healthypocket.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.DeslizarParaAccionMed
import com.healthypocket.MedicamentoViewModel
import com.healthypocket.R
import com.healthypocket.adapter.MedicamentoAdapter
import com.healthypocket.databinding.FragmentMedicamentoBinding
import com.healthypocket.dto.MedicamentoDTO
import com.healthypocket.model.Medicamento

import com.healthypocket.ui.components.MedicamentoDialog

class MedicamentoFragment : Fragment() {
    private var _binding: FragmentMedicamentoBinding? = null
    private val binding: FragmentMedicamentoBinding
        get() = _binding!!
    private lateinit var  vm: MedicamentoViewModel
    private lateinit var adapter: MedicamentoAdapter

    private var medicamentoData = mutableListOf<Medicamento>() //Lista que va a tener lo que traiga el api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MedicamentoViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMedicamentoBinding
            .inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MedicamentoAdapter(medicamentoData)
        binding.recyclerMedicamento.layoutManager = LinearLayoutManager(activity)
        binding.recyclerMedicamento.adapter = adapter


        vm.datamed.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
        adapter.notifyDataSetChanged()
        vm.getmedicamentos()

        val DeslizarParaAccionMed = object : DeslizarParaAccionMed(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val dto = MedicamentoDTO()
                val adiosmed = adapter.getmedID(position)
                dto.deleteMeds(adiosmed.toString())
                binding.recyclerMedicamento.adapter?.notifyItemRemoved(position)
                adapter.notifyDataSetChanged()
                refreshborrarFrag()
            }

        }
        val itemTouchHelper = ItemTouchHelper(DeslizarParaAccionMed)
        itemTouchHelper.attachToRecyclerView(binding.recyclerMedicamento)

        binding.agregarMedicinaBtn.setOnClickListener {
            MedicamentoDialog { nombremed: String, horamed: String ->
                Toast.makeText(
                    requireContext(),
                    "Datos seleccionados: $nombremed and $horamed",
                    Toast.LENGTH_SHORT
                ).show()
            }.show(parentFragmentManager, "dialogaddmed")

        }
    }

    fun refreshborrarFrag(){
        Toast.makeText(requireContext(),"Eliminado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.menuPrincipalFragment,arguments,
            NavOptions.Builder().setPopUpTo(R.id.menuPrincipalFragment,true).build())
    }
}