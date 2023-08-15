package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public class TurnoB extends TurnoAb{

    protected TurnoB(TabelloneIF tabellone){
        super(tabellone);
    }

    public void esegui(Giocatore giocatore){

        int risultatoDadi = lanciaDadoSingolo();

        muovi(giocatore, risultatoDadi);

        if(giocatore.getMolla()){
            muovi(giocatore, risultatoDadi);
        }

        if(giocatore.getHaVinto()){
            return;
        }

        if(giocatore.getRigioca()){
            esegui(giocatore);
        }
    }
}
