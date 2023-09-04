package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniCaselle;
import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;

public class TabelloneBuilderDirector {

    private TabelloneBuilderIF builder;

    public TabelloneBuilderDirector(TabelloneBuilderIF builder){
        this.builder = builder;
    }

    public void build(ImpostazioniCaselle impostazioniCaselle, ImpostazioniTabellone impostazioniTabellone){

        builder.buildBase(impostazioniTabellone);

        builder.setTraguardo();

        if(impostazioniCaselle.getCasellaPremio()){
            builder.buildCasellaPremio();
        }

        if(impostazioniCaselle.getCasellaSosta()){
            builder.buildCasellaSosta();
        }

        if(impostazioniCaselle.getUlterioriCarte()) {
            builder.buildCasellaPescaUnaCartaMod();
        } else if(impostazioniCaselle.getCasellaPescaUnaCarta()){
            builder.buildCasellaPescaUnaCarta();
        }

        builder.buildSerpenti();

        builder.buildScale();
    }

}
