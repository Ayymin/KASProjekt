package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.model.Number;


public class AdministrationWindow extends Stage {
    private Konference konference;
    private String topic;
    private String description;
    private double priceADay;

    public AdministrationWindow(String title, Konference konference) {
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

    public AdministrationWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfStartDato, txfSlutDato, txfSted, txfTopic, txfDescription, txfPriceADay;
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

        Label lblTopic = new Label("Emne");
        pane.add(lblTopic, 0, 8);

        txfTopic = new TextField();
        pane.add(txfTopic, 0, 9);

        txfDescription = new TextField();
        txfPriceADay = new TextField();
        pane.add(txfDescription, 0, 11);
        pane.add(txfPriceADay, 0, 13);

        Label lblDescription = new Label("Beskrivelse");
        Label lblPriceADay = new Label("Pris pr dag");
        pane.add(lblDescription, 0, 10);
        pane.add(lblPriceADay, 0, 12);




        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 14);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 14);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> this.okAction());

        lblError = new Label();
        pane.add(lblError, 0, 15);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
        if (konference != null) {
            txfName.setText(konference.getName());
            txfStartDato.setText(konference.getDate());
            txfSlutDato.setText(konference.getEndDate());
            txfSted.setText(konference.getAdress());
            txfDescription.setText(konference.getDescription());
            txfTopic.setText(konference.getTopic());
            txfPriceADay.setText(String.valueOf(konference.getPriceADay()));

        } else {
            txfName.clear();
            txfStartDato.clear();
            txfSlutDato.clear();
            txfSted.clear();
            txfDescription.clear();
            txfPriceADay.clear();
            txfTopic.clear();
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
        String priceADay = txfPriceADay.getText().trim();
        String description = txfDescription.getText().trim();
        String topic = txfTopic.getText().trim();


        if (!Number.isANumber(priceADay)) {
            lblError.setText("Indtast pris");
        } else if (description.length() == 0) {
            lblError.setText("Indtast beskrivelse");
        } else if (topic.length() == 0) {
            lblError.setText("Indtast emne");
        } else if (name.length() == 0) {
            lblError.setText("Indtast navn");
        } else if (startDato.length() == 0) {
            lblError.setText("Indtast startdato");
        } else if (sted.length() == 0) {
            lblError.setText("Indtast sted");
        } else if (slutDato.length() == 0){
            lblError.setText("Indtast slutdato");
        } else if (konference != null){
            Controller.updateKonference(konference, name, startDato, slutDato, sted);
            this.hide();
        } else {
            Controller.createKonference(name, slutDato, sted, topic, description, startDato, Double.parseDouble(priceADay));
            this.hide();
        }





        /*if (name.length() == 0) {
            lblError.setText("Indtast navn");
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
                            Controller.createKonference(name, slutDato, sted, topic, description, startDato, priceADay);
                        }

                        this.hide();
                    }
                }
            }
        }*/
    }
}





