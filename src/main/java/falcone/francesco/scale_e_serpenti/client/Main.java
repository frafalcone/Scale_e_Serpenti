package falcone.francesco.scale_e_serpenti.client;

import falcone.francesco.scale_e_serpenti.logica.partita.Partita;

public class Main {
    public static void main(String[] args) {
        Partita partita = Partita.getPartita();
        partita.creaPartita();
        partita.gameInitAndLoop();
    }
}
