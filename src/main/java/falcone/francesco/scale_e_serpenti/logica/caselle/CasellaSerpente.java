package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class CasellaSerpente extends CasellaDecorator{

    private int destinazione;

    public CasellaSerpente(CasellaIF casellaAssegnata, int destinazione){
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
        return "\nGiocatore arriva su un Serpente, posizione: "+ posizionePrecedente +";"
         +"\nGiocatore scivola dal Serpente, posizione: "+ giocatore.getPosizione();
    }

}
