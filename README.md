# Gestore Eventi

**Repository**: `java-gestore-eventi`  
**Descrizione**: Un programma Java per la gestione di eventi (concerti, conferenze, spettacoli, etc.) che sfrutta i principi della programmazione orientata agli oggetti (OOP). L'applicazione consente di creare, gestire e visualizzare eventi con funzionalità di prenotazione e disdetta, oltre a estensioni specifiche per eventi come i concerti.

## Obiettivi Didattici

Questo progetto è stato progettato per esercitarsi e padroneggiare i seguenti concetti:

- Creare un programma in Java eseguibile da terminale.
- Saper tipizzare correttamente una variabile.
- Utilizzare strutture dati e relativi metodi.
- Accedere ai valori in lettura e scrittura.
- Iterare su array e liste.
- Creare classi e istanziare oggetti.
- Applicare i principi della programmazione orientata agli oggetti (OOP).

## Requisiti

Per completare il progetto, è necessario conoscere:

- **Fondamenti di Java**
- **Concetti di OOP**:
  - Classi e oggetti
  - Costruttori
  - Attributi
  - Incapsulamento
  - Ereditarietà
  - Composizione
  - Polimorfismo
  - Metodi e attributi statici
  - Interfacce
  - Classi astratte
- **Java Collections**
- **Gestione date**

## Struttura del progetto 

### Step 1: Creazione della Classe Evento
- **Attributi**:
  - `titolo`: titolo dell'evento.
  - `data`: data dell'evento.
  - `postiTotali`: numero totale di posti.
  - `postiPrenotati`: numero di posti prenotati (inizializzato a 0).
- **Metodi**:
  - Getter e setter per `titolo` e `data`.
  - Getter per `postiTotali` e `postiPrenotati`.
  - `prenota`: Aggiunge prenotazioni, verificando la disponibilità e la validità della data.
  - `disdici`: Rimuove prenotazioni, verificando la validità della data.
  - `toString`: Restituisce una stringa formattata con la data e il titolo.

### Step 2: Test con Classe Main
- Permette all'utente di:
  - Inserire i dettagli di un nuovo evento.
  - Effettuare prenotazioni e disdette con controlli adeguati.
  - Stampare il numero di posti prenotati e disponibili.

### Step 3: Creazione della Classe Concerto
- **Estensione della classe `Evento`**:
  - Nuovi attributi:
    - `ora`: orario dell'evento.
    - `prezzo`: prezzo del biglietto.
  - **Metodi aggiuntivi**:
    - Restituzione di data e ora formattate.
    - Restituzione del prezzo formattato (es. `50,00€`).
    - Override del metodo `toString` per includere data, ora, titolo e prezzo.

### Step 4: Classe ProgrammaEventi (Bonus)
- **Attributi**:
  - `titolo`: titolo del programma.
  - `eventi`: lista di eventi.
- **Metodi**:
  - Aggiungere un evento alla lista.
  - Restituire una lista di eventi per una data specifica.
  - Contare il numero di eventi nel programma.
  - Svuotare la lista di eventi.
  - Restituire una stringa con il titolo del programma e gli eventi ordinati per data.

---

## Tecnologie Utilizzate

- **Java SE**: Linguaggio di programmazione principale.
- **Librerie Utilizzate**:
  - `java.time`: Per la gestione di date e orari.
  - `java.util`: Per la gestione delle collezioni (`ArrayList`, `List`).
- **Esecuzione da terminale**.