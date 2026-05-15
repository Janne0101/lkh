package com.wohnungssuche.app.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wohnungssuche.app.R
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.databinding.ItemApartmentBinding
import java.text.NumberFormat
import java.util.Locale

class ApartmentAdapter(
    private val onItemClick: (Apartment) -> Unit,
    private val onRatingChange: (Apartment, Int) -> Unit,
    private val onCompareToggle: (Apartment) -> Unit,
    private val onDelete: (Apartment) -> Unit
) : ListAdapter<Apartment, ApartmentAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Apartment>() {
            override fun areItemsTheSame(a: Apartment, b: Apartment) = a.id == b.id
            override fun areContentsTheSame(a: Apartment, b: Apartment) = a == b
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemApartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemApartmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apartment: Apartment) {
            val fmt = NumberFormat.getCurrencyInstance(Locale.GERMANY)

            binding.tvTitle.text = apartment.title
            binding.tvAddress.text = apartment.address.ifBlank { "Adresse nicht eingetragen" }
            binding.tvRent.text = if (apartment.rent > 0) fmt.format(apartment.rent) else "–"
            binding.tvTotalRent.text = if (apartment.totalRent > 0) "Warm: ${fmt.format(apartment.totalRent)}" else ""
            binding.tvSize.text = if (apartment.sizeM2 > 0) "${apartment.sizeM2} m²" else "–"
            binding.tvRooms.text = if (apartment.rooms > 0) "${apartment.rooms} Zi." else "–"
            binding.tvPortal.text = apartment.portalName.ifBlank { "Manuell" }

            binding.ratingBar.rating = apartment.rating.toFloat()
            binding.ratingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
                if (fromUser) onRatingChange(apartment, rating.toInt())
            }

            val compareColor = if (apartment.isComparing)
                ContextCompat.getColor(binding.root.context, R.color.primary)
            else
                ContextCompat.getColor(binding.root.context, R.color.on_surface_variant)

            binding.btnCompare.setColorFilter(compareColor)
            binding.btnCompare.setOnClickListener { onCompareToggle(apartment) }

            binding.btnDelete.setOnClickListener { onDelete(apartment) }
            binding.root.setOnClickListener { onItemClick(apartment) }
        }
    }
}
