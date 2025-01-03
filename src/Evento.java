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

    public String prenota(int numeroPrenotazioni) {
        if (isEventoPassato()) {
            throw new IllegalStateException(ERRORE_DATA_PASSATA);
        }

        if (!hasPostiDisponibili()) {
            throw new IllegalStateException(ERRORE_POSTI_FINITI);
        }

        int postiDisponibili = postiTotali - postiPrenotati;

        if (numeroPrenotazioni > postiDisponibili) {
            return "Posti insufficienti: disponibili solo " + postiDisponibili + " posti.";
        }

        // Effettua le prenotazioni
        postiPrenotati += numeroPrenotazioni;

        return numeroPrenotazioni == 1
                ? "1 prenotazione effettuata con successo."
                : numeroPrenotazioni + " prenotazioni effettuate con successo.";
    }

    // public String prenota(int numeroPrenotazioni) {
    // // Verifica se la data dell'evento è passata
    // if (isEventoPassato()) {
    // throw new IllegalStateException(ERRORE_DATA_PASSATA);
    // }

    // // Verifica se ci sono posti disponibili
    // if (!hasPostiDisponibili()) {
    // throw new IllegalStateException(ERRORE_POSTI_FINITI);
    // }
    // int prenotazioniEffettuate = 0; // Conta le prenotazioni effettuate
    // for (int i = 0; i < numeroPrenotazioni; i++) {
    // if (!hasPostiDisponibili()) {
    // break; // Esce dal ciclo se non ci sono più posti
    // }
    // postiPrenotati++;
    // prenotazioniEffettuate++;
    // }

    // if (prenotazioniEffettuate == 0) {
    // return ERRORE_POSTI_FINITI;
    // }

    // // Costruisce il messaggio in base al numero di prenotazioni effettuate
    // return prenotazioniEffettuate == 1
    // ? "1 prenotazione effettuata con successo."
    // : prenotazioniEffettuate + " prenotazioni effettuate con successo.";
    // }

    public String disdici(int numeroDisdette) {
        // Verifica se la data dell'evento è passata
        if (isEventoPassato()) {
            throw new IllegalStateException(ERRORE_DATA_PASSATA);
        }

        int disdetteEffettuate = 0; // Conta le disdette effettuate
        for (int i = 0; i < numeroDisdette; i++) {
            if (!hasPrenotazioni()) {
                break; // Esce dal ciclo se non ci sono prenotazioni da disdire
            }
            postiPrenotati--;
            disdetteEffettuate++;
        }

        if (disdetteEffettuate == 0) {
            return "Non è stato possibile effettuare disdette.";
        }

        // Costruisce il messaggio in base al numero di disdette effettuate
        return disdetteEffettuate == 1
                ? "1 disdetta effettuata con successo."
                : disdetteEffettuate + " disdette effettuate con successo.";
    }

    // public String disdici(int numeroDisdette) {
    // // Verifica se la data dell'evento è passata
    // if (isEventoPassato()) {
    // throw new IllegalStateException(ERRORE_DATA_PASSATA);
    // }

    // int disdetteEffettuate = 0; // Conta le disdette effettuate
    // for (int i = 0; i < numeroDisdette; i++) {
    // if (!hasPrenotazioni()) {
    // break; // Esce dal ciclo se non ci sono prenotazioni da disdire
    // }
    // postiPrenotati--;
    // disdetteEffettuate++;
    // }

    // if (disdetteEffettuate == 0) {
    // return "Non è stato possibile effettuare disdette.";
    // }

    // Costruisce il messaggio in base al numero di disdette effettuate
    // return disdetteEffettuate==1?"1 disdetta effettuata con
    // successo.":disdetteEffettuate+" disdette effettuate con successo.";

    // }

    @Override
    public String toString() {
        return formattaData(data) + " - " + titolo;
    }

    public static String formattaData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
