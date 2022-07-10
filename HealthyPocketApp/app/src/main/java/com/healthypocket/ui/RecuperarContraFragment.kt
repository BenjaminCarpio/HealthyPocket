package com.healthypocket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.healthypocket.R
import com.healthypocket.databinding.FragmentRecuperarContraBinding

class RecuperarContraFragment : Fragment() {

    private var _binding: FragmentRecuperarContraBinding? = null
    private val binding: FragmentRecuperarContraBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecuperarContraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recuperarConfirmarBtn.setOnClickListener {

            val emailrecu = binding.etemailrecu.text.toString()
            if (emailrecu.isEmpty()) {
                binding.etemailrecu.error = "Email Requerido"
                binding.etemailrecu.requestFocus()
                return@setOnClickListener
            } else {
                findNavController().navigate(
                    R.id.navigate_to_inicio, arguments,
                    NavOptions.Builder().setPopUpTo(R.id.navigate_to_inicio, true).build()
                )
            }
        }
    }
}