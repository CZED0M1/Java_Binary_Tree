package gui;

import BVS.AbstrTable;
import BVS.eTypProhl;
import Perzistence.Perzistence;
import static generator.Generator.generuj;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kolekce.EnumPozice;
import pobocka.IPobocka;
import pobocka.Pobocka;
import pobocky.Autopujcovna;
import static pobocky.Autopujcovna.SEZNAM_POBOCEK;
import static pobocky.Autopujcovna.SEZNAM_VYPUJC;
import pobocky.eTyp;
import prostredky.Auto;
import prostredky.OsobniAuta;
import prostredky.UzitkovaAuta;
import util.Barva;

public class ProgAutopujcovna extends Application {

    private static Scene scene;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private static Stage stage;

    private final ObservableList<IPobocka> LIST_POBOCKY = FXCollections.observableArrayList();
    private final ObservableList<Auto> LIST_AUTA = FXCollections.observableArrayList();
    private final ObservableList<Auto> LIST_VYPUJCENA_AUTA = FXCollections.observableArrayList();

    private final ListView<IPobocka> LIST_VIEW_POBOCKY = new ListView<>(LIST_POBOCKY);
    private final ListView<Auto> LIST_VIEW_AUTA = new ListView<>(LIST_AUTA);
    private final ListView<Auto> LIST_VIEW_VYPUJCENA_AUTA = new ListView<>(LIST_VYPUJCENA_AUTA);

    private final Autopujcovna autopujcovna = new Autopujcovna();

    private static final String FILE_AUTA = "SaveAuta.txt";
    private static final String FILE_VYPUJC = "SaveVypujAuta.txt";
    private static final String FILE_POBOCKY = "SavePobocky.txt";

