import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Benvenuto nel Gestore Eventi!");

        // Creazione di un Concerto
        String titolo = InputHelper.leggiTitolo("Inserisci il titolo del concerto:");
        LocalDate data = InputHelper.leggiDataSeparata();
        int postiTotali = InputHelper.leggiInteroNonNegativo("Inserisci il numero totale di posti:");
        LocalTime ora = InputHelper.leggiOra("Inserisci l'orario del concerto (formato HH:mm):");
        double prezzo = InputHelper.leggiPrezzo("Inserisci il prezzo del biglietto:");

        Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
        System.out.println("Concerto creato con successo: " + concerto);

        // Gestione del menu principale
        gestisciOperazioni(concerto);
    }

    private static void gestisciOperazioni(Concerto concerto) {
        while (true) {
            // Mostra il menu principale
            int scelta = InputHelper.leggiSceltaMenu(
                    "\nCosa vorresti fare?\n1. Prenotazioni\n2. Disdette\n3. Esci",
                    1, 3);

            // Gestione delle opzioni del menu
            switch (scelta) {
                case 1 -> gestisciPrenotazioni(concerto);
                case 2 -> gestisciDisdette(concerto);
                case 3 -> {
                    System.out.println("Grazie per aver utilizzato il Gestore Eventi. Arrivederci!");
                    return;
                }
                default -> System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void gestisciPrenotazioni(Concerto concerto) {
        while (true) {
            // Chiedi il numero di prenotazioni
            int prenotazioni = InputHelper.leggiInteroNonNegativo(
                    "Quante prenotazioni vuoi fare? (Inserisci 0 per tornare al menu)");
            if (prenotazioni == 0) {
                System.out.println("Ritorno al menu principale.");
                break;
            }

            // Effettua la prenotazione
            String messaggio = concerto.prenota(prenotazioni);
            System.out.println(messaggio);

            // Chiedi se continuare con altre prenotazioni
            boolean continua = InputHelper.confermaOperazione("Vuoi effettuare altre prenotazioni?");
            if (!continua) {
                System.out.println("Ritorno al menu principale.");
                break;
            }
        }
    }

    private static void gestisciDisdette(Concerto concerto) {
        while (true) {
            // Chiedi il numero di disdette
            int disdette = InputHelper.leggiInteroNonNegativo(
                    "Quante disdette vuoi fare? (Inserisci 0 per tornare al menu)");
            if (disdette == 0) {
                System.out.println("Ritorno al menu principale.");
                break;
            }

            int postiPrenotati = concerto.getPostiPrenotati();
            if (disdette > postiPrenotati) {
                System.out.println("Errore: Hai prenotato solo " + postiPrenotati + " posti.");
                disdette = InputHelper.leggiInteroNonNegativo(
                        "Quanti di questi vuoi disdire? (Max: " + postiPrenotati + ")");

                // Se l'utente inserisce 0 per tornare al menu
                if (disdette == 0) {
                    System.out.println("Nessuna disdetta effettuata. Ritorno al menu principale.");
                    break;
                }
            }

            // Effettua la disdetta
            String messaggio = concerto.disdici(disdette);
            System.out.println(messaggio);

            // Chiedi se continuare con altre disdette
            boolean continua = InputHelper.confermaOperazione("Vuoi effettuare altre disdette?");
            if (!continua) {
                System.out.println("Ritorno al menu principale.");
                break;
            }
        }
    }
}