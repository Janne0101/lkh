package com.wohnungssuche.app.ui.compare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.wohnungssuche.app.databinding.FragmentCompareBinding
import com.wohnungssuche.app.viewmodel.ApartmentViewModel

class CompareFragment : Fragment() {

    private var _binding: FragmentCompareBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApartmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CompareAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.comparingApartments.observe(viewLifecycleOwner) { apartments ->
            adapter.submitList(apartments)
            binding.emptyView.visibility = if (apartments.isEmpty()) View.VISIBLE else View.GONE
            binding.hintView.visibility = if (apartments.isNotEmpty() && apartments.size < 2) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
