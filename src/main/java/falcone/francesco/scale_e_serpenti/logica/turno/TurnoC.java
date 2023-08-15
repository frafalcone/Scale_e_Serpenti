package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public class TurnoC extends TurnoAb{

    protected TurnoC(TabelloneIF tabellone){
        super(tabellone);
    }

    public void esegui(Giocatore giocatore){

        int risultatoDadi = 0;
        if(tabellone.getDimensione() - giocatore.getPosizione() < 6){
            risultatoDadi = lanciaDadoSingolo();
        } else {
            risultatoDadi = lanciaDadiDoppi();
        }

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
