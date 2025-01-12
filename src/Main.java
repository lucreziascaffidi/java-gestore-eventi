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
            stampaMenuPrincipale();
            int scelta = InputHelper.leggiSceltaMenu("Scegli un'opzione:", 1, 7);
            switch (scelta) {
                case 1 -> aggiungiConcerto(programma);
                case 2 -> visualizzaEventi(programma);
                case 3 -> visualizzaEventiPerData(programma);
                case 4 -> gestisciPrenotazioniDisdette(programma);
                case 5 -> programma.stampaRiepilogoPrenotatiPerTutti();
                case 6 -> svuotaEventi(programma);
                case 7 -> {
                    System.out.println("Grazie per aver utilizzato il Gestore Eventi!");
                    return;
                }
                default -> System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
            }
        }
    }

    // Metodi di supporto
    private static void stampaMenuPrincipale() {
        System.out.println("\n1. Aggiungi Evento");
        System.out.println("2. Visualizza Eventi");
        System.out.println("3. Visualizza Eventi per data");
        System.out.println("4. Gestisci prenotazioni/disdette");
        System.out.println("5. Riepilogo delle tue prenotazioni");
        System.out.println("6. Svuota Eventi");
        System.out.println("7. Esci");
    }

    private static void stampaMenùPrenotazioniDisdette() {
        System.out.println("\n1. Prenota posti");
        System.out.println("2. Disdici posti");
        System.out.println("3. Seleziona un altro evento");
        System.out.println("4. Torna al menu principale");
    }

    private static void aggiungiConcerto(ProgrammaEventi programma) {
        String titolo = InputHelper.leggiTitolo();
        LocalDate data = InputHelper.leggiDataSeparata();
        int postiTotali = InputHelper.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_NUMERO_POSTI);
        LocalTime ora = InputHelper.leggiOra();
        double prezzo = InputHelper.leggiPrezzo();

        Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
        programma.aggiungiEvento(concerto);
        System.out.println(MessaggiCostanti.CONFERMA_AGGIUNTA_EVENTO);
        System.out.println(concerto.toString());
        System.out.println("Posti Prenotati: " + concerto.postiPrenotati);
        System.out.println("Posti Totali: " + concerto.postiTotali);
    }

    private static void visualizzaEventi(ProgrammaEventi programma) {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.NESSUN_EVENTO);
        } else {
            System.out.println(programma.descriviProgramma());
        }
    }

    private static void visualizzaEventiPerData(ProgrammaEventi programma) {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.NESSUN_EVENTO);
        } else {
            LocalDate data = InputHelper.leggiDataSeparata();
            List<Evento> eventi = programma.eventiPerData(data);
            if (eventi.isEmpty()) {
                System.out.println(MessaggiCostanti.ERRORE_NO_EVENTO_PER_DATA);
            } else {
                System.out.println(MessaggiCostanti.CONFERMA_EVENTI_PER_DATA);
                eventi.forEach(System.out::println);
            }
        }

    }

    private static void gestisciPrenotazioniDisdette(ProgrammaEventi programma) {

        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.NESSUN_EVENTO);
            return;
        }

        while (true) {
            Evento evento = selezionaEvento(programma);
            if (evento == null)
                return;

            while (true) {
                stampaMenùPrenotazioniDisdette();

                int scelta = InputHelper.leggiSceltaMenu("Scegli un'opzione:", 1, 4);

                switch (scelta) {
                    case 1 -> gestisciPrenotazioni(evento);
                    case 2 -> gestisciDisdette(evento);
                    case 3 -> {
                        evento = null;
                        break;
                    }
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Opzione non valida. Riprova.");
                }

                if (evento == null) {
                    break;
                }
            }
        }
    }

    private static Evento selezionaEvento(ProgrammaEventi programma) {
        while (true) {
            System.out.println("\nEventi disponibili:");
            System.out.println(programma.descriviProgramma());
            String titolo = InputHelper.leggiTitolo();
            Evento evento = programma.getEventoByTitolo(titolo);

            if (evento != null)
                return evento;
            System.out.println(MessaggiCostanti.ERRORE_EVENTO_NON_TROVATO);
            if (!InputHelper.confermaOperazione(MessaggiCostanti.CONFERMA_RIPROVA))
                return null;
        }
    }

    private static void gestisciPrenotazioni(Evento evento) {
        while (true) {
            int prenotazioni = InputHelper.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_QUANTE_PRENOTAZIONI);
            if (prenotazioni == 0)
                break;

            boolean success = evento.prenota(prenotazioni);

            if (success) {
                double totaleSpeso = evento.calcolaCostoTotale(prenotazioni);
                String messaggioPrenotazioni = prenotazioni == 1
                        ? "1 prenotazione è stata effettuata con successo."
                        : prenotazioni + " prenotazioni sono state effettuate con successo.";
                System.out.printf("%s\nTotale speso: €%.2f\n", messaggioPrenotazioni, totaleSpeso);
            } else {
                System.out.println("Errore: Impossibile prenotare i posti richiesti.");
            }

            if (InputHelper.confermaRitornoAlMenu()) {
                break;
            }
        }
    }

    private static void gestisciDisdette(Evento evento) {
        while (true) {

            if (evento.getPostiPrenotati() == 0) {
                System.out.println(MessaggiCostanti.ERRORE_POSTI_PRENOTATI);
                return;
            }

            int disdette = InputHelper.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_QUANTE_DISDETTE);

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

            evento.stampaRiepilogo();
            if (InputHelper.confermaRitornoAlMenu())
                break;
        }
    }

    private static void svuotaEventi(ProgrammaEventi programma) {
        if (!programma.haEventi()) {
            System.out.println("Non ci sono eventi da rimuovere.");
        } else {
            programma.svuotaEventi();
            System.out.println("Tutti gli eventi sono stati rimossi.");
        }
    }
}