package falcone.francesco.scale_e_serpenti.grafica;

import falcone.francesco.scale_e_serpenti.logica.caselle.*;
import falcone.francesco.scale_e_serpenti.logica.giocatore.Giocatore;
import falcone.francesco.scale_e_serpenti.logica.partita.Partita;
import falcone.francesco.scale_e_serpenti.logica.tabellone.TabelloneIF;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class MainWindow extends Application {

    private static Parent actualScene;

    private static boolean casellaPremio = false, casellaSosta = false, casellaPescaCarta = false,
            doppioSei = false, dadoSingolo = false, lancioSingolo = false, ulterioriCarte = false;

    private static int[] parametri = new int[15];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Scale e Serpenti");

        Pane placeHolder = new Pane();
        Scene scene = new Scene(placeHolder);

        actualScene = schermataIniziale(scene, stage);

        scene.setRoot(actualScene);

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    private static Parent schermataIniziale(Scene scene, Stage stage){

        stage.setWidth(640);
        stage.setHeight(480);
        stage.centerOnScreen();

        VBox root = new VBox();
        root.setPrefSize(640, 480);
        root.setSpacing(50);

        root.setAlignment(Pos.CENTER);

        Button nuova = new Button();
        nuova.setText("Nuova Configurazione");

        Button carica = new Button();
        carica.setText("Carica Configurazione");

        Button esci = new Button();
        esci.setText("Esci");

        nuova.setPrefSize(150,25);
        carica.setPrefSize(150, 25);
        esci.setPrefSize(150, 25);

        nuova.setFocusTraversable(false);
        carica.setFocusTraversable(false);
        esci.setFocusTraversable(false);

        nuova.setOnMouseClicked(mouseEvent -> {
            actualScene = schermataCreazione(scene, stage);
            scene.setRoot(actualScene);
        });

        carica.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SAVE files (*.save)", "*.save");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                Partita partita = Partita.getPartita();
                partita.inizializzaPartita();
                partita.caricaPartita(file.getAbsolutePath());

                actualScene = schermataGioco(scene, stage);
                scene.setRoot(actualScene);
            }
        });

        esci.setOnMouseClicked(mouseEvent -> {
            Platform.exit();
        });

        root.getChildren().addAll(nuova, carica, esci);

        Parent content = root;

        return content;
    }

    private static Parent schermataCreazione(Scene scene, Stage stage){

        stage.setWidth(640);
        stage.setHeight(480);
        stage.centerOnScreen();

        VBox root = new VBox();
        root.setPrefSize(640, 480);
        root.setSpacing(50);

        //DEFINIZIONI VARIABILI PARTE DI CONTROLLO

        VBox regoleRoot = new VBox();
        regoleRoot.setAlignment(Pos.CENTER);
        regoleRoot.setSpacing(15);

        Label titoloRegole = new Label();
        titoloRegole.setText("REGOLE");
        titoloRegole.setAlignment(Pos.CENTER);

        HBox backMenuRegole = new HBox();

        backMenuRegole.setAlignment(Pos.CENTER);

        VBox AmenuUno = new VBox();
        VBox AmenuDue = new VBox();
        VBox AmenuTre = new VBox();

        AmenuUno.setAlignment(Pos.TOP_CENTER);
        AmenuDue.setAlignment(Pos.TOP_CENTER);
        AmenuTre.setAlignment(Pos.TOP_CENTER);

        AmenuUno.setSpacing(10);
        AmenuDue.setSpacing(10);
        AmenuTre.setSpacing(10);

        HBox ArigaUnoUno = new HBox();
        HBox ArigaUnoDue = new HBox();
        HBox ArigaUnoTre = new HBox();

        HBox ArigaDueUno = new HBox();
        HBox ArigaDueDue = new HBox();
        HBox ArigaDueTre = new HBox();

        HBox ArigaTreUno = new HBox();

        ArigaUnoUno.setSpacing(5);
        ArigaUnoDue.setSpacing(5);
        ArigaUnoTre.setSpacing(5);

        ArigaDueUno.setSpacing(5);
        ArigaDueDue.setSpacing(5);
        ArigaDueTre.setSpacing(5);

        ArigaTreUno.setSpacing(5);

        Label AtestoRegolaUno  = new Label();
        CheckBox checkRegolaUno = new CheckBox();
        checkRegolaUno.setFocusTraversable(false);

        Label AtestoRegolaDue  = new Label();
        CheckBox checkRegolaDue = new CheckBox();
        checkRegolaDue.setFocusTraversable(false);

        Label AtestoRegolaTre  = new Label();
        CheckBox checkRegolaTre = new CheckBox();
        checkRegolaTre.setFocusTraversable(false);

        Label AtestoRegolaQuattro  = new Label();
        CheckBox checkRegolaQuattro = new CheckBox();
        checkRegolaQuattro.setFocusTraversable(false);

        Label AtestoRegolaCinque  = new Label();
        CheckBox checkRegolaCinque = new CheckBox();
        checkRegolaCinque.setFocusTraversable(false);

        Label AtestoRegolaSei = new Label();
        CheckBox checkRegolaSei = new CheckBox();
        checkRegolaSei.setFocusTraversable(false);

        Label AtestoRegolaSette = new Label();
        CheckBox checkRegolaSette = new CheckBox();
        checkRegolaSette.setFocusTraversable(false);

        checkRegolaSette.setDisable(true);

        AtestoRegolaUno.setText("Caselle Premio");
        AtestoRegolaDue.setText("Caselle Sosta");
        AtestoRegolaTre.setText("Caselle Pesca Carta");

        AtestoRegolaQuattro.setText("Dado Singolo");
        AtestoRegolaCinque.setText("Doppio Sei");
        AtestoRegolaSei.setText("Lancio Singolo");

        AtestoRegolaSette.setText("Ulteriori Carte");

        VBox parametriRoot = new VBox();
        parametriRoot.setAlignment(Pos.CENTER);
        parametriRoot.setSpacing(15);

        Label titoloParametri = new Label();
        titoloParametri.setText("PARAMETRI");
        titoloParametri.setAlignment(Pos.CENTER);




        // DEFINIZIONI DELLE VARIABILI PARTE PARAMETRICA

        HBox backMenuParametri = new HBox();

        backMenuParametri.setAlignment(Pos.CENTER);

        VBox BmenuUno = new VBox();
        VBox BmenuDue = new VBox();
        VBox BmenuTre = new VBox();

        BmenuUno.setAlignment(Pos.TOP_CENTER);
        BmenuDue.setAlignment(Pos.TOP_CENTER);
        BmenuTre.setAlignment(Pos.TOP_CENTER);

        BmenuUno.setSpacing(10);
        BmenuDue.setSpacing(10);
        BmenuTre.setSpacing(10);

        HBox BrigaUnoUno = new HBox();
        HBox BrigaUnoDue = new HBox();
        HBox BrigaUnoTre = new HBox();

        HBox BrigaDueUno = new HBox();
        HBox BrigaDueDue = new HBox();
        HBox BrigaDueTre = new HBox();

        HBox rigaTreUno = new HBox();
        HBox rigaTreDue = new HBox();

        BrigaUnoUno.setSpacing(5);
        BrigaUnoDue.setSpacing(5);
        BrigaUnoTre.setSpacing(5);

        BrigaDueUno.setSpacing(5);
        BrigaDueDue.setSpacing(5);
        BrigaDueTre.setSpacing(5);

        rigaTreUno.setSpacing(5);
        rigaTreDue.setSpacing(5);

        TextField BtestoRegolaUno  = new TextField();
        TextField BtestoRegolaDue  = new TextField();
        TextField BtestoRegolaTre  = new TextField();

        TextField BtestoRegolaQuattro  = new TextField();
        TextField BtestoRegolaCinque  = new TextField();
        TextField BtestoRegolaSei = new TextField();

        TextField BtestoRegolaSette = new TextField();
        TextField BtestoRegolaOtto = new TextField();

        BtestoRegolaUno.setPromptText("Numero Giocatori");
        BtestoRegolaDue.setPromptText("Righe Tabellone");
        BtestoRegolaTre.setPromptText("Colonne Tabellone");

        BtestoRegolaQuattro.setPromptText("Numero Caselle Premio");
        BtestoRegolaCinque.setPromptText("Numero Caselle Sosta");
        BtestoRegolaSei.setPromptText("Numero Caselle P. C.");

        BtestoRegolaQuattro.setDisable(true);
        BtestoRegolaCinque.setDisable(true);
        BtestoRegolaSei.setDisable(true);

        BtestoRegolaSette.setPromptText("Numero Caselle Scale");
        BtestoRegolaOtto.setPromptText("Numero Caselle Serpenti");

        BtestoRegolaUno.setFocusTraversable(false);
        BtestoRegolaDue.setFocusTraversable(false);
        BtestoRegolaTre.setFocusTraversable(false);
        BtestoRegolaQuattro.setFocusTraversable(false);
        BtestoRegolaCinque.setFocusTraversable(false);
        BtestoRegolaSei.setFocusTraversable(false);
        BtestoRegolaSette.setFocusTraversable(false);
        BtestoRegolaOtto.setFocusTraversable(false);




        //PARTE DI CONTROLLO

        checkRegolaUno.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    casellaPremio = checkRegolaUno.isSelected();
                    if(checkRegolaUno.isSelected()){
                        parametri[1] = 1;
                    } else {
                        parametri[1] = 0;
                        BtestoRegolaQuattro.setText("");
                    }

                    BtestoRegolaQuattro.setDisable(!casellaPremio);
                });

        checkRegolaDue.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    casellaSosta = checkRegolaDue.isSelected();
                    if(checkRegolaDue.isSelected()){
                        parametri[2] = 1;
                    } else {
                        parametri[2] = 0;
                        BtestoRegolaCinque.setText("");
                    }

                    BtestoRegolaCinque.setDisable(!casellaSosta);
                });

        checkRegolaTre.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    casellaPescaCarta = checkRegolaTre.isSelected();
                    checkRegolaSette.setDisable(!casellaPescaCarta);

                    if(!casellaPescaCarta){
                        checkRegolaSette.setSelected(false);
                    }

                    if(checkRegolaTre.isSelected()){
                        parametri[3] = 1;
                    } else {
                        parametri[3] = 0;
                        BtestoRegolaSei.setText("");
                    }

                    BtestoRegolaSei.setDisable(!casellaPescaCarta);
                });

        checkRegolaQuattro.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    dadoSingolo = checkRegolaQuattro.isSelected();

                    checkRegolaCinque.setDisable(checkRegolaQuattro.isSelected());
                    checkRegolaSei.setDisable(checkRegolaQuattro.isSelected());

                    if(dadoSingolo){
                        checkRegolaCinque.setSelected(false);
                        checkRegolaSei.setSelected(false);
                    }

                    if(checkRegolaQuattro.isSelected()){
                        parametri[4] = 1;
                    } else {
                        parametri[4] = 0;
                    }
                });

        checkRegolaCinque.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    doppioSei = checkRegolaCinque.isSelected();

                    checkRegolaQuattro.setDisable(doppioSei);
                    if(doppioSei){
                        checkRegolaQuattro.setSelected(false);
                    }

                    if(checkRegolaCinque.isSelected()){
                        parametri[5] = 1;
                    } else {
                        parametri[5] = 0;
                    }
                });

        checkRegolaSei.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    lancioSingolo = checkRegolaSei.isSelected();

                    checkRegolaQuattro.setDisable(lancioSingolo);
                    if(lancioSingolo){
                        checkRegolaQuattro.setSelected(false);
                    }

                    if(checkRegolaSei.isSelected()){
                        parametri[6] = 1;
                    } else {
                        parametri[6] = 0;
                    }
                });

        checkRegolaSette.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    ulterioriCarte = checkRegolaSette.isSelected();
                    if(checkRegolaSette.isSelected()){
                        parametri[7] = 1;
                    } else {
                        parametri[7] = 0;
                    }
                });




        //AGGIUNTA ALLA ROOT DELLA PARTE REGOLE

        ArigaUnoUno.getChildren().addAll(checkRegolaUno, AtestoRegolaUno);
        ArigaUnoDue.getChildren().addAll(checkRegolaDue, AtestoRegolaDue);
        ArigaUnoTre.getChildren().addAll(checkRegolaTre, AtestoRegolaTre);

        ArigaDueUno.getChildren().addAll(checkRegolaQuattro,AtestoRegolaQuattro);
        ArigaDueDue.getChildren().addAll(checkRegolaCinque, AtestoRegolaCinque);
        ArigaDueTre.getChildren().addAll(checkRegolaSei, AtestoRegolaSei);

        ArigaTreUno.getChildren().addAll(checkRegolaSette, AtestoRegolaSette );

        AmenuUno.getChildren().addAll(ArigaUnoUno, ArigaUnoDue, ArigaUnoTre);
        AmenuDue.getChildren().addAll(ArigaDueUno, ArigaDueDue, ArigaDueTre);
        AmenuTre.getChildren().addAll(ArigaTreUno);

        backMenuRegole.getChildren().addAll(AmenuUno, AmenuDue, AmenuTre);
        backMenuRegole.setSpacing(50);

        regoleRoot.getChildren().addAll(titoloRegole, backMenuRegole);

        root.getChildren().addAll(regoleRoot);




        // AGGIUNTA ALLA ROOT DELLA PARTE PARAMETRI

        BrigaUnoUno.getChildren().addAll(BtestoRegolaUno);
        BrigaUnoDue.getChildren().addAll(BtestoRegolaDue);
        BrigaUnoTre.getChildren().addAll(BtestoRegolaTre);

        BrigaDueUno.getChildren().addAll(BtestoRegolaQuattro);
        BrigaDueDue.getChildren().addAll(BtestoRegolaCinque);
        BrigaDueTre.getChildren().addAll(BtestoRegolaSei);

        rigaTreUno.getChildren().addAll(BtestoRegolaSette);
        rigaTreDue.getChildren().addAll(BtestoRegolaOtto);

        BmenuUno.getChildren().addAll(BrigaUnoUno, BrigaUnoDue, BrigaUnoTre);
        BmenuDue.getChildren().addAll(BrigaDueUno, BrigaDueDue, BrigaDueTre);
        BmenuTre.getChildren().addAll(rigaTreUno, rigaTreDue);

        backMenuParametri.getChildren().addAll(BmenuUno, BmenuDue, BmenuTre);
        backMenuParametri.setSpacing(50);

        parametriRoot.getChildren().addAll(titoloParametri, backMenuParametri);

        root.getChildren().addAll(parametriRoot);




        //AGGIUNTA DEI BOTTONI DI COMANDO

        HBox menuComandi = new HBox();
        menuComandi.setAlignment(Pos.CENTER);
        menuComandi.setSpacing(50);

        root.setAlignment(Pos.CENTER);

        Button indietro = new Button();
        indietro.setText("Indietro");

        Button salva = new Button();
        salva.setText("Salva");

        Button inizia = new Button();
        inizia.setText("Inizia");

        indietro.setPrefSize(150,25);
        indietro.setFocusTraversable(false);

        salva.setPrefSize(150,25);
        salva.setFocusTraversable(false);

        inizia.setPrefSize(150,25);
        inizia.setFocusTraversable(false);

        indietro.setOnMouseClicked(mouseEvent -> {
            for(int i=0; i<parametri.length; i++){
                parametri[i]=0;
            }
            casellaPremio=false;
            casellaSosta=false;
            casellaPescaCarta=false;
            ulterioriCarte=false;
            dadoSingolo=false;
            doppioSei=false;
            lancioSingolo=false;

            actualScene = schermataIniziale(scene, stage);
            scene.setRoot(actualScene);
        });

        salva.setOnMouseClicked(mouseEvent -> {

            parametri[0] = (!BtestoRegolaUno.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaUno.getText()) : 0;

            parametri[1] = (casellaPremio) ? 1 : 0;
            parametri[2] = (casellaSosta) ? 1 : 0;
            parametri[3] = (casellaPescaCarta) ? 1 : 0;
            parametri[4] = (ulterioriCarte) ? 1 : 0;
            parametri[5] = (dadoSingolo) ? 1 : 0;
            parametri[6] = (doppioSei) ? 1 : 0;
            parametri[7] = (lancioSingolo) ? 1 : 0;

            parametri[8] = (!BtestoRegolaDue.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaDue.getText()) : 0;
            parametri[9] = (!BtestoRegolaTre.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaTre.getText()) : 0;


            parametri[10] = (!BtestoRegolaSette.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaSette.getText()) : 0;
            parametri[11] = (!BtestoRegolaOtto.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaOtto.getText()) : 0;


            if(casellaPremio)
                parametri[12] = (!BtestoRegolaQuattro.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaQuattro.getText()) : 0;

            if(casellaSosta)
                parametri[13] = (!BtestoRegolaCinque.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaCinque.getText()) : 0;

            if(casellaPescaCarta)
                parametri[14] = (!BtestoRegolaSei.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaSei.getText()) : 0;

            errorCheck();

            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SAVE files (*.save)", "*.save");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                Partita partita = Partita.getPartita();
                partita.inizializzaPartita();
                partita.passaParametri(parametri);
                partita.salvaPartita(file.getAbsolutePath());
            }
        });

        inizia.setOnMouseClicked(mouseEvent -> {
                parametri[0] = (!BtestoRegolaUno.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaUno.getText()) : 0;

                parametri[1] = (casellaPremio) ? 1 : 0;
                parametri[2] = (casellaSosta) ? 1 : 0;
                parametri[3] = (casellaPescaCarta) ? 1 : 0;
                parametri[4] = (ulterioriCarte) ? 1 : 0;
                parametri[5] = (dadoSingolo) ? 1 : 0;
                parametri[6] = (doppioSei) ? 1 : 0;
                parametri[7] = (lancioSingolo) ? 1 : 0;

                parametri[8] = (!BtestoRegolaDue.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaDue.getText()) : 0;
                parametri[9] = (!BtestoRegolaTre.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaTre.getText()) : 0;


                parametri[10] = (!BtestoRegolaSette.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaSette.getText()) : 0;
                parametri[11] = (!BtestoRegolaOtto.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaOtto.getText()) : 0;


                if(casellaPremio)
                    parametri[12] = (!BtestoRegolaQuattro.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaQuattro.getText()) : 0;

                if(casellaSosta)
                    parametri[13] = (!BtestoRegolaCinque.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaCinque.getText()) : 0;

                if(casellaPescaCarta)
                    parametri[14] = (!BtestoRegolaSei.getText().isEmpty()) ? Integer.parseInt(BtestoRegolaSei.getText()) : 0;

                errorCheck();

                Partita partita = Partita.getPartita();
                partita.inizializzaPartita();
                partita.passaParametri(parametri);
                partita.configuraPartita();

                actualScene = schermataGioco(scene, stage);
                scene.setRoot(actualScene);
        });

        menuComandi.getChildren().addAll(indietro, salva, inizia);

        root.getChildren().add(menuComandi);

        Parent content = root;

        return content;
    }

    private static Parent schermataGioco(Scene scene, Stage stage){

        stage.setWidth(1280);
        stage.setHeight(720);
        stage.centerOnScreen();

        HBox root = new HBox();

        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);

        VBox menuLaterale = new VBox();
        menuLaterale.setMinSize(450, 720);

        menuLaterale.setSpacing(20);

        Canvas canvasLg = new Canvas(420,300);
        GraphicsContext gcLg = canvasLg.getGraphicsContext2D();

        disegnaLegenda(gcLg);

        TextArea testoTurno = new TextArea();
        testoTurno.setWrapText(true);
        testoTurno.setEditable(false);
        testoTurno.setFocusTraversable(false);

        HBox pulsanti = new HBox();
        HBox automatico = new HBox();

        pulsanti.setAlignment(Pos.CENTER);

        Label labelAutomatico = new Label();
        CheckBox turnoAutomatico = new CheckBox();
        turnoAutomatico.setFocusTraversable(false);

        labelAutomatico.setText("Turnazione Automatica");
        automatico.setSpacing(5);
        automatico.getChildren().addAll(turnoAutomatico, labelAutomatico);

        Button avanzaTurno = new Button();
        avanzaTurno.setText("Turno Successivo");
        avanzaTurno.setPrefSize(150,25);
        avanzaTurno.setFocusTraversable(false);

        inizializzaGioco();

        testoTurno.setText(azioneTurno);


        pulsanti.setSpacing(50);
        pulsanti.getChildren().addAll(automatico, avanzaTurno);

        menuLaterale.getChildren().addAll(canvasLg, testoTurno, pulsanti);
        menuLaterale.setAlignment(Pos.CENTER);


        VBox tabelloneBg = new VBox();
        tabelloneBg.setAlignment(Pos.CENTER);

        StackPane tabellone = new StackPane();

        Rectangle backGroundCanvas_0 = new Rectangle(655,655, Color.BLACK);
        Rectangle backGroundCanvas_1 = new Rectangle(654,654, Color.ROSYBROWN);

        Canvas canvasTb = new Canvas(600,600);
        GraphicsContext graphicsContextTb = canvasTb.getGraphicsContext2D();

        coordinateX = new double[Partita.getPartita().getImpostazioniTabellone().getRighe()*Partita.getPartita().getImpostazioniTabellone().getColonne()];
        coordinateY = new double[Partita.getPartita().getImpostazioniTabellone().getRighe()*Partita.getPartita().getImpostazioniTabellone().getColonne()];

        disegnaTabellone(graphicsContextTb, coordinateX, coordinateY);


        double casellaX = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getRighe();
        double casellaY = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getColonne();


        graphicsContextTb.setFill(Color.BLACK);
        graphicsContextTb.fillRect((coordinateY[0])+(casellaY/4), coordinateX[0]+(casellaX/4), (casellaY)/2, (casellaX)/2);
        graphicsContextTb.setFill(Color.DARKGRAY);
        graphicsContextTb.fillRect((coordinateY[0])+(Partita.getPartita().getTabellone().getColonne()/2)+(casellaY/4), coordinateX[0]+(Partita.getPartita().getTabellone().getRighe()/2)+(casellaX/4), (casellaY/2)-Partita.getPartita().getTabellone().getColonne(), (casellaX/2)-Partita.getPartita().getTabellone().getRighe());


        turnoAutomatico.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {


                    avanzaTurno.setDisable(turnoAutomatico.isSelected());

                    class Automatizzato extends Thread {

                        private static boolean concluso = false;

                        public void run(){

                            while(turnoAutomatico.isSelected() && !concluso){
                                StringBuilder stbr = new StringBuilder();

                                int indiceGiocatoreAttuale = turnoAttuale % Partita.getPartita().getImpostazioniGiocatori().getNumeroGiocatori();
                                Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

                                stbr.append("TURNO "+ turnoAttuale+"; GIOCATORE "+indiceGiocatoreAttuale+"\n");

                                if(giocatoreAttuale.getAttesa()<=0){
                                    stbr.append(Partita.getPartita().getSistemaTurni().esegui(giocatoreAttuale));

                                    if(giocatoreAttuale.getUsatoDivietoSosta()){
                                        CasellaPescaUnaCartaMod.riconsegnaDivietoSosta(giocatoreAttuale);
                                    }

                                    giocatoreAttuale.setMolla(false);
                                    giocatoreAttuale.setRigioca(false);


                                    if(giocatoreAttuale.getHaVinto()){
                                        concluso=true;
                                    }
                                }
                                else {
                                    if(giocatoreAttuale.getAttesa()-1==0){
                                        stbr.append("\nGiocatore deve attendere per questo turno;");
                                    } else {
                                        stbr.append("\nGiocatore deve attendere per altri "+ (giocatoreAttuale.getAttesa()) +" turni;");
                                    }
                                    giocatoreAttuale.setAttesa(giocatoreAttuale.getAttesa() - 1);
                                }

                                azioneTurno = stbr.toString();

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        testoTurno.setText(azioneTurno);
                                    }
                                });

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        disegnaTabellone(graphicsContextTb, coordinateX, coordinateY);

                                        double casellaX = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getRighe();
                                        double casellaY = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getColonne();

                                        for (int i = 0; i < giocatori.length; i++) {
                                            double posizioneSuTabelloneX = (giocatori[i].getPosizione()!=0) ? coordinateX[giocatori[i].getPosizione()-1] : coordinateX[0];
                                            double posizioneSuTabelloneY = (giocatori[i].getPosizione()!=0) ? coordinateY[giocatori[i].getPosizione()-1] : coordinateY[0];

                                            graphicsContextTb.setFill(Color.BLACK);
                                            graphicsContextTb.fillRect((posizioneSuTabelloneY)+(casellaY/4), posizioneSuTabelloneX+(casellaX/4), (casellaY)/2, (casellaX)/2);
                                            graphicsContextTb.setFill(Color.DARKGRAY);
                                            graphicsContextTb.fillRect((posizioneSuTabelloneY)+(Partita.getPartita().getTabellone().getColonne()/2)+(casellaY/4), posizioneSuTabelloneX+(Partita.getPartita().getTabellone().getRighe()/2)+(casellaX/4), (casellaY/2)-Partita.getPartita().getTabellone().getColonne(), (casellaX/2)-Partita.getPartita().getTabellone().getRighe());
                                            graphicsContextTb.setFill(Color.BLACK);
                                            graphicsContextTb.fillText((i)+"",(posizioneSuTabelloneY+((casellaY)/2.25)), posizioneSuTabelloneX+((casellaX)/1.75));

                                        }

                                    }
                                });

                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }


                                aggiornaTurno();
                            }

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    if(concluso) {
                                        Button indietro = new Button();
                                        indietro.setText("Torna Al Menu Principale");
                                        indietro.setPrefSize(150, 25);
                                        indietro.setFocusTraversable(false);

                                        indietro.setOnMouseClicked(mouseEvent2 -> {
                                            actualScene = schermataIniziale(scene, stage);
                                            scene.setRoot(actualScene);
                                        });

                                        menuLaterale.getChildren().add(indietro);

                                        concluso=false;

                                        avanzaTurno.setDisable(true);
                                        turnoAutomatico.setSelected(false);
                                        turnoAutomatico.setDisable(true);
                                    }
                                }
                            });
                        }
                    }

                    if(turnoAutomatico.isSelected()){
                        new Automatizzato().start();
                    }


                });

        avanzaTurno.setOnMouseClicked(mouseEvent -> {

            class Turno extends Thread {

                private static boolean concluso = false;
                public void run() {

                    StringBuilder stbr = new StringBuilder();

                    int indiceGiocatoreAttuale = turnoAttuale % Partita.getPartita().getImpostazioniGiocatori().getNumeroGiocatori();
                    Giocatore giocatoreAttuale = giocatori[indiceGiocatoreAttuale];

                    stbr.append("TURNO " + turnoAttuale + "; GIOCATORE " + indiceGiocatoreAttuale + "\n");

                    if (giocatoreAttuale.getAttesa() <= 0) {
                        stbr.append(Partita.getPartita().getSistemaTurni().esegui(giocatoreAttuale));

                        if (giocatoreAttuale.getUsatoDivietoSosta()) {
                            CasellaPescaUnaCartaMod.riconsegnaDivietoSosta(giocatoreAttuale);
                        }

                        giocatoreAttuale.setMolla(false);
                        giocatoreAttuale.setRigioca(false);

                        if (giocatoreAttuale.getHaVinto()) {
                            concluso = true;
                        }
                    } else {
                        if(giocatoreAttuale.getAttesa()-1==0){
                            stbr.append("\nGiocatore deve attendere per questo turno;");
                        } else {
                            stbr.append("\nGiocatore deve attendere per altri "+ (giocatoreAttuale.getAttesa()) +" turni;");
                        }

                        giocatoreAttuale.setAttesa(giocatoreAttuale.getAttesa() - 1);
                    }

                    azioneTurno = stbr.toString();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            testoTurno.setText(azioneTurno);
                        }
                    });

                    aggiornaTurno();


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            disegnaTabellone(graphicsContextTb, coordinateX, coordinateY);

                            double casellaX = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getRighe();
                            double casellaY = 600 / (double) Partita.getPartita().getImpostazioniTabellone().getColonne();

                            for (int i = 0; i < giocatori.length; i++) {
                                double posizioneSuTabelloneX = (giocatori[i].getPosizione()!=0) ? coordinateX[giocatori[i].getPosizione()-1] : coordinateX[0];
                                double posizioneSuTabelloneY = (giocatori[i].getPosizione()!=0) ? coordinateY[giocatori[i].getPosizione()-1] : coordinateY[0];

                                graphicsContextTb.setFill(Color.BLACK);
                                graphicsContextTb.fillRect((posizioneSuTabelloneY)+(casellaY/4), posizioneSuTabelloneX+(casellaX/4), (casellaY)/2, (casellaX)/2);
                                graphicsContextTb.setFill(Color.WHITE);
                                graphicsContextTb.fillRect((posizioneSuTabelloneY)+(Partita.getPartita().getTabellone().getColonne()/2)+(casellaY/4), posizioneSuTabelloneX+(Partita.getPartita().getTabellone().getRighe()/2)+(casellaX/4), (casellaY/2)-Partita.getPartita().getTabellone().getColonne(), (casellaX/2)-Partita.getPartita().getTabellone().getRighe());
                                graphicsContextTb.setFill(Color.BLACK);
                                graphicsContextTb.fillText((i)+"",(posizioneSuTabelloneY+((casellaY)/2.25)), posizioneSuTabelloneX+((casellaX)/1.75));

                            }

                        }
                    });

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            if (concluso) {
                                Button indietro = new Button();
                                indietro.setText("Torna Al Menu Principale");
                                indietro.setPrefSize(150, 25);
                                indietro.setFocusTraversable(false);

                                indietro.setOnMouseClicked(mouseEvent2 -> {
                                    actualScene = schermataIniziale(scene, stage);
                                    scene.setRoot(actualScene);
                                });

                                menuLaterale.getChildren().add(indietro);

                                concluso = false;
                                avanzaTurno.setDisable(true);
                                turnoAutomatico.setSelected(false);
                                turnoAutomatico.setDisable(true);
                            }
                        }
                    });
                }
            }

            new Turno().start();
        });

        tabellone.setAlignment(Pos.CENTER);

        tabellone.getChildren().addAll(backGroundCanvas_0, backGroundCanvas_1, canvasTb);
        tabelloneBg.getChildren().add(tabellone);

        root.getChildren().add(menuLaterale);
        root.getChildren().add(tabelloneBg);

        Parent content = root;

        return content;
    }

    private static void disegnaLegenda(GraphicsContext gc){

        //Base Legenda

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, 420, 300);
        gc.setFill(Color.WHITE);
        gc.fillRect(1,1, 418, 298);

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, 420, 300);
        gc.setFill(Color.WHITE);
        gc.fillRect(1,1, 418, 298);



        //Scale
        gc.setFill(Color.BLACK);
        gc.fillRect(30,20, 45, 45);
        gc.setFill(Color.rgb(176,161,186));
        gc.fillRect(31,21, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("SCALE", 90, 45);

        //Serpenti
        gc.setFill(Color.BLACK);
        gc.fillRect(30,80, 45, 45);
        gc.setFill(Color.rgb(155,197,61));
        gc.fillRect(31,81, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("SERPENTI", 90, 60+45);



        //Dadi
        gc.setFill(Color.BLACK);
        gc.fillRect(30,180, 45, 45);
        gc.setFill(Color.rgb(195,66,63));
        gc.fillRect(31,181, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("DADO", 90, 160+45);

        //Molla
        gc.setFill(Color.BLACK);
        gc.fillRect(30,240, 45, 45);
        gc.setFill(Color.rgb(249,160,63));
        gc.fillRect(31,241, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("MOLLA", 90, 220+45);



        //Panchina
        gc.setFill(Color.BLACK);
        gc.fillRect(230,20, 45, 45);
        gc.setFill(Color.rgb(95,191,249));
        gc.fillRect(231,21, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("PANCHINA", 200+90, 45);

        //Locanda
        gc.setFill(Color.BLACK);
        gc.fillRect(230,80, 45, 45);
        gc.setFill(Color.rgb(72,86,150));
        gc.fillRect(231,81, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("LOCANDA", 200+90, 60+45);



        //Pesca una Carta
        gc.setFill(Color.BLACK);
        gc.fillRect(230,180, 45, 45);
        gc.setFill(Color.rgb(210,149,191));
        gc.fillRect(231,181, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("PESCA UNA CARTA", 200+90, 160+45);


        //Neutra
        gc.setFill(Color.BLACK);
        gc.fillRect(230,240, 45, 45);
        gc.setFill(Color.rgb(232,225,239));
        gc.fillRect(231,241, 43, 43);

        gc.setFill(Color.BLACK);
        gc.fillText("CASELLA NEUTRA", 200+90, 220+45);


        gc.stroke();
    }

    private static void disegnaTabellone(GraphicsContext gc, double[] x, double[] y) {
        gc.rect(0,0,600, 600);

        gc.setFill(Color.rgb(232,225,239));
        gc.fillRect(0, 0, 600, 600);

        TabelloneIF tabellone = Partita.getPartita().getTabellone();

        int righe = Partita.getPartita().getImpostazioniTabellone().getRighe();
        int colonne = Partita.getPartita().getImpostazioniTabellone().getColonne();

        double casellaX = 600 / (double) righe;
        double casellaY = 600 / (double) colonne;

        int posizione = 1;

        for(int i=righe-1; i>=0; i--) {
            if (i % 2 != 0){
                    for (int j = 0; j < colonne; j++) {

                        double v = (double) j * (casellaY);
                        double v1 = (double) (i) * (casellaX);
                        double v2 = casellaY;
                        double v3 = casellaX;

                        x[posizione-1] = v1;
                        y[posizione-1] = v;

                        if (tabellone.getCasella(posizione) instanceof CasellaScala) {
                            gc.setFill(Color.rgb(176,161,186));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaSerpente) {
                            gc.setFill(Color.rgb(155,197,61));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaDadi) {
                            gc.setFill(Color.rgb(195,66,63));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaMolla) {
                            gc.setFill(Color.rgb(249,160,63));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaPanchina) {
                            gc.setFill(Color.rgb(95,191,249));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaLocanda) {
                            gc.setFill(Color.rgb(72,86,150));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaPescaUnaCarta || tabellone.getCasella(posizione) instanceof CasellaPescaUnaCartaMod) {
                            gc.setFill(Color.rgb(210,149,191));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (tabellone.getCasella(posizione) instanceof CasellaTraguardo) {
                            gc.setFill(Color.rgb(107,5,4));
                            gc.fillRect(v, v1, v2, v3);
                        } else if (posizione == 1) {
                            gc.setFill(Color.rgb(232,225,239));
                            gc.fillRect(v, v1, v2, v3);
                        }

                        gc.setFill(Color.BLACK);

                        gc.fillText(posizione + "", (j) * casellaY + 3, (i + 1) * casellaX - 4);
                        gc.rect(v, v1, v2, v3);

                        posizione++;
                    }
            }
            else {
                for (int j = colonne-1; j >= 0; j--) {

                    double v = (double) j * (casellaY);
                    double v1 = (double) (i) * (casellaX);
                    double v2 = (double) casellaY;
                    double v3 = (double) casellaX;

                    x[posizione-1] = v1;
                    y[posizione-1] = v;

                    if (tabellone.getCasella(posizione) instanceof CasellaScala) {
                        gc.setFill(Color.rgb(176,161,186));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaSerpente) {
                        gc.setFill(Color.rgb(155,197,61));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaDadi) {
                        gc.setFill(Color.rgb(195,66,63));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaMolla) {
                        gc.setFill(Color.rgb(249,160,63));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaPanchina) {
                        gc.setFill(Color.rgb(95,191,249));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaLocanda) {
                        gc.setFill(Color.rgb(72,86,150));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaPescaUnaCarta || tabellone.getCasella(posizione) instanceof CasellaPescaUnaCartaMod) {
                        gc.setFill(Color.rgb(210,149,191));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (tabellone.getCasella(posizione) instanceof CasellaTraguardo) {
                        gc.setFill(Color.rgb(107,5,4));
                        gc.fillRect(v, v1, v2, v3);
                    } else if (posizione == 1) {
                        gc.setFill(Color.rgb(232,225,239));
                        gc.fillRect(v, v1, v2, v3);
                    }


                    gc.setFill(Color.BLACK);

                    gc.fillText(posizione + "", (j) * casellaY + 3, (i + 1) * casellaX - 4);
                    gc.rect(v, v1, v2, v3);

                    posizione++;
                }
            }
        }

        gc.stroke();
    }

    private static void errorCheck(){
        StringBuilder errorLog = new StringBuilder();
        int caselleDisponibili = parametri[8]*parametri[9]-1;

        boolean errorFound = false;

        errorLog.append("\n");

        for(int i=0; i<parametri.length; i++){
            switch (i){
                case 0 -> {
                    if(parametri[i]<=0){
                        errorFound=true;
                        errorLog.append("* Il numero di giocatori deve essere compreso tra 1 e 5;\n");
                    } else if(parametri[i]>5){
                        errorFound=true;
                        errorLog.append("* Il numero di giocatori deve essere compreso tra 1 e 5;\n");
                    }
                }
                case 8 -> {
                    if(parametri[i]<8 || parametri[i]>12){
                        errorFound=true;
                        errorLog.append("* Il numero di righe può andare da un minimo di 8 a un massimo di 12;\n");
                    }
                }
                case 9 -> {
                    if(parametri[i]<8 || parametri[i]>12){
                        errorFound=true;
                        errorLog.append("* Il numero di colonne può andare da un minimo di 8 a un massimo di 12;\n");
                    }
                }
                case 10 -> {
                    if(parametri[i]<0){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle deve essere maggiore o uguale a 0 (Scale);\n");
                    }
                    if(parametri[i]*2>caselleDisponibili){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle disponibili è insufficente (Scale);\n");
                    }
                }
                case 11 -> {
                    if(parametri[i]<0){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle deve essere maggiore o uguale a 0 (Serpenti);\n");
                    }
                    if(parametri[i]*2>caselleDisponibili){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle disponibili è insufficente (Serpenti);\n");
                    }
                }
                case 12 -> {
                    if(parametri[i]<0){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle deve essere maggiore o uguale a 0 (Premio);\n");
                    }
                    if(parametri[i]>caselleDisponibili){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle disponibili è insufficente (Premio);\n");
                    }
                }
                case 13 -> {
                    if(parametri[i]<0){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle deve essere maggiore o uguale a 0 (Sosta);\n");
                    }
                    if(parametri[i]>caselleDisponibili){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle disponibili è insufficente (Sosta);\n");
                    }
                }
                case 14 -> {
                    if(parametri[i]<0){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle deve essere maggiore o uguale a 0 (P. C.);\n");
                    }
                    if(parametri[i]>caselleDisponibili){
                        errorFound=true;
                        errorLog.append("* Il numero di caselle disponibili è insufficente (P. C.);\n");
                    }
                }
            }
        }

        if(errorFound){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attenzione!");
            alert.setHeaderText("Valore inserito non valido");



            Label label = new Label("Lista degli errori:");

            TextArea textArea = new TextArea(errorLog.toString());
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);


            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();

            throw new IllegalArgumentException(errorLog.toString());
        }
    }



    private static double[] coordinateX;
    private static double[] coordinateY;

    private static Giocatore[] giocatori;
    private static int turnoAttuale;
    private static String azioneTurno;

    private static void inizializzaGioco(){
        giocatori = new Giocatore[Partita.getPartita().getImpostazioniGiocatori().getNumeroGiocatori()];
        for(int i = 0; i<giocatori.length; i++){
            giocatori[i] = new Giocatore();
        }

        turnoAttuale = 0;
        azioneTurno = "INIZIO PARTITA";
    }

    private static void aggiornaTurno(){
        turnoAttuale++;
    }
}




