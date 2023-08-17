package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaPanchina extends CasellaDecorator{

    public CasellaPanchina(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        if(!giocatore.getPossiedeDivietoSosta()){
            System.out.println("Giocatore arriva su una Panchina, deve riposarsi per 1 turno;");
            giocatore.setAttesa(1);
        }
        else{
            System.out.println("Giocatore arriva su una Panchina, ma usa la carta Divieto di Sosta;");
            giocatore.setPossiedeDivietoSosta(false);
            giocatore.setUsatoDivietoSosta(true);
        }
    }

}
