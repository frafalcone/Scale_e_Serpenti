package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

import java.util.Random;

public abstract class TurnoAb implements TurnoIF {

    private boolean automatizza;
    private TabelloneIF tabellone;

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

}
