package com.jobsearch.app.ui.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jobsearch.app.R
import com.jobsearch.app.data.database.AppDatabase
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.repository.JobRepository
import com.jobsearch.app.databinding.ActivityJobDetailBinding
import kotlinx.coroutines.launch

class JobDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_JOB = "extra_job"
    }

    private lateinit var binding: ActivityJobDetailBinding
    private lateinit var repository: JobRepository
    private var currentJob: Job? = null
    private var isSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)
        repository = JobRepository(db.jobDao())

        val job = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_JOB, Job::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_JOB) as? Job
        }

        if (job == null) {
            finish()
            return
        }

        currentJob = job
        setupToolbar(job)
        displayJobDetails(job)
        checkSavedStatus(job)
        setupButtons(job)
    }

    private fun setupToolbar(job: Job) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }

        try {
            val color = Color.parseColor(job.provider.colorHex)
            binding.appBarLayout.setBackgroundColor(color)
            binding.toolbar.setBackgroundColor(color)
            window.statusBarColor = color
        } catch (e: IllegalArgumentException) {
            // use default color
        }
    }

    private fun displayJobDetails(job: Job) {
        binding.apply {
            textDetailTitle.text = job.title
            textDetailCompany.text = job.company
            textDetailLocation.text = job.location
            textDetailSalary.text = job.salary ?: getString(R.string.salary_not_specified)
            textDetailJobType.text = job.jobType.displayName
            textDetailPostedDate.text = getString(R.string.posted_on, job.postedDate)
            textDetailProvider.text = job.provider.displayName
            textDetailDescription.text = formatDescription(job.description)

            try {
                val color = Color.parseColor(job.provider.colorHex)
                chipProvider.chipBackgroundColor = android.content.res.ColorStateList.valueOf(color)
                chipProvider.setTextColor(Color.WHITE)
            } catch (e: IllegalArgumentException) {
                // use default
            }
            chipProvider.text = job.provider.displayName

            if (job.isRemote) {
                chipRemote.visibility = android.view.View.VISIBLE
            } else {
                chipRemote.visibility = android.view.View.GONE
            }
        }
    }

    private fun formatDescription(description: String): String {
        // Convert markdown-like formatting to plain text with spacing
        return description
            .replace("**", "")
            .replace("*", "•")
            .lines()
            .joinToString("\n") { line ->
                if (line.startsWith("- ")) "  • ${line.removePrefix("- ")}" else line
            }
    }

    private fun checkSavedStatus(job: Job) {
        lifecycleScope.launch {
            isSaved = repository.isJobSaved(job.id)
            updateBookmarkIcon()
        }
    }

    private fun updateBookmarkIcon() {
        val icon = if (isSaved) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_outline
        binding.fabBookmark.setImageResource(icon)
    }

    private fun setupButtons(job: Job) {
        binding.buttonApply.setOnClickListener {
            openBrowser(job.url)
        }

        binding.fabBookmark.setOnClickListener {
            lifecycleScope.launch {
                isSaved = repository.toggleSaveJob(job)
                updateBookmarkIcon()
                val message = if (isSaved) R.string.job_saved else R.string.job_removed
                Toast.makeText(this@JobDetailActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, R.string.error_opening_url, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
