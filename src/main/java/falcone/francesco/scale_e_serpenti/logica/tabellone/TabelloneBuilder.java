package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.caselle.*;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;

import java.util.Random;

public class TabelloneBuilder implements TabelloneBuilderIF{

    private Tabellone tabellone;
    private ImpostazioniTabellone impostazioniTabellone;

    public TabelloneBuilder( ){
    }

    @Override
    public void buildBase(ImpostazioniTabellone impostazioniTabellone) {
        this.impostazioniTabellone = impostazioniTabellone;
        tabellone = new Tabellone(impostazioniTabellone.getRighe(), impostazioniTabellone.getColonne());
    }

    @Override
    public void setTraguardo() {
        CasellaIF casella = new CasellaTraguardo( tabellone.getCasella(impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne()));
        tabellone.getCasella(impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne()).setAssegnata(true);
        tabellone.setCasella(casella);
    }

    @Override
    public void buildSerpenti() {
        int numeroSerpenti = impostazioniTabellone.getNumeroSerpenti();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroSerpenti>0){
            int indiceTestaSerpente = -1;
            int indiceCodaSerpente = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceTestaSerpente = random.nextInt(0, dimensione)+1;
                indiceCodaSerpente = random.nextInt(1, indiceTestaSerpente);
                if(!tabellone.getCasella(indiceTestaSerpente).getAssegnata() || !tabellone.getCasella(indiceCodaSerpente).getAssegnata()){
                    CasellaIF casella = new CasellaSerpente(tabellone.getCasella(indiceTestaSerpente), indiceCodaSerpente);
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    tabellone.getCasella(indiceCodaSerpente).setAssegnata(true);
                    numeroSerpenti--;
                    posizionato = true;
                }
            }
        }
    }

    @Override
    public void buildScale() {
        int numeroScale = impostazioniTabellone.getNumeroScale();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroScale>0){
            int indiceTestaScala = -1;
            int indiceCodaScala = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceTestaScala = random.nextInt(0, dimensione);
                indiceCodaScala = random.nextInt(indiceTestaScala, dimensione)+1;
                if(!tabellone.getCasella(indiceTestaScala).getAssegnata() || !tabellone.getCasella(indiceCodaScala).getAssegnata()){
                    CasellaIF casella = new CasellaScala(tabellone.getCasella(indiceCodaScala), indiceTestaScala);
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    tabellone.getCasella(indiceTestaScala).setAssegnata(true);
                    numeroScale--;
                    posizionato = true;
                }
            }
        }
    }

    @Override
    public void buildCasellaSosta() {
        int numeroCaselleSosta = impostazioniTabellone.getNumeroCaselleSosta();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroCaselleSosta>0){
            int indiceCasella = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(0, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    if ((random.nextInt(0,1)+1)%2==0){
                        CasellaIF casella =  new CasellaPanchina(tabellone.getCasella(indiceCasella));
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);

                    }
                    else {
                        CasellaIF casella = new CasellaLocanda(tabellone.getCasella(indiceCasella));
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);
                    }
                    posizionato=true;
                    numeroCaselleSosta--;
                }
            }
        }
    }

    @Override
    public void buildCasellaPremio() {
        int numeroCasellePremio = impostazioniTabellone.getNumeroCasellePremio();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroCasellePremio>0){
            int indiceCasella = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(0, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    if ((random.nextInt(0,1)+1)%2==0){
                        CasellaIF casella = new CasellaDadi(tabellone.getCasella(indiceCasella));
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);
                    }
                    else {
                        CasellaIF casella = new CasellaMolla(tabellone.getCasella(indiceCasella));
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);
                    }
                    posizionato=true;
                    numeroCasellePremio--;
                }
            }
        }
    }

    @Override
    public void buildCasellaPescaUnaCarta() {
        int numeroCasellePescaUnaCarta = impostazioniTabellone.getNumeroCasellePescaUnaCarta();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroCasellePescaUnaCarta>0){
            int indiceCasella = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(0, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    CasellaIF casella = new CasellaPescaUnaCarta(tabellone.getCasella(indiceCasella));
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    posizionato=true;
                    numeroCasellePescaUnaCarta--;
                }
            }
        }
    }

    @Override
    public void buildCasellaPescaUnaCartaMod() {
        int numeroCasellePescaUnaCarta = impostazioniTabellone.getNumeroCasellePescaUnaCarta();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroCasellePescaUnaCarta>0){
            int indiceCasella = -1;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(0, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    CasellaIF casella = new CasellaPescaUnaCartaMod(tabellone.getCasella(indiceCasella));
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    posizionato=true;
                    numeroCasellePescaUnaCarta--;
                }
            }
        }
    }

    public Tabellone getTabellone() {return this.tabellone;}
}
