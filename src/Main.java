import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
        public static void main(String[] args) {

                ProgrammaEventi programma = inizializzaProgrammaEventi();
                GestoreEventi gestoreEventi = new GestoreEventi(programma);
                GestoreMenu gestoreMenu = new GestoreMenu(gestoreEventi);
                gestoreMenu.gestisciMenuPrincipale();

        }

        // Metodi di supporto
        private static ProgrammaEventi inizializzaProgrammaEventi() {
                ProgrammaEventi programma = new ProgrammaEventi("Programma Eventi");

                programma.aggiungiEvento(
                                new Concerto("Arctic Monkeys", LocalDate.of(2025, 07, 16), 15000, LocalTime.of(21, 30),
                                                40.99));
                programma.aggiungiEvento(
                                new Concerto("Pearl Jam", LocalDate.of(2025, 07, 16), 12000, LocalTime.of(20, 45),
                                                63.50));
                programma.aggiungiEvento(
                                new Concerto("Guns N' Roses", LocalDate.of(2026, 5, 23), 30000, LocalTime.of(22, 00),
                                                56.70));
                programma.aggiungiEvento(
                                new Concerto("Aerosmith", LocalDate.of(2025, 5, 20), 50000, LocalTime.of(21, 45),
                                                76.80));
                return programma;
        }
}