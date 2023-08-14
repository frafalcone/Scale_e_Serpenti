package falcone.francesco.scale_e_serpenti.logica.giocatore;

public class Giocatore {
    private int posizioneX;
    private int posizioneY;
    private int posizione;
    private int attesa;
    private boolean possiedeDivietoSosta;
    private boolean rigioca;

    public Giocatore() {
        posizioneX = 0;
        posizioneY = 0;
        posizione = 0;
        attesa = 0;
        possiedeDivietoSosta = false;
        rigioca = false;
    }


    public int getPosizione() {
        return posizione;
    }

    public int getPosizioneX() {
        return posizioneX;
    }

    public int getPosizioneY() {
        return posizioneY;
    }

    public int getAttesa() {
        return attesa;
    }

    public boolean getPossiedeDivietoSosta() {
        return possiedeDivietoSosta;
    }

    public boolean getRigioca() {
        return rigioca;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public void setPosizioneX(int posizioneX) {
        this.posizioneY = posizioneX;
    }

    public void setPosizioneY(int posizioneY) {
        this.posizioneY = posizioneY;
    }

    public void setAttesa(int turni) {
        this.attesa = turni;
    }

    public void setPossiedeDivietoSosta(boolean possiede) {
        this.possiedeDivietoSosta = possiede;
    }

    public void setRigioca(boolean rigioca) {this.rigioca = rigioca;}
}