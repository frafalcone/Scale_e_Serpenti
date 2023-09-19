package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaMolla extends CasellaDecorator{

    public CasellaMolla(CasellaIF casellaAssegnata){
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
        giocatore.setMolla(true);
        return "\nGiocatore arriva su una Molla;";
    }

}
