package gui;

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

        Scene scene = new Scene(pane, 600,300);
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


        Tab tabBookHotel = new Tab("Book Hotel");
        tabPane.getTabs().add(tabBookHotel);

        HotelPane hotelPane = new HotelPane();
        tabBookHotel.setContent(hotelPane);

        Tab tabTilføjLedsager = new Tab("Tilmeld Udflugt");
        tabPane.getTabs().add(tabTilføjLedsager);

        UdflugtPane ledsagerPane = new UdflugtPane();
        tabTilføjLedsager.setContent(ledsagerPane);


    }

}