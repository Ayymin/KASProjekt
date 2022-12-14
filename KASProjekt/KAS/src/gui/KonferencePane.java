package gui;

import application.controller.Controller;
import application.model.Konference;
import application.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;


import java.util.Optional;

public class KonferencePane extends GridPane {

    private TextField txfName, txfDesc, txfDate, txfendDate, txfTopic;

    private Button btnTilmeld = new Button("Tilmeld konference");

    private Button btnPris = new Button("Samlet pris");

    private ListView<Konference> lvwKonferencer;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

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
        this.add(txfTopic, 3, 4);

        Label lbTopic = new Label("Topic:");
        this.add(lbTopic, 2, 4);

        txfDesc = new TextField();
        this.add(txfDesc, 3, 5);
        txfDesc.setPrefSize(250, 80);

        Label lbDesc = new Label("Description");
        this.add(lbDesc, 2, 5);

        HBox hbxButtons1 = new HBox(10);
        this.add(hbxButtons1, 0, 6, 3, 1);
        hbxButtons1.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons1.setAlignment(Pos.BASELINE_RIGHT);

        hbxButtons1.getChildren().add(btnTilmeld);
        hbxButtons1.getChildren().add(btnPris);
        btnTilmeld.setDisable(true);

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 0, 1, 1, 3);
        lvwKonferencer.setPrefSize(250, 100);
        lvwKonferencer.getItems().setAll(Controller.getKonference());


        //------------------------------ Gui Hell ends ------------------------------------

        btnTilmeld.setOnAction(event -> this.tilmeldAction());
        btnPris.setOnAction(actionEvent -> this.samletPrisAction());
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> this.selectedKonferenceChanged());
    }

    private void tilmeldAction() {
        KonferenceWindow window = new KonferenceWindow("Yo", lvwKonferencer.getSelectionModel().getSelectedItem());
        window.showAndWait();
    }

    private void selectedKonferenceChanged() {
        if (lvwKonferencer.getSelectionModel().getSelectedItem() != null) {
            btnTilmeld.setDisable(false);
            Konference selectedKonference = lvwKonferencer.getSelectionModel().getSelectedItem();
            txfName.setText(selectedKonference.getName());
            txfTopic.setText(selectedKonference.getTopic());
            txfDate.setText(selectedKonference.getDate());
            txfendDate.setText(selectedKonference.getEndDate());
            txfDesc.setText(selectedKonference.getDescription());
        }
    }


    public void samletPrisAction() {
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Indtast dit navn");
        dialog.setHeaderText("Indtast dit navn for at se samlet pris");

        Optional<String> result = dialog.showAndWait();

        // wait for the modal dialog to close

        String input = "";
        boolean nameFound = false;
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                //L??ber gennem alle tilmeldinger
                for (Tilmelding aTilmelding : Controller.getTilmeldinger()) {
                    //Tjekker om en tilmelding indeholder en deltager med det indtastede navn
                    if (aTilmelding.getDeltager().getName().equalsIgnoreCase(input)) {
                        nameFound = true;

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Samlet pris");
                        alert.setHeaderText("Den samlede pris");
                        alert.setContentText("Den samlede pris er " + aTilmelding.getTotalPrice());
                        alert.show();

                    }
                }

                if (!nameFound) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Book hotel");
                    alert.setHeaderText("Forkert navn");
                    alert.setContentText("Skriv navnet p?? personen i tilmeldingen");
                    alert.show();
                }
            }
        }
    }

    public void updateControls() {
        if (Controller.getKonferencer() != null) {
            lvwKonferencer.getItems().setAll(Controller.getKonferencer());
        }
    }
}




