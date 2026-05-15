package com.wohnungssuche.app.ui.portals

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wohnungssuche.app.data.model.Portal

class PortalPagerAdapter(fragment: Fragment, private val portals: List<Portal>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = portals.size

    override fun createFragment(position: Int): Fragment =
        PortalWebViewFragment.newInstance(portals[position])
}
