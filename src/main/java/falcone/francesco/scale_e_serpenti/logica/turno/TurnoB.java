package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoB extends TurnoAb{

    protected TurnoB(){
        super();
    }

    public void esegui(Giocatore giocatore){

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi = lanciaDadoSingolo();

        System.out.println("Giocatore lancia il dado: " +risultatoDadi);

        muovi(giocatore, risultatoDadi);

        if(giocatore.getHaVinto()){
            return;
        }

        molla(giocatore, risultatoDadi);
        rigioca(giocatore);
    }
}
