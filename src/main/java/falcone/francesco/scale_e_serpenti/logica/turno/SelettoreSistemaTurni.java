package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniRegole;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

public class SelettoreSistemaTurni {

    public TurnoIF seleziona(ImpostazioniRegole impostazioniRegole, TabelloneIF tabellone) {

        boolean dadoSingolo = impostazioniRegole.getDadoSingolo();
        boolean lancioSingolo = impostazioniRegole.getLancioSingolo();
        boolean doppioSei = impostazioniRegole.getDoppioSei();

        TurnoIF turnazione = null;

        if (!dadoSingolo && !lancioSingolo && !doppioSei) {
            turnazione = new TurnoA(tabellone);
        }
        if (dadoSingolo && !lancioSingolo && !doppioSei) {
            turnazione = new TurnoB(tabellone);
        }
        if (!dadoSingolo && lancioSingolo && !doppioSei) {
            turnazione = new TurnoC(tabellone);
        }
        if (!dadoSingolo && !lancioSingolo && doppioSei) {
            turnazione = new TurnoD(tabellone);
        }
        if (!dadoSingolo && lancioSingolo && doppioSei) {
            turnazione = new TurnoE(tabellone);
        }

        return turnazione;
    }
}
