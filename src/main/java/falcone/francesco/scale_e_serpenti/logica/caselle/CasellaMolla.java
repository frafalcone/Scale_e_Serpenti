package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaMolla extends CasellaDecorator{

    public CasellaMolla(CasellaIF casellaAssegnata){
        super(casellaAssegnata);
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        System.out.println("Giocatore arriva su una molla;");
        giocatore.setMolla(true);

    }

}
