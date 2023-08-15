package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaIF;

public class Tabellone implements TabelloneIF{

    private int righe;
    private int colonne;
    private int dimensione;

    protected Tabellone(int righe, int colonne){
        this.righe = righe;
        this.colonne = colonne;

        dimensione = righe*colonne;
    }

    @Override
    public CasellaIF getCasella(int posizione) {
        return null;
    }

    @Override
    public int getRighe() {
        return righe;
    }

    public int getColonne(){
        return colonne;
    }

    @Override
    public int getDimensione() {
        return dimensione;
    }
}
