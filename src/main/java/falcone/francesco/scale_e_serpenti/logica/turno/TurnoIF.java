package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public interface TurnoIF {
    String esegui(Giocatore giocatore);
    void setAutomatizza(boolean automatizza);
    boolean getAutomatizza();
    void setTabellone(TabelloneIF tabellone);
}
