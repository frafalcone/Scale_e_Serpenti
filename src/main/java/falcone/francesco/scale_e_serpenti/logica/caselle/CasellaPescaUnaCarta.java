package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

import java.util.Collections;
import java.util.LinkedList;

public class CasellaPescaUnaCarta extends CasellaDecorator{

    private static LinkedList<Integer> carte;
    private static boolean creato = false;

    public CasellaPescaUnaCarta(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
        if(!creato){
            creaMazzo();
        }

    }

    public void creaMazzo(){
        carte = new LinkedList<>();

        for(int i=0; i<4; i++){
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

        switch (cartaPescata) {
            case 0 -> {
                System.out.println("Giocatore pesca la carta: Dado;");
                giocatore.setRigioca(true);
            }
            case 1 -> {
                System.out.println("Giocatore pesca la carta: Panchina;");
                giocatore.setAttesa(1);
            }
            case 2 -> {
                System.out.println("Giocatore pesca la carta: Molla;");
                giocatore.setMolla(true);
            }
            case 3 -> {
                System.out.println("Giocatore pesca la carta: Locanda;");
                giocatore.setAttesa(3);
            }
        }
    }

}
