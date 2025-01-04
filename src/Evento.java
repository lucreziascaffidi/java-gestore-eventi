import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Evento {

    protected String titolo;
    protected LocalDate data;
    protected int postiTotali;
    protected int postiPrenotati;

    private static final String ERRORE_DATA_PASSATA = "La data dell'evento risulta passata.";
    private static final String ERRORE_POSTI_NEGATIVI = "Il numero di posti totale non può essere inferiore a 0.";

    public Evento(String titolo, LocalDate data, int postiTotali) {
        validaData(data);
        validaPostiTotali(postiTotali);
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

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

    protected void validaData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(ERRORE_DATA_PASSATA);
        }
    }

    protected void validaPostiTotali(int postiTotali) {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException(ERRORE_POSTI_NEGATIVI);
        }
    }

    public String prenota(int numeroPrenotazioni) {
        int postiDisponibili = postiTotali - postiPrenotati;

        if (numeroPrenotazioni > postiDisponibili) {
            return "Posti insufficienti: disponibili solo " + postiDisponibili + " posti.";
        }

        postiPrenotati += numeroPrenotazioni;
        return numeroPrenotazioni + " prenotazioni effettuate con successo.";
    }

    public String disdici(int numeroDisdette) {
        int disdetteEffettuate = Math.min(numeroDisdette, postiPrenotati);
        postiPrenotati -= disdetteEffettuate;
        return disdetteEffettuate + " disdette effettuate con successo.";
    }

    public abstract String descriviEvento();

    @Override
    public String toString() {
        return formattaData(data) + " - " + titolo;
    }

    public static String formattaData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return data.format(formatter);
    }
}