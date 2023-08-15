package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

import java.util.Collections;
import java.util.LinkedList;

public class CasellaPescaUnaCartaMod extends CasellaDecorator{

    private static LinkedList<Integer> carte;

    public CasellaPescaUnaCartaMod(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
        for(int i=0; i<5; i++){
            carte.add(i);
            carte.add(i);
            carte.add(i);
            carte.add(i);
        }

        Collections.shuffle(carte);
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        int cartaPescata = carte.getFirst();
        carte.removeFirst();
        if(cartaPescata==4)
            giocatore.setPossiedeDivietoSosta(true);
        if(cartaPescata!=4)
            carte.addLast(cartaPescata);

        switch(cartaPescata){
            case 0:
                giocatore.setRigioca(true);
                break;
            case 1:
                if(!giocatore.getPossiedeDivietoSosta()){
                    giocatore.setAttesa(1);
                }
                else{
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                }

                break;
            case 2:
                giocatore.setMolla(true);
                break;
            case 3:
                if(!giocatore.getPossiedeDivietoSosta()){
                    giocatore.setAttesa(3);
                }
                else{
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                }
                break;
            case 4:
                giocatore.setPossiedeDivietoSosta(true);
                break;
        }
    }

    public static void riconsegnaDivietoSosta(Giocatore giocatore){
        giocatore.setUsatoDivietoSosta(false);
        carte.addLast(4);
    }
}