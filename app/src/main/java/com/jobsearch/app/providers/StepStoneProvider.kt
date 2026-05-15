package com.jobsearch.app.providers

import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import kotlinx.coroutines.delay

class StepStoneProvider : com.jobsearch.app.providers.JobProvider {

    private val allJobs = listOf(
        Job(
            id = "stepstone_001",
            title = "Projektleiter IT / Project Manager (m/w/d)",
            company = "T-Systems International GmbH",
            location = "Düsseldorf, Nordrhein-Westfalen",
            salary = "80.000 – 100.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """T-Systems, die B2B-Tochter der Deutschen Telekom, sucht einen erfahrenen IT Project Manager.

**Ihre Aufgaben:**
- Leitung komplexer IT-Transformationsprojekte für Großkunden
- Budgetverantwortung (bis 5 Mio. €)
- Stakeholder-Management auf C-Level-Ebene
- Koordination interdisziplinärer Projektteams
- Risikomanagement und Eskalationsbehandlung

**Ihr Profil:**
- PMP- oder PRINCE2-Zertifizierung
- 7+ Jahre Projektleitungserfahrung in IT-Projekten
- Erfahrung mit SAP oder IT-Infrastrukturprojekten
- Ausgeprägte Führungsstärke
- Reisebereitschaft ca. 30%

**Wir bieten:**
- Attraktives Gehalt + Bonus
- Firmenwagen oder BahnCard 100
- 30 Tage Urlaub
- Betriebliche Altersvorsorge""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/t-systems-project-manager",
            postedDate = "2024-01-15",
            isRemote = false
        ),
        Job(
            id = "stepstone_002",
            title = "Ingenieur Maschinenbau (m/w/d) – Produktentwicklung",
            company = "Trumpf GmbH + Co. KG",
            location = "Ditzingen, Baden-Württemberg",
            salary = "60.000 – 80.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """TRUMPF, Weltmarktführer für Lasertechnologie und Werkzeugmaschinen, sucht einen Maschinenbauingenieur.

**Ihre Aufgaben:**
- Konstruktion und Entwicklung von Lasersystemen und Werkzeugmaschinen
- Durchführung von FEM-Analysen und Simulationen
- Erstellung technischer Dokumentationen und Zeichnungen
- Enger Austausch mit Fertigung, Montage und Service
- Patentanmeldungen und IP-Management

**Ihr Profil:**
- Abgeschlossenes Studium Maschinenbau / Mechatronik
- Sehr gute CAD-Kenntnisse (CATIA V5 oder SolidWorks)
- Grundkenntnisse in FEM (ANSYS oder ähnlich)
- Analytisches Denkvermögen
- Teamgeist und Eigeninitiative

**Wir bieten:**
- Innovative Produktumgebung
- Tarifliches Gehalt + Erfolgsbonus
- Betriebliche Altersvorsorge
- Weiterbildungsprogramme""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/trumpf-maschinenbauingenieur",
            postedDate = "2024-01-14",
            isRemote = false
        ),
        Job(
            id = "stepstone_003",
            title = "Softwareentwickler Python / Django (m/w/d)",
            company = "ImmobilienScout24 GmbH",
            location = "Berlin, Deutschland",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """ImmobilienScout24, Deutschlands führendes Immobilienportal, sucht einen Python-Entwickler.

**Ihre Aufgaben:**
- Entwicklung und Pflege von Backend-Services mit Python/Django
- Optimierung von Datenbankabfragen (PostgreSQL, Elasticsearch)
- Mitgestaltung der Systemarchitektur
- Code Reviews und technische Dokumentation
- Agile Entwicklung in einem engagierten Team

**Ihr Profil:**
- 3+ Jahre Erfahrung in Python-Entwicklung
- Gute Kenntnisse in Django/DRF oder FastAPI
- Erfahrung mit relationalen und NoSQL-Datenbanken
- Kenntnisse in Docker und Kubernetes
- Teamorientiert und kommunikationsstark

**Wir bieten:**
- Hybrides Arbeitsmodell
- Wettbewerbsfähiges Gehalt
- Lernbudget und Konferenzbesuche
- Modernes Büro in Berlin-Mitte""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/immoscout24-python-developer",
            postedDate = "2024-01-13",
            isRemote = false
        ),
        Job(
            id = "stepstone_004",
            title = "Controlling Spezialist (m/w/d)",
            company = "Henkel AG & Co. KGaA",
            location = "Düsseldorf, Nordrhein-Westfalen",
            salary = "60.000 – 75.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Henkel, ein globales Unternehmen in den Bereichen Adhesive Technologies, Beauty und Laundry & Home Care, sucht einen Controller.

**Ihre Aufgaben:**
- Erstellung von Monats-, Quartals- und Jahresberichten
- Budgetplanung und -überwachung
- Ad-hoc-Analysen und Business Intelligence
- Unterstützung bei der strategischen Planung
- Weiterentwicklung von Reporting-Tools und -Prozessen

**Ihr Profil:**
- Abgeschlossenes Studium in BWL/Wirtschaftswissenschaften
- 3+ Jahre Berufserfahrung im Controlling
- Sehr gute Kenntnisse in SAP CO und Excel
- Analytisches Denkvermögen
- Gute Englischkenntnisse

**Wir bieten:**
- Internationales Umfeld
- Attraktives Vergütungspaket
- Flexible Arbeitszeiten
- Betriebliche Altersvorsorge""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/henkel-controller",
            postedDate = "2024-01-12",
            isRemote = false
        ),
        Job(
            id = "stepstone_005",
            title = "Android Developer (m/w/d) – Kotlin",
            company = "XING SE",
            location = "Hamburg, Deutschland",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """XING SE (New Work SE), das Karrierenetzwerk für den deutschsprachigen Raum, sucht einen Android Developer.

**Ihre Aufgaben:**
- Weiterentwicklung der XING-App für Android (5M+ User)
- Implementierung neuer Features mit Kotlin und Jetpack Compose
- Performance-Optimierung und Fehleranalyse
- Code Reviews und Pair Programming
- Mitwirkung bei technischen Architekturentscheidungen

**Ihr Profil:**
- 3+ Jahre Android-Entwicklungserfahrung
- Sehr gute Kotlin-Kenntnisse
- Erfahrung mit Jetpack Compose
- Kenntnisse in MVVM und Clean Architecture
- Teamorientierte Arbeitsweise

**Wir bieten:**
- Flexible Arbeitszeiten und Homeoffice
- Modernes Büro in Hamburg
- XING Premium Account
- Weiterbildungsbudget""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/xing-android-developer",
            postedDate = "2024-01-11",
            isRemote = false
        ),
        Job(
            id = "stepstone_006",
            title = "Vertriebsleiter / Sales Manager DACH (m/w/d)",
            company = "Salesforce Inc.",
            location = "München, Bayern",
            salary = "120.000 – 160.000 € / Jahr (inkl. Variable)",
            jobType = JobType.FULL_TIME,
            description = """Salesforce, Weltmarktführer für CRM-Software, sucht einen Sales Manager für die DACH-Region.

**Ihre Aufgaben:**
- Aufbau und Führung eines Vertriebsteams (10 Account Executives)
- Verantwortung für Umsatzziele in der DACH-Region
- Entwicklung und Umsetzung der Vertriebsstrategie
- Pflege strategischer Kundenbeziehungen
- Recruiting und Entwicklung von Vertriebstalenten

**Ihr Profil:**
- 8+ Jahre B2B-Vertriebserfahrung, davon 3+ Jahre in Führungsrolle
- Nachgewiesene Erfolge im SaaS/Enterprise-Vertrieb
- Starkes Netzwerk im deutschen Unternehmensumfeld
- Fließendes Deutsch und Englisch
- Reisebereitschaft ca. 40%

**Wir bieten:**
- Sehr attraktives Gesamtpaket (Fixum + OTE)
- Aktienoptionen
- Umfangreiches Benefit-Paket
- Globale Karrieremöglichkeiten""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/salesforce-sales-manager",
            postedDate = "2024-01-10",
            isRemote = false
        ),
        Job(
            id = "stepstone_007",
            title = "Pflegefachkraft (m/w/d) – Krankenhaus",
            company = "Charité – Universitätsmedizin Berlin",
            location = "Berlin, Deutschland",
            salary = "38.000 – 48.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Die Charité, eines der größten Universitätskrankenhäuser Europas, sucht qualifizierte Pflegefachkräfte.

**Ihre Aufgaben:**
- Grund- und Behandlungspflege von Patienten
- Assistenz bei diagnostischen und therapeutischen Maßnahmen
- Dokumentation und Pflegeplanung
- Zusammenarbeit mit Ärzten und therapeutischen Fachkräften
- Anleitung von Pflegeschülern

**Ihr Profil:**
- Abgeschlossene Ausbildung zur Pflegefachkraft (3 Jahre)
- Einfühlungsvermögen und Belastbarkeit
- Teamfähigkeit und Zuverlässigkeit
- Bereitschaft zur Schichtarbeit
- Gute Deutschkenntnisse

**Wir bieten:**
- Tarifliches Gehalt nach TVöD-K
- 30 Tage Urlaub
- Betriebliche Altersvorsorge
- Kinderbetreuungsangebote""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/charite-pflegefachkraft",
            postedDate = "2024-01-09",
            isRemote = false
        ),
        Job(
            id = "stepstone_008",
            title = "Embedded Software Engineer (m/w/d) – C/C++",
            company = "Continental AG",
            location = "Hannover, Niedersachsen",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Continental, einer der führenden Automobilzulieferer, sucht einen Embedded Software Engineer.

**Ihre Aufgaben:**
- Entwicklung von Embedded-Software für Fahrerassistenzsysteme (ADAS)
- Implementierung in C und C++ nach MISRA-Standards
- Integration und Test auf Ziel-Hardware
- Erstellung von technischen Spezifikationen
- Zusammenarbeit mit interdisziplinären Entwicklungsteams

**Ihr Profil:**
- Abgeschlossenes Studium Elektrotechnik / Informatik
- 3+ Jahre Erfahrung in der Embedded-Software-Entwicklung
- Sehr gute Kenntnisse in C/C++
- Kenntnisse in AUTOSAR und ISO 26262 von Vorteil
- Analytisches Denkvermögen

**Wir bieten:**
- Attraktives Gehalt
- Innovatives Arbeitsumfeld
- Weiterbildungsprogramme
- Betriebliche Altersvorsorge""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/continental-embedded-engineer",
            postedDate = "2024-01-08",
            isRemote = false
        ),
        Job(
            id = "stepstone_009",
            title = "Freelance Java Developer (m/w/d)",
            company = "Hays AG",
            location = "Frankfurt am Main / Remote",
            salary = "700 – 900 € / Tag",
            jobType = JobType.FREELANCE,
            description = """Hays, eines der weltweit führenden Personaldienstleistungsunternehmen, sucht im Auftrag eines Kunden einen Freelance Java Developer.

**Das Projekt:**
- Entwicklung einer neuen Banking-Plattform für eine deutsche Großbank
- Projektlaufzeit: 12 Monate (Verlängerung möglich)
- Start: sofort oder nach Vereinbarung
- Hauptsächlich Remote, ca. 1 Tag/Woche vor Ort in Frankfurt

**Anforderungen:**
- 5+ Jahre Java-Entwicklungserfahrung
- Kenntnisse in Spring Boot, Microservices, Kafka
- Erfahrung mit Banking/FinTech von Vorteil
- Sehr gute Deutschkenntnisse
- Gültige Arbeitserlaubnis in Deutschland

**Das bieten wir:**
- Attraktiver Tagessatz
- Langfristiges Projekt
- Möglichkeit zur Verlängerung
- Remote-First-Ansatz""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/hays-freelance-java-developer",
            postedDate = "2024-01-07",
            isRemote = true
        ),
        Job(
            id = "stepstone_010",
            title = "Logistik & Warehouse Manager (m/w/d)",
            company = "Amazon Deutschland GmbH",
            location = "Leipzig, Sachsen",
            salary = "45.000 – 60.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Amazon sucht einen Warehouse Manager für unser Logistikzentrum in Leipzig.

**Ihre Aufgaben:**
- Führung und Motivation eines Teams von 50–100 Mitarbeitern
- Überwachung der Betriebs- und Sicherheitskennzahlen
- Prozessoptimierung und Effizienzsteigerung
- Schichtplanung und Ressourcenmanagement
- Zusammenarbeit mit anderen Abteilungen (HR, Safety, IT)

**Ihr Profil:**
- Abgeschlossenes Studium Logistik/BWL oder vergleichbare Ausbildung
- 3+ Jahre Führungserfahrung in der Logistik
- Erfahrung mit Lean Management und Kaizen
- Bereitschaft zur Schichtarbeit
- Gute Deutsch- und Englischkenntnisse

**Wir bieten:**
- Wettbewerbsfähiges Gehalt + Bonus
- Aktienpaket (RSUs)
- Betriebliche Krankenversicherung
- Mitarbeiterrabatte""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/amazon-warehouse-manager",
            postedDate = "2024-01-06",
            isRemote = false
        ),
        Job(
            id = "stepstone_011",
            title = "Senior Data Engineer (m/w/d) – Remote",
            company = "Personio SE & Co. KG",
            location = "München / Remote",
            salary = "85.000 – 105.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """Personio, Europas führende HR-Software für KMU, sucht einen Senior Data Engineer.

**Ihre Aufgaben:**
- Design und Aufbau von skalierbaren Datenpipelines
- Entwicklung und Pflege des Data Warehouse (Snowflake)
- Zusammenarbeit mit Data Scientists und Analysten
- Datenqualitätssicherung und Monitoring
- Mentoring von Junior Engineers

**Ihr Profil:**
- 5+ Jahre Erfahrung als Data Engineer
- Sehr gute Kenntnisse in Python und SQL
- Erfahrung mit Airflow, dbt, Snowflake oder ähnlichen Tools
- Kenntnisse in Cloud-Infrastruktur (AWS/GCP)
- Erfahrung mit großen Datenmengen (100M+ Zeilen)

**Wir bieten:**
- Kompetitives Gehalt + Equity
- Remote-First-Kultur
- 2.000 € Weiterbildungsbudget/Jahr
- Personio-Werte-getriebene Unternehmenskultur""",
            provider = JobProvider.STEPSTONE,
            url = "https://www.stepstone.de/jobs/personio-data-engineer",
            postedDate = "2024-01-05",
            isRemote = true
        )
    )

    override suspend fun searchJobs(filter: JobFilter): List<Job> {
        delay(400)
        return allJobs.filter { job -> matchesFilter(job, filter) }
    }

    private fun matchesFilter(job: Job, filter: JobFilter): Boolean {
        if (filter.keyword.isNotEmpty()) {
            val keyword = filter.keyword.lowercase()
            if (!job.title.lowercase().contains(keyword) &&
                !job.company.lowercase().contains(keyword) &&
                !job.description.lowercase().contains(keyword)
            ) return false
        }
        if (filter.location.isNotEmpty()) {
            if (!job.location.lowercase().contains(filter.location.lowercase())) return false
        }
        if (filter.remoteOnly && !job.isRemote) return false
        if (filter.selectedJobTypes.isNotEmpty() && job.jobType !in filter.selectedJobTypes) return false
        return true
    }

    override fun getProviderName(): String = "StepStone"
}
