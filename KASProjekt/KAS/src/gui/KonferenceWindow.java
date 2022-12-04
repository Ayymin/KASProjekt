package gui;

import application.model.Konference;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;


public class KonferenceWindow extends Stage {
    //Textfields
    private TextField txfAnkomst = new TextField();
    private TextField txfAdress = new TextField();
    private TextField txfName = new TextField();
    private TextField txfFirma = new TextField();
    private TextField txfTlfNr = new TextField();
    private TextField txfAfrejse = new TextField();
    private TextField txfFirmaTlfNr = new TextField();

    private TextField txfBy = new TextField();
    private TextField txfLand = new TextField();

    private Label lblError = new Label();

    //Checkbox
    private CheckBox cbSpeaker = new CheckBox();

    private Konference selectedKonference;


    public KonferenceWindow(String title, Konference selectedItem) {
        this.selectedKonference = selectedItem;
        this.setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }



    private void initContent(GridPane pane) {
        pane.setVgap(10);
        pane.setHgap(20);
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));

        //Textfield name
        pane.add(new Label("Deltagernavn:"), 0, 0);
        pane.add(txfName, 1, 0);

        //Textfield Adress
        pane.add(new Label("Adress"), 0, 1);
        pane.add(txfAdress, 1, 1);

        //Textfield by
        pane.add(new Label("By:"), 0, 2);
        pane.add(txfBy, 1, 2);

        //Textfield land
        pane.add(new Label("Land:"), 0, 3);
        pane.add(txfLand, 1, 3);

        //Textfield tfl
        pane.add(new Label("Telefonnr:"), 0, 4);
        pane.add(txfTlfNr, 1, 4);

        //Textfield Ankomstdato
        pane.add(new Label("Ankomstdato:"), 2, 0);
        pane.add(txfAnkomst, 3, 0);

        //Textfield Afrejsedato
        pane.add(new Label("Afrejsedato:"), 2, 1);
        pane.add(txfAfrejse, 3, 1);

        //Textfield Firmanavn
        pane.add(new Label("Firmanavn:*"), 2, 2);
        pane.add(txfFirma, 3, 2);

        //Textfield firmatlf
        pane.add(new Label("Firma tlfNr:*"), 2, 3);
        pane.add(txfFirmaTlfNr, 3, 3);

        //Checkbox foredragsholder
        pane.add(new Label("Foredragsholder: "), 2, 4);
        pane.add(cbSpeaker, 3, 4);


        //Tilmeld knap
        Button btnOK = new Button("Tilmeld");
        pane.add(btnOK, 0, 5, 4, 1);
        GridPane.setHalignment(btnOK, HPos.CENTER);
        btnOK.setOnAction(event -> tilmeldingAction());

        //Label error
        pane.add(lblError, 0, 6, 4, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");
    }


    private void tilmeldingAction() {
        String fejlBesked = null;
        if (txfName.getText().isEmpty()) {
            fejlBesked = "Indtast et navn";
        } else if (txfAdress.getText().isEmpty()) {
            fejlBesked = "Indtast en adresse";
        } else if (txfBy.getText().isEmpty()) {
            fejlBesked = "Indtast en by";
        } else if (txfLand.getText().isEmpty()) {
            fejlBesked = "Indtast et land";
        } else if (txfTlfNr.getText().isEmpty()) {
            fejlBesked = "Indtast et tlfNr";
        } else if (txfAnkomst.getText().isEmpty()) {
            fejlBesked = "Indtast en ankomst";
        } else if (txfAfrejse.getText().isEmpty()) {
            fejlBesked = "Indtast en afrejsedato";
        }


        if (fejlBesked != null) {
            lblError.setText(fejlBesked);
            return;
        }
    }

}




