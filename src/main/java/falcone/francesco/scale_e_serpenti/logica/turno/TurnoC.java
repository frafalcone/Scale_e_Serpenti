package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoC extends TurnoAb{

    protected TurnoC(){
        super();
    }

    public void esegui(Giocatore giocatore){

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi = 0;
        if(tabellone.getDimensione() - giocatore.getPosizione() < 6){
            System.out.println("Giocatore lancia il dado: " +risultatoDadi);
            risultatoDadi = lanciaDadoSingolo();
        } else {
            System.out.println("Giocatore lancia i dadi: " +risultatoDadi);
            risultatoDadi = lanciaDadiDoppi();
        }

        muovi(giocatore, risultatoDadi);

        if(giocatore.getHaVinto()){
            return;
        }

        molla(giocatore, risultatoDadi);
        rigioca(giocatore);
    }

}
