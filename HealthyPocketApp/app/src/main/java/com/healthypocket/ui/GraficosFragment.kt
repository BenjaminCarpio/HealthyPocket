package com.healthypocket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healthypocket.databinding.FragmentGraficosBinding

class GraficosFragment : Fragment() {

    private var _binding: FragmentGraficosBinding? = null
    private val binding: FragmentGraficosBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGraficosBinding.inflate(inflater, container, false)
        return binding.root
    }

}