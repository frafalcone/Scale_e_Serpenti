package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
public abstract class CasellaDecorator implements CasellaIF{

    private CasellaIF casellaAssegnata;

    public CasellaDecorator(CasellaIF casellaAssegnata){
        this.casellaAssegnata = casellaAssegnata;
    }

    public String passaggio (Giocatore giocatore){
        return casellaAssegnata.passaggio(giocatore);
    }

    public int getIndiceCasella() {
        int indice = -1;
        if(casellaAssegnata instanceof Casella){
            indice = ((Casella) casellaAssegnata).getIndiceCasella();
        }
        return indice;
    }

    public boolean getAssegnata() {
        boolean assegnata = false;
        if(casellaAssegnata instanceof Casella){
            assegnata = ((Casella) casellaAssegnata).getAssegnata();
        }
        return assegnata;
    }

    public void setIndiceCasella(int indice) {
        if(casellaAssegnata instanceof Casella){
           casellaAssegnata.setIndiceCasella(indice);
        }
    }

    public void setAssegnata(boolean assegnata) {
        if(casellaAssegnata instanceof Casella){
            casellaAssegnata.setAssegnata(assegnata);
        }
    }
}
