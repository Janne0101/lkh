package com.jobsearch.app.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobsearch.app.R
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.databinding.ItemJobBinding

class JobAdapter(
    private val onJobClick: (Job) -> Unit,
    private val onBookmarkClick: (Job) -> Unit
) : ListAdapter<Job, JobAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.apply {
                textJobTitle.text = job.title
                textCompany.text = job.company
                textLocation.text = job.location
                textSalary.text = job.salary ?: root.context.getString(R.string.salary_not_specified)
                textJobType.text = job.jobType.displayName
                textProviderName.text = job.provider.displayName

                // Provider badge color
                try {
                    val color = Color.parseColor(job.provider.colorHex)
                    textProviderName.setBackgroundColor(color)
                    cardProviderBadge.setCardBackgroundColor(color)
                } catch (e: IllegalArgumentException) {
                    // fallback color already set in XML
                }

                // Bookmark icon
                val bookmarkIcon = if (job.isSaved) {
                    R.drawable.ic_bookmark_filled
                } else {
                    R.drawable.ic_bookmark_outline
                }
                imageBookmark.setImageResource(bookmarkIcon)

                // Remote badge visibility
                if (job.isRemote) {
                    chipRemote.visibility = android.view.View.VISIBLE
                } else {
                    chipRemote.visibility = android.view.View.GONE
                }

                // Click listeners
                root.setOnClickListener { onJobClick(job) }
                imageBookmark.setOnClickListener { onBookmarkClick(job) }
            }
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean = oldItem == newItem
    }
}
