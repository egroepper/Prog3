# Beleg (93)
Checkboxen gemäß Bearbeitung befüllen und _kursiv_ gesetzten Text durch entsprechende Angaben ersetzten.
Bei keiner Angabe wird nur Entwurf, Tests (ohne Testabdeckung Rest), Fehlerfreiheit und Basisfunktionalität bewertet.
Die Zahl in der Klammer sind die jeweiligen Punkte für die Bewertung.
Die empfohlenen Realisierungen sind **fett** gesetzt und ergeben 47 Punkte.
Ergänzende Anmerkungen bitte immer _kursiv_ setzen. Andere Änderungen sind nicht zulässig.

## Vorrausetzungen für das Bestehen
- [x] Quellen angegeben
- [x] zip Archiv mit dem Projekt im root
- [x] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [x] keine weiteren Bibliotheken außer JUnit5, Mockito und JavaFX
- [x] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [x] mindestens sechs modules (zu jeder Basisfunktionalität außer I/O + belegProg3)
- [x] Trennung zwischen Test- und Produktiv-Code
- [x] kompilierbar
- [x] geforderte main-Methoden nur im default package des module belegProg3
- [x] keine vorgetäuschte Funktionalität

## Entwurf (10)
- [x] **Benennung** (2)
- [x] **Zuständigkeit** (2)
- [x] **Paketierung** (2)
- [x] Schichtenaufteilung (2)
- [x] Architekturdiagramm (1)
- [x] keine Duplikate (1)

## Tests (28)
- [x] **Testqualität** (7)
- [x] **Testabdeckung GL (inkl. Abhängigkeiten)** (7) _VerkaufsautomatTest Abdeckung 100 % fuer class, method, line. ObstkuchenImplTest Abdeckung 100 % class, 86 % method, 92 % line_ 
- [ ] Testabdeckung Rest (6)
  - [ ] Einfügen von Kund*innen über das CLI _getestete Klassen angeben_
  - [ ] Anzeigen von Kund*innen über das CLI _getestete Klassen angeben_
  - [ ] ein Beobachter _getestete Klassen angeben_
  - [ ] deterministische Funktionalität der Simulationen _getestete Klassen angeben_
  - [ ] Speichern via JOS oder JBP _getestete Klassen angeben_
  - [ ] Laden via JOS oder JBP _getestete Klassen angeben_
- [ ] **mindestens 5 Unittests, die Mockito verwenden** (4)
- [ ] mindestens 4 Spy- / Verhaltens-Tests (3)
- [x] **keine unbeabsichtigt fehlschlagenden Test** (1)

## Fehlerfreiheit (10)
- [x] **Kapselung** (5)
- [x] **keine Ablauffehler** (5)

## Basisfunktionalität (12)
- [x] **CRUD** (2)
- [x] **CLI** (2)
  * Syntax gemäß Anforderungen
- [x] **Simulation** (2)
  * ohne race conditions
- [x] **GUI** (2)
- [x] **I/O** (2)
  * in CLI oder GUI integriert
- [x] **Net** (2) _in CLI integriert_

## Funktionalität (23)
- [ ] vollständige GL (2)
- [x] threadsichere GL (1)
- [ ] vollständiges CLI (1)
- [x] alternatives CLI (1)
  * _Inspektionsdatum aendern und Kuchen entfernen sind deaktiviert_
- [x] ausdifferenziertes event-System mit mindestens 3 events (2)
- [ ] observer (2)
- [x] angemessene Aufzählungstypen (2)
- [ ] Simulation 2 (1)
- [ ] Simulation 3 (1)
- [ ] skalierbare GUI (1)
- [ ] vollständige GUI (1)
- [ ] data binding verwendet (1)
- [x] Änderung der Fachnummer mittels drag&drop (1)
- [x] Einfügen von Kuchen via GUI erfolgt nebenläufig (1)
- [ ] sowohl JBP als auch JOS (2)
- [ ] sowohl TCP als auch UDP (1)
- [ ] Server unterstützt konkurierende Clients für TCP oder UDP (2)

## zusätzliche Anforderungen (10)
- [x] parallele Ausführung CLI und GUI (2)
- [ ] Änderungen in der GUI direkt sichtbar (4)
- [x] CLI und GUI unabhängig (4)

