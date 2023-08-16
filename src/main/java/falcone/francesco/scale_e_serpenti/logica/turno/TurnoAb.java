package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

import java.util.Random;

public abstract class TurnoAb implements TurnoIF {

    private boolean automatizza;
    protected TabelloneIF tabellone;

    protected TurnoAb(){
        automatizza = false;
    }

    @Override
    public boolean getAutomatizza() {
        return automatizza;
    }

    public void setAutomatizza(boolean automatizza) {this.automatizza = automatizza;}

    public void setTabellone(TabelloneIF tabellone) {this.tabellone = tabellone;}

    protected int lanciaDadiDoppi() {return new Random().nextInt(1, 13);}
    protected int lanciaDadoSingolo() {return new Random().nextInt(1, 7);}

    protected void muovi(Giocatore giocatore, int risultatoDadi){
        if(giocatore.getPosizione()+risultatoDadi <= tabellone.getDimensione()){
            giocatore.setPosizione(giocatore.getPosizione()+risultatoDadi);
            tabellone.getCasella(giocatore.getPosizione()).passaggio(giocatore);
        }

        if(giocatore.getPosizione()+risultatoDadi > tabellone.getDimensione()){
            int risultatoDadiAggiornato = (giocatore.getPosizione() + risultatoDadi) - tabellone.getDimensione();
            giocatore.setPosizione((giocatore.getPosizione()-risultatoDadiAggiornato));
            tabellone.getCasella((giocatore.getPosizione()-risultatoDadiAggiornato)).passaggio(giocatore);
        }
    }

}
