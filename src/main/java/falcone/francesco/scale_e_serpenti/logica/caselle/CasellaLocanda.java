package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaLocanda extends CasellaDecorator{

    public CasellaLocanda(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
    }

    @Override
    public String passaggio(Giocatore giocatore) {
        StringBuilder stbr = new StringBuilder();
        stbr.append(super.passaggio(giocatore));
        stbr.append(comportamentoAggiunto(giocatore));
        return stbr.toString();
    }

    private String comportamentoAggiunto(Giocatore giocatore) {
        if(!giocatore.getPossiedeDivietoSosta()){
            giocatore.setAttesa(3);
            return "\nGiocatore arriva su una Locanda, deve riposarsi per 3 turni;";
        }
        else{
            giocatore.setPossiedeDivietoSosta(false);
            giocatore.setUsatoDivietoSosta(true);
            return "\nGiocatore arriva su una Molla, ma usa la carta Divieto di Sosta;";
        }
    }

}
