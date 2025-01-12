package gestione.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.util.Scanner;

// Classe utility per leggere e validare l'input dell'utente
public class ResponsabileInput {
    private static final Scanner scanner = new Scanner(System.in);

    // Metodi
    public static String leggiTitolo() {
        String titolo;
        do {
            System.out.println(MessaggiCostanti.INSERISCI_TITOLO);
            titolo = scanner.nextLine().trim();
            if (titolo.isEmpty() || !titolo.matches(".*[a-zA-Z].*")) {
                System.out.println(MessaggiCostanti.ERRORE_TITOLO_INCORRETTO);
            }
        } while (titolo.isEmpty() || !titolo.matches(".*[a-zA-Z].*"));
        return titolo;
    }

    public static LocalDate leggiDataSeparata() {
        int anno = 0, mese = 0, giorno = 0;

        while (true) {
            try {
                while (true) {
                    System.out.println(MessaggiCostanti.INSERISCI_ANNO);
                    anno = Integer.parseInt(scanner.nextLine().trim());
                    if (anno >= LocalDate.now().getYear())
                        break;
                    System.out.println(MessaggiCostanti.ERRORE_ANNO_INCORRETTO);
                }

                while (true) {
                    System.out.println(MessaggiCostanti.INSERISCI_MESE);
                    mese = Integer.parseInt(scanner.nextLine().trim());
                    if (mese >= 1 && mese <= 12)
                        break;
                    System.out.println(MessaggiCostanti.ERRORE_MESE_INCORRETTO);
                }

                while (true) {
                    System.out.println(MessaggiCostanti.INSERISCI_GIORNO);
                    giorno = Integer.parseInt(scanner.nextLine().trim());
                    if (giorno >= 1 && giorno <= 31)
                        break;
                    System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
                }

                LocalDate data = LocalDate.of(anno, mese, giorno);
                if (data.isBefore(LocalDate.now())) {
                    System.out.println(MessaggiCostanti.ERRORE_EVENTO_PASSATO);
                } else {
                    return data;
                }

            } catch (NumberFormatException | DateTimeException e) {
                System.out.println(MessaggiCostanti.ERRORE_DATA_PASSATA);
            }
        }

    }

    public static LocalTime leggiOra() {
        while (true) {
            System.out.println(MessaggiCostanti.INSERISCI_ORARIO);
            try {
                return LocalTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println(MessaggiCostanti.ERRORE_ORARIO_INCORRETTO);
            }
        }
    }

    public static double leggiPrezzo() {
        while (true) {
            System.out.println(MessaggiCostanti.INSERISCI_PREZZO);
            try {
                double prezzo = Double.parseDouble(scanner.nextLine().trim());
                if (prezzo > 0) {
                    return prezzo;
                } else {
                    System.out.println(MessaggiCostanti.ERRORE_NUMERO_NEGATIVO);
                }
            } catch (NumberFormatException e) {
                System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
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
                    System.out.println(MessaggiCostanti.ERRORE_NUMERO_NEGATIVO);
                }
            } catch (NumberFormatException e) {
                System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
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
                    System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
                }
            } catch (NumberFormatException e) {
                System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
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
                System.out.println(MessaggiCostanti.ERRORE_RISPOSTA_INCORRETTA);
            }
        }
    }

    public static boolean confermaRitornoAlMenu() {
        return !confermaOperazione(MessaggiCostanti.CONFERMA_CONTINUA_OPERAZIONE);
    }

}