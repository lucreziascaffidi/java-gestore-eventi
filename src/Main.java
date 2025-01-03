import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Lettura dati evento
        String titolo = InputHelper.leggiStringa("Inserisci il titolo dell'evento:");
        LocalDate data = InputHelper.leggiData("Inserisci la data dell'evento (formato dd/MM/yyyy):");
        int postiTotali = InputHelper.leggiInteroPositivo("Inserisci il numero totale di posti:");

        // Creazione evento
        Evento evento = new Evento(titolo, data, postiTotali);
        System.out.println("Evento creato: " + evento);

        // Prenotazioni
        try {
            int prenotazioni = InputHelper.leggiInteroPositivo("Quante prenotazioni vuoi fare?");
            for (int i = 0; i < prenotazioni; i++) {
                String messaggio = evento.prenota();
                System.out.println(messaggio);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Disdette
        try {
            int disdette = InputHelper.leggiInteroPositivo("Quante disdette vuoi fare?");
            for (int i = 0; i < disdette; i++) {
                String messaggio = evento.disdici();
                System.out.println(messaggio);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Stato finale
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
    }
}
