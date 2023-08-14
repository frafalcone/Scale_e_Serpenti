package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;

public interface TabelloneBuilderIF {

    public void buildBase(ImpostazioniTabellone impostazioniTabellone);
    public void buildCasellaSosta();
    public void buildCasellaPremio();
    public void buildCasellaPescaUnaCarta();
    public void buildCasellaPescaUnaCartaMod();
    public TabelloneIF getTabellone();
}
