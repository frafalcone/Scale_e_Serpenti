package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public interface CasellaIF {
    public void passaggio(Giocatore giocatore);
    public boolean getAssegnata();
    public int getIndiceCasella();
    public void setAssegnata(boolean assegnata);
    public void setIndiceCasella(int indice);

}
