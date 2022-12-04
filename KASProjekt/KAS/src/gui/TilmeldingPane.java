package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Service;
import application.model.Tilmelding;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TilmeldingPane extends GridPane {
    private final String[] labels = {"Deltagernavn:", "Adresse:", "By:", "Ankomstdato:", "Tlf.nr:", "Afrejsedato:", "Firma tlf.nr:", "Land"};
    private final String[] svar = {"Ja", "Nej"};

    private TextField txfAnkomst = new TextField();
    private TextField txfByLand = new TextField();
    private TextField txfAdress = new TextField();
    private TextField txfName = new TextField();
    private TextField txfFirma = new TextField();
    private TextField txfTlfNr = new TextField();
    private TextField txfAfrejse = new TextField();
    private TextField txfFirmaTlfNr = new TextField();
    private TextField[] txfFields = {txfName, txfAdress, txfByLand, txfAnkomst, txfFirma, txfTlfNr, txfAfrejse, txfFirmaTlfNr};
    private final ToggleGroup fHolder = new ToggleGroup();
    private CheckBox cbSpeaker = new CheckBox();



    public TilmeldingPane() {
        initContent(this);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);

        for (int i = 0; i < txfFields.length - 4; i++) {
            TextField tilmelding = txfFields[i];
            this.add(tilmelding,1,i);
        }
        for (int i = 0; i < txfFields.length - 4; i++) {
            TextField tilmelding = txfFields[i+4];
            this.add(tilmelding,4,i);
        }

        for (int i = 0; i < labels.length - 4; i++) {                   //Prints Strings of ArrayList
            Label lbl = new Label(labels[i]);
            add(lbl, 0, 0 + i);
        }
        for (int j = 5; j < labels.length; j++) {                       //Prints String of ArrayList
            Label lbl2 = new Label(labels[j]);
            add(lbl2, 3, j - 5);
        }

        Label lblSpeaker = new Label("Foredragsholder: ");
        this.add(lblSpeaker, 0, 4);
        this.add(cbSpeaker,1,4);

        Label lbLand = new Label("Land");
        this.add(lbLand,2,3);
    }

    private void initContent(GridPane pane) {
        HBox box = new HBox();
        pane.add(box, 4, 0);

        for (int i = 0; i < svar.length; i++) {
            RadioButton rb = new RadioButton();
            box.getChildren().add(rb);
            rb.setText(svar[i]);
            rb.setToggleGroup(fHolder);
        }

        Button tilmeld = new Button("Tilmeld");
        add(tilmeld, 5, 4);
        GridPane.setMargin(tilmeld, new Insets(10, 10, 0, 10));
        tilmeld.setOnAction(event -> createTilmelding());

        Button btnSamletPris = new Button("Se samlet pris");
        this.add(btnSamletPris, 4, 4);
        btnSamletPris.setOnAction(event -> samletPris());
    }

    //<---------------------------------------------------------------------------------------------------------------->
    private void setLabels() {
        RadioButton rb = (RadioButton) fHolder.getSelectedToggle();
    }
    //TODO Advarselsvindue hvis man efterlader et felt tomt i tilmeldingspan vinduet.


    private void createTilmelding() {
        try {

            //Tilmelding tilmelding = new Tilmelding(txfAnkomst.getText(), );
        } catch (Exception ex) {

        }
    }

    public void samletPris() {

        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Indtast dit navn");
        dialog.setHeaderText("Indtast dit navn for at se samlet pris");

        Optional<String> result = dialog.showAndWait();

        // wait for the modal dialog to close
        double samletPris = 0;

        String input = "";
        boolean nameFound = false;
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                //Løber gennem alle tilmeldinger
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
                    alert.setContentText("Skriv navnet på personen i tilmeldingen");
                    alert.show();
                }
            }
        }
    }
}

