package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KonferencePane extends GridPane {

    private TextField txfName, txfDesc, txfDate, txfendDate,txfTopic;

    private final String[] svar = {"Ja", "Nej"};

    private final ToggleGroup fHolder = new ToggleGroup();
    private CheckBox cbSpeaker = new CheckBox();


    private ListView<Konference> lvwKonferencer;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //------------------------------ Gui Hell ------------------------------------

        Label lblComp = new Label("Konference");
        this.add(lblComp, 0, 0);

        txfName = new TextField();
        this.add(txfName, 3, 1);

        Label lblName = new Label("Name:");
        this.add(lblName, 2, 1);

        txfDate = new TextField();
        this.add(txfDate, 3, 2);

        Label lbDate = new Label("Date:");
        this.add(lbDate, 2, 2);

        txfendDate = new TextField();
        this.add(txfendDate, 3, 3);

        Label lbendDate = new Label("End date:");
        this.add(lbendDate, 2, 3);

        txfTopic = new TextField();
        this.add(txfTopic,3,4);

        Label lbTopic = new Label("Topic:");
        this.add(lbTopic,2,4);

        txfDesc = new TextField();
        this.add(txfDesc, 3, 5);
        txfDesc.setPrefSize(250, 80);

        Label lbDesc = new Label("Description");
        this.add(lbDesc, 2, 5);

        HBox hbxButtons1 = new HBox(10);
        this.add(hbxButtons1,0,6,3,1);
        hbxButtons1.setPadding(new Insets(10,0,0,0));
        hbxButtons1.setAlignment(Pos.BASELINE_RIGHT);



        Button tilmeld = new Button("Tilmeld");
        add(tilmeld, 5, 4);
        GridPane.setMargin(tilmeld, new Insets(10, 10, 0, 10));
        tilmeld.setOnAction(event -> createTilmelding());


        Button btnTilmeld = new Button("Tilmeld konference");
        hbxButtons1.getChildren().add(btnTilmeld);
        btnTilmeld.setOnAction(event -> this.tilmeldAction());

        //------------------------------ Gui Hell ends ------------------------------------

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 0, 1, 1, 3);
        lvwKonferencer.setPrefSize(250, 100);
        lvwKonferencer.getItems().setAll(Controller.getKonference());

        ChangeListener<Konference> listener = (ov, o, n) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void tilmeldAction() {
        KonferenceWindow konferenceWindow = new KonferenceWindow("Yo");
        Stage stage = new Stage();
        Scene scene = new Scene(konferenceWindow);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tilmelding");
    }

    private void selectedKonferenceChanged() {                                                  //Prints out information regarding a selected Conference.
        Konference selectedKonference = lvwKonferencer.getSelectionModel().getSelectedItem();
        txfName.setText(selectedKonference.getName());
        txfTopic.setText(selectedKonference.getTopic());
        txfDate.setText(selectedKonference.getDate());
        txfendDate.setText(selectedKonference.getEndDate());
        txfDesc.setText(selectedKonference.getDescription());
    }

    private void createTilmelding() {
        try {

            //Tilmelding tilmelding = new Tilmelding(txfAnkomst.getText(), );
        } catch (Exception ex) {

        }
    }

}


