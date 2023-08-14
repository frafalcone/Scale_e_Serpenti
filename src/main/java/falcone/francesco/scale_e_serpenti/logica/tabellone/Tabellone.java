package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaIF;

public class Tabellone implements TabelloneIF{

    private int righe;
    private int colonne;

    protected Tabellone(int righe, int colonne){
        this.righe = righe;
        this.colonne = colonne;
    }

    @Override
    public CasellaIF getCasella(int riga, int colonna) {
        return null;
    }
}
