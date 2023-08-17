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

        switch (cartaPescata) {
            case 0 -> {
                System.out.println("Giocatore pesca la carta: Dadi;");
                giocatore.setRigioca(true);
            }
            case 1 -> {
                if (!giocatore.getPossiedeDivietoSosta()) {
                    System.out.println("Giocatore pesca la carta: Panchina;");
                    giocatore.setAttesa(1);
                } else {
                    System.out.println("Giocatore pesca la carta: Panchina; Ma usa Divieto di Sosta!");
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                }
            }
            case 2 -> {
                System.out.println("Giocatore pesca la carta: Molla;");
                giocatore.setMolla(true);
            }
            case 3 -> {
                if (!giocatore.getPossiedeDivietoSosta()) {
                    System.out.println("Giocatore pesca la carta: Locanda;");
                    giocatore.setAttesa(3);
                } else {
                    System.out.println("Giocatore pesca la carta: Locanda; Ma usa Divieto di Sosta!");
                    giocatore.setPossiedeDivietoSosta(false);
                    carte.addLast(4);
                }
            }
            case 4 -> {
                System.out.println("Giocatore pesca la carta: Divieto di Sosta;");
                giocatore.setPossiedeDivietoSosta(true);
            }
        }
    }

    public static void riconsegnaDivietoSosta(Giocatore giocatore){
        giocatore.setUsatoDivietoSosta(false);
        carte.addLast(4);
    }
}