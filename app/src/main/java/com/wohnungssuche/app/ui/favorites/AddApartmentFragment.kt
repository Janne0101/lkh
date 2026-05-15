package com.wohnungssuche.app.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.data.model.Portal
import com.wohnungssuche.app.databinding.FragmentAddApartmentBinding
import com.wohnungssuche.app.viewmodel.ApartmentViewModel

class AddApartmentFragment : Fragment() {

    private var _binding: FragmentAddApartmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApartmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddApartmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate portal dropdown
        val portalNames = Portal.values().map { it.displayName } + listOf("Sonstige")
        val adapter = android.widget.ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            portalNames
        )
        binding.actvPortal.setAdapter(adapter)

        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.btnSave.setOnClickListener {
            if (validate()) {
                saveApartment()
                findNavController().popBackStack()
            }
        }
    }

    private fun validate(): Boolean {
        val title = binding.etTitle.text.toString()
        if (title.isBlank()) {
            binding.tilTitle.error = "Bitte Titel eingeben"
            return false
        }
        binding.tilTitle.error = null
        return true
    }

    private fun saveApartment() {
        viewModel.insert(
            Apartment(
                title = binding.etTitle.text.toString(),
                address = binding.etAddress.text.toString(),
                rent = binding.etRent.text.toString().toDoubleOrNull() ?: 0.0,
                totalRent = binding.etTotalRent.text.toString().toDoubleOrNull() ?: 0.0,
                sizeM2 = binding.etSize.text.toString().toDoubleOrNull() ?: 0.0,
                rooms = binding.etRooms.text.toString().toDoubleOrNull() ?: 0.0,
                floor = binding.etFloor.text.toString(),
                availableFrom = binding.etAvailableFrom.text.toString(),
                url = binding.etUrl.text.toString(),
                portalName = binding.actvPortal.text.toString(),
                notes = binding.etNotes.text.toString(),
                pros = binding.etPros.text.toString(),
                cons = binding.etCons.text.toString(),
                rating = binding.ratingBar.rating.toInt()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
