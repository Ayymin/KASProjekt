package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class StartWindow extends Application {





        @Override
        public void start(Stage stage) {
            stage.setTitle("Arcitecture Demo");
            BorderPane pane = new BorderPane();
            this.initContent(pane);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        }

        // -------------------------------------------------------------------------

        private void initContent(BorderPane pane) {
            TabPane tabPane = new TabPane();
            this.initTabPane(tabPane);
            pane.setCenter(tabPane);
        }

        private void initTabPane(TabPane tabPane) {
            tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

            Tab tabKonference = new Tab("Konference");
            tabPane.getTabs().add(tabKonference);

            KonferencePane konferencePane = new KonferencePane();
            tabKonference.setContent(konferencePane);
            //tabKonference.setOnSelectionChanged(event -> konferencePane.);

            Tab tabTilmelding = new Tab("Tilmelding");
            tabPane.getTabs().add(tabTilmelding);

            TilmeldingPane tilmeldingPane = new TilmeldingPane();
            tabTilmelding.setContent(tilmeldingPane);
            //tabTilmelding.setOnSelectionChanged(event -> tilmeldingPane.());




        }

    }


