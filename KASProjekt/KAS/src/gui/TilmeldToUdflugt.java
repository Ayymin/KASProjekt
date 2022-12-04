package gui;

import application.controller.Controller;
import application.model.Ledsager;
import application.model.Number;
import application.model.Tilmelding;
import application.model.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.geometry.HPos;

public class TilmeldToUdflugt extends Stage {

    private Label lblError;

    private Udflugt udflugt;

    private Ledsager ledsager;


    private TextField txfName, txfNavnPaaDeltager, txfAdress, txfNr, txfCity, txfCountry;


    public TilmeldToUdflugt(String title, Ledsager ledsager, Udflugt udflugt) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        this.ledsager = ledsager;
        this.udflugt = udflugt;
        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    public TilmeldToUdflugt(String title, Udflugt udflugt) {
        this(title, null, udflugt);
    }

    private Label lblerror;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Ledsager navn");
        pane.add(lblName, 1, 1);

        txfName = new TextField();
        pane.add(txfName, 2, 1);
        txfName.setPrefWidth(200);

        Label lblDeltagerNavn = new Label("Deltager navn");
        pane.add(lblDeltagerNavn, 1,2);

        txfNavnPaaDeltager = new TextField();
        pane.add(txfNavnPaaDeltager, 2,2);

        Label lblAdress = new Label("Adresse");
        pane.add(lblAdress, 1, 3);

        txfAdress = new TextField();
        pane.add(txfAdress, 2, 3);

        Label lblTlfNr = new Label("TlfNr.");
        pane.add(lblTlfNr, 1, 4);

        txfNr = new TextField();
        pane.add(txfNr, 2, 4);

        Label lblCity = new Label("By");
        pane.add(lblCity,1,5);

        txfCity = new TextField();
        pane.add(txfCity,2,5);

        Label lblCountry = new Label("Land");
        pane.add(lblCountry, 1, 6);

        txfCountry = new TextField();
        pane.add(txfCountry, 2, 6);

        Button btnOK = new Button("OK");
        pane.add(btnOK, 2, 7);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 1, 7);
        GridPane.setHalignment(btnCancel, HPos.RIGHT);
        btnCancel.setOnAction(event -> cancelAction());

        initControls();
    }
    private void initControls() {
        if (ledsager != null) {
            txfName.setText(ledsager.getName());
            txfNavnPaaDeltager.setText(ledsager.getName());
            txfAdress.setText(ledsager.getAdress());
            txfNr.setText("" + ledsager.getPhoneNr());
            txfCity.setText(ledsager.getCity());
            txfCountry.setText(ledsager.getCountry());

        } else {
            txfName.clear();
            txfNavnPaaDeltager.clear();
            txfAdress.clear();
            txfNr.clear();
            txfCity.clear();
            txfCountry.clear();
        }
    }

    private void cancelAction() {
        hide();
    }

    private void okAction() {
        String name = txfName.getText();
        String adress = txfAdress.getText();
        String country = txfCountry.getText();
        String deltagerNavn = txfNavnPaaDeltager.getText();
        String city = txfCity.getText();

        int tlfnummer = Number.isANumber(txfNr.getText()) ? Integer.parseInt(txfNr.getText()) : -1;

        if (name.isEmpty()) {
            alertfejl("navn");
            txfNr.getText();

        } else if (adress.isEmpty()) {
            alertfejl("Adress");

        } else if (tlfnummer < 0) {
            alertfejl("Telefon nr");

        } else if (country.isEmpty()) {
            alertfejl("Country");

        } else if (deltagerNavn.isEmpty()) {
            alertfejl("Deltager navn");

        } else if (city.isEmpty()) {
            alertfejl("By");



        } else {

            String input = deltagerNavn;
            boolean nameFound = false;
            for (Tilmelding aTilmelding : Controller.getTilmeldinger()) {
                if (aTilmelding.getDeltager().getName().equalsIgnoreCase(input)) {

                    Ledsager ledsager = new Ledsager(name, adress, tlfnummer, city, country);
                    ledsager.addUdflugt(udflugt);
                    aTilmelding.setLedsager(ledsager);
                    nameFound = true;

                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Tilmelding");
                    a.setHeaderText("Du er nu tilmeldt");
                    a.setContentText("Tak for din tilmeldning");
                    a.showAndWait();
                    this.hide();

                }
            }

            if (!nameFound) {
                alertfejl("Deltagers navn findes ikke");
            }
        }
    }






    private void alertfejl (String str){
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle("Fejl");
        a.setHeaderText("Fejl i et felt");
        a.setContentText("Indtast " + str);
        a.showAndWait();
    }
}
