package com.jobsearch.app.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jobsearch.app.R
import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import com.jobsearch.app.databinding.FragmentSearchBinding
import com.jobsearch.app.ui.adapter.JobAdapter
import com.jobsearch.app.ui.adapter.ProviderFilterAdapter
import com.jobsearch.app.ui.detail.JobDetailActivity
import com.jobsearch.app.viewmodel.SearchState
import com.jobsearch.app.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()

    private lateinit var jobAdapter: JobAdapter
    private lateinit var providerFilterAdapter: ProviderFilterAdapter

    private var currentKeyword = ""
    private var currentLocation = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupJobRecyclerView()
        setupProviderFilter()
        setupSearchBar()
        setupFilterButton()
        observeViewModel()
    }

    private fun setupJobRecyclerView() {
        jobAdapter = JobAdapter(
            onJobClick = { job -> openJobDetail(job) },
            onBookmarkClick = { job -> viewModel.toggleSaveJob(job) }
        )

        binding.recyclerViewJobs.apply {
            adapter = jobAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        binding.fabFilter.shrink()
                    } else if (dy < 0) {
                        binding.fabFilter.extend()
                    }
                }
            })
        }
    }

    private fun setupProviderFilter() {
        providerFilterAdapter = ProviderFilterAdapter { provider ->
            viewModel.toggleProvider(provider)
        }

        binding.recyclerViewProviders.apply {
            adapter = providerFilterAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupSearchBar() {
        binding.searchViewKeyword.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    currentKeyword = query?.trim() ?: ""
                    viewModel.search(currentKeyword, currentLocation)
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    currentKeyword = newText?.trim() ?: ""
                    if (newText.isNullOrEmpty()) {
                        viewModel.search("", currentLocation)
                    }
                    return false
                }
            })
        }

        binding.searchViewLocation.apply {
            queryHint = getString(R.string.location_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    currentLocation = query?.trim() ?: ""
                    viewModel.search(currentKeyword, currentLocation)
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    currentLocation = newText?.trim() ?: ""
                    if (newText.isNullOrEmpty()) {
                        viewModel.search(currentKeyword, "")
                    }
                    return false
                }
            })
        }
    }

    private fun setupFilterButton() {
        binding.fabFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun showFilterDialog() {
        val currentFilter = viewModel.currentFilter.value ?: return
        val jobTypes = JobType.values()
        var remoteOnly = currentFilter.remoteOnly

        val selectedJobTypes = currentFilter.selectedJobTypes.toMutableSet()

        val dialogView = layoutInflater.inflate(R.layout.dialog_filter, null)
        val chipGroupJobTypes = dialogView.findViewById<com.google.android.material.chip.ChipGroup>(
            R.id.chipGroupJobTypes
        )
        val switchRemote = dialogView.findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(
            R.id.switchRemoteOnly
        )

        // Populate job type chips
        jobTypes.forEach { jobType ->
            val chip = Chip(requireContext()).apply {
                text = jobType.displayName
                isCheckable = true
                isChecked = jobType in currentFilter.selectedJobTypes
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) selectedJobTypes.add(jobType)
                    else selectedJobTypes.remove(jobType)
                }
            }
            chipGroupJobTypes.addView(chip)
        }

        switchRemote.isChecked = remoteOnly
        switchRemote.setOnCheckedChangeListener { _, isChecked ->
            remoteOnly = isChecked
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.filter_title)
            .setView(dialogView)
            .setPositiveButton(R.string.apply) { _, _ ->
                viewModel.setJobTypeFilter(selectedJobTypes)
                viewModel.setRemoteOnly(remoteOnly)
            }
            .setNegativeButton(R.string.cancel, null)
            .setNeutralButton(R.string.reset) { _, _ ->
                viewModel.setJobTypeFilter(emptySet())
                viewModel.setRemoteOnly(false)
            }
            .show()
    }

    private fun observeViewModel() {
        viewModel.searchState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textEmpty.visibility = View.VISIBLE
                    binding.textEmpty.text = getString(R.string.search_prompt)
                }
                is SearchState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textEmpty.visibility = View.GONE
                }
                is SearchState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (state.jobs.isEmpty()) {
                        binding.textEmpty.visibility = View.VISIBLE
                        binding.textEmpty.text = getString(R.string.no_jobs_found)
                    } else {
                        binding.textEmpty.visibility = View.GONE
                    }
                    jobAdapter.submitList(state.jobs)
                    binding.textResultCount.text = getString(R.string.job_count, state.jobs.size)
                    binding.textResultCount.visibility = View.VISIBLE
                }
                is SearchState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textEmpty.visibility = View.VISIBLE
                    binding.textEmpty.text = getString(R.string.error_loading_jobs)
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.currentFilter.observe(viewLifecycleOwner) { filter ->
            providerFilterAdapter.updateSelectedProviders(filter.selectedProviders)
        }
    }

    private fun openJobDetail(job: Job) {
        val intent = Intent(requireContext(), JobDetailActivity::class.java).apply {
            putExtra(JobDetailActivity.EXTRA_JOB, job)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
