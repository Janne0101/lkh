package com.jobsearch.app.providers

import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import kotlinx.coroutines.delay

class IndeedProvider : com.jobsearch.app.providers.JobProvider {

    private val allJobs = listOf(
        Job(
            id = "indeed_001",
            title = "Senior Software Engineer (Java/Kotlin)",
            company = "SAP SE",
            location = "Walldorf, Baden-Württemberg",
            salary = "80.000 – 110.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """SAP SE sucht einen erfahrenen Software Engineer für unser Cloud-Team.

**Ihre Aufgaben:**
- Entwicklung und Wartung von Enterprise-Software-Lösungen in Java und Kotlin
- Design und Implementierung von Microservices-Architekturen
- Code Reviews und technische Dokumentation
- Zusammenarbeit mit internationalen Teams in agilen Projekten
- Mitwirkung bei der Weiterentwicklung unserer SAP Cloud Platform

**Ihr Profil:**
- Mindestens 5 Jahre Berufserfahrung in der Softwareentwicklung
- Sehr gute Kenntnisse in Java 17+ und Kotlin
- Erfahrung mit Spring Boot, Docker und Kubernetes
- Kenntnisse in CI/CD-Pipelines (Jenkins, GitHub Actions)
- Gute Deutsch- und Englischkenntnisse

**Wir bieten:**
- Attraktives Gehalt mit Bonus
- Flexible Arbeitszeiten und Home-Office-Möglichkeiten
- Betriebliche Altersvorsorge
- Internationale Karrieremöglichkeiten
- Modernes Arbeitsumfeld mit neuester Technologie""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/sap-senior-software-engineer",
            postedDate = "2024-01-15",
            isRemote = false
        ),
        Job(
            id = "indeed_002",
            title = "Data Scientist (m/w/d)",
            company = "Allianz SE",
            location = "München, Bayern",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Die Allianz SE, einer der weltweit führenden Versicherungskonzerne, sucht einen Data Scientist für unser Analytics-Team.

**Ihre Aufgaben:**
- Entwicklung von Machine-Learning-Modellen zur Risikoanalyse
- Datenanalyse und -visualisierung mit Python und R
- Zusammenarbeit mit Fachabteilungen zur Umsetzung datengetriebener Lösungen
- Aufbau und Pflege von Data Pipelines
- Präsentation von Ergebnissen an das Management

**Ihr Profil:**
- Abgeschlossenes Studium in Mathematik, Statistik oder Informatik
- Sehr gute Kenntnisse in Python (Pandas, scikit-learn, TensorFlow)
- Erfahrung mit SQL und Big Data-Technologien
- Kenntnisse im Versicherungswesen von Vorteil
- Analytisches Denkvermögen und Kommunikationsstärke

**Wir bieten:**
- Überdurchschnittliches Gehalt
- 30 Tage Urlaub
- Gesundheitsförderung und Sport
- Weiterbildungsmöglichkeiten""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/allianz-data-scientist",
            postedDate = "2024-01-14",
            isRemote = false
        ),
        Job(
            id = "indeed_003",
            title = "DevOps Engineer (m/w/d) – Remote",
            company = "Zalando SE",
            location = "Berlin (Remote möglich)",
            salary = "75.000 – 95.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """Zalando, Europas führende Online-Modeplattform, sucht einen DevOps Engineer.

**Ihre Aufgaben:**
- Betrieb und Weiterentwicklung unserer Cloud-Infrastruktur auf AWS
- Automatisierung von Deployment-Prozessen mit Terraform und Ansible
- Monitoring und Incident-Management
- Unterstützung der Entwicklungsteams bei DevOps-Best-Practices
- Optimierung von Performance und Sicherheit

**Ihr Profil:**
- 3+ Jahre Erfahrung als DevOps/Cloud Engineer
- Sehr gute Kenntnisse in AWS, Azure oder GCP
- Erfahrung mit Kubernetes und Docker
- Kenntnisse in Infrastructure as Code (Terraform, Pulumi)
- Scripting-Kenntnisse in Python oder Bash

**Wir bieten:**
- 100% Remote-Arbeit möglich
- Modernes Tech-Stack
- Offene Unternehmenskultur
- Mitarbeiterrabatte bei Zalando""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/zalando-devops-engineer",
            postedDate = "2024-01-13",
            isRemote = true
        ),
        Job(
            id = "indeed_004",
            title = "Product Manager – FinTech",
            company = "N26 GmbH",
            location = "Berlin, Brandenburg",
            salary = "85.000 – 105.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """N26, Europas führende Mobile Bank, sucht einen erfahrenen Product Manager.

**Ihre Aufgaben:**
- Entwicklung und Umsetzung der Produktstrategie für unsere Banking-Features
- Enge Zusammenarbeit mit Engineering, Design und Business Teams
- Marktanalysen und Wettbewerbsbeobachtung
- Definition und Tracking von KPIs
- Stakeholder-Management und Roadmap-Planung

**Ihr Profil:**
- 4+ Jahre Erfahrung als Product Manager, idealerweise im FinTech-Bereich
- Ausgeprägte analytische Fähigkeiten
- Erfahrung mit agilen Methoden (Scrum, Kanban)
- Sehr gute Englischkenntnisse
- Technisches Verständnis von Vorteil

**Wir bieten:**
- Wettbewerbsfähiges Gehalt + Aktienoptionen
- Flexible Arbeitszeiten
- Internationales Team
- Weiterbildungsbudget""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/n26-product-manager",
            postedDate = "2024-01-12",
            isRemote = false
        ),
        Job(
            id = "indeed_005",
            title = "UX Designer (m/w/d)",
            company = "Siemens AG",
            location = "München, Bayern",
            salary = "60.000 – 80.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Siemens AG sucht einen kreativen UX Designer für unser Digital Industries Team.

**Ihre Aufgaben:**
- Gestaltung von Benutzeroberflächen für industrielle IoT-Anwendungen
- Durchführung von User Research und Usability-Tests
- Erstellung von Wireframes, Prototypen und Design-Spezifikationen
- Zusammenarbeit mit Entwicklern und Produktmanagern
- Pflege und Weiterentwicklung des Design Systems

**Ihr Profil:**
- Abgeschlossenes Studium im Bereich Design, HCI oder ähnlichem
- 3+ Jahre Berufserfahrung als UX Designer
- Sehr gute Kenntnisse in Figma und Adobe Creative Suite
- Erfahrung mit User Research Methoden
- Portfolio mit relevanten Projekten erforderlich

**Wir bieten:**
- Hybrides Arbeitsmodell
- Umfangreiche Sozialleistungen
- Weiterbildungsprogramme
- Innovative Projekte""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/siemens-ux-designer",
            postedDate = "2024-01-11",
            isRemote = false
        ),
        Job(
            id = "indeed_006",
            title = "Marketing Manager Digital (m/w/d)",
            company = "REWE Group",
            location = "Köln, Nordrhein-Westfalen",
            salary = "55.000 – 70.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """REWE Group sucht einen Digital Marketing Manager für unser Online-Team.

**Ihre Aufgaben:**
- Planung und Durchführung von Online-Marketing-Kampagnen
- SEO/SEA Management und Performance-Optimierung
- Social Media Marketing auf allen relevanten Plattformen
- Analyse von Marketing-KPIs und Ableitung von Handlungsempfehlungen
- Zusammenarbeit mit Agenturen und internen Teams

**Ihr Profil:**
- Abgeschlossenes Studium in Marketing, BWL oder ähnlichem
- 3+ Jahre Erfahrung im digitalen Marketing
- Sehr gute Kenntnisse in Google Analytics, Google Ads und Meta Ads
- Erfahrung mit CRM-Systemen
- Kreativität und analytisches Denkvermögen

**Wir bieten:**
- Attraktives Gehaltspaket
- Mitarbeiterrabatte
- Betriebsrestaurant
- Gute Entwicklungsmöglichkeiten""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/rewe-marketing-manager",
            postedDate = "2024-01-10",
            isRemote = false
        ),
        Job(
            id = "indeed_007",
            title = "Frontend Developer React (m/w/d)",
            company = "CHECK24 GmbH",
            location = "München, Bayern",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """CHECK24, Deutschlands größtes Vergleichsportal, sucht Frontend Developer.

**Ihre Aufgaben:**
- Entwicklung moderner Web-Applikationen mit React und TypeScript
- Performance-Optimierung und Code-Qualitätssicherung
- Enge Zusammenarbeit mit Backend-Entwicklern und UX-Designern
- Mitwirkung bei der Architektur neuer Features
- Code Reviews und technisches Mentoring

**Ihr Profil:**
- 2+ Jahre Erfahrung mit React und TypeScript
- Gute Kenntnisse in HTML5, CSS3 und modernem JavaScript
- Erfahrung mit Testing-Frameworks (Jest, React Testing Library)
- Git-Kenntnisse und agile Arbeitsmethoden
- Leidenschaft für sauberen Code

**Wir bieten:**
- Hybrides Arbeitsmodell
- Moderne Technologien
- Flache Hierarchien
- Teamevents und Firmenveranstaltungen""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/check24-frontend-developer",
            postedDate = "2024-01-09",
            isRemote = false
        ),
        Job(
            id = "indeed_008",
            title = "Buchhalter / Accountant (m/w/d)",
            company = "Deloitte GmbH",
            location = "Frankfurt am Main, Hessen",
            salary = "50.000 – 65.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Deloitte, eine der führenden Wirtschaftsprüfungs- und Beratungsgesellschaften, sucht einen Buchhalter.

**Ihre Aufgaben:**
- Erstellung von Monats-, Quartals- und Jahresabschlüssen
- Buchführung nach HGB und IFRS
- Vorbereitung von Steuererklärungen
- Kontenabstimmung und -pflege
- Zusammenarbeit mit Steuerberatern und Wirtschaftsprüfern

**Ihr Profil:**
- Ausbildung zum Bilanzbuchhalter oder ähnliche Qualifikation
- Mindestens 3 Jahre Berufserfahrung
- Sehr gute Kenntnisse in SAP FI und DATEV
- Gute Englischkenntnisse
- Zuverlässigkeit und Genauigkeit

**Wir bieten:**
- Attraktives Vergütungspaket
- Flexible Arbeitszeiten
- Weiterbildungsmöglichkeiten
- Betriebliche Altersvorsorge""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/deloitte-buchhalter",
            postedDate = "2024-01-08",
            isRemote = false
        ),
        Job(
            id = "indeed_009",
            title = "Cybersecurity Analyst (m/w/d)",
            company = "Deutsche Telekom AG",
            location = "Bonn, Nordrhein-Westfalen",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Deutsche Telekom sucht einen Cybersecurity Analyst für unser Security Operations Center.

**Ihre Aufgaben:**
- Monitoring und Analyse von Sicherheitsvorfällen
- Durchführung von Vulnerability Assessments und Penetration Tests
- Entwicklung und Implementierung von Sicherheitsrichtlinien
- Incident Response und forensische Analyse
- Schulung von Mitarbeitern zu Sicherheitsthemen

**Ihr Profil:**
- Abgeschlossenes Studium der Informatik oder IT-Sicherheit
- 3+ Jahre Erfahrung im Cybersecurity-Bereich
- Kenntnisse in SIEM-Systemen (Splunk, QRadar)
- Zertifizierungen wie CISSP, CEH oder OSCP von Vorteil
- Analytisches Denkvermögen

**Wir bieten:**
- Attraktives Gehalt
- Interessante Projekte
- Weiterbildungszuschüsse für Zertifizierungen
- Work-Life-Balance""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/telekom-cybersecurity-analyst",
            postedDate = "2024-01-07",
            isRemote = false
        ),
        Job(
            id = "indeed_010",
            title = "HR Business Partner (m/w/d)",
            company = "BMW Group",
            location = "München, Bayern",
            salary = "65.000 – 80.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """BMW Group sucht einen erfahrenen HR Business Partner für unser internationales Team.

**Ihre Aufgaben:**
- Beratung von Führungskräften in allen personalrelevanten Fragen
- Begleitung von Veränderungsprozessen und Organisationsentwicklung
- Steuerung des Recruitingprozesses für definierte Bereiche
- Durchführung von Mitarbeitergesprächen und Talentprogrammen
- Mitarbeit bei der Weiterentwicklung der HR-Strategie

**Ihr Profil:**
- Abgeschlossenes Studium in BWL, Personalmanagement oder ähnlichem
- 5+ Jahre Erfahrung als HR Business Partner
- Sehr gute Arbeitsrechtskenntnisse
- Erfahrung mit SAP SuccessFactors
- Kommunikationsstärke und Durchsetzungsvermögen

**Wir bieten:**
- Attraktives Vergütungspaket inkl. Bonus
- Dienstwagen-Option
- Betriebliche Altersvorsorge
- Internationale Karrieremöglichkeiten""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/bmw-hr-business-partner",
            postedDate = "2024-01-06",
            isRemote = false
        ),
        Job(
            id = "indeed_011",
            title = "Cloud Architect (m/w/d) – AWS",
            company = "msg systems ag",
            location = "München / Remote",
            salary = "90.000 – 120.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """msg systems ag sucht einen erfahrenen Cloud Architect für komplexe Enterprise-Projekte.

**Ihre Aufgaben:**
- Design und Implementierung von Cloud-Architekturen auf AWS
- Technische Beratung und Konzeption für Kundenprojekte
- Migration von On-Premise zu Cloud-Lösungen
- Bewertung und Auswahl von Cloud-Technologien
- Mentoring von Junior-Kollegen

**Ihr Profil:**
- 7+ Jahre IT-Erfahrung, davon 3+ Jahre als Cloud Architect
- AWS-Zertifizierungen (Solutions Architect Professional)
- Kenntnisse in Kubernetes, Terraform und CI/CD
- Projekterfahrung in Enterprise-Umgebungen
- Reisebereitschaft (ca. 20%)

**Wir bieten:**
- Überdurchschnittliches Gehalt
- Homeoffice-Möglichkeit
- AWS-Zertifizierungsunterstützung
- Firmenwagen oder BahnCard 100""",
            provider = JobProvider.INDEED,
            url = "https://www.indeed.de/jobs/msg-cloud-architect",
            postedDate = "2024-01-05",
            isRemote = true
        )
    )

    override suspend fun searchJobs(filter: JobFilter): List<Job> {
        delay(300) // Simulate network delay
        return allJobs.filter { job ->
            matchesFilter(job, filter)
        }
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

    override fun getProviderName(): String = "Indeed"
}
