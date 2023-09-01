package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaPanchina extends CasellaDecorator{

    public CasellaPanchina(CasellaIF casellaAssegnata){
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
            giocatore.setAttesa(1);
            return "\nGiocatore arriva su una Panchina, deve riposarsi per 1 turno;";
        }
        else{
            giocatore.setPossiedeDivietoSosta(false);
            giocatore.setUsatoDivietoSosta(true);
            return "\nGiocatore arriva su una Panchina, ma usa la carta Divieto di Sosta;";
        }
    }

}
