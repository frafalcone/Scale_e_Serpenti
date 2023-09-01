package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoA extends TurnoAb{

    protected TurnoA(){
        super();
    }

    public String esegui(Giocatore giocatore){

        StringBuilder stbr = new StringBuilder();

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi = lanciaDadiDoppi();

        stbr.append("\nGiocatore lancia i dadi: " +risultatoDadi);

        stbr.append(muovi(giocatore, risultatoDadi));

        if(giocatore.getHaVinto()){
            return stbr.toString();
        }

        stbr.append(molla(giocatore, risultatoDadi));
        stbr.append(rigioca(giocatore));

        return stbr.toString();
    }

}
