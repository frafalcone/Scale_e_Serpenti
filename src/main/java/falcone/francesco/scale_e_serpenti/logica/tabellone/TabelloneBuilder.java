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
            int indiceTestaSerpente;
            int indiceCodaSerpente;
            boolean posizionato = false;


            while(!posizionato){
                indiceTestaSerpente = random.nextInt(2, dimensione);
                indiceCodaSerpente = random.nextInt(1, indiceTestaSerpente);
                if(!tabellone.getCasella(indiceTestaSerpente).getAssegnata() && !tabellone.getCasella(indiceCodaSerpente).getAssegnata()){
                    CasellaIF casella = new CasellaSerpente(tabellone.getCasella(indiceTestaSerpente), indiceCodaSerpente);
                    if(casella.getIndiceCasella()==-1)
                        break;
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    tabellone.getCasella(indiceCodaSerpente).setAssegnata(true);
                    numeroSerpenti--;
                    posizionato = true;
                }
                else {
                    posizionato = false;
                }
            }
        }
    }

    @Override
    public void buildScale() {
        boolean traguardoAssegnato = false;

        int numeroScale = impostazioniTabellone.getNumeroScale();
        int dimensione = impostazioniTabellone.getRighe()*impostazioniTabellone.getColonne();
        Random random = new Random();

        while (numeroScale>0){
            int indiceBaseScala;
            int indiceFineScala;
            boolean posizionato = false;

            while(!posizionato){
                indiceBaseScala = random.nextInt(2, dimensione);
                indiceFineScala = random.nextInt(indiceBaseScala, dimensione);
                if(!traguardoAssegnato && (indiceFineScala == dimensione)){
                    CasellaIF casella = new CasellaScala(tabellone.getCasella(indiceBaseScala), indiceFineScala);
                    if(casella.getIndiceCasella()==-1)
                        break;
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    tabellone.getCasella(indiceFineScala).setAssegnata(true);
                    numeroScale--;
                    posizionato = true;
                    traguardoAssegnato = true;
                }
                else if(!tabellone.getCasella(indiceFineScala).getAssegnata() && !tabellone.getCasella(indiceBaseScala).getAssegnata()){
                    CasellaIF casella = new CasellaScala(tabellone.getCasella(indiceBaseScala), indiceFineScala);
                    if(casella.getIndiceCasella()==-1)
                        break;
                    casella.setAssegnata(true);
                    tabellone.setCasella(casella);
                    tabellone.getCasella(indiceFineScala).setAssegnata(true);
                    numeroScale--;
                    posizionato = true;
                }
                else {
                    posizionato = false;
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
            int indiceCasella;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(2, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    if ((random.nextInt(0,9)+1)%2==0){
                        CasellaIF casella =  new CasellaPanchina(tabellone.getCasella(indiceCasella));
                        if(casella.getIndiceCasella()==-1)
                            break;
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);

                    }
                    else {
                        CasellaIF casella = new CasellaLocanda(tabellone.getCasella(indiceCasella));
                        if(casella.getIndiceCasella()==-1)
                            break;
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
            int indiceCasella;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(2, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    if ((random.nextInt(0,9)+1)%2==0){
                        CasellaIF casella = new CasellaDadi(tabellone.getCasella(indiceCasella));
                        if(casella.getIndiceCasella()==-1)
                            break;
                        casella.setAssegnata(true);
                        tabellone.setCasella(casella);
                    }
                    else {
                        CasellaIF casella = new CasellaMolla(tabellone.getCasella(indiceCasella));
                        if(casella.getIndiceCasella()==-1)
                            break;
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
            int indiceCasella;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(2, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    CasellaIF casella = new CasellaPescaUnaCarta(tabellone.getCasella(indiceCasella));
                    if(casella.getIndiceCasella()==-1)
                        break;
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
            int indiceCasella;
            boolean posizionato = false;

            while(!posizionato){
                indiceCasella = random.nextInt(2, dimensione);
                if(!tabellone.getCasella(indiceCasella).getAssegnata()){
                    CasellaIF casella = new CasellaPescaUnaCartaMod(tabellone.getCasella(indiceCasella));
                    if(casella.getIndiceCasella()==-1)
                        break;
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
