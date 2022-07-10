package com.healthypocket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.healthypocket.R
import com.healthypocket.UserViewModel
import com.healthypocket.databinding.FragmentCitaBinding
import com.healthypocket.databinding.FragmentPerfilBinding
import com.healthypocket.dto.UserDTO
import com.healthypocket.model.User


class PerfilFragment : Fragment() {

    private lateinit var vm: UserViewModel
    private var _binding: FragmentPerfilBinding? = null
    private val binding: FragmentPerfilBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.dataUser.observe(viewLifecycleOwner) {
            binding.etNombre.setText(it.name)
            binding.etApellido.setText(it.lastname)
            binding.etCorreo.setText(it.email)
            binding.etDireccion.setText(it.direccion)
            binding.etTelefono.setText(it.telefono)
            binding.etCumpleaOs.setText(it.fechaNacimiento)
            val idUpdate = (it._id)

            binding.actualizarPerfilBtn.setOnClickListener {
                val dto = UserDTO()
                val actualizandoUsuario = User()
                actualizandoUsuario.name = binding.etNombre.text.toString()
                actualizandoUsuario.lastname = binding.etApellido.text.toString()
                actualizandoUsuario.email = binding.etCorreo.text.toString()
                actualizandoUsuario.direccion = binding.etDireccion.text.toString()
                actualizandoUsuario.telefono = binding.etTelefono.text.toString()
                actualizandoUsuario.fechaNacimiento = binding.etCumpleaOs.text.toString()
                if (idUpdate != null) {
                    dto.putUser(idUpdate, actualizandoUsuario)
                    refreshUpdate()
                }
            }

        }
        //ID Usuario Prueba
        vm.getSingleUser("62c843b72dbcde6d4ae13065")

    }

    fun refreshUpdate() {
        Toast.makeText(requireContext(), "Actualizado Correctamente", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.perfilFragment, arguments,
            NavOptions.Builder().setPopUpTo(R.id.perfilFragment, true).build()
        )
    }

}