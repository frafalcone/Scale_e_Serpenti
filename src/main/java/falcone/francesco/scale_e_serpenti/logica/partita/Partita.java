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

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("CallToPrintStackTrace")
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

    public void passaParametri(int[] parametri){
        for(int i=0; i<parametri.length; i++){
            switch (i){
                case 0 -> impostazioniGiocatori.setNumeroGiocatori(parametri[i]);

                case 1 -> impostazioniCaselle.setCasellaPremio(parametri[i] == 1);

                case 2 -> impostazioniCaselle.setCasellaSosta(parametri[i] == 1);

                case 3 -> impostazioniCaselle.setCasellaPescaUnaCarta(parametri[i] == 1);

                case 4 -> impostazioniCaselle.setUlterioriCarte(parametri[i] == 1);

                case 5 -> impostazioniRegole.setDadoSingolo(parametri[i] == 1);

                case 6 -> impostazioniRegole.setDoppioSei(parametri[i] == 1);

                case 7 -> impostazioniRegole.setLancioSingolo(parametri[i] == 1);

                case 8 -> impostazioniTabellone.setRighe(parametri[i]);

                case 9 -> impostazioniTabellone.setColonne(parametri[i]);

                case 10 -> impostazioniTabellone.setNumeroScale(parametri[i]);

                case 11 -> impostazioniTabellone.setNumeroSerpenti(parametri[i]);

                case 12 -> impostazioniTabellone.setNumeroCasellePremio(parametri[i]);

                case 13 -> impostazioniTabellone.setNumeroCaselleSosta(parametri[i]);

                case 14 -> impostazioniTabellone.setNumeroCasellePescaUnaCarta(parametri[i]);

            }
        }
    }

    public void configuraPartita(){
        sistemaTurni = new SelettoreSistemaTurni().seleziona(impostazioniRegole);

        TabelloneBuilderIF builder = new TabelloneBuilder();
        TabelloneBuilderDirector director = new TabelloneBuilderDirector(builder);
        director.build(impostazioniCaselle, impostazioniTabellone);
        tabellone = builder.getTabellone();

        sistemaTurni.setTabellone(tabellone);
    }





    public void salvaPartita(String nomeConfigurazione){
            try {
                FileWriter flwr = new FileWriter(nomeConfigurazione);
                StringBuilder stbr = new StringBuilder();

                stbr.append(impostazioniGiocatori.getNumeroGiocatori());
                stbr.append("\n");

                stbr.append(impostazioniCaselle.getCasellaPremio());
                stbr.append("\n");
                stbr.append(impostazioniCaselle.getCasellaSosta());
                stbr.append("\n");
                stbr.append(impostazioniCaselle.getCasellaPescaUnaCarta());
                stbr.append("\n");

                stbr.append(impostazioniRegole.getDadoSingolo());
                stbr.append("\n");
                stbr.append(impostazioniRegole.getDoppioSei());
                stbr.append("\n");
                stbr.append(impostazioniRegole.getLancioSingolo());
                stbr.append("\n");
                stbr.append(impostazioniCaselle.getUlterioriCarte());
                stbr.append("\n");

                stbr.append(impostazioniTabellone.getRighe());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getColonne());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getNumeroScale());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getNumeroSerpenti());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getNumeroCasellePremio());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getNumeroCaselleSosta());
                stbr.append("\n");
                stbr.append(impostazioniTabellone.getNumeroCasellePescaUnaCarta());
                stbr.append("\n");


                String str = stbr.toString();
                flwr.write(str);
                flwr.close();
            } catch (IOException e){
                e.printStackTrace();
            }


    }

    public void caricaPartita(String nomeConfigurazione){
        try {
            File file = new File(nomeConfigurazione);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;
            int fase = 0;
            
            while ((str = br.readLine()) != null){
                switch (fase){
                    case 0 -> impostazioniGiocatori.setNumeroGiocatori(Integer.parseInt(str));

                    case 1 -> impostazioniCaselle.setCasellaPremio(Boolean.parseBoolean(str));

                    case 2 -> impostazioniCaselle.setCasellaSosta(Boolean.parseBoolean(str));

                    case 3 -> impostazioniCaselle.setCasellaPescaUnaCarta(Boolean.parseBoolean(str));

                    case 4 -> impostazioniRegole.setDadoSingolo(Boolean.parseBoolean(str));

                    case 5 -> impostazioniRegole.setDoppioSei(Boolean.parseBoolean(str));

                    case 6 -> impostazioniRegole.setLancioSingolo(Boolean.parseBoolean(str));

                    case 7 -> impostazioniCaselle.setUlterioriCarte(Boolean.parseBoolean(str));

                    case 8 -> impostazioniTabellone.setRighe(Integer.parseInt(str));

                    case 9 -> impostazioniTabellone.setColonne(Integer.parseInt(str));

                    case 10 -> impostazioniTabellone.setNumeroScale(Integer.parseInt(str));

                    case 11 -> impostazioniTabellone.setNumeroSerpenti(Integer.parseInt(str));

                    case 12 -> impostazioniTabellone.setNumeroCasellePremio(Integer.parseInt(str));

                    case 13 -> impostazioniTabellone.setNumeroCaselleSosta(Integer.parseInt(str));

                    case 14 -> impostazioniTabellone.setNumeroCasellePescaUnaCarta(Integer.parseInt(str));

                    default -> throw new IllegalStateException("Errore nella fase: " + fase);
                }
                fase++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        configuraPartita();

    }

    private void gestioneAutomatizzazione() {
        if (sistemaTurni.getAutomatizza()) {
            return;
            //Avanzamento Automatico
        } else {
            return;
            //Avanzamento Manuale
        }
    }









    private void giocaDaTerminale(){
        menuTerminale();
    }

    private void menuTerminale(){
        inizializzaPartita();

        Scanner sc = new Scanner(System.in);
        int input;
        boolean scelto=false;

        while (!scelto){
            System.out.println("\n\n========================================");
            System.out.println("     CREARE O CARICARE LA PARTITA?      ");
            System.out.println("----------------------------------------");
            System.out.print("(1 per creare; 0 per caricare): ");
            input = sc.nextInt();
            if(input==0) {
                scelto = true;
                Scanner newScanner = new Scanner(System.in);
                System.out.print("Nome Configurazione: ");
                String nomeConfigurazione = newScanner.nextLine();
                newScanner.close();
                caricaPartita(nomeConfigurazione);
            } else if(input == 1){
                scelto = true;
                creaTerminale();
            } else {
                System.err.println("\nSi accetta solo 1 o 0 come risposta;");
            }
        }

        sc.close();
        gameInitAndLoopTerminale();

    }

    private void creaTerminale(){

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
                    System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Scale (Richiedono n*2): ");
                    input = sc.nextInt();
                    if (input * 2> caselleDisponibili) {
                        System.err.print("Caselle non disponibili;");
                        break;
                    } else if (input < 0) {
                        System.err.print("Inserire un numero maggiore di 0;\n");
                        break;
                    }
                    impostazioniTabellone.setNumeroScale(input);
                    caselleDisponibili -= input * 2;
                    System.out.print("\n(Caselle Disponibili: " + caselleDisponibili + "); Serpenti (Richiedono n*2): ");
                    input = sc.nextInt();
                    if (input * 2> caselleDisponibili) {
                        System.err.print("Caselle non disponibili;");
                        break;
                    } else if (input < 0) {
                        System.err.print("Inserire un numero maggiore di 0;\n");
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
                        } else if (input < 0) {
                            System.err.print("Inserire un numero maggiore di 0;\n");
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
                        } else if (input < 0) {
                            System.err.print("Inserire un numero maggiore di 0;\n");
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
                        } else if (input < 0) {
                            System.err.print("Inserire un numero maggiore di 0;\n");
                            break;
                        }
                        impostazioniTabellone.setNumeroCasellePescaUnaCarta(input);
                    }

                    System.out.println("\n========================================\n");
                    faseCorretta = true;
                }
                case 4 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("     VUOI SALVARE LA CONFIGURAZIONE?    ");
                    System.out.println("----------------------------------------");
                    System.out.print("(1 per dire si; 0 per dire no): ");
                    input = sc.nextInt();
                    if(input==1){
                        Scanner newScanner = new Scanner(System.in);
                        System.out.print("Nome Configurazione: ");
                        String nomeConfigurazione = newScanner.nextLine();
                        newScanner.close();
                        salvaPartita(nomeConfigurazione);
                        faseCorretta = true;
                    } else if (input == 0) {
                        faseCorretta = true;
                    } else {
                        System.err.println("\nSi accetta solo 1 o 0 come risposta;");
                    }
                }
                case 5 -> {
                    System.out.println("\n\n========================================");
                    System.out.println("             FINE CREAZIONE             ");
                    System.out.println("========================================\n");
                    finitaCreazione = true;
                }
            }
            if(faseCorretta)
                fase++;
        }

        sc.close();

       configuraPartita();

    }

    public void gameInitAndLoopTerminale(){

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

            int indiceGiocatoreAttuale = turno % impostazioniGiocatori.getNumeroGiocatori();
            Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

            System.out.println("----------------------------------------\n");
            System.out.println("TURNO "+ turno +"; GIOCATORE "+indiceGiocatoreAttuale +"\n");

            if(giocatoreAttuale.getAttesa()<=0){
                System.out.println(sistemaTurni.esegui(giocatoreAttuale));

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
                System.out.println("----------------------------------------");
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

}
