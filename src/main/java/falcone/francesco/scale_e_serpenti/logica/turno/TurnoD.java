package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoD extends TurnoAb{

    protected TurnoD(){
        super();
    }

    public void esegui(Giocatore giocatore){

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi = lanciaDadiDoppi();

        System.out.println("Giocatore lancia i dadi: " +risultatoDadi);

        muovi(giocatore, risultatoDadi);

        if(giocatore.getHaVinto()){
            return;
        }

        molla(giocatore, risultatoDadi);
        doppioSei(giocatore, risultatoDadi);
        rigioca(giocatore);
    }

}
