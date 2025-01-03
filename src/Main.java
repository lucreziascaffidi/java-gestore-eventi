import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Lettura dati evento
        String titolo = InputHelper.leggiStringa("Inserisci il titolo dell'evento:");
        LocalDate data = InputHelper.leggiDataSeparata();
        int postiTotali = InputHelper.leggiInteroNonNegativo("Inserisci il numero totale di posti:");

        // Creazione evento
        Evento evento = new Evento(titolo, data, postiTotali);
        System.out.println("Evento creato: " + evento);

        // Prenotazioni
        while (true) {
            int prenotazioni = InputHelper
                    .leggiInteroNonNegativo("Quante prenotazioni vuoi fare? (Inserisci 0 per uscire)");
            if (prenotazioni == 0) {
                System.out.println("Operazione annullata. Nessuna prenotazione effettuata.");
                break;
            }

            String messaggio = evento.prenota(prenotazioni);
            if (messaggio.startsWith("Posti insufficienti")) {
                System.out.println(messaggio);

                // Chiede all'utente cosa fare
                int postiDisponibili = evento.getPostiTotali() - evento.getPostiPrenotati();
                System.out.println("Vuoi prenotare i posti disponibili (" + postiDisponibili + ")? (S/N)");
                String risposta = InputHelper.leggiStringa("Risposta (S per sì, N per no):");

                if (risposta.equalsIgnoreCase("S")) {
                    String messaggioConferma = evento.prenota(postiDisponibili);
                    System.out.println(messaggioConferma);
                } else {
                    System.out.println("Operazione annullata. Nessuna prenotazione effettuata.");
                }
            } else {
                System.out.println(messaggio); // Messaggio di successo
                break;
            }
        }

        // Disdette
        while (true) {
            int disdette = InputHelper.leggiInteroNonNegativo("Quante disdette vuoi fare? (Inserisci 0 per uscire)");
            if (disdette == 0) {
                System.out.println("Operazione annullata. Nessuna disdetta effettuata.");
                break;
            }

            int postiPrenotati = evento.getPostiPrenotati();
            if (disdette > postiPrenotati) {
                System.out.println("Il numero di disdette richieste supera le prenotazioni attuali.");
                System.out.println("Prenotazioni attuali: " + postiPrenotati);

                // Chiede all'utente cosa fare
                System.out.println("Vuoi disdire tutte le prenotazioni attuali (" + postiPrenotati + ")? (S/N)");
                String risposta = InputHelper.leggiStringa("Risposta (S per sì, N per no):");

                if (risposta.equalsIgnoreCase("S")) {
                    String messaggioConferma = evento.disdici(postiPrenotati);
                    System.out.println(messaggioConferma);
                } else {
                    System.out.println("Operazione annullata. Nessuna disdetta effettuata.");
                }
            } else {
                String messaggio = evento.disdici(disdette);
                System.out.println(messaggio); // Messaggio di successo
                break;
            }
        }

        // Stato finale
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
    }
}
