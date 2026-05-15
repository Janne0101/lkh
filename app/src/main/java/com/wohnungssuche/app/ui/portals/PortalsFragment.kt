package com.wohnungssuche.app.ui.portals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.wohnungssuche.app.data.model.Portal
import com.wohnungssuche.app.databinding.FragmentPortalsBinding

class PortalsFragment : Fragment() {

    private var _binding: FragmentPortalsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val portals = Portal.values().toList()
        binding.viewPager.adapter = PortalPagerAdapter(this, portals)
        binding.viewPager.offscreenPageLimit = portals.size

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = portals[position].displayName
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
