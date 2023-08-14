package falcone.francesco.scale_e_serpenti.logica.impostazioni;

public class ImpostazioniTabellone {

    private int righe;
    private int colonne;

    public ImpostazioniTabellone(){
        righe = 0;
        colonne = 0;
    }

    public int getRighe() {return righe;}
    public int getColonne() {return colonne;}

    public void setRighe(int righe) {this.righe = righe;}
    public void setColonne(int colonne) {this.colonne = colonne;}
}
