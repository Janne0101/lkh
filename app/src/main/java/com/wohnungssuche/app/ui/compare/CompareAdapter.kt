package com.wohnungssuche.app.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wohnungssuche.app.data.model.Apartment
import com.wohnungssuche.app.databinding.ItemCompareBinding
import java.text.NumberFormat
import java.util.Locale

class CompareAdapter : ListAdapter<Apartment, CompareAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Apartment>() {
            override fun areItemsTheSame(a: Apartment, b: Apartment) = a.id == b.id
            override fun areContentsTheSame(a: Apartment, b: Apartment) = a == b
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCompareBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val b: ItemCompareBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(a: Apartment) {
            val fmt = NumberFormat.getCurrencyInstance(Locale.GERMANY)
            b.tvTitle.text = a.title
            b.tvPortal.text = a.portalName.ifBlank { "Manuell" }
            b.tvAddress.text = a.address.ifBlank { "–" }
            b.tvRent.text = if (a.rent > 0) fmt.format(a.rent) else "–"
            b.tvTotalRent.text = if (a.totalRent > 0) fmt.format(a.totalRent) else "–"
            b.tvSize.text = if (a.sizeM2 > 0) "${a.sizeM2} m²" else "–"
            b.tvRooms.text = if (a.rooms > 0) "${a.rooms}" else "–"
            b.tvPricePerSqm.text = if (a.rent > 0 && a.sizeM2 > 0)
                "${String.format("%.2f", a.rent / a.sizeM2)} €/m²" else "–"
            b.tvFloor.text = a.floor.ifBlank { "–" }
            b.tvAvailableFrom.text = a.availableFrom.ifBlank { "–" }
            b.tvPros.text = a.pros.ifBlank { "–" }
            b.tvCons.text = a.cons.ifBlank { "–" }
            b.tvNotes.text = a.notes.ifBlank { "–" }
            b.ratingBar.rating = a.rating.toFloat()
        }
    }
}
