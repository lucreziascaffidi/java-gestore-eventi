public class GestoreMenu {

    private GestoreEventi gestoreEventi;

    public GestoreMenu(GestoreEventi gestoreEventi) {
        this.gestoreEventi = gestoreEventi;
    }

    public void gestisciMenuPrincipale() {
        System.out.println("Benvenuto nel Gestore Eventi!");
        while (true) {
            stampaMenuPrincipale();
            int scelta = GestoreInput.leggiSceltaMenu(MessaggiCostanti.SCEGLI_OPZIONI, 1, 7);
            switch (scelta) {
                case 1 -> gestoreEventi.aggiungiEvento();
                case 2 -> gestoreEventi.visualizzaEventi();
                case 3 -> gestoreEventi.visualizzaEventiPerData();
                case 4 -> gestoreEventi.gestisciPrenotazioniDisdette();
                case 5 -> gestoreEventi.stampaRiepilogoPrenotatiPerTutti();
                case 6 -> gestoreEventi.svuotaEventi();
                case 7 -> {
                    System.out.println("Grazie per aver utilizzato il Gestore Eventi!");
                    return;
                }
                default -> System.out.println(MessaggiCostanti.ERRORE_INPUT_NUMERO);
            }
        }
    }

    private void stampaMenuPrincipale() {
        System.out.println("\n1. Aggiungi Evento");
        System.out.println("2. Visualizza Eventi");
        System.out.println("3. Visualizza Eventi per data");
        System.out.println("4. Gestisci prenotazioni/disdette");
        System.out.println("5. Riepilogo delle tue prenotazioni");
        System.out.println("6. Svuota Eventi");
        System.out.println("7. Esci");
    }

    public static void stampaMenuPrenotazioniDisdette() {
        System.out.println("\n1. Prenota posti");
        System.out.println("2. Disdici posti");
        System.out.println("3. Seleziona un altro evento");
        System.out.println("4. Torna al menu principale");
    }
}
