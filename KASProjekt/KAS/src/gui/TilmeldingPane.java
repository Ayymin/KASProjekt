package gui;

import application.model.Tilmelding;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;

public class TilmeldingPane extends GridPane {
    private final String[] labels = {"Deltagernavn:", "Adresse:", "By/Land:", "Ankomstdato:", "Firmanavn:", "Tlf.nr:", "Afrejsedato:", "Firma tlf.nr:"};
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
}

