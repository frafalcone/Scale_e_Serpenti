package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class TurnoC extends TurnoAb{

    protected TurnoC(){
        super();
    }

    public String esegui(Giocatore giocatore){

        StringBuilder stbr = new StringBuilder();

        if(giocatore.getRigioca()){
            giocatore.setRigioca(false);
        }

        int risultatoDadi = 0;
        if(tabellone.getDimensione() - giocatore.getPosizione() < 6){
            stbr.append("\nGiocatore lancia il dado: " +risultatoDadi);
            risultatoDadi = lanciaDadoSingolo();
        } else {
            stbr.append("\nGiocatore lancia i dadi: " +risultatoDadi);
            risultatoDadi = lanciaDadiDoppi();
        }

        stbr.append(muovi(giocatore, risultatoDadi));


        if(giocatore.getHaVinto()){
            return stbr.toString();
        }


        stbr.append(molla(giocatore, risultatoDadi));
        stbr.append(rigioca(giocatore));

        return stbr.toString();
    }

}
