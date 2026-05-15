package com.jobsearch.app.providers

import com.jobsearch.app.data.model.Job
import com.jobsearch.app.data.model.JobFilter
import com.jobsearch.app.data.model.JobProvider
import com.jobsearch.app.data.model.JobType
import kotlinx.coroutines.delay

class LinkedInProvider : com.jobsearch.app.providers.JobProvider {

    private val allJobs = listOf(
        Job(
            id = "linkedin_001",
            title = "Engineering Manager – Backend",
            company = "Delivery Hero SE",
            location = "Berlin, Deutschland",
            salary = "100.000 – 130.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Delivery Hero, einer der weltweit führenden lokalen Lieferdienste, sucht einen Engineering Manager.

**Ihre Aufgaben:**
- Leitung und Entwicklung eines Teams von 6–10 Backend-Ingenieuren
- Technische Verantwortung für unsere Bestellplattform
- Zusammenarbeit mit Produktmanagement und Architekten
- Definition technischer Standards und Best Practices
- Karriereentwicklung und Mentoring der Teammitglieder

**Ihr Profil:**
- 3+ Jahre Erfahrung als Engineering Manager oder Tech Lead
- Starker technischer Hintergrund in Java, Go oder Kotlin
- Erfahrung mit verteilten Systemen und Microservices
- Exzellente Kommunikationsfähigkeiten
- Sehr gute Englischkenntnisse

**Wir bieten:**
- Wettbewerbsfähiges Gehalt + Aktienoptionen
- Hybrides Arbeitsmodell
- Lernen & Entwicklungsbudget
- Internationales, diverses Team""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/delivery-hero-engineering-manager",
            postedDate = "2024-01-15",
            isRemote = false
        ),
        Job(
            id = "linkedin_002",
            title = "Financial Analyst (m/w/d)",
            company = "Deutsche Bank AG",
            location = "Frankfurt am Main, Hessen",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Deutsche Bank sucht einen analytisch starken Financial Analyst für unser Investment Banking Team.

**Ihre Aufgaben:**
- Finanzmodellierung und Bewertung von Unternehmen
- Erstellung von Präsentationen und Pitch Books
- Markt- und Branchenanalysen
- Unterstützung bei M&A-Transaktionen und Kapitalmarktgeschäften
- Due Diligence und Dokumentation

**Ihr Profil:**
- Abgeschlossenes Studium in Finance, Wirtschaft oder Mathematik
- 1–3 Jahre Erfahrung im Investment Banking oder Consulting
- Sehr gute Excel und PowerPoint Kenntnisse
- Kenntnisse in Bloomberg und Factset
- Fließende Deutsch- und Englischkenntnisse

**Wir bieten:**
- Attraktives Basisgehalt + Bonus
- Umfangreiche Weiterbildungsprogramme
- Globales Netzwerk
- Betriebliche Altersvorsorge""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/deutsche-bank-financial-analyst",
            postedDate = "2024-01-14",
            isRemote = false
        ),
        Job(
            id = "linkedin_003",
            title = "Machine Learning Engineer (m/w/d)",
            company = "Bosch GmbH",
            location = "Stuttgart, Baden-Württemberg",
            salary = "80.000 – 100.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Robert Bosch GmbH sucht einen ML Engineer für unser AI/ML Center of Excellence.

**Ihre Aufgaben:**
- Entwicklung und Deployment von ML-Modellen für Automotive-Anwendungen
- Integration von AI-Lösungen in Embedded Systems
- Zusammenarbeit mit Data Scientists und Software Engineers
- Optimierung von Modellen für Edge Computing
- Technische Dokumentation und Patentanmeldungen

**Ihr Profil:**
- Abgeschlossenes Studium in Informatik, Mathematik oder Ingenieurswesen
- Sehr gute Kenntnisse in Python, PyTorch/TensorFlow
- Erfahrung mit MLOps und Modell-Deployment
- Grundkenntnisse in C++ von Vorteil
- Teamfähigkeit und selbstständige Arbeitsweise

**Wir bieten:**
- Innovatives Arbeitsumfeld
- Flexible Arbeitszeiten
- Betriebliche Altersvorsorge
- Umfangreiches Weiterbildungsangebot""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/bosch-ml-engineer",
            postedDate = "2024-01-13",
            isRemote = false
        ),
        Job(
            id = "linkedin_004",
            title = "Head of Marketing – DACH Region",
            company = "Spotify Technology",
            location = "Hamburg, Deutschland",
            salary = "110.000 – 140.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """Spotify sucht einen erfahrenen Head of Marketing für die DACH-Region.

**Ihre Aufgaben:**
- Führung und Entwicklung des DACH Marketing-Teams (8 Personen)
- Entwicklung und Umsetzung der Marketing-Strategie für Deutschland, Österreich und Schweiz
- Verantwortung für Kampagnenplanung und -durchführung
- Enge Zusammenarbeit mit globalen und lokalen Teams
- Budget-Verantwortung (7-stellig)

**Ihr Profil:**
- 8+ Jahre Marketing-Erfahrung, davon 3+ Jahre in Führungsrolle
- Nachgewiesene Erfolge in der DACH-Region
- Tiefes Verständnis von Consumer Marketing und digitalem Marketing
- Fließendes Deutsch und Englisch
- Leidenschaft für Musik und Audio

**Wir bieten:**
- Top-Gehalt + Equity
- Premium Spotify-Account für Familie
- Flexible Work-from-anywhere-Policy
- Globale Karrieremöglichkeiten""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/spotify-head-of-marketing",
            postedDate = "2024-01-12",
            isRemote = false
        ),
        Job(
            id = "linkedin_005",
            title = "Senior iOS Developer (m/w/d)",
            company = "Flixbus / FlixMobility",
            location = "München, Bayern",
            salary = "75.000 – 95.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """FlixMobility, Betreiber von FlixBus und FlixTrain, sucht einen Senior iOS Developer.

**Ihre Aufgaben:**
- Weiterentwicklung unserer iOS-App (10M+ Downloads)
- Implementierung neuer Features in Swift und SwiftUI
- Performance-Optimierung und Code-Qualitätssicherung
- Enge Zusammenarbeit mit Backend-Teams und UX-Designern
- Technische Führung innerhalb des mobilen Teams

**Ihr Profil:**
- 4+ Jahre iOS-Entwicklungserfahrung
- Sehr gute Kenntnisse in Swift und iOS SDK
- Erfahrung mit SwiftUI und UIKit
- Kenntnisse in RESTful APIs und JSON
- Erfahrung mit CI/CD (Fastlane, Bitrise)

**Wir bieten:**
- Wettbewerbsfähiges Gehalt
- Deutschlandticket oder Firmenwagen
- Flexible Arbeitszeiten
- Reiserabatte bei FlixBus/FlixTrain""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/flixbus-ios-developer",
            postedDate = "2024-01-11",
            isRemote = false
        ),
        Job(
            id = "linkedin_006",
            title = "Scrum Master / Agile Coach (m/w/d)",
            company = "Volkswagen AG",
            location = "Wolfsburg, Niedersachsen",
            salary = "70.000 – 88.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """Volkswagen AG sucht einen erfahrenen Scrum Master für unsere Software-Entwicklungsabteilung.

**Ihre Aufgaben:**
- Begleitung mehrerer agiler Teams als Scrum Master
- Coaching von Teams und Führungskräften in agilen Methoden
- Organisation und Moderation von agilen Zeremonien
- Identifikation und Beseitigung von Hindernissen
- Weiterentwicklung der agilen Transformation bei VW

**Ihr Profil:**
- Zertifizierung als Scrum Master (CSM, PSM I/II)
- 3+ Jahre Erfahrung als Scrum Master oder Agile Coach
- Kenntnisse in SAFe von Vorteil
- Starke Moderations- und Kommunikationsfähigkeiten
- Erfahrung im Automotive-Umfeld von Vorteil

**Wir bieten:**
- Attraktives Tarifgehalt
- 30 Tage Urlaub
- VW-Mitarbeiterleasing
- Betriebliche Altersvorsorge""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/volkswagen-scrum-master",
            postedDate = "2024-01-10",
            isRemote = false
        ),
        Job(
            id = "linkedin_007",
            title = "Backend Engineer – Golang (Remote)",
            company = "Contentful GmbH",
            location = "Berlin (100% Remote)",
            salary = "80.000 – 100.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """Contentful, die führende Content-Plattform, sucht einen Backend Engineer mit Go-Erfahrung.

**Ihre Aufgaben:**
- Entwicklung hochskalierbarer Microservices in Go
- Design von REST- und GraphQL-APIs
- Datenbankoptimierung (PostgreSQL, DynamoDB)
- Mitgestaltung der technischen Architektur
- On-Call-Bereitschaft (rotierend)

**Ihr Profil:**
- 3+ Jahre Erfahrung in der Backend-Entwicklung
- Sehr gute Kenntnisse in Go (Golang)
- Erfahrung mit AWS-Services
- Kenntnisse in Distributed Systems
- Gute Englischkenntnisse

**Wir bieten:**
- 100% Remote-Arbeit weltweit
- Hohes Gehalt + Aktienoptionen
- Home-Office-Budget (1.500 €)
- Jährliches Team-Retreat""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/contentful-golang-engineer",
            postedDate = "2024-01-09",
            isRemote = true
        ),
        Job(
            id = "linkedin_008",
            title = "Supply Chain Manager (m/w/d)",
            company = "BASF SE",
            location = "Ludwigshafen, Rheinland-Pfalz",
            salary = "75.000 – 95.000 € / Jahr",
            jobType = JobType.FULL_TIME,
            description = """BASF, der weltgrößte Chemiekonzern, sucht einen Supply Chain Manager.

**Ihre Aufgaben:**
- Steuerung und Optimierung globaler Lieferketten
- Lieferantenmanagement und -entwicklung
- Risikomanagement in der Lieferkette
- Implementierung von Supply Chain Projekten
- Reporting und KPI-Tracking

**Ihr Profil:**
- Abgeschlossenes Studium in Logistik, BWL oder Ingenieurswesen
- 5+ Jahre Erfahrung im Supply Chain Management
- Kenntnisse in SAP SCM
- Internationale Erfahrung und Reisebereitschaft
- Sehr gute Englisch- und Deutschkenntnisse

**Wir bieten:**
- Tarifliches Gehalt + Erfolgsbeteiligung
- 30 Tage Urlaub + Sonderurlaub
- Betriebliche Altersvorsorge
- Umfangreiches Gesundheitsprogramm""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/basf-supply-chain-manager",
            postedDate = "2024-01-08",
            isRemote = false
        ),
        Job(
            id = "linkedin_009",
            title = "Fullstack Developer – Node.js / React",
            company = "HelloFresh SE",
            location = "Berlin, Deutschland",
            salary = "70.000 – 90.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """HelloFresh, der weltweit führende Kochboxen-Anbieter, sucht einen Fullstack Developer.

**Ihre Aufgaben:**
- Entwicklung von Web-Applikationen mit React (Frontend) und Node.js (Backend)
- Mitgestaltung von Architekturentscheidungen
- Enge Zusammenarbeit in cross-funktionalen Teams
- Code Reviews und technische Dokumentation
- Mitwirkung an der Optimierung bestehender Systeme

**Ihr Profil:**
- 3+ Jahre Full-Stack-Entwicklungserfahrung
- Sehr gute Kenntnisse in React und Node.js/TypeScript
- Erfahrung mit PostgreSQL und Redis
- Kenntnisse in AWS und containerisierten Anwendungen
- Teamorientierte Arbeitsweise

**Wir bieten:**
- Wettbewerbsfähiges Gehalt
- Wöchentliche HelloFresh-Box
- Hybrides Arbeitsmodell
- Weiterbildungsbudget""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/hellofresh-fullstack-developer",
            postedDate = "2024-01-07",
            isRemote = false
        ),
        Job(
            id = "linkedin_010",
            title = "Senior Product Designer (m/w/d)",
            company = "About You GmbH",
            location = "Hamburg, Deutschland",
            salary = "65.000 – 85.000 € / Jahr",
            jobType = JobType.HYBRID,
            description = """About You, Europas wachstumsstärkste Fashion-Plattform, sucht einen Senior Product Designer.

**Ihre Aufgaben:**
- Gestaltung nutzerzentrierter Erlebnisse für Web und Mobile
- Durchführung von UX-Research und Usability-Tests
- Enge Zusammenarbeit mit Product Managern und Engineers
- Mitentwicklung und Pflege des Design Systems
- Präsentation von Konzepten vor Stakeholdern

**Ihr Profil:**
- 4+ Jahre Erfahrung als Product/UX Designer
- Sehr gute Kenntnisse in Figma
- Portfolio mit relevanten E-Commerce Projekten
- Erfahrung mit quantitativen und qualitativen Research-Methoden
- Fließendes Englisch

**Wir bieten:**
- Kompetitives Gehalt
- About You Mitarbeiterrabatt (25%)
- Urban Sports Club Mitgliedschaft
- Weiterbildungsbudget 1.500 €/Jahr""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/aboutyou-product-designer",
            postedDate = "2024-01-06",
            isRemote = false
        ),
        Job(
            id = "linkedin_011",
            title = "Tax Manager DACH (m/w/d) – Remote",
            company = "Klarna Bank AB",
            location = "Deutschland (Remote)",
            salary = "90.000 – 115.000 € / Jahr",
            jobType = JobType.REMOTE,
            description = """Klarna, Europas wertvollstes FinTech-Unternehmen, sucht einen Tax Manager für die DACH-Region.

**Ihre Aufgaben:**
- Verantwortung für Steuerangelegenheiten in Deutschland, Österreich und der Schweiz
- Erstellung von Steuererklärungen und steuerlichen Meldungen
- Tax Compliance und Risikomanagement
- Beratung der Geschäftsführung in steuerlichen Fragen
- Koordination mit externen Steuerberatern

**Ihr Profil:**
- Abgeschlossenes Studium + Steuerberaterexamen
- 5+ Jahre Erfahrung im Steuerbereich, idealerweise FinTech
- Sehr gute Kenntnisse im deutschen Steuerrecht
- Erfahrung mit internationalen Steuerstrukturen
- Fließendes Deutsch und Englisch

**Wir bieten:**
- Top-Gehalt + Equity
- 100% Remote in Deutschland möglich
- Flexible Arbeitszeiten
- Globales Team""",
            provider = JobProvider.LINKEDIN,
            url = "https://www.linkedin.com/jobs/view/klarna-tax-manager",
            postedDate = "2024-01-05",
            isRemote = true
        )
    )

    override suspend fun searchJobs(filter: JobFilter): List<Job> {
        delay(350)
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

    override fun getProviderName(): String = "LinkedIn"
}
