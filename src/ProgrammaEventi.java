import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.println("Non hai ancora effettuato prenotazioni");
        } else {
            System.out.println("\n Riepilogo posti prenotati per tutti gli eventi");
            for (Evento evento : eventi) {
                System.out.println(evento.getTitolo() + ":");
                System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
            }
        }
    }
}