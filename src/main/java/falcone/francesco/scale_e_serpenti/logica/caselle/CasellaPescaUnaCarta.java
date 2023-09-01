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

    @Override
    public String passaggio(Giocatore giocatore) {
        StringBuilder stbr = new StringBuilder();
        stbr.append(super.passaggio(giocatore));
        stbr.append(comportamentoAggiunto(giocatore));
        return stbr.toString();
    }

    private String comportamentoAggiunto(Giocatore giocatore) {
        int cartaPescata = carte.getFirst();
        carte.removeFirst();
        carte.addLast(cartaPescata);

        switch (cartaPescata) {
            case 0 -> {
                giocatore.setRigioca(true);
                return "\nGiocatore pesca la carta: Dado;";
            }
            case 1 -> {
                giocatore.setAttesa(1);
                return "\nGiocatore pesca la carta: Panchina;";
            }
            case 2 -> {
                giocatore.setMolla(true);
                return "\nGiocatore pesca la carta: Molla;";
            }
            case 3 -> {
                giocatore.setAttesa(3);
                return "\nGiocatore pesca la carta: Locanda;";
            }
        }

        return "";
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

}
