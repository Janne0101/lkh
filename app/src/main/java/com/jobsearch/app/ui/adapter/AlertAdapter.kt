package com.jobsearch.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobsearch.app.data.model.JobAlert
import com.jobsearch.app.databinding.ItemAlertBinding

class AlertAdapter(
    private val onToggle: (JobAlert) -> Unit,
    private val onDelete: (JobAlert) -> Unit
) : ListAdapter<JobAlert, AlertAdapter.AlertViewHolder>(DiffCallback) {

    inner class AlertViewHolder(private val binding: ItemAlertBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(alert: JobAlert) {
            binding.textAlertKeyword.text = if (alert.keyword.isBlank()) "Alle Jobs" else alert.keyword
            binding.textAlertLocation.text = when {
                alert.location.isNotBlank() && alert.remoteOnly -> "${alert.location} · Remote"
                alert.location.isNotBlank() -> alert.location
                alert.remoteOnly -> "Nur Remote"
                else -> "Alle Standorte"
            }
            binding.textAlertInterval.text = when (alert.intervalHours) {
                1 -> "Stündlich"
                6 -> "Alle 6 Stunden"
                12 -> "Alle 12 Stunden"
                else -> "Täglich"
            }
            binding.switchAlertActive.isChecked = alert.isActive
            binding.switchAlertActive.setOnCheckedChangeListener(null)
            binding.switchAlertActive.setOnCheckedChangeListener { _, _ -> onToggle(alert) }
            binding.buttonDeleteAlert.setOnClickListener { onDelete(alert) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val binding = ItemAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallback : DiffUtil.ItemCallback<JobAlert>() {
        override fun areItemsTheSame(old: JobAlert, new: JobAlert) = old.id == new.id
        override fun areContentsTheSame(old: JobAlert, new: JobAlert) = old == new
    }
}
