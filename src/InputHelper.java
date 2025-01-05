import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.util.Scanner;

// Classe utility per leggere e validare l'input dell'utente
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    // Metodi
    public static String leggiTitolo(String messaggio) {
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

    public static LocalDate leggiDataSeparata() {
        int anno = 0, mese = 0, giorno = 0;

        while (true) {
            try {
                while (true) {
                    System.out.println("Inserisci l'anno (es. 2025):");
                    anno = Integer.parseInt(scanner.nextLine().trim());
                    if (anno >= LocalDate.now().getYear())
                        break;
                    System.out.println("Errore: L'anno deve essere presente o futuro.");
                }

                while (true) {
                    System.out.println("Inserisci il mese (1-12):");
                    mese = Integer.parseInt(scanner.nextLine().trim());
                    if (mese >= 1 && mese <= 12)
                        break;
                    System.out.println("Errore: Il mese deve essere compreso tra 1 e 12.");
                }

                while (true) {
                    System.out.println("Inserisci il giorno:");
                    giorno = Integer.parseInt(scanner.nextLine().trim());
                    if (giorno >= 1 && giorno <= 31)
                        break;
                    System.out.println("Errore: Il giorno deve essere compreso tra 1 e 31.");
                }

                LocalDate data = LocalDate.of(anno, mese, giorno);
                if (data.isBefore(LocalDate.now())) {
                    System.out.println("Errore: La data è nel passato. Inserisci una data futura.");
                } else {
                    return data;
                }
            } catch (NumberFormatException | DateTimeException e) {
                System.out.println("Errore: La data inserita non esiste. Riprova.");
            }
        }
    }

    public static LocalTime leggiOra(String messaggio) {
        while (true) {
            System.out.println(messaggio);
            try {
                return LocalTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Errore: Formato orario non valido. Usa il formato HH:mm.");
            }
        }
    }

    public static double leggiPrezzo(String messaggio) {
        while (true) {
            System.out.println(messaggio);
            try {
                double prezzo = Double.parseDouble(scanner.nextLine().trim());
                if (prezzo > 0) {
                    return prezzo;
                } else {
                    System.out.println("Errore: Il prezzo deve essere maggiore di 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido per il prezzo.");
            }
        }
    }

    public static int leggiInteroNonNegativo(String messaggio) {
        while (true) {
            System.out.println(messaggio);
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero >= 0) {
                    return numero;
                } else {
                    System.out.println("Errore: Il numero deve essere 0 o positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido.");
            }
        }
    }

    public static int leggiSceltaMenu(String messaggio, int min, int max) {
        while (true) {
            System.out.println(messaggio);
            try {
                int scelta = Integer.parseInt(scanner.nextLine().trim());
                if (scelta >= min && scelta <= max) {
                    return scelta;
                } else {
                    System.out.println("Errore: Inserisci un numero tra " + min + " e " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido.");
            }
        }
    }

    public static boolean confermaOperazione(String messaggio) {
        while (true) {
            System.out.println(messaggio + " (S/N)");
            String risposta = scanner.nextLine().trim().toUpperCase();
            if (risposta.equals("S")) {
                return true;
            } else if (risposta.equals("N")) {
                return false;
            } else {
                System.out.println("Errore: Rispondi con 'S' per sì o 'N' per no.");
            }
        }
    }

    public static boolean confermaRitornoAlMenu() {
        return !confermaOperazione("Vuoi continuare con questa operazione?");
    }

}