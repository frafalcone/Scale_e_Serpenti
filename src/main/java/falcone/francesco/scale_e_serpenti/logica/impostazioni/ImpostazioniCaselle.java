package falcone.francesco.scale_e_serpenti.logica.impostazioni;

public class ImpostazioniCaselle {

    private boolean casellaSosta;
    private boolean casellaPremio;
    private boolean casellaPescaUnaCarta;
    private boolean ulterioriCarte;

    public ImpostazioniCaselle() {
        casellaSosta = false;
        casellaPremio = false;
        casellaPescaUnaCarta = false;
        ulterioriCarte = false;
    }

    public boolean getCasellaSosta() {
        return casellaSosta;
    }

    public boolean getCasellaPremio() {
        return casellaPremio;
    }

    public boolean getCasellaPescaUnaCarta() {
        return casellaPescaUnaCarta;
    }

    public boolean getUlterioriCarte() {
        return ulterioriCarte;
    }

    public void setCasellaSosta(boolean casellaSosta) {
        this.casellaSosta = casellaSosta;
    }

    public void setCasellaPremio(boolean casellaPremio) {
        this.casellaPremio = casellaPremio;
    }

    public void setCasellaPescaUnaCarta(boolean casellaPescaUnaCarta) {
        this.casellaPescaUnaCarta = casellaPescaUnaCarta;
    }

    public void setUlterioriCarte(boolean ulterioriCarte) {
        this.ulterioriCarte = ulterioriCarte;
    }
}
