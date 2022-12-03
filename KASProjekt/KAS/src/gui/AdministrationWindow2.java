package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Udflugt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationWindow2 extends Stage {
    private Konference konference;
    private String endDate;
    private String adress;
    private String topic;
    private String description;
    private String date;
    private double priceADay;

    public AdministrationWindow2(String title, Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.konference = konference;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public AdministrationWindow2(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfStartDato, txfSlutDato, txfSted;
    private ListView<Hotel> hotel;
    private ListView<Udflugt> udflugt;
    private Label lblError;
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Konferencenavn");
        pane.add(lblName, 0, 0);

        txfName = new TextField();
        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblStartDato = new Label("Startdato");
        pane.add(lblStartDato, 0, 2);

        txfStartDato = new TextField();
        pane.add(txfStartDato, 0, 3);

        Label lblSlutDato = new Label("Slutdato");
        pane.add(lblSlutDato, 0, 4);

        txfSlutDato = new TextField();
        pane.add(txfSlutDato, 0, 5);

        Label lblSted = new Label("Sted");
        pane.add(lblSted, 0, 6);

        txfSted = new TextField();
        pane.add(txfSted, 0, 7);

        Label lblHotel = new Label("Hotel");
        pane.add(lblHotel, 0, 8);

        hotel = new ListView<>();
        pane.add(hotel, 0,9);
        hotel.setPrefWidth(250);
        hotel.setPrefHeight(100);
        hotel.getItems().setAll(Controller.getHotels());

        Label lblUdflugt = new Label("Udflugt");
        pane.add(lblUdflugt, 0, 10);

        udflugt = new ListView<>();
        pane.add(udflugt, 0, 11);
        udflugt.setPrefWidth(300);
        udflugt.setPrefHeight(100);
        udflugt.getItems().setAll(Controller.getUdflugter());

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 12);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 12);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> this.okAction());

        lblError = new Label();
        pane.add(lblError, 0, 14);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
        if (konference != null) {
            txfName.setText(konference.getName());
            txfStartDato.setText(konference.getDate());
            txfSlutDato.setText(konference.getEndDate());
            txfSted.setText(konference.getTopic());

        } else {
            txfName.clear();
            txfStartDato.clear();
            txfSlutDato.clear();
            txfSted.clear();
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        String startDato = txfStartDato.getText().trim();
        String slutDato = txfSlutDato.getText().trim();
        String sted = txfSted.getText().trim();

        if (name.length() == 0) {
            lblError.setText("Indtast konferencenavn");
        } else {
            if (startDato.length() == 0) {
                lblError.setText("Indtast startdato");
            } else {
                if (slutDato.length() == 0) {
                    lblError.setText("Indtast slutdato");
                } else {
                    if (sted.length() == 0) {
                        lblError.setText("Indtast sted");
                    } else {
                        if (konference != null) {
                            Controller.updateKonference(konference, name, startDato, slutDato, sted);
                        } else {
                            Controller.createKonference(name, endDate, adress, topic, description, date, priceADay);
                        }
                        this.hide();
                    }
                }
            }
        }
    }
}





