package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaDadi extends CasellaDecorator{

    public CasellaDadi(CasellaIF casellaAssegnata){
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
        giocatore.setRigioca(true);
        return "\nGiocatore ottiene di nuovo i Dadi;";
    }

}
