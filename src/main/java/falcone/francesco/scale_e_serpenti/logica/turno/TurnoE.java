package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public class TurnoE extends TurnoAb{

    protected TurnoE(){
        super();
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

        if(risultatoDadi == 12){
            esegui(giocatore);
        }

        if(giocatore.getRigioca()){
            esegui(giocatore);
        }
    }

}
