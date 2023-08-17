package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaIF;

public interface TabelloneIF {

    CasellaIF getCasella(int posizione);
    int getRighe();
    int getColonne();
    int getDimensione();
    void setCasella(CasellaIF casella);
}
