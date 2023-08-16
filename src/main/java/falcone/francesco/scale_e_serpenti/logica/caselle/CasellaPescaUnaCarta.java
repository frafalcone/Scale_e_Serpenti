package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

import java.util.Collections;
import java.util.LinkedList;

public class CasellaPescaUnaCarta extends CasellaDecorator{

    private LinkedList<Integer> carte;
    private boolean creato = false;

    public CasellaPescaUnaCarta(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
        creaMazzo();
    }

    public void creaMazzo(){
        carte = new LinkedList<Integer>();

        for(int i=0; i<5; i++){
            carte.add(i);
            carte.add(i);
            carte.add(i);
            carte.add(i);
        }

        Collections.shuffle(carte);
        creato = true;
    }
    
    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        int cartaPescata = carte.getFirst();
        carte.removeFirst();
        carte.addLast(cartaPescata);

        switch(cartaPescata){
            case 0:
                giocatore.setRigioca(true);
                break;
            case 1:
                giocatore.setAttesa(1);
                break;
            case 2:
                giocatore.setMolla(true);
                break;
            case 3:
                giocatore.setAttesa(3);
                break;
        }
    }

}
