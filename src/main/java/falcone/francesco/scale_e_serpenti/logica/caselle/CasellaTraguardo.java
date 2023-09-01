package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaTraguardo extends CasellaDecorator{

    public CasellaTraguardo(CasellaIF casellaAssegnata){
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
        giocatore.setHaVinto(true);
        return "\nGiocatore ha raggiunto il Traguardo;";
    }

}
