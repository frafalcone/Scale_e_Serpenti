package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaScala extends CasellaDecorator{

    private int destinazione;

    public CasellaScala(CasellaIF casellaAssegnata, int destinazione){
        super(casellaAssegnata);
        this.destinazione = destinazione;
    }

    @Override
    public String passaggio(Giocatore giocatore) {
        StringBuilder stbr = new StringBuilder();
        stbr.append(super.passaggio(giocatore));
        stbr.append(comportamentoAggiunto(giocatore));
        return stbr.toString();
    }

    private String comportamentoAggiunto(Giocatore giocatore) {
        int posizionePrecedente = giocatore.getPosizione();
        giocatore.setPosizione(destinazione);
        return "\nGiocatore arriva su una Scala, posizione: "+ posizionePrecedente +";"
         + "\nGiocatore sale la Scala, posizione: "+ giocatore.getPosizione() +";";
    }


}
