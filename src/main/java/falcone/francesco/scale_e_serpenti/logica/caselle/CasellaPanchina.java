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
            giocatore.setAttesa(1);
        }
        else{
            giocatore.setPossiedeDivietoSosta(false);
            giocatore.setUsatoDivietoSosta(true);
        }
    }

}
