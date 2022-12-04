package gui;

import application.model.Tilmelding;
import application.model.Deltager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class KonferenceWindow extends GridPane {
    private final String[] labels = {"Deltagernavn:", "Adresse:", "By:", "Ankomstdato:", "Firmanavn:", "Tlf.nr:", "Afrejsedato:", "Firma tlf.nr:", "Land: "};
    private TextField txfAnkomst = new TextField();
    private TextField txfByLand = new TextField();
    private TextField txfAdress = new TextField();
    private TextField txfName = new TextField();
    private TextField txfFirma = new TextField();
    private TextField txfTlfNr = new TextField();
    private TextField txfAfrejse = new TextField();

    private TextField txfBy = new TextField();
    private TextField txfLand = new TextField();

    private Label lblError = new Label();
    private TextField txfFirmaTlfNr = new TextField();
    private TextField[] txfFields = {txfName, txfAdress, txfBy, txfAnkomst, txfFirma, txfTlfNr, txfAfrejse, txfFirmaTlfNr, txfLand};
    private final ToggleGroup fHolder = new ToggleGroup();
    private CheckBox cbSpeaker = new CheckBox();


    public KonferenceWindow(String title) {
        this.setGridLinesVisible(true);
        initContent(this);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        GridPane pane = new GridPane();
        initContent(pane);

        for (int i = 0; i < txfFields.length - 4; i++) {
            TextField tilmelding = txfFields[i];
            this.add(tilmelding, 1, i);
        }
        for (int i = 1; i < txfFields.length - 4; i++) {
            TextField tilmelding = txfFields[i + 4];
            this.add(tilmelding, 4, i);
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
        this.add(cbSpeaker, 1, 4);

        Button btnOK = new Button("Tilmeld");
        this.add(btnOK, 3, 4);
        btnOK.setOnAction(event -> tilmeldingAction());

        lblError = new Label("aweqr ");
        pane.add(lblError, 4, 4);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void initContent(GridPane pane) {
        HBox box = new HBox();
        pane.add(box, 4, 0);


    }


    private void tilmeldingAction() {
        String fejlBesked = null;
        if (txfName.getText().isEmpty()) {
            fejlBesked = "Indtast et navn";
        } else if (txfAdress.getText().isEmpty()) {
            fejlBesked = "Indtast en adresse";
        } else if (txfBy.getText().isEmpty()) {
            fejlBesked = "Indtast en adresse";
        } else if (txfLand.getText().isEmpty()) {
            fejlBesked = "Indtast et land";
        } else if (txfTlfNr.getText().isEmpty()) {
            fejlBesked = "Indtast et tlfNr";
        } else if (txfAfrejse.getText().isEmpty()) {
            lblError.setText("Indtast en afrejsedato");
        } else if (txfBy.getText().isEmpty()) {
            lblError.setText("Indtast en by");
        }

        if (fejlBesked != null) {
            lblError.setText(fejlBesked);
            return;
        }
        String deltagerNavn = txfName.getText().trim();
        String adresse = txfAdress.getText().trim();
        String by = txfBy.getText().trim();
        String land = txfLand.getText().trim();
        String ankomstDato = txfAnkomst.getText().trim();
        int tlfNr = Integer.parseInt(txfTlfNr.getText().trim());
        String afrejseDato = txfAfrejse.getText().trim();
        int firmaTlf = Integer.parseInt(txfFirmaTlfNr.getText().trim());
        boolean isSpeaker = cbSpeaker.isSelected();
        txfFirmaTlfNr.getText().isEmpty();
        Deltager deltager = new Deltager(deltagerNavn, adresse, tlfNr, ankomstDato, by, isSpeaker, by, firmaTlf);
    }

}


//<---------------------------------------------------------------------------------------------------------------->


