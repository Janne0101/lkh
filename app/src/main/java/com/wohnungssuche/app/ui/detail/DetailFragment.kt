package com.wohnungssuche.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wohnungssuche.app.databinding.FragmentDetailBinding
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.viewmodel.ApartmentViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApartmentViewModel by activityViewModels()
    private var currentApartment: Apartment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apartmentId = requireArguments().getLong("apartmentId")
        viewModel.loadApartment(apartmentId)

        viewModel.currentApartment.observe(viewLifecycleOwner) { apartment ->
            apartment ?: return@observe
            currentApartment = apartment
            populateForm(apartment)
        }

        binding.btnSave.setOnClickListener { saveApartment() }

        binding.btnOpenUrl.setOnClickListener {
            val url = binding.etUrl.text.toString()
            if (url.isNotBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun populateForm(a: Apartment) {
        binding.toolbar.title = a.title
        binding.etTitle.setText(a.title)
        binding.etAddress.setText(a.address)
        binding.etRent.setText(if (a.rent > 0) a.rent.toString() else "")
        binding.etTotalRent.setText(if (a.totalRent > 0) a.totalRent.toString() else "")
        binding.etSize.setText(if (a.sizeM2 > 0) a.sizeM2.toString() else "")
        binding.etRooms.setText(if (a.rooms > 0) a.rooms.toString() else "")
        binding.etFloor.setText(a.floor)
        binding.etAvailableFrom.setText(a.availableFrom)
        binding.etUrl.setText(a.url)
        binding.etPortal.setText(a.portalName)
        binding.etNotes.setText(a.notes)
        binding.etPros.setText(a.pros)
        binding.etCons.setText(a.cons)
        binding.ratingBar.rating = a.rating.toFloat()
    }

    private fun saveApartment() {
        val a = currentApartment ?: return
        val updated = a.copy(
            title = binding.etTitle.text.toString().ifBlank { "Wohnung" },
            address = binding.etAddress.text.toString(),
            rent = binding.etRent.text.toString().toDoubleOrNull() ?: 0.0,
            totalRent = binding.etTotalRent.text.toString().toDoubleOrNull() ?: 0.0,
            sizeM2 = binding.etSize.text.toString().toDoubleOrNull() ?: 0.0,
            rooms = binding.etRooms.text.toString().toDoubleOrNull() ?: 0.0,
            floor = binding.etFloor.text.toString(),
            availableFrom = binding.etAvailableFrom.text.toString(),
            url = binding.etUrl.text.toString(),
            portalName = binding.etPortal.text.toString(),
            notes = binding.etNotes.text.toString(),
            pros = binding.etPros.text.toString(),
            cons = binding.etCons.text.toString(),
            rating = binding.ratingBar.rating.toInt()
        )
        viewModel.update(updated)
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
