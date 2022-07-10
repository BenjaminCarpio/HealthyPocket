package com.healthypocket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.healthypocket.R
import com.healthypocket.databinding.FragmentRegistrarseBinding
import com.healthypocket.dto.UserDTO
import com.healthypocket.model.User

class RegistrarseFragment : Fragment() {

    private var _binding: FragmentRegistrarseBinding? = null
    private val binding: FragmentRegistrarseBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrarseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrarCuentaBtn.setOnClickListener {
            val nombreUN = binding.etNombreuser.text.toString()
            val emailUN = binding.etemailuser.text.toString()
            val passUN = binding.etpassuser.text.toString()
            val passconfirmUN = binding.etpassconfirmuser.text.toString()
            val termcon = binding.terminosycondiciones1.isChecked

            if (nombreUN.isEmpty()) {
                binding.etNombreuser.error = "Usuario Requerido"
                binding.etNombreuser.requestFocus()
                return@setOnClickListener
            } else if (emailUN.isEmpty()) {
                binding.etemailuser.error = "Email Requerido"
                binding.etemailuser.requestFocus()
                return@setOnClickListener
            } else if (passUN.isEmpty()) {
                binding.etpassuser.error = "Password Requerido"
                binding.etpassuser.requestFocus()
                return@setOnClickListener
            } else if (passconfirmUN.isEmpty() || passUN != passconfirmUN) {
                binding.etpassconfirmuser.error = "Password No coincide"
                binding.etpassconfirmuser.requestFocus()
                return@setOnClickListener
            } else if (!termcon) {
                binding.terminosycondiciones1.error = "Debe aceptar para registarse"
                binding.terminosycondiciones1.requestFocus()
                return@setOnClickListener
            } else {
                val userNuevo = User(
                    username = nombreUN,
                    email = emailUN,
                    password = passUN
                )
                val dto = UserDTO()
                dto.postUser(userNuevo) {
                    Toast.makeText(requireContext(), "Usuario Registrado", Toast.LENGTH_SHORT)
                }
                findNavController().navigate(
                    R.id.navigate_to_iniciar, arguments,
                    NavOptions.Builder().setPopUpTo(R.id.navigate_to_iniciar, true).build()
                )
            }

        }


    }


}