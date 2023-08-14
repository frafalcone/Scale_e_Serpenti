package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;

public class TabelloneBuilder implements TabelloneBuilderIF{

    private Tabellone tabellone;
    private ImpostazioniTabellone impostazioniTabellone;

    public TabelloneBuilder(){
        this.impostazioniTabellone = impostazioniTabellone;
    }

    @Override
    public void buildBase(ImpostazioniTabellone impostazioniTabellone) {
    }

    @Override
    public void buildCasellaSosta() {
    }

    @Override
    public void buildCasellaPremio() {
    }

    @Override
    public void buildCasellaPescaUnaCarta() {
    }

    @Override
    public void buildCasellaPescaUnaCartaMod() {
    }

    public Tabellone getTabellone() {return this.tabellone;}
}
