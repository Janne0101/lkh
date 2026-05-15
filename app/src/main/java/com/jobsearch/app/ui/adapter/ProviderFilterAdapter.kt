package com.jobsearch.app.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.databinding.ItemProviderFilterBinding

class ProviderFilterAdapter(
    private val onProviderToggled: (JobProvider) -> Unit
) : RecyclerView.Adapter<ProviderFilterAdapter.ProviderViewHolder>() {

    private val providers = JobProvider.values().toList()
    private val selectedProviders = JobProvider.values().toMutableSet()

    fun updateSelectedProviders(selected: Set<JobProvider>) {
        selectedProviders.clear()
        selectedProviders.addAll(selected)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        val binding = ItemProviderFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProviderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        holder.bind(providers[position])
    }

    override fun getItemCount(): Int = providers.size

    inner class ProviderViewHolder(private val binding: ItemProviderFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(provider: JobProvider) {
            binding.apply {
                chipProvider.text = provider.displayName
                chipProvider.isChecked = provider in selectedProviders

                try {
                    val color = Color.parseColor(provider.colorHex)
                    chipProvider.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        if (provider in selectedProviders) color else 0xFFEEEEEE.toInt()
                    )
                    chipProvider.setTextColor(
                        if (provider in selectedProviders) Color.WHITE else Color.DKGRAY
                    )
                } catch (e: IllegalArgumentException) {
                    // use default styling
                }

                chipProvider.setOnClickListener {
                    onProviderToggled(provider)
                }
            }
        }
    }
}
