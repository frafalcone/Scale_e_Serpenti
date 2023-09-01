package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public interface CasellaIF {
    String passaggio(Giocatore giocatore);
    boolean getAssegnata();
    int getIndiceCasella();
    void setAssegnata(boolean assegnata);
    void setIndiceCasella(int indice);

}
