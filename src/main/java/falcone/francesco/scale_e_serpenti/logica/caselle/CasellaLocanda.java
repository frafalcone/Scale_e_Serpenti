package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaLocanda extends CasellaDecorator{

    public CasellaLocanda(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        if(!giocatore.getPossiedeDivietoSosta()){
            System.out.println("Giocatore arriva su una Locanda, deve riposarsi per 3 turni;");
            giocatore.setAttesa(3);
        }
        else{
            System.out.println("Giocatore arriva su una Molla, ma usa la carta Divieto di Sosta;");
            giocatore.setPossiedeDivietoSosta(false);
            giocatore.setUsatoDivietoSosta(true);
        }
    }

}
