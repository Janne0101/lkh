package com.jobsearch.app.ui.saved

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jobsearch.app.R
import com.jobsearch.app.databinding.FragmentSavedBinding
import com.jobsearch.app.ui.adapter.JobAdapter
import com.jobsearch.app.ui.detail.JobDetailActivity
import com.jobsearch.app.viewmodel.SavedViewModel

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter(
            onJobClick = { job ->
                val intent = Intent(requireContext(), JobDetailActivity::class.java).apply {
                    putExtra(JobDetailActivity.EXTRA_JOB, job)
                }
                startActivity(intent)
            },
            onBookmarkClick = { job ->
                viewModel.removeJob(job)
            }
        )

        binding.recyclerViewSaved.apply {
            adapter = jobAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
        }
    }

    private fun observeViewModel() {
        viewModel.savedJobs.observe(viewLifecycleOwner) { jobs ->
            if (jobs.isEmpty()) {
                binding.textEmpty.visibility = View.VISIBLE
                binding.recyclerViewSaved.visibility = View.GONE
                binding.textSavedCount.visibility = View.GONE
            } else {
                binding.textEmpty.visibility = View.GONE
                binding.recyclerViewSaved.visibility = View.VISIBLE
                binding.textSavedCount.visibility = View.VISIBLE
                binding.textSavedCount.text = getString(R.string.saved_count, jobs.size)
                jobAdapter.submitList(jobs)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
