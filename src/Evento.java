import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Classe astratta Evento che rappresenta un evento generico
public abstract class Evento {

    // Attributi comuni a tutti gli eventi
    protected String titolo;
    protected LocalDate data;
    protected int postiTotali;
    protected int postiPrenotati;

    // Costruttore con validazioni
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
    protected void validaData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(MessaggiCostanti.ERRORE_EVENTO_PASSATO);
        }
    }

    protected void validaPostiTotali(int postiTotali) {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException(MessaggiCostanti.ERRORE_NUMERO_NEGATIVO);
        }
    }

    public boolean prenota(int numeroPrenotazioni) {
        if (numeroPrenotazioni <= 0 || postiPrenotati + numeroPrenotazioni > postiTotali) {
            return false;
        }
        postiPrenotati += numeroPrenotazioni;
        return true;
    }

    public boolean disdici(int numeroDisdette) {
        if (numeroDisdette <= 0 || numeroDisdette > postiPrenotati) {
            return false;
        }
        postiPrenotati -= numeroDisdette;
        return true;
    }

    public abstract String descriviEvento(); // Ogni sottoclasse deve fornire una descrizione specifica dell'evento

    @Override
    public String toString() {
        return String.format("%s - %s", formattaData(data), titolo);
    }

    public static String formattaData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return data.format(formatter);
    }

    public abstract double getPrezzoPerPosto();

    public double calcolaCostoTotale(int numeroPrenotazioni) {
        return numeroPrenotazioni * getPrezzoPerPosto();
    }

    public void stampaRiepilogo() {
        System.out.println("Posti prenotati: " + postiPrenotati);
        System.out.println("Posti disponibili: " + (postiTotali - postiPrenotati));
    }
}