package falcone.francesco.scale_e_serpenti.logica.turno;

import falcone.francesco.scale_e_serpenti.logica.impostazioni.ImpostazioniRegole;

public class SelettoreSistemaTurni {

    public TurnoIF seleziona(ImpostazioniRegole impostazioniRegole) {

        boolean dadoSingolo = impostazioniRegole.getDadoSingolo();
        boolean lancioSingolo = impostazioniRegole.getLancioSingolo();
        boolean doppioSei = impostazioniRegole.getDoppioSei();

        TurnoIF turnazione = null;

        if (!dadoSingolo && !lancioSingolo && !doppioSei) {
            turnazione = new TurnoA();
        }
        if (dadoSingolo && !lancioSingolo && !doppioSei) {
            turnazione = new TurnoB();
        }
        if (!dadoSingolo && lancioSingolo && !doppioSei) {
            turnazione = new TurnoC();
        }
        if (!dadoSingolo && !lancioSingolo && doppioSei) {
            turnazione = new TurnoD();
        }
        if (!dadoSingolo && lancioSingolo && doppioSei) {
            turnazione = new TurnoE();
        }

        return turnazione;
    }
}
