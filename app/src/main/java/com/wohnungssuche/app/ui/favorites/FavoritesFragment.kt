package com.wohnungssuche.app.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wohnungssuche.app.R
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.databinding.FragmentFavoritesBinding
import com.wohnungssuche.app.viewmodel.ApartmentViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApartmentViewModel by activityViewModels()
    private lateinit var adapter: ApartmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ApartmentAdapter(
            onItemClick = { apartment ->
                findNavController().navigate(
                    R.id.action_favoritesFragment_to_detailFragment,
                    bundleOf("apartmentId" to apartment.id)
                )
            },
            onRatingChange = { apartment, rating ->
                viewModel.updateRating(apartment.id, rating)
            },
            onCompareToggle = { apartment ->
                viewModel.setComparing(apartment.id, !apartment.isComparing)
            },
            onDelete = { apartment ->
                confirmDelete(apartment)
            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.allApartments.observe(viewLifecycleOwner) { apartments ->
            adapter.submitList(apartments)
            binding.emptyView.visibility = if (apartments.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_favoritesFragment_to_addApartmentFragment)
        }
    }

    private fun confirmDelete(apartment: Apartment) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Wohnung löschen")
            .setMessage("\"${apartment.title}\" wirklich löschen?")
            .setPositiveButton("Löschen") { _, _ -> viewModel.delete(apartment) }
            .setNegativeButton("Abbrechen", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
