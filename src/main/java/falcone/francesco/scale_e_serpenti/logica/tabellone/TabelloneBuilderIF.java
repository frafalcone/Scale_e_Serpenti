package falcone.francesco.scale_e_serpenti.logica.tabellone;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniTabellone;

public interface TabelloneBuilderIF {

    void buildBase(ImpostazioniTabellone impostazioniTabellone);
    void setTraguardo();
    void buildSerpenti();
    void buildScale();
    void buildCasellaSosta();
    void buildCasellaPremio();
    void buildCasellaPescaUnaCarta();
    void buildCasellaPescaUnaCartaMod();
    TabelloneIF getTabellone();
}
