import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammaEventi {

    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
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
}