package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

import java.util.Collections;
import java.util.LinkedList;

public class CasellaPescaUnaCartaMod extends CasellaDecorator{

    private static LinkedList<Integer> carte;
    private static boolean creato = false;

    public CasellaPescaUnaCartaMod(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
        if(!creato)
            creaMazzo();
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
        if(cartaPescata==4)
            giocatore.setPossiedeDivietoSosta(true);
        if(cartaPescata!=4)
            carte.addLast(cartaPescata);

        switch (cartaPescata) {
            case 0 -> {
                giocatore.setRigioca(true);
                return "\nGiocatore pesca la carta: Dadi;";
            }
            case 1 -> {
                if (!giocatore.getPossiedeDivietoSosta()) {
                    giocatore.setAttesa(1);
                    return "\nGiocatore pesca la carta: Panchina;";
                } else {
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                    return "\nGiocatore pesca la carta: Panchina; Ma usa Divieto di Sosta!";
                }
            }
            case 2 -> {
                giocatore.setMolla(true);
                return "\nGiocatore pesca la carta: Molla;";
            }
            case 3 -> {
                if (!giocatore.getPossiedeDivietoSosta()) {
                    giocatore.setAttesa(3);
                    return "\nGiocatore pesca la carta: Locanda;";
                } else {
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                    return "\nGiocatore pesca la carta: Locanda; Ma usa Divieto di Sosta!";
                }
            }
            case 4 -> {
                giocatore.setPossiedeDivietoSosta(true);
                return "\nGiocatore pesca la carta: Divieto di Sosta;";
            }
        }
        return "";
    }

    public static void riconsegnaDivietoSosta(Giocatore giocatore){
        giocatore.setUsatoDivietoSosta(false);
        carte.addLast(4);
    }

    public void creaMazzo(){
        carte = new LinkedList<>();

        for(int i=0; i<5; i++){
            carte.add(i);
            carte.add(i);
            carte.add(i);
            carte.add(i);
        }

        Collections.shuffle(carte);
        creato = true;
    }
}