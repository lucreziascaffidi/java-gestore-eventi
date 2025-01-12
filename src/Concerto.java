import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Classe Concerto, che estende la classe astratta Evento
public class Concerto extends Evento {

    // Attributi specifici
    private LocalTime ora;
    private double prezzo;

    // Costruttore
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzo) {
        super(titolo, data, postiTotali);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    // Getter e Setter per gli attributi specifici
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    // Metodi
    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return data.format(dateFormatter) + " " + ora.format(timeFormatter);
    }

    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

    @Override // Implementazione del metodo astratto della classe base Evento
    public String descriviEvento() {
        return "Concerto: " + titolo + " alle " + ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + titolo + " - " + getPrezzoFormattato();
    }

    @Override
    public double getPrezzoPerPosto() {
        return prezzo;
    }

}