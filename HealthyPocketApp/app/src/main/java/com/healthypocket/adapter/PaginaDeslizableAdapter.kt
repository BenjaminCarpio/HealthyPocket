package com.healthypocket.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.healthypocket.ui.MenuPrincipalFragment

class PaginaDeslizableAdapter(
    val items: ArrayList<Fragment>,
    activity: MenuPrincipalFragment
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }


}