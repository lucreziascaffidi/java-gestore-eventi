import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Nuovo programma eventi
        ProgrammaEventi programma = new ProgrammaEventi("Programma Eventi");

        // Popola il programma con eventi predefiniti
        programma.aggiungiEvento(
                new Concerto("Arctic Monkeys", LocalDate.of(2025, 07, 16), 15000, LocalTime.of(21, 30), 40.99));
        programma.aggiungiEvento(
                new Concerto("Pearl Jam", LocalDate.of(2025, 07, 16), 12000, LocalTime.of(20, 45), 63.50));
        programma.aggiungiEvento(
                new Concerto("Guns N' Roses", LocalDate.of(2026, 5, 23), 30000, LocalTime.of(22, 00), 56.70));
        programma.aggiungiEvento(
                new Concerto("Aerosmith", LocalDate.of(2025, 5, 20), 50000, LocalTime.of(21, 45), 76.80));

        // Menù principale
        System.out.println("Benvenuto nel Gestore Eventi!");
        while (true) {
            System.out.println("\n1. Aggiungi Evento");
            System.out.println("2. Visualizza Eventi");
            System.out.println("3. Visualizza Eventi per data");
            System.out.println("4. Gestisci prenotazioni/disdette");
            System.out.println("5. Svuota Eventi");
            System.out.println("6. Esci");

            int scelta = InputHelper.leggiSceltaMenu("Scegli un'opzione:", 1, 6);
            switch (scelta) {
                case 1 -> aggiungiConcerto(programma);
                case 2 -> {
                    if (programma.numeroEventi() == 0) {
                        System.out.println("Ci dispiace. Non sono presenti ancora eventi nel programma.");
                    } else {
                        System.out.println(programma.descriviProgramma());
                    }
                }
                case 3 -> visualizzaEventiPerData(programma);
                case 4 -> {
                    if (programma.numeroEventi() == 0) {
                        System.out.println("Ci dispiace. Non sono presenti ancora eventi nel programma.");
                    } else {
                        gestisciPrenotazioniDisdette(programma);
                    }
                }

                case 5 -> {
                    programma.svuotaEventi();
                    System.out.println("Tutti gli eventi sono stati rimossi.");
                }
                case 6 -> {
                    System.out.println("Grazie per aver utilizzato il Gestore Eventi!");
                    return;
                }
                default -> System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    // Metodi di supporto
    private static void aggiungiConcerto(ProgrammaEventi programma) {
        String titolo = InputHelper.leggiTitolo("Inserisci il titolo dell'evento:");
        LocalDate data = InputHelper.leggiDataSeparata();
        int postiTotali = InputHelper.leggiInteroNonNegativo("Inserisci il numero totale di posti:");
        LocalTime ora = InputHelper.leggiOra("Inserisci l'orario dell'evento (HH:mm):");
        double prezzo = InputHelper.leggiPrezzo("Inserisci il prezzo del biglietto:");
        Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
        programma.aggiungiEvento(concerto);
        System.out.println("Concerto aggiunto con successo!");
        System.out.println(concerto.toString());
        System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
        System.out.println("Posti totali: " + concerto.getPostiTotali());

    }

    private static void visualizzaEventiPerData(ProgrammaEventi programma) {
        LocalDate data = InputHelper.leggiDataSeparata();
        List<Evento> eventi = programma.eventiPerData(data);
        if (eventi.isEmpty()) {
            System.out.println("Nessun evento trovato per questa data.");
        } else {
            System.out.println("Ecco l'elenco degli eventi presenti nella data selezionata:");
            eventi.forEach(System.out::println);
        }
    }

    private static void gestisciPrenotazioniDisdette(ProgrammaEventi programma) {
        if (programma.numeroEventi() == 0) {
            System.out.println("Ci dispiace. Non sono presenti ancora eventi nel programma.");
            return;
        }

        Evento evento = null;

        // Chiede un titolo valido o offre la possibilità di tornare al menu
        while (evento == null) {
            System.out.println("\nEventi disponibili:");
            System.out.println(programma.descriviProgramma());

            String titolo = InputHelper.leggiTitolo("Inserisci il titolo dell'evento su cui operare:");
            evento = programma.getEventoByTitolo(titolo);

            if (evento == null) {
                System.out.println("Errore: Evento non trovato.");
                boolean riprova = InputHelper.confermaOperazione("Vuoi riprovare? (S/N)");
                if (!riprova) {
                    return; // Torna al menu principale
                }
            }
        }

        // Gestisce prenotazioni o disdette per l'evento trovato
        while (true) {
            stampaRiepilogoPosti(evento);
            System.out.println("\n1. Prenota posti");
            System.out.println("2. Disdici posti");
            System.out.println("3. Torna al menu principale");

            int scelta = InputHelper.leggiSceltaMenu("Scegli un'opzione:", 1, 3);
            switch (scelta) {
                case 1 -> gestisciPrenotazioni(evento);
                case 2 -> gestisciDisdette(evento);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    private static void gestisciPrenotazioni(Evento evento) {
        while (true) {
            int prenotazioni = InputHelper.leggiInteroNonNegativo(
                    "Quante prenotazioni vuoi fare? (Inserisci 0 per tornare al menu)");
            if (prenotazioni == 0)
                break;

            boolean success = evento.prenota(prenotazioni);

            if (success) {
                String messaggio = prenotazioni == 1
                        ? "1 prenotazione è stata effettuata con successo."
                        : prenotazioni + " prenotazioni sono state effettuate con successo.";
                System.out.println(messaggio);
            } else {
                System.out.println("Errore: Impossibile prenotare i posti richiesti.");
            }

            stampaRiepilogoPosti(evento);

            if (InputHelper.confermaRitornoAlMenu()) {
                break;
            }
        }
    }

    private static void gestisciDisdette(Evento evento) {
        while (true) {
            int disdette = InputHelper.leggiInteroNonNegativo(
                    "Quante disdette vuoi fare? (Inserisci 0 per tornare al menu)");

            if (disdette == 0)
                break;

            int postiPrenotati = evento.getPostiPrenotati();
            if (disdette > postiPrenotati) {
                System.out.println("Errore: Hai prenotato solo " + postiPrenotati + " posti.");
                disdette = InputHelper.leggiInteroNonNegativo(
                        "Quanti di questi vuoi disdire? (Max: " + postiPrenotati + ")");
                if (disdette == 0)
                    break;
            }

            boolean success = evento.disdici(disdette);

            if (success) {
                String messaggio = disdette == 1
                        ? "1 disdetta è stata effettuata con successo."
                        : disdette + " disdette sono state effettuate con successo.";
                System.out.println(messaggio);
            } else {
                System.out.println("Errore: Impossibile disdire i posti richiesti.");
            }

            stampaRiepilogoPosti(evento);

            if (InputHelper.confermaRitornoAlMenu())
                break;
        }
    }

    private static void stampaRiepilogoPosti(Evento evento) {
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() -
                evento.getPostiPrenotati()));
    }
}
