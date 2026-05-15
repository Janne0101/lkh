package com.jobsearch.app.providers

import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import kotlinx.coroutines.delay

class XingProvider : com.jobsearch.app.providers.JobProvider {

    private val allJobs = listOf(
        Job(
            id = "xing_001",
            title = "Teamleiter Softwareentwicklung (m/w/d)",
            company = "Otto GmbH & Co KG",
            location = "Hamburg, Deutschland",
            salary = "90.000 – 115.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Otto, eines der größten Online-Handelsunternehmen Europas, sucht einen Teamleiter Softwareentwicklung.

**Ihre Aufgaben:**
- Fachliche und disziplinarische Führung eines 8-köpfigen Entwicklungsteams
- Verantwortung für die technische Weiterentwicklung der E-Commerce-Plattform
- Architekturentscheidungen und technische Roadmap-Planung
- Förderung einer positiven Teamkultur und agilen Arbeitsweise
- Recruiting und Onboarding neuer Teammitglieder

**Ihr Profil:**
- 6+ Jahre Softwareentwicklungserfahrung, davon 2+ Jahre in Führungsrolle
- Starke technische Kenntnisse in Java oder Kotlin/Spring Boot
- Erfahrung mit Microservices und Cloud-Architekturen (AWS)
- Ausgeprägte Kommunikations- und Führungsstärke
- Agile Methodenkenntnisse (Scrum, SAFe)

**Wir bieten:**
- Attraktives Gehalt + Bonus
- Hybrides Arbeitsmodell
- Weiterbildungsbudget
- Betriebliche Altersvorsorge""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/otto-teamleiter-softwareentwicklung",
            postedDate = "2024-01-15",
            isRemote = false
        ),
        Job(
            id = "xing_002",
            title = "Wirtschaftsprüfer / Auditor (m/w/d)",
            company = "KPMG AG Wirtschaftsprüfungsgesellschaft",
            location = "Berlin, Deutschland",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """KPMG, eine der weltweit führenden Wirtschaftsprüfungs- und Beratungsgesellschaften, sucht einen Auditor.

**Ihre Aufgaben:**
- Durchführung von Jahres- und Konzernabschlussprüfungen nach HGB und IFRS
- Prüfung interner Kontrollsysteme
- Erstellung von Prüfungsberichten und Management Letters
- Beratung von Mandanten in Rechnungslegungsfragen
- Betreuung und Weiterentwicklung von Prüfungsmethoden

**Ihr Profil:**
- Abgeschlossenes Studium + Wirtschaftsprüferexamen oder kurz davor
- 3+ Jahre Berufserfahrung in der Wirtschaftsprüfung
- Sehr gute IFRS-Kenntnisse
- Analytisches Denkvermögen und Sorgfalt
- Fließendes Deutsch und Englisch

**Wir bieten:**
- Unterstützung beim WP-Examen
- Internationales Arbeitsumfeld
- Attraktives Vergütungspaket
- Mentoring-Programm""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/kpmg-auditor",
            postedDate = "2024-01-14",
            isRemote = false
        ),
        Job(
            id = "xing_003",
            title = "DevSecOps Engineer (m/w/d) – Remote First",
            company = "Scout24 AG",
            location = "München / Remote",
            salary = "80.000 – 100.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """Scout24, Betreiber von AutoScout24 und ImmobilienScout24, sucht einen DevSecOps Engineer.

**Ihre Aufgaben:**
- Integration von Security-Praktiken in CI/CD-Pipelines
- Durchführung von Security Assessments und Penetration Tests
- Aufbau und Betrieb von SIEM und Security-Monitoring
- Entwicklung von Sicherheitsrichtlinien und -standards
- Schulung von Entwicklungsteams in Security Best Practices

**Ihr Profil:**
- 4+ Jahre Erfahrung im DevOps/DevSecOps-Bereich
- Kenntnisse in AWS Security, IAM, GuardDuty
- Erfahrung mit Container-Security (Docker, Kubernetes)
- CISSP, CEH oder AWS Security Specialty von Vorteil
- Scripting in Python oder Bash

**Wir bieten:**
- Remote-First-Kultur
- Wettbewerbsfähiges Gehalt
- Security-Konferenzbesuche (Black Hat, DEF CON)
- Flexible Arbeitszeiten""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/scout24-devsecops-engineer",
            postedDate = "2024-01-13",
            isRemote = true
        ),
        Job(
            id = "xing_004",
            title = "Brand Manager (m/w/d)",
            company = "adidas AG",
            location = "Herzogenaurach, Bayern",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """adidas, eine der weltweit bekanntesten Sportmarken, sucht einen Brand Manager.

**Ihre Aufgaben:**
- Entwicklung und Umsetzung von Marketingkampagnen für den deutschen Markt
- Markenpositionierung und -kommunikation
- Zusammenarbeit mit globalen und lokalen Teams
- Budgetverantwortung und ROI-Tracking
- Kampagnen-Briefing für Agenturen und Kreativ-Teams

**Ihr Profil:**
- Abgeschlossenes Studium in Marketing/Kommunikation
- 4+ Jahre Erfahrung im Brand Management, idealerweise im Consumer Goods / Sports
- Kreativität gepaart mit analytischem Denken
- Erfahrung mit multimedialen Kampagnen
- Fließendes Englisch und Deutsch

**Wir bieten:**
- Attraktives Gehalt + Bonus
- adidas-Mitarbeiterrabatt (50%)
- Betriebliches Fitnessstudio
- Globales Karrierenetzwerk""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/adidas-brand-manager",
            postedDate = "2024-01-12",
            isRemote = false
        ),
        Job(
            id = "xing_005",
            title = "Physiotherapeut (m/w/d) – Teilzeit möglich",
            company = "Helios Kliniken GmbH",
            location = "Berlin, Deutschland",
            salary = "35.000 – 45.000 € / Jahr",
            jobType = JobType.PART_TIME,
            description = """Helios Kliniken, Deutschlands größter privater Krankenhausbetreiber, sucht Physiotherapeuten.

**Ihre Aufgaben:**
- Physiotherapeutische Behandlung von stationären und ambulanten Patienten
- Erstellung und Umsetzung individueller Behandlungspläne
- Dokumentation und Befunderhebung
- Zusammenarbeit mit Ärzten und Pflegepersonal
- Anleitung von Patienten zu Heimübungen

**Ihr Profil:**
- Abgeschlossene Ausbildung als Physiotherapeut/in
- Berufserfahrung in der Klinik von Vorteil
- Zusatzqualifikationen (z.B. Manuelle Therapie, Vojta) willkommen
- Einfühlungsvermögen und Patientenorientierung
- Teamfähigkeit

**Wir bieten:**
- Attraktive Vergütung nach Haustarif
- Teilzeit oder Vollzeit möglich
- Fort- und Weiterbildungsmöglichkeiten
- Betriebliche Altersvorsorge""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/helios-physiotherapeut",
            postedDate = "2024-01-11",
            isRemote = false
        ),
        Job(
            id = "xing_006",
            title = "SAP ABAP Entwickler (m/w/d)",
            company = "Capgemini Deutschland GmbH",
            location = "Frankfurt am Main, Hessen",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Capgemini, eines der weltweit führenden IT-Dienstleistungsunternehmen, sucht einen SAP ABAP Entwickler.

**Ihre Aufgaben:**
- Entwicklung und Anpassung von SAP-Modulen in ABAP/ABAP OO
- Design und Implementierung von SAP-Schnittstellen (ALE, IDocs, BAPIs)
- Technische Beratung bei SAP S/4HANA-Migrationen
- Code Reviews und Dokumentation
- Kundenkommunikation und -beratung

**Ihr Profil:**
- 4+ Jahre SAP ABAP-Entwicklungserfahrung
- Kenntnisse in SAP S/4HANA und Fiori
- Erfahrung mit SAP BTP von Vorteil
- ABAP-Zertifizierungen willkommen
- Reisebereitschaft ca. 20%

**Wir bieten:**
- Attraktives Gehaltspaket
- SAP-Zertifizierungsunterstützung
- Hybrides Arbeitsmodell
- Internationales Projektumfeld""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/capgemini-sap-abap-developer",
            postedDate = "2024-01-10",
            isRemote = false
        ),
        Job(
            id = "xing_007",
            title = "Business Development Manager (m/w/d)",
            company = "Celonis SE",
            location = "München, Bayern",
            salary = "90.000 – 120.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Celonis, der globale Marktführer für Process Mining, sucht einen Business Development Manager.

**Ihre Aufgaben:**
- Identifikation und Akquise von Neukunden im Enterprise-Segment
- Entwicklung von Vertriebsstrategien für neue Märkte
- Aufbau strategischer Partnerschaften
- Zusammenarbeit mit Marketing und Product Teams
- Repräsentation von Celonis auf Konferenzen und Events

**Ihr Profil:**
- 5+ Jahre Erfahrung in Business Development oder Enterprise Sales
- Verständnis von Process Mining oder ERP-Systemen
- Ausgeprägte Verhandlungs- und Präsentationsstärke
- Exzellente Deutsch- und Englischkenntnisse
- Reisebereitschaft ca. 30%

**Wir bieten:**
- Wettbewerbsfähiges OTE (Fixum + Provision)
- Aktienoptionen
- Dynamisches Scale-up-Umfeld
- Regelmäßige Team-Events""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/celonis-business-development-manager",
            postedDate = "2024-01-09",
            isRemote = false
        ),
        Job(
            id = "xing_008",
            title = "Verwaltungsfachangestellte/r (m/w/d)",
            company = "Bundesagentur für Arbeit",
            location = "Nürnberg, Bayern",
            salary = "30.000 – 42.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Die Bundesagentur für Arbeit, der größte Dienstleister des deutschen Arbeitsmarkts, sucht Verwaltungsfachangestellte.

**Ihre Aufgaben:**
- Beratung und Betreuung von Arbeitssuchenden und Unternehmen
- Bearbeitung von Anträgen auf Arbeitslosengeld
- Verwaltungsaufgaben und Datenpflege
- Telefonische und persönliche Kundenbetreuung
- Zusammenarbeit mit internen Abteilungen

**Ihr Profil:**
- Abgeschlossene Ausbildung als Verwaltungsfachangestellte/r oder vergleichbar
- Gute EDV-Kenntnisse
- Serviceorientierung und Einfühlungsvermögen
- Teamfähigkeit und Belastbarkeit
- Deutschkenntnisse auf Muttersprachenniveau

**Wir bieten:**
- Tarifliches Gehalt nach TV-BA
- Sicherer Arbeitsplatz im öffentlichen Dienst
- 30 Tage Urlaub
- Betriebliche Altersvorsorge (VBL)""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/bundesagentur-verwaltungsfachangestellte",
            postedDate = "2024-01-08",
            isRemote = false
        ),
        Job(
            id = "xing_009",
            title = "Technical Writer / Technischer Redakteur (m/w/d)",
            company = "MaibornWolff GmbH",
            location = "München / Remote",
            salary = "55.000 – 70.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """MaibornWolff, eine der renommiertesten IT-Beratungen Deutschlands, sucht einen Technical Writer.

**Ihre Aufgaben:**
- Erstellung von Technischen Dokumentationen, API-Dokumentationen und Benutzerhandbüchern
- Zusammenarbeit mit Entwicklern und Produktmanagern
- Pflege und Strukturierung von Wikis (Confluence)
- Erstellung von Tutorials und How-to-Guides
- Review und Qualitätssicherung von Dokumenten

**Ihr Profil:**
- Abgeschlossenes Studium in Technischer Redaktion, Informatik oder ähnlichem
- 2+ Jahre Erfahrung als Technical Writer
- Gute Kenntnisse in Markdown, AsciiDoc oder DITA
- Erfahrung mit Docs-as-Code-Ansätzen
- Sehr gutes Sprachgefühl in Deutsch und Englisch

**Wir bieten:**
- Remote-Arbeit möglich
- Attraktives Gehalt
- Weiterbildungsbudget
- Flache Hierarchien""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/maibornwolff-technical-writer",
            postedDate = "2024-01-07",
            isRemote = true
        ),
        Job(
            id = "xing_010",
            title = "Werkstudent IT / Softwareentwicklung (m/w/d)",
            company = "Sixt SE",
            location = "München, Bayern",
            salary = "15 – 18 € / Stunde",
            jobType = JobType.PART_TIME,
            description = """Sixt SE, eines der weltweit führenden Mobilitätsunternehmen, sucht Werkstudenten im IT-Bereich.

**Ihre Aufgaben:**
- Unterstützung bei der Entwicklung interner Tools und Applikationen
- Mitwirkung an agilen Entwicklungsprojekten
- Bug-Fixing und Code-Review-Unterstützung
- Erstellung technischer Dokumentationen
- Eigenständige Übernahme kleiner Teilprojekte

**Ihr Profil:**
- Laufendes Studium der Informatik oder eines verwandten Fachs
- Erste Programmierkenntnisse (Java, Python, JavaScript)
- Lernbereitschaft und Eigeninitiative
- Teamfähigkeit
- 15–20 Stunden/Woche verfügbar

**Wir bieten:**
- Wettbewerbsfähige Vergütung
- Einblick in ein globales Tech-Unternehmen
- Übernahmemöglichkeit nach dem Studium
- Mitarbeiterrabatte bei Sixt""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/sixt-werkstudent-it",
            postedDate = "2024-01-06",
            isRemote = false
        ),
        Job(
            id = "xing_011",
            title = "Inhouse Consultant SAP S/4HANA (m/w/d)",
            company = "Merck KGaA",
            location = "Darmstadt, Hessen",
            salary = "80.000 – 100.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Merck KGaA, ein führendes Wissenschafts- und Technologieunternehmen, sucht einen SAP S/4HANA Consultant.

**Ihre Aufgaben:**
- Beratung und Implementierung von SAP S/4HANA-Projekten
- Analyse von Geschäftsprozessen und Entwicklung von Lösungskonzepten
- Customizing und Konfiguration von SAP-Modulen (MM, SD, FI)
- Schulung und Support von Key-Usern
- Koordination mit externen SAP-Partnern

**Ihr Profil:**
- Abgeschlossenes Studium Wirtschaftsinformatik oder ähnlich
- 4+ Jahre SAP-Projekterfahrung (S/4HANA bevorzugt)
- Kenntnisse in mindestens 2 SAP-Modulen
- Prozessverständnis in Life Sciences von Vorteil
- Deutsch und Englisch fließend

**Wir bieten:**
- Attraktives Gesamtpaket
- Innovatives Pharmaumfeld
- Hybrides Arbeitsmodell
- Internationale Projektmöglichkeiten""",
            provider = JobProvider.XING,
            url = "https://www.xing.com/jobs/merck-sap-s4hana-consultant",
            postedDate = "2024-01-05",
            isRemote = false
        )
    )

    override suspend fun searchJobs(filter: JobFilter): List<Job> {
        delay(320)
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

    override fun getProviderName(): String = "Xing"
}
