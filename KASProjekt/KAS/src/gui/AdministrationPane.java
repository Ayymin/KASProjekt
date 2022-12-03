package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class AdministrationPane extends GridPane {
    private TextField txfName, txfStartDato, txfSted, txfSlutDato;
    private ListView<Konference> konference;
    private ListView<Hotel> hotel;
    private ListView<Udflugt> udflugt;


    public AdministrationPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblComp = new Label("Konferencer");
        this.add(lblComp, 0, 0);

        konference = new ListView<>();
        this.add(konference, 0, 1, 1, 3);
        konference.setPrefWidth(200);
        konference.setPrefHeight(200);
        konference.getItems().setAll(Controller.getKonference());

        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        konference.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblHotel = new Label("Hotel");
        this.add(lblHotel, 1, 3);

        hotel = new ListView<>();
        this.add(hotel, 2,3);
        hotel.setPrefWidth(250);
        hotel.setPrefHeight(100);
        hotel.getItems().setAll(Controller.getHotels());

		/*ChangeListener<Hotel> hotelChangeListener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
		hotel.getSelectionModel().selectedItemProperty().addListener(hotelChangeListener);*/

        Label lblUdflugt = new Label("Udflugt");
        this.add(lblUdflugt, 1, 7);

        udflugt = new ListView<>();
        this.add(udflugt, 2, 7);
        udflugt.setPrefWidth(300);
        udflugt.setPrefHeight(100);
        udflugt.getItems().setAll(Controller.getUdflugter());

		/*ChangeListener<Udflugt> udflugtChangeListener = (ov, oldUdflugt, newUdflugt) -> this.selectedUdflugtChaned();
		udflugt.getSelectionModel().selectedItemProperty().addListener(udflugtChangeListener);*/

        Label lblName = new Label("Konferencenavn");
        this.add(lblName, 1, 1);

        txfName = new TextField();
        this.add(txfName, 2, 1);
        txfName.setEditable(false);

        Label lblStartDato = new Label("Startdato");
        this.add(lblStartDato, 1, 4);

        txfStartDato = new TextField();
        this.add(txfStartDato, 2, 4);
        txfStartDato.setEditable(false);

        Label lblSlutdato = new Label("Slutdato");
        this.add(lblSlutdato, 1, 5);

        txfSlutDato = new TextField();
        this.add(txfSlutDato, 2 ,5);
        txfSlutDato.setEditable(false);

        Label lblSted = new Label("Sted");
        this.add(lblSted, 1, 2);

        txfSted = new TextField();
        this.add(txfSted, 2, 2);
        txfSted.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 2, 9, 3, 1);
        hbxButtons.setPadding(new Insets(10,0,0,0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnCreate = new Button("Opret konference");
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createAction());

        Button btnUpdate = new Button("Opdater");
        hbxButtons.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateAction());

        Button btnDelete = new Button("Slet");
        hbxButtons.getChildren().add(btnDelete);
        btnDelete.setOnAction(event -> this.deleteAction());

        HBox h= new HBox(15);
        this.add(h, 0, 4, 1, 1);
        h.setPadding(new Insets(10,0,0,0));
        h.setAlignment(Pos.BOTTOM_LEFT);

        Button btnHotel = new Button("Tilføj hotel");
        h.getChildren().add(btnHotel);
        btnHotel.setOnAction(event -> tilføjHotel());

        Button btnUdflugt = new Button("Tilføj udflugt");
        h.getChildren().add(btnUdflugt);
        btnUdflugt.setOnAction(event -> tilføjUdflugt());

        if (konference.getItems().size() > 0) {
            konference.getSelectionModel().select(0);
        }
    }

    // -------------------------------------------------------------------------

    private void tilføjHotel() {
        Hotel hotel = this.hotel.getSelectionModel().getSelectedItem();
        if (konference != null) {

            AdministrationWindow3 aw3 = new AdministrationWindow3("Tilføj hotel", hotel);
            aw3.showAndWait();

            int selectIndex = this.hotel.getSelectionModel().getSelectedIndex();
            this.hotel.getItems().setAll(Controller.getHotels());
            this.hotel.getSelectionModel().select(selectIndex);
        }
    }

    private void tilføjUdflugt() {
        Udflugt udflugt = this.udflugt.getSelectionModel().getSelectedItem();
        if (konference != null) {

            AdministrationWindow4 aw4 = new AdministrationWindow4("Tilføj udflugt", udflugt);
            aw4.showAndWait();

            int selectIndex = this.udflugt.getSelectionModel().getSelectedIndex();
            this.udflugt.getItems().setAll(Controller.getUdflugter());
            this.udflugt.getSelectionModel().select(selectIndex);
        }
    }

    private void createAction() {
        AdministrationWindow aw = new AdministrationWindow("Opret konference");
        aw.showAndWait();

        konference.getItems().setAll(Controller.getKonferencer());
        int index = konference.getItems().size() - 1;
        konference.getSelectionModel().select(index);
    }

    private void updateAction() {
        Konference konference = this.konference.getSelectionModel().getSelectedItem();
        if (konference != null) {

            AdministrationWindow2 aw2 = new AdministrationWindow2("Opdater konference", konference);
            aw2.showAndWait();

            int selectIndex = this.konference.getSelectionModel().getSelectedIndex();
            this.konference.getItems().setAll(Controller.getKonferencer());
            this.konference.getSelectionModel().select(selectIndex);
        }
    }

    private void deleteAction() {
        Konference konference = this.konference.getSelectionModel().getSelectedItem();
        if (konference != null) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Slet konference");
            alert.setHeaderText("Konferencen vil blive slettet permanent");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                Controller.deleteKonference(konference);
                this.konference.getItems().setAll(Controller.getKonferencer());
                this.updateControls();
            }
        }
    }


    private void selectedKonferenceChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Konference konference = this.konference.getSelectionModel().getSelectedItem();
        if (konference != null) {
            txfName.setText(konference.getName());
            txfStartDato.setText(konference.getDate());
            txfSlutDato.setText(konference.getEndDate());
            txfSted.setText(konference.getAdress());

        } else {
            txfName.clear();
            txfStartDato.clear();
            txfSlutDato.clear();
            txfSted.clear();

        }
    }
}



