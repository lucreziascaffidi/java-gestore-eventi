import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {
    // Scanner unico per leggere l'input dell'utente
    private static final Scanner scanner = new Scanner(System.in);

    // Metodo per leggere il titolo (non vuoto)
    public static String leggiStringa(String messaggio) {
        String input;
        do {
            System.out.println(messaggio);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("L'input non può essere vuoto. Riprova.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Metodo per leggere la data (valida)
    public static LocalDate leggiData(String messaggio) {
        LocalDate data;
        while (true) {
            System.out.println(messaggio);
            String input = scanner.nextLine().trim();
            try {
                // Prova a parsare la data
                data = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // Controlla se la data è nel passato
                if (data.isBefore(LocalDate.now())) {
                    System.out.println("La data deve essere presente o futura. Riprova.");
                } else {
                    return data; // Ritorna la data valida
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido. Usa il formato dd/MM/yyyy.");
            }
        }
    }

    // Metodo per leggere numero intero positivo
    public static int leggiInteroPositivo(String messaggio) {
        int numero;
        while (true) {
            System.out.println(messaggio);
            try {
                numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero > 0) {
                    return numero;
                } else {
                    System.out.println("Il numero deve essere positivo. Riprova.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido. Riprova.");
            }
        }
    }
}
