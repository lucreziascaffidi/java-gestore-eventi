package gestione.gestori;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import gestione.eventi.Concerto;
import gestione.eventi.Evento;
import gestione.eventi.ProgrammaEventi;
import gestione.util.MessaggiCostanti;
import gestione.util.ResponsabileInput;

public class GestoreEventi {

    private ProgrammaEventi programma;

    public GestoreEventi(ProgrammaEventi programma) {
        this.programma = programma;
    }

    public void aggiungiEvento() {
        Concerto concerto = creaConcerto();
        programma.aggiungiEvento(concerto);
        System.out.println(MessaggiCostanti.CONFERMA_AGGIUNTA_EVENTO);
        System.out.println(concerto.toString());
        System.out.println("Posti Prenotati: " + concerto.getPostiPrenotati());
        System.out.println("Posti Totali: " + concerto.getPostiTotali());
    }

    private Concerto creaConcerto() {
        String titolo = ResponsabileInput.leggiTitolo();
        LocalDate data = ResponsabileInput.leggiDataSeparata();
        int postiTotali = ResponsabileInput.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_NUMERO_POSTI);
        LocalTime ora = ResponsabileInput.leggiOra();
        double prezzo = ResponsabileInput.leggiPrezzo();
        return new Concerto(titolo, data, postiTotali, ora, prezzo);
    }

    public void visualizzaEventi() {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.ERRORE_NESSUN_EVENTO);
        } else {
            System.out.println(programma.descriviProgramma());
        }
    }

    public void visualizzaEventiPerData() {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.ERRORE_NESSUN_EVENTO);
        } else {
            LocalDate data = ResponsabileInput.leggiDataSeparata();
            List<Evento> eventi = programma.eventiPerData(data);
            if (eventi.isEmpty()) {
                System.out.println(MessaggiCostanti.ERRORE_NO_EVENTO_PER_DATA);
            } else {
                System.out.println(MessaggiCostanti.ELENCA_EVENTI_PER_DATA);
                eventi.forEach(System.out::println);
            }
        }
    }

    public void gestisciPrenotazioniDisdette() {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.ERRORE_NESSUN_EVENTO);
            return;
        }

        while (true) {
            Evento evento = selezionaEvento();
            if (evento == null)
                return;

            while (true) {
                GestoreMenu.stampaMenuPrenotazioniDisdette();
                int scelta = ResponsabileInput.leggiSceltaMenu(MessaggiCostanti.SCEGLI_OPZIONI, 1, 4);
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
                    default -> System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
                }

                if (evento == null) {
                    break;
                }
            }
        }
    }

    private Evento selezionaEvento() {
        while (true) {
            System.out.println("\nEventi disponibili:");
            System.out.println(programma.descriviProgramma());
            String titolo = ResponsabileInput.leggiTitolo();
            Evento evento = programma.getEventoByTitolo(titolo);

            if (evento != null)
                return evento;

            System.out.println(MessaggiCostanti.ERRORE_EVENTO_NON_TROVATO);
            if (!ResponsabileInput.confermaOperazione(MessaggiCostanti.CONFERMA_RIPROVA))
                return null;
        }
    }

    private void gestisciPrenotazioni(Evento evento) {
        while (true) {
            int prenotazioni = ResponsabileInput.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_QUANTE_PRENOTAZIONI);
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
                System.out.println(MessaggiCostanti.ERRORE_PRENOTAZIONE);
            }

            if (ResponsabileInput.confermaRitornoAlMenu()) {
                break;
            }
        }
    }

    private void gestisciDisdette(Evento evento) {
        while (true) {
            if (evento.getPostiPrenotati() == 0) {
                System.out.println(MessaggiCostanti.ERRORE_POSTI_PRENOTATI);
                return;
            }

            int disdette = ResponsabileInput.leggiInteroNonNegativo(MessaggiCostanti.INSERISCI_QUANTE_DISDETTE);
            if (disdette == 0)
                break;

            boolean success = evento.disdici(disdette);
            if (success) {
                String messaggio = disdette == 1
                        ? "1 disdetta è stata effettuata con successo."
                        : disdette + " disdette sono state effettuate con successo.";
                System.out.println(messaggio);
            } else {
                System.out.println(MessaggiCostanti.ERRORE_DISDETTA);
            }

            evento.stampaRiepilogo();
            if (ResponsabileInput.confermaRitornoAlMenu())
                break;
        }
    }

    public void svuotaEventi() {
        if (!programma.haEventi()) {
            System.out.println(MessaggiCostanti.ERRORE_NESSUN_EVENTO);
        } else {
            programma.svuotaEventi();
            System.out.println(MessaggiCostanti.EVENTI_RIMOSSI);
        }
    }

    public void stampaRiepilogoPrenotatiPerTutti() {
        programma.stampaRiepilogoPrenotatiPerTutti();
    }
}
