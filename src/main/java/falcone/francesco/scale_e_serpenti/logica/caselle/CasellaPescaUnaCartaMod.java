package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaPescaUnaCartaMod extends CasellaDecorator{

    public CasellaPescaUnaCartaMod(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
    }

}