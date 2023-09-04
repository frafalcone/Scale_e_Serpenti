package falcone.francesco.scale_e_serpenti.logica.giocatore;

public class Giocatore {
    private int posizione;
    private int attesa;
    private boolean possiedeDivietoSosta;
    private boolean rigioca;
    private boolean haVinto;
    private boolean molla;
    private boolean scala;
    private boolean serpente;
    private boolean usatoDivietoSosta;

    public Giocatore() {
        posizione = 0;
        attesa = 0;
        possiedeDivietoSosta = false;
        usatoDivietoSosta = false;
        rigioca = false;
        haVinto = false;
        serpente = false;
        scala = false;
    }


    public int getPosizione() {
        return posizione;
    }

    public int getAttesa() {
        return attesa;
    }

    public boolean getPossiedeDivietoSosta() {
        return possiedeDivietoSosta;
    }

    public boolean getMolla() {
        return molla;
    }

    public boolean getRigioca() {
        return rigioca;
    }

    public boolean getSerpente() {
        return serpente;
    }

    public boolean getScala() {
        return scala;
    }

    public boolean getHaVinto() {
        return haVinto;
    }

    public boolean getUsatoDivietoSosta() {
        return usatoDivietoSosta;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public void setScala(boolean scala) {
        this.scala = scala;
    }

    public void setSerpente(boolean serpente) {
        this.serpente = serpente;
    }

    public void setAttesa(int turni) {
        this.attesa = turni;
    }

    public void setPossiedeDivietoSosta(boolean possiede) {
        this.possiedeDivietoSosta = possiede;
    }

    public void setRigioca(boolean rigioca) {
        this.rigioca = rigioca;
    }

    public void setHaVinto(boolean haVinto) {
        this.haVinto = haVinto;
    }

    public void setMolla(boolean molla) {
        this.molla = molla;
    }

    public void setUsatoDivietoSosta(boolean usato) {
        this.usatoDivietoSosta = usato;
    }
}