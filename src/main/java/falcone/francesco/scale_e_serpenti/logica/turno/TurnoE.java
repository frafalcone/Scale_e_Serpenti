package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoE extends TurnoAb{

    protected TurnoE(){
        super();
    }

    public void esegui(Giocatore giocatore){

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi;

        if(tabellone.getDimensione() - giocatore.getPosizione() < 6){
            risultatoDadi = lanciaDadoSingolo();
            System.out.println("Giocatore lancia il dado: " +risultatoDadi);
        } else {
            risultatoDadi = lanciaDadiDoppi();
            System.out.println("Giocatore lancia i dadi: " +risultatoDadi);
        }

        muovi(giocatore, risultatoDadi);

        if(giocatore.getHaVinto()){
            return;
        }

        molla(giocatore, risultatoDadi);
        doppioSei(giocatore, risultatoDadi);
        rigioca(giocatore);

    }

}
