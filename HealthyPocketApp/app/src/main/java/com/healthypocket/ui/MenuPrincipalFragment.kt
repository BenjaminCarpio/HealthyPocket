package com.healthypocket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.healthypocket.adapter.PaginaDeslizableAdapter
import com.healthypocket.R
import com.healthypocket.databinding.FragmentCitaBinding
import com.healthypocket.databinding.FragmentMenuPrincipalBinding


class MenuPrincipalFragment : Fragment() {

    private var _binding: FragmentMenuPrincipalBinding? = null
    private val binding: FragmentMenuPrincipalBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager_menu)
        val fragmentspage: ArrayList<Fragment> = arrayListOf(
            MedicamentoFragment(),
            CitaFragment()
        )
        val adapter = PaginaDeslizableAdapter(fragmentspage, this)
        viewPager.adapter = adapter


        val tabLayout: TabLayout = view.findViewById(R.id.tablayout_menu)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Medicamento"
                }
                1 -> {
                    tab.text = "Citas"
                }
            }
        }.attach()

    }
}