package falcone.francesco.scale_e_serpenti.logica.partita;

import falcone.francesco.scale_e_serpenti.logica.caselle.CasellaPescaUnaCartaMod;
import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniCaselle;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniGiocatori;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniRegole;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneBuilder;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneBuilderDirector;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneBuilderIF;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;
import falcone.francesco.scale_e_serpenti.logica.turno.SelettoreSistemaTurni;
import falcone.francesco.scale_e_serpenti.logica.turno.TurnoIF;

public class Partita {

    private static Partita instance;

    private ImpostazioniRegole impostazioniRegole;
    private ImpostazioniCaselle impostazioniCaselle;
    private ImpostazioniTabellone impostazioniTabellone;
    private ImpostazioniGiocatori impostazioniGiocatori;
    private TabelloneIF tabellone;
    private TurnoIF sistemaTurni;

    private Partita(){
    }

    public static Partita getPartita(){
        if(instance == null) instance = new Partita();
        return instance;
    }

    public void inizializzaPartita(){
        impostazioniTabellone = new ImpostazioniTabellone();
        impostazioniCaselle = new ImpostazioniCaselle();
        impostazioniRegole = new ImpostazioniRegole();
        impostazioniGiocatori = new ImpostazioniGiocatori();
    }

    public void creaPartita(){
        /*
        Sequenza di I/O per abilitare/disabilitare le varie impostazioni;
        */

        sistemaTurni = new SelettoreSistemaTurni().seleziona(impostazioniRegole);

        TabelloneBuilderIF builder = new TabelloneBuilder();
        TabelloneBuilderDirector director = new TabelloneBuilderDirector(builder);
        director.build(impostazioniCaselle, impostazioniTabellone);
        tabellone = builder.getTabellone();

        sistemaTurni.setTabellone(tabellone);
    }

    public void salvaPartita(){
        /*
        scrittura delle variabili su file ".save" delle varie impostazioni, tramite i metodi di get;
         */
    }

    public void caricaPartita(){
        /*
        lettura delle variabili da file ".save" delle varie impostazioni, tramite i metodi di set;
         */

        sistemaTurni = new SelettoreSistemaTurni().seleziona(impostazioniRegole);

        TabelloneBuilderIF builder = new TabelloneBuilder();
        TabelloneBuilderDirector director = new TabelloneBuilderDirector(builder);
        director.build(impostazioniCaselle, impostazioniTabellone);
        tabellone = builder.getTabellone();

        sistemaTurni.setTabellone(tabellone);
    }


    public void gameInitAndLoop(){

        boolean finito = false;
        int turno = 0;

        Giocatore giocatori[] = new Giocatore[impostazioniGiocatori.getNumeroGiocatori()];

        for(int i=0; i<impostazioniGiocatori.getNumeroGiocatori(); i++){
            giocatori[i] = new Giocatore();
        }

        int indiceVincitore = -1;

        while(finito){

            if(sistemaTurni.getAutomatizza()){
                //Avanzamento Automatico
            } else {
                //Avanzamento Manuale
            }

            int indiceGiocatoreAttuale = turno % impostazioniGiocatori.getNumeroGiocatori();
            Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

            if(giocatoreAttuale.getAttesa()<=0){
                sistemaTurni.esegui(giocatoreAttuale);

                if(giocatoreAttuale.getUsatoDivietoSosta()){
                    CasellaPescaUnaCartaMod.riconsegnaDivietoSosta(giocatoreAttuale);
                }

                giocatoreAttuale.setMolla(false);
                giocatoreAttuale.setRigioca(false);

                if(giocatoreAttuale.getHaVinto()){
                    indiceVincitore = indiceGiocatoreAttuale;
                    finito = true;
                }
            }

            else {
                giocatoreAttuale.setAttesa(giocatoreAttuale.getAttesa()-1);
            }

            turno++;
        }

    }
}
