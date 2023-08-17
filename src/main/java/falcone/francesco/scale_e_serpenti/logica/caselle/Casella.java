package falcone.francesco.scale_e_serpenti.logica.caselle;

import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;

public class Casella implements CasellaIF{

    private boolean assegnata;
    private int indiceCasella;

    public Casella(){
        assegnata = false;
        indiceCasella = 0;
    }

    public void passaggio(Giocatore giocatore){
       System.out.println("\nCasella Raggiunta["+indiceCasella+"];");
    };

    public boolean getAssegnata() {return assegnata;}
    public int getIndiceCasella() {return indiceCasella;}

    public void setAssegnata(boolean assegnata) {this.assegnata = assegnata;}
    public void setIndiceCasella(int indice) {this.indiceCasella = indice;}
}
