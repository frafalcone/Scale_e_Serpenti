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

    protected int lanciaDadiDoppi() {return new Random().nextInt(2, 13);}
    protected int lanciaDadoSingolo() {return new Random().nextInt(1, 7);}

    protected void muovi(Giocatore giocatore, int risultatoDadi){
        if(giocatore.getHaVinto())
            return;

        if(giocatore.getPosizione()+risultatoDadi <= tabellone.getDimensione()){
            giocatore.setPosizione(giocatore.getPosizione()+risultatoDadi);
            tabellone.getCasella(giocatore.getPosizione()).passaggio(giocatore);
        }

        else if(giocatore.getPosizione()+risultatoDadi > tabellone.getDimensione()){
            int A = (tabellone.getDimensione() - giocatore.getPosizione());
            int B = (risultatoDadi-A);
            int C = (giocatore.getPosizione() - B)+A;
            giocatore.setPosizione(C);
            tabellone.getCasella(giocatore.getPosizione()).passaggio(giocatore);
        }
    }

    protected void molla(Giocatore giocatore, int risultatoDadi){
        if(giocatore.getMolla()){
            System.out.println("Giocatore rimbalza sulla Molla;");
            muovi(giocatore, risultatoDadi);
        }
    }

    protected void doppioSei(Giocatore giocatore, int risultatoDadi){
        if(risultatoDadi == 12){
            System.out.println("Giocatore ha fatto Doppio Sei;");
            esegui(giocatore);
        }
    }

    protected  void rigioca(Giocatore giocatore){
        if(giocatore.getRigioca()){
            System.out.println("Giocatore riesegue il Turno;");
            esegui(giocatore);
        }
    }
}
