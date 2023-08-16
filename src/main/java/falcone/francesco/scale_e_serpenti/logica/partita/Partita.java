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

import java.util.Scanner;

public class Partita {

    private static Partita instance;

    private ImpostazioniRegole impostazioniRegole;
    private ImpostazioniCaselle impostazioniCaselle;
    private ImpostazioniTabellone impostazioniTabellone;
    private ImpostazioniGiocatori impostazioniGiocatori;
    private TabelloneIF tabellone;
    private TurnoIF sistemaTurni;

    private Partita(){
        inizializzaPartita();
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

        boolean finitaCreazione = false;
        int fase = 0;

        Scanner sc = new Scanner(System.in);

        while(!finitaCreazione){
            switch (fase){

                case 0: //NUMEROGIOCATORI
                    System.out.print("\nGiocatori: ");
                    impostazioniGiocatori.setNumeroGiocatori(sc.nextInt());
                    break;

                case 1: //CASELLE
                    System.out.print("\nAbilita casellePremio (1: si; altro: no): ");
                    if(sc.nextInt() == 1)
                        impostazioniCaselle.setCasellaPremio(true);
                    else {
                        impostazioniCaselle.setCasellaPremio(false);
                    }
                    System.out.print("\nAbilita asellaPescaUnaCarta (1: si; altro: no): ");
                    if(sc.nextInt() == 1)
                        impostazioniCaselle.setCasellaPescaUnaCarta(true);
                    else {
                        impostazioniCaselle.setCasellaPescaUnaCarta(false);
                    }
                    System.out.print("\nAbilita casellaSosta (1: si; altro: no): ");
                    if(sc.nextInt() == 1)
                        impostazioniCaselle.setCasellaSosta(true);
                    else {
                        impostazioniCaselle.setCasellaSosta(false);
                    }
                    break;

                case 2: //REGOLE
                    System.out.print("\nAbilita dadoSingolo (1: si; altro: no): ");
                    if(sc.nextInt() == 1)
                        impostazioniRegole.setDadoSingolo(true);
                    else {
                        impostazioniRegole.setDadoSingolo(false);
                    }
                    if(!impostazioniRegole.getDadoSingolo()){
                        System.out.print("\nAbilita doppioSei (1: si; altro: no): ");
                        if(sc.nextInt() == 1)
                            impostazioniRegole.setDoppioSei(true);
                        else {
                            impostazioniRegole.setDoppioSei(false);
                        }
                    }
                    if(!impostazioniRegole.getDadoSingolo()) {
                        System.out.print("\nAbilita lancioSingolo (1: si; altro: no): ");
                        if (sc.nextInt() == 1)
                            impostazioniRegole.setLancioSingolo(true);
                        else {
                            impostazioniRegole.setLancioSingolo(false);
                        }
                    }
                    if(impostazioniCaselle.getCasellaPescaUnaCarta()){
                        System.out.print("\nAbilita ulterioriCarte (1: si; altro: no): ");
                        if(sc.nextInt() == 1)
                            impostazioniCaselle.setUlterioriCarte(true);
                        else {
                            impostazioniCaselle.setUlterioriCarte(false);
                        }
                    }
                    break;

                case 3: //TABELLONE
                    System.out.print("\nRighe: ");
                    impostazioniTabellone.setRighe(sc.nextInt());
                    System.out.print("\nColonne: ");
                    impostazioniTabellone.setColonne(sc.nextInt());
                    System.out.print("\nScale: ");
                    impostazioniTabellone.setNumeroScale(sc.nextInt());
                    System.out.print("\nSerpenti: ");
                    impostazioniTabellone.setNumeroSerpenti(sc.nextInt());
                    if(impostazioniCaselle.getCasellaPremio()){
                        System.out.print("\nPremio: ");
                        impostazioniTabellone.setNumeroCasellePremio(sc.nextInt());
                    }
                    if(impostazioniCaselle.getCasellaSosta()){
                        System.out.print("\nSosta: ");
                        impostazioniTabellone.setNumeroCaselleSosta(sc.nextInt());
                    }
                    if(impostazioniCaselle.getCasellaPescaUnaCarta()){
                        System.out.print("\nPesca_Carta: ");
                        impostazioniTabellone.setNumeroCasellePescaUnaCarta(sc.nextInt());
                    }
                    break;
                case 4:
                    finitaCreazione = true;
            }
            fase++;
        }


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

        while(!finito){

            gestioneAutomatizzazione();

            int indiceGiocatoreAttuale = turno % impostazioniGiocatori.getNumeroGiocatori();
            Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

            System.out.println("TURNO "+ turno +"; GIOCATORE "+indiceGiocatoreAttuale);

            if(giocatoreAttuale.getAttesa()<=0){
                sistemaTurni.esegui(giocatoreAttuale);

                if(giocatoreAttuale.getUsatoDivietoSosta()){
                    CasellaPescaUnaCartaMod.riconsegnaDivietoSosta(giocatoreAttuale);
                }

                giocatoreAttuale.setMolla(false);
                giocatoreAttuale.setRigioca(false);

                if(giocatoreAttuale.getHaVinto()){
                    indiceVincitore = indiceGiocatoreAttuale;
                    System.out.println("VINCITORE:" + indiceVincitore);
                    finito = true;
                }
            }

            else {
                giocatoreAttuale.setAttesa(giocatoreAttuale.getAttesa()-1);
                System.out.println("G"+indiceGiocatoreAttuale+" ATTESA");
            }

            turno++;
        }

    }

    private void gestioneAutomatizzazione() {
        if (sistemaTurni.getAutomatizza()) {
            //Avanzamento Automatico
        } else {
            //Avanzamento Manuale
        }
    }
}
