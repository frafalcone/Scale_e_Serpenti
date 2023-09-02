package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.Casella;
import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaIF;

public class Tabellone implements TabelloneIF{

    private int righe;
    private int colonne;
    private int dimensione;
    private CasellaIF[][] tabella;

    protected Tabellone(int righe, int colonne){
        this.righe = righe;
        this.colonne = colonne;

        this.tabella = new CasellaIF[righe][colonne];
        int index = 1;

        for (int i=0; i<righe; i++){
            for (int j=0; j<colonne; j++){
                tabella[i][j]=new Casella();
                tabella[i][j].setIndiceCasella(index);
                tabella[i][j].setAssegnata(false);

                index++;
            }
        }

        dimensione = righe*colonne;
    }

    @Override
    public CasellaIF getCasella(int posizione) {
        CasellaIF res = null;
        if(posizione<=dimensione){
            for (int i=0; i<righe; i++){
                for (int j=0; j<colonne; j++){
                    if(tabella[i][j].getIndiceCasella()==posizione)
                        res = tabella[i][j];
                }
            }
        } else {
            throw new IllegalArgumentException("Casella non esistente");
        }
        return res;
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

    public void setCasella(CasellaIF casella){
        for (int i=0; i<righe; i++){
            for (int j=0; j<colonne; j++){
                if(tabella[i][j].getIndiceCasella()==casella.getIndiceCasella())
                    tabella[i][j] = casella;
            }
        }
    }



}
