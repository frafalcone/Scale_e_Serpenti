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

    protected String muovi(Giocatore giocatore, int risultatoDadi){
        if(giocatore.getHaVinto())
            return "";

        if(giocatore.getPosizione()+risultatoDadi <= tabellone.getDimensione()){
            giocatore.setPosizione(giocatore.getPosizione()+risultatoDadi);
            return tabellone.getCasella(giocatore.getPosizione()).passaggio(giocatore);
        }

        else if(giocatore.getPosizione()+risultatoDadi > tabellone.getDimensione()){
            int A = (tabellone.getDimensione() - giocatore.getPosizione());
            int B = (risultatoDadi-A);
            int C = (giocatore.getPosizione() - B)+A;
            giocatore.setPosizione(C);
            return tabellone.getCasella(giocatore.getPosizione()).passaggio(giocatore);
        }
        return "";
    }

    protected String molla(Giocatore giocatore, int risultatoDadi){
        if(giocatore.getMolla()){
            StringBuilder stbr = new StringBuilder();
            stbr.append("\nGiocatore rimbalza sulla Molla;");
            stbr.append(muovi(giocatore, risultatoDadi));
            return stbr.toString();
        }
        return "";
    }

    protected String doppioSei(Giocatore giocatore, int risultatoDadi){
        if(risultatoDadi == 12){
            StringBuilder stbr = new StringBuilder();
            stbr.append("\nGiocatore ha fatto Doppio Sei;");
            stbr.append(esegui(giocatore));
            return stbr.toString();
        }
        return "";
    }

    protected  String rigioca(Giocatore giocatore){
        if(giocatore.getRigioca()){
            StringBuilder stbr = new StringBuilder();
            stbr.append("\nGiocatore riesegue il Turno;");
            stbr.append(esegui(giocatore));
            return stbr.toString();
        }
        return "";
    }
}
