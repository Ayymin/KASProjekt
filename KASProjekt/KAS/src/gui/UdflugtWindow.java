package gui;


import application.controller.Controller;
import application.model.Ledsager;
//import application.model.Number;
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


import java.awt.*;

import static storage.Storage.initContent;

public class UdflugtWindow extends Stage {

    private Ledsager ledsager;

    private UdflugtPane udflugtPane;

    private Udflugt udflugt;

    private ListView<Udflugt> lvwUdflugter = new ListView<>();

    public UdflugtWindow(String title, Udflugt udflugt) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        this.udflugt = udflugt;
        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    public UdflugtWindow(String title){
        this(title, null);
    }

    private TextField txfName, txfAdress, txfPrice, txfDescription, txfDato;

    private Label lblError;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 1, 1);

        txfName = new TextField();
        pane.add(txfName, 2, 1);
        txfName.setPrefWidth(200);

        Label lblPrice = new Label("Price");
        pane.add(lblPrice, 1, 2);

        txfPrice = new TextField();
        pane.add(txfPrice, 2, 2);

        Label lblDato = new Label("Dato");
        pane.add(lblDato, 1, 3);

        txfDato = new TextField();
        pane.add(txfDato, 2, 3);

        Label lblAdress = new Label("Adress");
        pane.add(lblAdress, 1, 4);

        txfAdress = new TextField();
        pane.add(txfAdress, 2, 4);

        Label lblDescription = new Label("Description");
        pane.add(lblDescription, 1, 5);

        txfDescription = new TextField();
        pane.add(txfDescription, 2, 5);

        Button btnOK = new Button("OK");
        pane.add(btnOK, 2, 6);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 1, 6);
        GridPane.setHalignment(btnCancel, HPos.RIGHT);
        btnCancel.setOnAction(event -> cancelAction());

        lblError = new Label();
        pane.add(lblError, 0, 7);
        lblError.setStyle("-fx-text-fill: red");

        initControls();

    }

    private void initControls() {
        if (udflugt != null) {
            txfName.setText(udflugt.getName());
            txfPrice.setText("" + udflugt.getPrice());
            txfAdress.setText(udflugt.getAdress());
            txfDato.setText(udflugt.getDate());
            txfDescription.setText(udflugt.getDescription());
        }
    }


    private void cancelAction() {
        hide();
    }

    private void okAction() {

        String adress = txfAdress.getText().trim();
        String name = txfName.getText().trim();
        String description = txfDescription.getText().trim();
        String dato = txfDato.getText().trim();

        if (name.length() == 0) {
            lblError.setText("Name is empty");

        } else if (adress.length() == 0) {
            lblError.setText("Adress is empty");

        } else if (description.length() == 0) {
            lblError.setText("Description is empty");

        } else if (dato.length() == 0) {
            lblError.setText("Date is empty");

        } else {
            int price = -1;
            try {
                price = Integer.parseInt(txfPrice.getText().trim());
            } catch (NumberFormatException ex) {
            }
            if (price < 0) {
                lblError.setText("Price is not a positive number");

            }
            if (udflugt != null) {
                Controller.updateUdflugt(udflugt, name, dato, adress, description, price);

            } else {
                if (price > 0)
                    Controller.createUdflugt(name, adress, dato, price, description);
            }

        }
        hide();
    }
}


/*
    private void alertFejl(String str) {
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle("Fejl");
        a.setHeaderText("Fejl i et felt");
        a.setContentText("Indtast " + str);
        a.showAndWait();
    }
*/
















