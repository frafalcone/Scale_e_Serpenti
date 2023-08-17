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
            boolean faseCorretta = false;
            int input;

            switch (fase) {
                case 0 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("         IMPOSTAZIONI GIOCATORI         ");
                    System.out.println("----------------------------------------");
                    System.out.print("\nNumero dei Giocatori: ");
                    input = sc.nextInt();
                    if (input > 0) {
                        impostazioniGiocatori.setNumeroGiocatori(input);
                    } else {
                        System.err.println("\nIl numero deve essere maggiore di 0");
                        break;
                    }

                    System.out.println("\n========================================\n");
                    faseCorretta = true;
                }
                case 1 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("         IMPOSTAZIONI CASELLE           ");
                    System.out.println("----------------------------------------");
                    System.out.print("\nAbilita casellePremio (1: si; 0: no): ");
                    input = sc.nextInt();
                    if (input == 1) {
                        impostazioniCaselle.setCasellaPremio(true);
                    } else if (input == 0) {
                        impostazioniCaselle.setCasellaPremio(false);
                    } else {
                        System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                        break;
                    }
                    System.out.print("\nAbilita casellaPesca (1: si; 0: no): ");
                    input = sc.nextInt();
                    if (input == 1) {
                        impostazioniCaselle.setCasellaPescaUnaCarta(true);
                    } else if (input == 0) {
                        impostazioniCaselle.setCasellaPescaUnaCarta(false);
                    } else {
                        System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                        break;
                    }
                    System.out.print("\nAbilita casellaSosta (1: si; 0: no): ");
                    input = sc.nextInt();
                    if (input == 1) {
                        impostazioniCaselle.setCasellaSosta(true);
                    } else if (input == 0) {
                        impostazioniCaselle.setCasellaSosta(false);
                    } else {
                        System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                        break;
                    }

                    System.out.println("\n========================================\n");
                    faseCorretta = true;

                }
                case 2 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("         IMPOSTAZIONI REGOLE            ");
                    System.out.println("----------------------------------------");
                    System.out.print("\nAbilita dadoSingolo (1: si; 0: no): ");
                    input = sc.nextInt();
                    if (input == 1) {
                        impostazioniRegole.setDadoSingolo(true);
                    } else if (input == 0) {
                        impostazioniRegole.setDadoSingolo(false);
                    } else {
                        System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                        break;
                    }
                    if (!impostazioniRegole.getDadoSingolo()) {
                        System.out.print("\nAbilita doppioSei (1: si; 0: no): ");
                        input = sc.nextInt();
                        if (input == 1) {
                            impostazioniRegole.setDoppioSei(true);
                        } else if (input == 0) {
                            impostazioniRegole.setDoppioSei(false);
                        } else {
                            System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                            break;
                        }
                    }
                    if (!impostazioniRegole.getDadoSingolo()) {
                        System.out.print("\nAbilita lancioSingolo (1: si; 0: no): ");
                        input = sc.nextInt();
                        if (input == 1) {
                            impostazioniRegole.setLancioSingolo(true);
                        } else if (input == 0) {
                            impostazioniRegole.setLancioSingolo(false);
                        } else {
                            System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                            break;
                        }
                    }
                    if (impostazioniCaselle.getCasellaPescaUnaCarta()) {
                        System.out.print("\nAbilita ulterioriCarte (1: si; 0: no): ");
                        input = sc.nextInt();
                        if (input == 1) {
                            impostazioniCaselle.setUlterioriCarte(true);
                        } else if (input == 0) {
                            impostazioniCaselle.setUlterioriCarte(false);
                        } else {
                            System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                            break;
                        }
                    }

                    System.out.println("\n========================================\n");
                    faseCorretta = true;
                }
                case 3 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("         IMPOSTAZIONI TABELLONE         ");
                    System.out.println("----------------------------------------");
                    System.out.print("\nRighe: ");
                    input = sc.nextInt();
                    if (input < 4 || input > 12) {
                        System.err.println("Numero min: 4; Numero max: 12;");
                        break;
                    }
                    impostazioniTabellone.setRighe(input);
                    System.out.print("\nColonne: ");
                    input = sc.nextInt();
                    if (input < 4 || input > 12) {
                        System.err.println("Numero min: 4; Numero max: 12;");
                        break;
                    }
                    impostazioniTabellone.setColonne(input);
                    int caselleDisponibili = (impostazioniTabellone.getRighe() * impostazioniTabellone.getColonne()) - 1;
                    System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Scale: ");
                    input = sc.nextInt();
                    if (input > caselleDisponibili) {
                        System.err.print("Caselle non disponibili;");
                        break;
                    }
                    impostazioniTabellone.setNumeroScale(input);
                    caselleDisponibili -= input * 2;
                    System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Serpenti: ");
                    input = sc.nextInt();
                    if (input > caselleDisponibili) {
                        System.err.print("Caselle non disponibili;");
                        break;
                    }
                    impostazioniTabellone.setNumeroSerpenti(input);
                    caselleDisponibili -= input * 2;
                    if (impostazioniCaselle.getCasellaPremio()) {
                        System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Premio: ");
                        input = sc.nextInt();
                        if (input > caselleDisponibili) {
                            System.err.print("Caselle non disponibili;");
                            break;
                        }
                        impostazioniTabellone.setNumeroCasellePremio(input);
                        caselleDisponibili -= input;
                    }
                    if (impostazioniCaselle.getCasellaSosta()) {
                        System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Sosta: ");
                        input = sc.nextInt();
                        if (input > caselleDisponibili) {
                            System.err.print("Caselle non disponibili;");
                            break;
                        }
                        impostazioniTabellone.setNumeroCaselleSosta(input);
                        caselleDisponibili -= input;
                    }
                    if (impostazioniCaselle.getCasellaPescaUnaCarta()) {
                        System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Pesca_Carta: ");
                        input = sc.nextInt();
                        if (input > caselleDisponibili) {
                            System.err.print("Caselle non disponibili;");
                            break;
                        }
                        impostazioniTabellone.setNumeroCasellePescaUnaCarta(input);
                    }

                    System.out.println("\n========================================\n");
                    faseCorretta = true;
                }
                case 4 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("             FINE CREAZIONE             ");
                    System.out.println("========================================\n");
                    finitaCreazione = true;
                }
            }
            if(faseCorretta)
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

        Giocatore[] giocatori = new Giocatore[impostazioniGiocatori.getNumeroGiocatori()];

        for(int i=0; i<impostazioniGiocatori.getNumeroGiocatori(); i++){
            giocatori[i] = new Giocatore();
        }

        int indiceVincitore = -1;

        System.out.println("\n\n========================================");
        System.out.println("             INIZIO PARTITA             ");
        System.out.println("========================================");

        while(!finito){

            gestioneAutomatizzazione();

            int indiceGiocatoreAttuale = turno % impostazioniGiocatori.getNumeroGiocatori();
            Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

            System.out.println("----------------------------------------\n");
            System.out.println("TURNO "+ turno +"; GIOCATORE "+indiceGiocatoreAttuale +"\n");

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
                System.out.println("\nFine turno;\n");
                System.out.println("----------------------------------------");
            }

            else {
                giocatoreAttuale.setAttesa(giocatoreAttuale.getAttesa()-1);
                System.out.println("Giocatore deve attendere;\n");
            }

            System.out.println("========================================");

            turno++;
        }
        System.out.println("----------------------------------------\n");
        System.out.println("\n========================================");
        System.out.println("******|| VINCITORE: Giocatore "+indiceVincitore+" ||******" );
        System.out.println("========================================\n");
        System.out.println("\n----------------------------------------\n");
    }

    private void gestioneAutomatizzazione() {
        if (sistemaTurni.getAutomatizza()) {
            //Avanzamento Automatico
        } else {
            //Avanzamento Manuale
        }
    }
}
