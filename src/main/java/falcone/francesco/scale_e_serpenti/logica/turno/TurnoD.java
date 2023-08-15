package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public class TurnoD extends TurnoAb{

    protected TurnoD(){
        super();
    }

    public void esegui(Giocatore giocatore){

        int risultatoDadi = lanciaDadiDoppi();

        muovi(giocatore, risultatoDadi);

        if(giocatore.getMolla()){
            muovi(giocatore, risultatoDadi);
        }

        if(risultatoDadi == 12){
            esegui(giocatore);
        }

        if(giocatore.getHaVinto()){
            return;
        }

        if(giocatore.getRigioca()){
            esegui(giocatore);
        }
    }

}
