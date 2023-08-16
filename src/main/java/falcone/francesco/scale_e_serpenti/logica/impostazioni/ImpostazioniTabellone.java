package falcone.francesco.scale_e_serpenti.logica.impostazioni;

public class ImpostazioniTabellone {

    private int righe;
    private int colonne;
    private int numeroSerpenti;
    private int numeroScale;
    private int numeroCasellePremio;
    private int numeroCaselleSosta;
    private int numeroCasellePescaUnaCarta;

    public ImpostazioniTabellone() {
        righe = 0;
        colonne = 0;
    }

    public int getNumeroSerpenti() {
        return numeroSerpenti;
    }

    public int getNumeroScale() {
        return numeroScale;
    }

    public int getNumeroCasellePremio() {
        return numeroCasellePremio;
    }

    public int getNumeroCasellePescaUnaCarta() {
        return numeroCasellePescaUnaCarta;
    }

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    public void setRighe(int righe) {
        this.righe = righe;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public ImpostazioniTabellone setNumeroSerpenti(int numeroSerpenti) {
        this.numeroSerpenti = numeroSerpenti;
        return this;
    }

    public void setNumeroCasellePescaUnaCarta(int numeroCasellePescaUnaCarta) {
        this.numeroCasellePescaUnaCarta = numeroCasellePescaUnaCarta;
    }

    public void setNumeroCaselleSosta(int numeroCaselleSosta) {
        this.numeroCaselleSosta = numeroCaselleSosta;
    }

    public void setNumeroCasellePremio(int numeroCasellePremio) {
        this.numeroCasellePremio = numeroCasellePremio;
    }

    public int getNumeroCaselleSosta() {
        return numeroCaselleSosta;
    }

    public void setNumeroScale(int numeroScale) {
        this.numeroScale = numeroScale;
    }

}
