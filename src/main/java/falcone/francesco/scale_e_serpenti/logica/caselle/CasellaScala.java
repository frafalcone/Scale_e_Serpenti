package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaScala extends CasellaDecorator{

    private int destinazione;

    public CasellaScala(CasellaIF casellaAssegnata, int destinazione){
        super(casellaAssegnata);
        this.destinazione = destinazione;
    }

    @Override
    public void passaggio(Giocatore giocatore) {
        super.passaggio(giocatore);
        comportamentoAggiunto(giocatore);
    }

    private void comportamentoAggiunto(Giocatore giocatore) {
        System.out.println("Giocatore arriva su una Scala, posizione: "+ giocatore.getPosizione() +";");
        giocatore.setPosizione(destinazione);
        System.out.println("Giocatore sale la Scala, posizione: "+ giocatore.getPosizione() +";");
    }


}
