import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    // Attributi
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    // Costanti per messaggi di errore e di successo
    private static final String ERRORE_DATA_PASSATA = "La data dell'evento risulta passata.";
    private static final String ERRORE_POSTI_NEGATIVI = "Il numero di posti totale non può essere inferiore a 0.";
    private static final String ERRORE_POSTI_FINITI = "Non ci sono posti disponibili.";
    private static final String ERRORE_NESSUNA_PRENOTAZIONE = "Non ci sono prenotazioni da disdire.";
    private static final String MESSAGGIO_PRENOTAZIONE_SUCCESSO = "Prenotazione effettuata con successo.";
    private static final String MESSAGGIO_DISDETTA_SUCCESSO = "Disdetta effettuata con successo.";

    // Costruttore
    public Evento(String titolo, LocalDate data, int postiTotali) {
        validaData(data);
        validaPostiTotali(postiTotali);
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
        validaData(data);
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // Metodi
    private void validaData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(ERRORE_DATA_PASSATA);
        }
    }

    private void validaPostiTotali(int postiTotali) {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException(ERRORE_POSTI_NEGATIVI);
        }
    }

    private boolean isEventoPassato() {
        return data.isBefore(LocalDate.now());
    }

    private boolean hasPostiDisponibili() {
        return postiPrenotati < postiTotali;
    }

    private boolean hasPrenotazioni() {
        return postiPrenotati > 0;
    }

    public String prenota() {
        if (isEventoPassato()) {
            throw new IllegalStateException(ERRORE_DATA_PASSATA);
        }
        if (!hasPostiDisponibili()) {
            throw new IllegalStateException(ERRORE_POSTI_FINITI);
        }
        postiPrenotati++;
        return MESSAGGIO_PRENOTAZIONE_SUCCESSO;
    }

    public String disdici() {
        if (isEventoPassato()) {
            throw new IllegalStateException(ERRORE_DATA_PASSATA);
        }
        if (!hasPrenotazioni()) {
            throw new IllegalStateException(ERRORE_NESSUNA_PRENOTAZIONE);
        }
        postiPrenotati--;
        return MESSAGGIO_DISDETTA_SUCCESSO;
    }

    @Override
    public String toString() {
        return formattaData(data) + " - " + titolo;
    }

    public static String formattaData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
