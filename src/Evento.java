import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    // Attributi
    private String titolo;
    private LocalDate data;
    private final int postiTotali;
    private int postiPrenotati;

    // Costruttore
    public Evento(String titolo, LocalDate data, int postiTotali) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento risulta passata.");
        }
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totale non può essere inferiore a 0.");
        }
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    // Getter e Setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento risulta passata.");
        }
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // Metodi
    public void prenota() {
        if (data.isBefore(LocalDate.now())) {
            System.out.println("Non è possibile prenotare per un evento già passato.");
            return;
        }
        if (postiPrenotati >= postiTotali) {
            System.out.println("Non ci sono posti disponibili.");
            return;
        }
        postiPrenotati++;
        System.out.println("La tua prenotazione è stata effettuata con successo.");
    }

    public void disdici() {
        if (data.isBefore(LocalDate.now())) {
            System.out.println("Non è possibile disdire un evento già passato.");
            return;
        }
        if (postiPrenotati <= 0) {
            System.out.println("Non ci sono prenotazioni da disdire.");
            return;
        }
        postiPrenotati--;
        System.out.println("La disdetta è stata effettuata con successo.");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo;
    }

}
