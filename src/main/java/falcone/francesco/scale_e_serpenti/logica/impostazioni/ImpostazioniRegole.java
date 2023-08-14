package falcone.francesco.scale_e_serpenti.logica.impostazioni;

public class ImpostazioniRegole {

    private boolean dadoSingolo;
    private boolean lancioSingolo;
    private boolean doppioSei;

    public ImpostazioniRegole() {
        dadoSingolo = false;
        lancioSingolo = false;
        doppioSei = false;
    }

    public boolean getDadoSingolo() {
        return dadoSingolo;
    }

    public boolean getLancioSingolo() {
        return lancioSingolo;
    }

    public boolean getDoppioSei() {
        return doppioSei;
    }

    public void setDadoSingolo(boolean dadoSingolo) {
        this.dadoSingolo = dadoSingolo;
    }

    public void setLancioSingolo(boolean lancioSingolo) {
        this.lancioSingolo = lancioSingolo;
    }

    public void setDoppioSei(boolean doppioSei) {
        this.doppioSei = doppioSei;
    }

}
