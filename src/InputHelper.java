import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class InputHelper {
    // Scanner unico per leggere l'input dell'utente
    private static final Scanner scanner = new Scanner(System.in);

    // Metodo per leggere il titolo (non vuoto)
    public static String leggiStringa(String messaggio) {
        String titolo;
        do {
            System.out.println(messaggio);
            titolo = scanner.nextLine().trim();
            if (titolo.isEmpty() || !titolo.matches(".*[a-zA-Z].*")) {
                System.out.println("Il titolo deve contenere almeno una lettera e non può essere vuoto. Riprova.");
            }
        } while (titolo.isEmpty() || !titolo.matches(".*[a-zA-Z].*"));
        return titolo;
    }

    // Metodo per leggere e validare data (separatamente anno, giorno, e data)
    public static LocalDate leggiDataSeparata() {
        int anno = 0, mese = 0, giorno = 0;

        while (true) {
            try {
                // Chiedi l'anno
                System.out.println("Inserisci l'anno (es. 2025):");
                anno = Integer.parseInt(scanner.nextLine().trim());

                // Chiedi il mese
                System.out.println("Inserisci il mese (1-12):");
                mese = Integer.parseInt(scanner.nextLine().trim());
                if (mese < 1 || mese > 12) {
                    throw new IllegalArgumentException("Il mese deve essere compreso tra 1 e 12.");
                }

                // Chiedi il giorno
                System.out.println("Inserisci il giorno:");
                giorno = Integer.parseInt(scanner.nextLine().trim());

                // Prova a creare la data
                LocalDate data = LocalDate.of(anno, mese, giorno);

                // Controlla se la data è nel passato
                if (data.isBefore(LocalDate.now())) {
                    System.out.println("Errore: La data è nel passato. Inserisci una data futura.");
                } else {
                    return data; // Data valida
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido.");
            } catch (DateTimeException e) {
                System.out.println("Errore: La data inserita non esiste. Riprova.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Metodo per leggere numero intero non negativo
    public static int leggiInteroNonNegativo(String messaggio) {
        int numero;
        while (true) {
            System.out.println(messaggio);
            try {
                numero = Integer.parseInt(scanner.nextLine().trim()); // Usa lo scanner statico globale
                if (numero >= 0) { // Accetta 0 o numeri positivi
                    return numero;
                } else {
                    System.out.println("Il numero deve essere 0 o positivo. Riprova.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido. Riprova.");
            }
        }
    }

    // Metodo per chiudere lo scanner statico (opzionale, ma utile per pulizia)
    public static void chiudiScanner() {
        scanner.close();
    }
}
