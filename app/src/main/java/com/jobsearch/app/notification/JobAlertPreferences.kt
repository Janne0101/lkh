package com.jobsearch.app.notification

import android.content.Context
import com.jobsearch.app.data.model.JobAlert
import java.util.UUID

class JobAlertPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("job_alerts", Context.MODE_PRIVATE)

    fun getAlerts(): List<JobAlert> {
        val count = prefs.getInt("count", 0)
        return (0 until count).mapNotNull { i ->
            val id = prefs.getString("alert_${i}_id", null) ?: return@mapNotNull null
            JobAlert(
                id = id,
                keyword = prefs.getString("alert_${i}_keyword", "") ?: "",
                location = prefs.getString("alert_${i}_location", "") ?: "",
                remoteOnly = prefs.getBoolean("alert_${i}_remote", false),
                intervalHours = prefs.getInt("alert_${i}_interval", 6),
                isActive = prefs.getBoolean("alert_${i}_active", true),
                createdAt = prefs.getLong("alert_${i}_created", System.currentTimeMillis())
            )
        }
    }

    fun saveAlerts(alerts: List<JobAlert>) {
        prefs.edit().apply {
            // Clear old keys
            val oldCount = prefs.getInt("count", 0)
            for (i in 0 until oldCount) {
                remove("alert_${i}_id")
                remove("alert_${i}_keyword")
                remove("alert_${i}_location")
                remove("alert_${i}_remote")
                remove("alert_${i}_interval")
                remove("alert_${i}_active")
                remove("alert_${i}_created")
            }
            putInt("count", alerts.size)
            alerts.forEachIndexed { i, alert ->
                putString("alert_${i}_id", alert.id)
                putString("alert_${i}_keyword", alert.keyword)
                putString("alert_${i}_location", alert.location)
                putBoolean("alert_${i}_remote", alert.remoteOnly)
                putInt("alert_${i}_interval", alert.intervalHours)
                putBoolean("alert_${i}_active", alert.isActive)
                putLong("alert_${i}_created", alert.createdAt)
            }
            apply()
        }
    }

    fun addAlert(alert: JobAlert) {
        val current = getAlerts().toMutableList()
        current.add(alert)
        saveAlerts(current)
    }

    fun removeAlert(alertId: String) {
        val updated = getAlerts().filter { it.id != alertId }
        saveAlerts(updated)
        // Also clear seen jobs for this alert
        prefs.edit().remove("seen_$alertId").apply()
    }

    fun toggleAlert(alertId: String) {
        val updated = getAlerts().map {
            if (it.id == alertId) it.copy(isActive = !it.isActive) else it
        }
        saveAlerts(updated)
    }

    fun getSeenJobIds(alertId: String): Set<String> {
        return prefs.getStringSet("seen_$alertId", emptySet()) ?: emptySet()
    }

    fun addSeenJobIds(alertId: String, jobIds: Set<String>) {
        val existing = getSeenJobIds(alertId)
        // Keep only last 200 to avoid unbounded growth
        val merged = (existing + jobIds).takeLast(200).toSet()
        prefs.edit().putStringSet("seen_$alertId", merged).apply()
    }

    fun createAlert(keyword: String, location: String, remoteOnly: Boolean, intervalHours: Int): JobAlert {
        return JobAlert(
            id = UUID.randomUUID().toString(),
            keyword = keyword,
            location = location,
            remoteOnly = remoteOnly,
            intervalHours = intervalHours
        )
    }
}
