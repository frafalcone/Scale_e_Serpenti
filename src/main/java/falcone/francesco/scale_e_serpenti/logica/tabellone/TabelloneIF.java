package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaIF;

public interface TabelloneIF {

    public CasellaIF getCasella(int posizione);
    public int getRighe();
    public int getColonne();
    public int getDimensione();
}
