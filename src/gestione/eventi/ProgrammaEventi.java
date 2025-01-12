package gestione.eventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gestione.util.MessaggiCostanti;

// Classe per gestire una collezione di eventi
public class ProgrammaEventi {

    // Attributi
    private String titolo;
    private List<Evento> eventi;

    // Costruttore
    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    // Metodi
    public boolean haEventi() {
        return !eventi.isEmpty();
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> eventiPerData(LocalDate data) {
        return eventi.stream()
                .filter(evento -> evento.getData().equals(data))
                .collect(Collectors.toList());
    }

    public int numeroEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        eventi.clear();
    }

    public String descriviProgramma() {
        StringBuilder sb = new StringBuilder(titolo + ":\n");
        eventi.stream()
                .sorted((e1, e2) -> e1.getData().compareTo(e2.getData()))
                .forEach(evento -> sb.append(evento.toString()).append("\n"));
        return sb.toString();
    }

    public Evento getEventoByTitolo(String titolo) {
        for (Evento evento : eventi) {
            if (evento.getTitolo().equalsIgnoreCase(titolo)) {
                return evento;
            }
        }
        return null;
    }

    public void stampaRiepilogoPrenotatiPerTutti() {
        boolean ciSonoPrenotazioni = eventi.stream().anyMatch(evento -> evento.getPostiPrenotati() > 0);

        if (!ciSonoPrenotazioni) {
            System.out.println(MessaggiCostanti.ERRORE_POSTI_PRENOTATI);
        } else {
            System.out.println("\nRiepilogo posti prenotati:");
            for (Evento evento : eventi) {
                if (evento.getPostiPrenotati() > 0) {
                    double totaleSpeso = evento.calcolaCostoTotale(evento.getPostiPrenotati());
                    System.out.printf("%s:\n  Posti prenotati: %d\n  Totale speso: €%.2f\n",
                            evento.getTitolo(), evento.getPostiPrenotati(), totaleSpeso);
                }
            }
        }
    }
}
