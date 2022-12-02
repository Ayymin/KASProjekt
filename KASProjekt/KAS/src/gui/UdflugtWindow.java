package gui;

import application.model.Ledsager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.model.Udflugt;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.*;

import static storage.Storage.initContent;

public class UdflugtWindow extends Stage {

    private Ledsager ledsager;

    public UdflugtWindow(String title, Ledsager ledsager) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        this.ledsager = ledsager;
        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    public UdflugtWindow(String title){
        this(title, null);
    }

    private TextField txfName, txfAdress, txfTelefonnr, txfCity, txfCountry;

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

        Label lblAdress = new Label("Adress");
        pane.add(lblAdress, 1, 5);

        txfAdress = new TextField();
        pane.add(txfAdress, 2, 5);

        Label lblTelefonnr = new Label("TelefonNr");
        pane.add(lblTelefonnr,1,4);

        txfTelefonnr = new TextField();
        pane.add(txfTelefonnr, 2,4);

        Label lblCity = new Label("City");
        pane.add(lblCity, 1,3);

        txfCity = new TextField();
        pane.add(txfCity,2,3);

        Label lblCountry = new Label("Country");
        pane.add(lblCountry,1,2);

        txfCountry = new TextField();
        pane.add(txfCountry,2,2);

        Button btnOK = new Button("OK");
        pane.add(btnOK, 2, 6);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        lblError = new Label();
        pane.add(lblError, 0, 7);
        lblError.setStyle("-fx-text-fill: red");

        initControls();

    }

    private void okAction() {

        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setTitle("Tilmelding");
        a.setHeaderText("Du er nu tilmeldt");
        a.setContentText("");

        a.showAndWait();


    }


    private void initControls(){
        if (ledsager != null) {
            txfName.setText(ledsager.getName());
            txfAdress.setText("" + ledsager.getAdress());
            txfTelefonnr.setText(ledsager.getPhoneNr() + "");

        } else {
            txfName.clear();
            txfAdress.clear();
            txfTelefonnr.clear();
        }
    }



}
