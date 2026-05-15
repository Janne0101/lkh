package com.wohnungssuche.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Portal(val displayName: String, val baseUrl: String) {
    IMMOSCOUT("ImmobilienScout24", "https://www.immobilienscout24.de/Suche/de/wohnung-mieten"),
    IMMOWELT("ImmoWelt", "https://www.immowelt.de/suche/wohnungen/mieten"),
    KLEINANZEIGEN("Kleinanzeigen", "https://www.kleinanzeigen.de/s-wohnung-mieten/"),
    WG_GESUCHT("WG-Gesucht", "https://www.wg-gesucht.de/")
}

@Entity(tableName = "apartments")
data class Apartment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val address: String,
    val rent: Double,           // Kaltmiete in €
    val totalRent: Double,      // Warmmiete in €
    val sizeM2: Double,         // Größe in m²
    val rooms: Double,
    val floor: String = "",
    val availableFrom: String = "",
    val url: String = "",
    val portalName: String = "",
    val notes: String = "",
    val rating: Int = 0,        // 0-5 Sterne
    val pros: String = "",
    val cons: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isComparing: Boolean = false
)
