package com.jobsearch.app.ui.alerts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jobsearch.app.R
import com.jobsearch.app.databinding.DialogAddAlertBinding
import com.jobsearch.app.databinding.FragmentAlertsBinding
import com.jobsearch.app.notification.JobAlertPreferences
import com.jobsearch.app.notification.JobCheckWorker
import com.jobsearch.app.ui.adapter.AlertAdapter

class AlertsFragment : Fragment() {

    private var _binding: FragmentAlertsBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefs: JobAlertPreferences
    private lateinit var adapter: AlertAdapter

    private val notifPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            showAddAlertDialog()
        } else {
            Toast.makeText(requireContext(), getString(R.string.notification_permission_denied), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAlertsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = JobAlertPreferences(requireContext())

        adapter = AlertAdapter(
            onToggle = { alert ->
                prefs.toggleAlert(alert.id)
                refreshList()
                rescheduleWorkerIfNeeded()
            },
            onDelete = { alert ->
                prefs.removeAlert(alert.id)
                refreshList()
                rescheduleWorkerIfNeeded()
                Toast.makeText(requireContext(), getString(R.string.alert_deleted), Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerViewAlerts.apply {
            this.adapter = this@AlertsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.fabAddAlert.setOnClickListener {
            requestNotificationPermissionThenAdd()
        }

        refreshList()
    }

    private fun requestNotificationPermissionThenAdd() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> showAddAlertDialog()

                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.notification_permission_title)
                        .setMessage(R.string.notification_permission_rationale)
                        .setPositiveButton(R.string.grant) { _, _ ->
                            notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                        .setNegativeButton(R.string.cancel, null)
                        .show()
                }

                else -> notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            showAddAlertDialog()
        }
    }

    private fun showAddAlertDialog() {
        val dialogBinding = DialogAddAlertBinding.inflate(layoutInflater)

        val intervalOptions = listOf(
            getString(R.string.interval_1h),
            getString(R.string.interval_6h),
            getString(R.string.interval_12h),
            getString(R.string.interval_24h)
        )
        val intervalHours = listOf(1, 6, 12, 24)

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, intervalOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogBinding.spinnerInterval.adapter = spinnerAdapter
        dialogBinding.spinnerInterval.setSelection(1) // default: 6h

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.add_alert_title)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                val keyword = dialogBinding.editTextAlertKeyword.text.toString().trim()
                val location = dialogBinding.editTextAlertLocation.text.toString().trim()
                val remoteOnly = dialogBinding.switchAlertRemote.isChecked
                val hours = intervalHours[dialogBinding.spinnerInterval.selectedItemPosition]

                if (keyword.isEmpty() && location.isEmpty()) {
                    Toast.makeText(requireContext(), getString(R.string.alert_keyword_required), Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val alert = prefs.createAlert(keyword, location, remoteOnly, hours)
                prefs.addAlert(alert)
                JobCheckWorker.schedule(requireContext(), hours.toLong())
                refreshList()
                Toast.makeText(requireContext(), getString(R.string.alert_created), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun refreshList() {
        val alerts = prefs.getAlerts()
        adapter.submitList(alerts.toList())
        binding.textEmptyAlerts.visibility = if (alerts.isEmpty()) View.VISIBLE else View.GONE
        binding.textAlertCount.text = getString(R.string.alert_count, alerts.size)
        binding.textAlertCount.visibility = if (alerts.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun rescheduleWorkerIfNeeded() {
        val activeAlerts = prefs.getAlerts().filter { it.isActive }
        if (activeAlerts.isEmpty()) {
            JobCheckWorker.cancel(requireContext())
        } else {
            val minInterval = activeAlerts.minOf { it.intervalHours }.toLong()
            JobCheckWorker.schedule(requireContext(), minInterval)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
