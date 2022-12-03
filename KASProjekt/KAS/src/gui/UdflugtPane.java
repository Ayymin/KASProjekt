package gui;

import application.controller.Controller;
import application.model.Tilmelding;
import application.model.Ledsager;
import application.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.Optional;


public class UdflugtPane extends GridPane {

    private TextField txfName, txfDescription, txfAdresse, txfDato, txfPrice;

    private ListView<Udflugt> lvwUdflugter = new ListView<>();



    public UdflugtPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setMinSize(125,50);
        this.setGridLinesVisible(false);


        Label lbludflugt = new Label("Udflugter");
        this.add(lbludflugt,0,0);


        this.add(lvwUdflugter,0,1,1,5);
        lvwUdflugter.setPrefWidth(200);
        lvwUdflugter.setPrefHeight(200);
        lvwUdflugter.getItems().setAll(Controller.getUdflugter());


        ChangeListener<Udflugt> listener = (ov, oldUdflugt, newUdflugt) -> this.selectedUdflugtChanged();
        lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblName = new Label("Name:");
        this.add(lblName, 1,1);

        txfName = new TextField();
        this.add(txfName,2,1);
        txfName.setPrefWidth(200);
        txfName.setEditable(false);

        Label lblDescription = new Label("Description");
        this.add(lblDescription,1,5);

        txfDescription = new TextField();
        this.add(txfDescription,2,5);
        txfDescription.setEditable(false);

        Label lblAdresse = new Label("Adresse");
        this.add(lblAdresse,1,4);

        txfAdresse = new TextField();
        this.add(txfAdresse,2,4);
        txfAdresse.setEditable(false);

        Label lblDato = new Label("Dato");
        this.add(lblDato,1,3);

        txfDato = new TextField();
        this.add(txfDato,2,3);
        txfDato.setEditable(false);

        Label lblPrice = new Label("Price");
        this.add(lblPrice,1,2);

        txfPrice = new TextField();
        this.add(txfPrice,2,2);
        txfPrice.setEditable(false);


        HBox hbxButtons1 = new HBox(10);
        this.add(hbxButtons1, 0, 7, 3, 1);
        hbxButtons1.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons1.setAlignment(Pos.BASELINE_RIGHT);


        Button btnTilmeld = new Button("Tilmeld");
        hbxButtons1.getChildren().add(btnTilmeld);
        btnTilmeld.setOnAction(event -> this.tilmeldAction());

        Button btnCreate = new Button("Create");
        hbxButtons1.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createAction());

        Button btnDelete = new Button("Delete");
        hbxButtons1.getChildren().add(btnDelete);
        btnDelete.setOnAction(event -> this.deleteAction());

        Button btnUpdate = new Button("Update");
        hbxButtons1.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateAction());



    }

    private void tilmeldAction() {

        TilmeldToUdflugt dia = new TilmeldToUdflugt("Tilmeld ledsager");
        dia.showAndWait();

    }


    private void updateAction(){
        Udflugt udflugter = lvwUdflugter.getSelectionModel().getSelectedItem();
        if(udflugter != null){

            UdflugtWindow dia = new UdflugtWindow("Update Udflugt", udflugter);
            dia.showAndWait();

            int selectIndex = lvwUdflugter.getSelectionModel().getSelectedIndex();
            lvwUdflugter.getItems().setAll(Controller.getUdflugter());
            lvwUdflugter.getSelectionModel().select(selectIndex);


        }
    }

    private void deleteAction() {
        Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
        if(udflugt != null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Udflugt");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();

            if((result.isPresent()) && (result.get() == ButtonType.OK)){
                //Controller.deleteUdflugt(udflugt);
                lvwUdflugter.getItems().setAll(Controller.getUdflugter());
                this.updateControls();
            }
        }


    }

    private void createAction() {

        UdflugtWindow dia = new UdflugtWindow("Create Udflugt");
        dia.showAndWait();

        lvwUdflugter.getItems().setAll(Controller.getUdflugter());
        this.updateControls();
    }



    private void selectedUdflugtChanged() {

        this.updateControls();
    }

    public void updateControls(){
        Udflugt udflugter = lvwUdflugter.getSelectionModel().getSelectedItem();
        if(udflugter != null){
            txfName.setText(udflugter.getName());
            txfDescription.setText(udflugter.getDescription());
            txfAdresse.setText(udflugter.getAdress());
            txfDato.setText(udflugter.getDate());
            txfPrice.setText("kr " + udflugter.getPrice());

        } else {
            txfName.clear();
            txfDescription.clear();
            txfAdresse.clear();
            txfDato.clear();
            txfPrice.clear();

        }
    }


}