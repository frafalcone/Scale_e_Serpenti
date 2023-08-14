package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public interface TurnoIF {
    public void esegui(Giocatore giocatore);
    public void setAutomatizza(boolean automatizza);
    public boolean getAutomatizza();
    public void setTabellone(TabelloneIF tabellone);
}