    @Override
    public void start(Stage primaryStage) {
        Button btnGeneruj = new Button();
        btnGeneruj.setText("Generuj auta");
        btnGeneruj.setOnAction((ActionEvent event) -> {
            Scene novaScena = new Scene(generujPocet());
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();
        });

     

        Button btnVypujci = new Button();
        btnVypujci.setText("Vypujci auto");
        btnVypujci.setOnAction((ActionEvent event) -> {
            System.out.println(autopujcovna.vypujcAuto("ahoj"));

            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());

            }

            LIST_VYPUJCENA_AUTA.clear();
            Iterator<Auto> itv = new Autopujcovna().iterator(eTyp.VYPUJCENA_AUTA);
            while (itv.hasNext()) {
                LIST_VYPUJCENA_AUTA.add(itv.next());
            }
        });

        Button btnNoveAuto = new Button();
        btnNoveAuto.setText("NoveAuto");
        btnNoveAuto.setOnAction((ActionEvent event) -> {

            Scene novaScena = new Scene(novyProstredek());
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();

        });

        Button btnGeneruj2 = new Button();
        btnGeneruj2.setText("Generuj Pobočku");
        btnGeneruj2.setOnAction((ActionEvent event) -> {
            Scene novaScena = new Scene(zadejJmeno());
            LIST_VIEW_POBOCKY.getSelectionModel().selectLast();
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();
        });

        Button btnPrvni2 = new Button();
        btnPrvni2.setText("Prvni pob");
        btnPrvni2.setOnAction((ActionEvent event) -> {
            SEZNAM_POBOCEK.zpristupniPrvni();
            LIST_VIEW_POBOCKY.getSelectionModel().selectFirst();
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }

        });
        Button btnDalsi2 = new Button();
        btnDalsi2.setText("Další pob");
        btnDalsi2.setOnAction((ActionEvent event) -> {
            SEZNAM_POBOCEK.zpristupniNaslednika();
            LIST_VIEW_POBOCKY.getSelectionModel().selectNext();
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }

        });
        Button btnPredchozi2 = new Button();
        btnPredchozi2.setText("Předchozí pob");
        btnPredchozi2.setOnAction((ActionEvent event) -> {
            SEZNAM_POBOCEK.zpristupniPredchudce();
            LIST_VIEW_POBOCKY.getSelectionModel().selectPrevious();
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }

        });
        Button btnPosledni2 = new Button();
        btnPosledni2.setText("Poslední pob");
        btnPosledni2.setOnAction((ActionEvent event) -> {
            SEZNAM_POBOCEK.zpristupniPosledni();
            LIST_VIEW_POBOCKY.getSelectionModel().selectLast();
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }

        });
        Button btnOdeber2 = new Button();
        btnOdeber2.setText("Odeber pob");
        btnOdeber2.setOnAction((ActionEvent event) -> {
            LIST_POBOCKY.remove(SEZNAM_POBOCEK.zpristupniAktualni());
            autopujcovna.zrusPobocku();
            LIST_AUTA.clear();
        });

        Button btnPrvni3 = new Button();
        btnPrvni3.setText("Prvni vypůjčené");
        btnPrvni3.setOnAction((ActionEvent event) -> {
            SEZNAM_VYPUJC.zpristupniPrvni();
            LIST_VIEW_VYPUJCENA_AUTA.getSelectionModel().select(0);
        });
        Button btnDalsi3 = new Button();
        btnDalsi3.setText("Další vypůjčené");
        btnDalsi3.setOnAction((ActionEvent event) -> {
            SEZNAM_VYPUJC.zpristupniNaslednika();
            LIST_VIEW_VYPUJCENA_AUTA.getSelectionModel().selectNext();
        });
        Button btnPredchozi3 = new Button();
        btnPredchozi3.setText("Předchozí vypůjčené");
        btnPredchozi3.setOnAction((ActionEvent event) -> {
            SEZNAM_VYPUJC.zpristupniPredchudce();
            LIST_VIEW_VYPUJCENA_AUTA.getSelectionModel().selectPrevious();
        });
        Button btnPosledni3 = new Button();
        btnPosledni3.setText("Poslední vypůjčené");
        btnPosledni3.setOnAction((ActionEvent event) -> {
            SEZNAM_VYPUJC.zpristupniPosledni();
            LIST_VIEW_VYPUJCENA_AUTA.getSelectionModel().selectLast();
        });

        Button btnOdeber3 = new Button();
        btnOdeber3.setText("Odeber vypůjčené");
        btnOdeber3.setOnAction((ActionEvent event) -> {
            SEZNAM_VYPUJC.odeberAktualni();
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }
            LIST_VYPUJCENA_AUTA.clear();
            Iterator<Auto> itv = new Autopujcovna().iterator(eTyp.VYPUJCENA_AUTA);
            while (itv.hasNext()) {
                LIST_VYPUJCENA_AUTA.add(itv.next());
            }
        });

        Button btnVrat = new Button();
        btnVrat.setText("Vrať vypůjčené");
        btnVrat.setOnAction((ActionEvent event) -> {
            autopujcovna.vratAuto(EnumPozice.AKTUALNI);
            LIST_AUTA.clear();
            Iterator<Auto> it = new Autopujcovna().iterator(eTyp.AUTA);
            while (it.hasNext()) {
                LIST_AUTA.add(it.next());
            }

            LIST_VYPUJCENA_AUTA.clear();
            Iterator<Auto> itv = new Autopujcovna().iterator(eTyp.VYPUJCENA_AUTA);
            while (itv.hasNext()) {
                LIST_VYPUJCENA_AUTA.add(itv.next());
            }
        });
        Button btnZrusAll = new Button();
        btnZrusAll.setText("ZRUŠ CELÉ");
        btnZrusAll.setOnAction((ActionEvent event) -> {
            SEZNAM_POBOCEK.zpristupniAktualni().zrus();
            autopujcovna.zrus();
            SEZNAM_VYPUJC.zrus();

            LIST_POBOCKY.clear();

            LIST_AUTA.clear();

            LIST_VYPUJCENA_AUTA.clear();

        });
        Button btnUloz = new Button();
        btnUloz.setText("Ulož");
        btnUloz.setOnAction((ActionEvent event) -> {
            Iterator<Auto> itv = new Autopujcovna().iterator(eTyp.VYPUJCENA_AUTA);
            Iterator<Pobocka> itp = new Autopujcovna().iterator(eTyp.POBOCKY);
            try {
                Perzistence.fileWriterPobocky(itp, FILE_POBOCKY);
                Perzistence.fileWriterVypujcena(itv, FILE_VYPUJC);
                SEZNAM_POBOCEK.zpristupniPrvni();

                Perzistence.fileWriter(SEZNAM_POBOCEK, FILE_AUTA);

            } catch (IOException ex) {
                Logger.getLogger(ProgAutopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button btnNacti = new Button();
        btnNacti.setText("Načti");
        btnNacti.setOnAction((ActionEvent event) -> {
            try {

                Perzistence.fileReaderPob(LIST_POBOCKY, FILE_POBOCKY);
              //  Perzistence.fileReaderVyp(LIST_VYPUJCENA_AUTA, FILE_VYPUJC);
                Perzistence.fileReader(LIST_AUTA, FILE_AUTA);
                SEZNAM_POBOCEK.zpristupniPrvni();
                LIST_VIEW_POBOCKY.getSelectionModel().selectFirst();;
                LIST_AUTA.clear();
                Iterator<Auto> it = SEZNAM_POBOCEK.zpristupniAktualni().iterator(eTypProhl.HLOUBKA);
                while (it.hasNext()) {
                    LIST_AUTA.add(it.next());
                }
            } catch (IOException ex) {
                Logger.getLogger(ProgAutopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Button btnNajdiAkt = new Button();
        btnNajdiAkt.setText("Najdi v akt");
        btnNajdiAkt.setOnAction((ActionEvent event) -> {
            Scene novaScena = new Scene(najdivAkt());
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();
        });

        Button btnNajdiAll = new Button();
        btnNajdiAll.setText("Najdi v all");
        btnNajdiAll.setOnAction((ActionEvent event) -> {
            Scene novaScena = new Scene(najdivAll());
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();
        });

        Button btnOdeberK = new Button();
        btnOdeberK.setText("Odeber Key");
        btnOdeberK.setOnAction((ActionEvent event) -> {
            Scene novaScena = new Scene(odeberK());
            stage = new Stage();
            stage.setScene(novaScena);
            stage.show();
        });
        
         Button btnStart = new Button();
        btnStart.setText("Example");
        btnStart.setOnAction((ActionEvent event) -> {
            try {
                startLoad();
            } catch (IOException ex) {
                Logger.getLogger(ProgAutopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            
        });

        BorderPane border = new BorderPane();
        scene = new Scene(border, WIDTH, HEIGHT);

        LIST_VIEW_AUTA.setPrefWidth(WIDTH / 3);
        LIST_VIEW_VYPUJCENA_AUTA.setPrefWidth(WIDTH / 3);

        HBox hbCenter = new HBox(LIST_VIEW_POBOCKY, LIST_VIEW_AUTA, LIST_VIEW_VYPUJCENA_AUTA);

        HBox hbBot = new HBox(btnGeneruj, btnVypujci, btnNoveAuto);
        HBox hbBot1 = new HBox(btnGeneruj2, btnPrvni2, btnDalsi2, btnPredchozi2, btnPosledni2, btnOdeber2);
        HBox hbBot2 = new HBox(btnPrvni3, btnDalsi3, btnPredchozi3, btnPosledni3, btnOdeber3, btnVrat);
        HBox hbBot3 = new HBox(btnZrusAll, btnUloz, btnNacti);

        HBox hbBot4 = new HBox(btnNajdiAkt, btnNajdiAll, btnOdeberK,btnStart);

        VBox vbBot = new VBox(hbBot, hbBot1, hbBot2, hbBot3, hbBot4);

        vbBot.setPadding(
                new Insets(10, 10, 10, 10));
        vbBot.setSpacing(
                10);

        hbBot.setPadding(
                new Insets(10, 10, 10, 10));
        hbBot.setSpacing(
                10);
        hbBot1.setPadding(
                new Insets(0, 10, 10, 10));
        hbBot1.setSpacing(
                10);

        hbBot2.setPadding(
                new Insets(0, 10, 10, 10));
        hbBot2.setSpacing(
                10);

        hbBot3.setPadding(
                new Insets(0, 10, 10, 10));
        hbBot3.setSpacing(
                10);

        hbBot4.setPadding(
                new Insets(0, 10, 10, 10));
        hbBot4.setSpacing(
                10);

        hbCenter.setSpacing(10);
        hbCenter.setAlignment(Pos.CENTER);
        border.setBottom(vbBot);
        border.setCenter(hbCenter);

        primaryStage.setTitle(
                "Správa dopravního prostředku");
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
        

    }

    private GridPane odeberK() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        Label lbl = new Label("Zadej key: ");
        TextField pocet = new TextField();

        HBox hbox = new HBox(lbl, pocet);
        hbox.setPadding(
                new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        Button btnOK = new Button("Accept");
        VBox vbbtn = new VBox(hbox, btnOK);

        vbbtn.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(vbbtn);
        btnOK.setOnAction((ActionEvent event) -> {
            for (IPobocka pob : LIST_POBOCKY) {
                pob.odeberAuto(pocet.getText());
            }
            stage.close();
        });
        return grid;
    }

    private GridPane generujPocet() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        Label lbl = new Label("Zadej počet: ");
        TextField pocet = new TextField();

        HBox hbox = new HBox(lbl, pocet);
        hbox.setPadding(
                new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        Button btnOK = new Button("Accept");
        VBox vbbtn = new VBox(hbox, btnOK);

        vbbtn.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(vbbtn);
        btnOK.setOnAction((ActionEvent event) -> {
            generuj(Integer.parseInt(pocet.getText()), Autopujcovna.SEZNAM_POBOCEK.zpristupniAktualni());
            stage.close();
        });
        return grid;
    }

    private GridPane zadejJmeno() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        Label lbl = new Label("Zadej jmeno: ");
        TextField jmeno = new TextField();

        Spinner spinPoz = new Spinner(FXCollections.observableArrayList(EnumPozice.values()));

        HBox hbox = new HBox(lbl, jmeno, spinPoz);
        hbox.setPadding(
                new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        Button btnOK = new Button("Accept");
        VBox vbbtn = new VBox(hbox, btnOK);

        vbbtn.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(vbbtn);
        btnOK.setOnAction((ActionEvent event) -> {
            IPobocka pob = new Pobocka(jmeno.getText());

            switch (spinPoz.getValue().toString()) {
                case "Poslední":
                    Autopujcovna.SEZNAM_POBOCEK.vlozPosledni(pob);
                    break;
                case "První":
                    Autopujcovna.SEZNAM_POBOCEK.vlozPrvni(pob);
                    break;
                case "Následník":
                    Autopujcovna.SEZNAM_POBOCEK.vlozNaslednika(pob);
                    break;
                case "Predchudce":
                    Autopujcovna.SEZNAM_POBOCEK.vlozPredchudce(pob);
                    break;
                default:
                    System.out.println("e");
            }
            LIST_POBOCKY.clear();
            Iterator<Pobocka> itp = new Autopujcovna().iterator(eTyp.POBOCKY);
            while (itp.hasNext()) {
                LIST_POBOCKY.add(itp.next());
            }

            SEZNAM_POBOCEK.zpristupniPosledni();
            stage.close();
        });
        return grid;
    }

    private GridPane novyProstredek() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        Spinner spin = new Spinner(FXCollections.observableArrayList("Osobní", "Užitkové"));

        Spinner spinPoz = new Spinner(FXCollections.observableArrayList(EnumPozice.values()));
        Label lbl = new Label("SPZ: ");
        TextField spz = new TextField();

        HBox hbox = new HBox(lbl, spz);

        Label lbl1 = new Label("Km: ");
        TextField km = new TextField();

        HBox hbox1 = new HBox(lbl1, km);

        Label lbl2 = new Label("vypůjčení: ");
        TextField vypujc = new TextField();

        HBox hbox2 = new HBox(lbl2, vypujc);

        hbox.setPadding(
                new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox1.setPadding(
                new Insets(10, 10, 10, 10));
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.TOP_RIGHT);
        hbox2.setPadding(
                new Insets(10, 10, 10, 10));
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.TOP_RIGHT);

        Button btnOK = new Button("Accept");

        HBox hbox3 = new HBox();
        hbox3.setPadding(
                new Insets(10, 10, 10, 10));
        hbox3.setSpacing(10);

        hbox3.setAlignment(Pos.TOP_RIGHT);
        Label lblBar = new Label("Barva");
        TextField tfBar = new TextField();
        hbox3.getChildren().addAll(lblBar, tfBar);

        VBox vbbtn = new VBox(spin, hbox, hbox1, hbox2, hbox3, spinPoz, btnOK);

        vbbtn.setPadding(
                new Insets(10, 10, 10, 10));
        vbbtn.setSpacing(10);

        vbbtn.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(vbbtn);

        spin.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.toString()) {
                case "Osobní":
                    lblBar.setText("Barva");

                    break;
                case "Užitkové":
                    lblBar.setText("Nosnost");

                    break;
            }
        });

        btnOK.setOnAction((ActionEvent event) -> {
            switch (spin.valueProperty().getValue().toString()) {
                case "Osobní":
                    for (Barva barva : Barva.values()) {
                        if (barva.toString().equals(tfBar.getText())) {

                            Auto auto
                                    = new OsobniAuta(barva, spz.getText(),
                                            Integer.parseInt(km.getText()),
                                            Integer.parseInt(vypujc.getText()));
                            autopujcovna.zpristupniPobocku((EnumPozice) spinPoz.getValue()).vlozAuto(auto);
                        } else {
                            System.out.println("Ne");
                        }
                    }

                    break;
                case "Užitkové":

                    Auto auto = new UzitkovaAuta(Integer.parseInt(
                            tfBar.getText()), spz.getText(),
                            Integer.parseInt(km.getText()),
                            Integer.parseInt(vypujc.getText()));
                    autopujcovna.zpristupniPobocku((EnumPozice) spinPoz.getValue()).vlozAuto(auto);

                    break;
            }

            stage.close();
        });
        return grid;
    }

    private GridPane najdivAkt() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        TextField tf = new TextField();
        Button btnOK = new Button("Accept");

        VBox vbbtn = new VBox(tf, btnOK);
        grid.getChildren().add(vbbtn);
        btnOK.setOnAction((ActionEvent event) -> {
            
            LIST_AUTA.clear();
           
            Auto au=SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(tf.getText());
            if (au!=null) {
            LIST_AUTA.add(au);
            
            }
            stage.close();
        });
        return grid;
    }

    private GridPane najdivAll() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        TextField tf = new TextField();
        Button btnOK = new Button("Accept");

        VBox vbbtn = new VBox(tf, btnOK);
        grid.getChildren().add(vbbtn);
        btnOK.setOnAction((ActionEvent event) -> {
            LIST_AUTA.clear();
            Auto au = null;
            for (IPobocka pob : LIST_POBOCKY) {
                au = pob.zpristupnAuto(tf.getText());
                if (au != null) {
                    LIST_AUTA.add(au);

                }

            }

            stage.close();
        });
        return grid;
    }
    private void startLoad() throws IOException {
        IPobocka pob = new Pobocka("start");
        autopujcovna.vlozPobocku(pob,EnumPozice.PRVNI);
        Perzistence.fileReader(LIST_AUTA, "Start.txt");
        LIST_POBOCKY.add(pob);
        
    }
}
