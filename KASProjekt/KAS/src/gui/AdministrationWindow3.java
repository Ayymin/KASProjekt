package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationWindow3 extends Stage {
    private Hotel hotel;
    private Konference konference;
    private String startDato;
    private String slutDato;
    private String sted;

    public AdministrationWindow3(String title, Hotel hotel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.hotel = hotel;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfDouble, txfSingle;
    private ComboBox<Konference> konferenceComboBox;
    private CheckBox konferenceCheckBox;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Hotelnavn");
        pane.add(lblName, 0, 0);

        txfName = new TextField();
        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblDouble = new Label("Doublepris");
        pane.add(lblDouble, 0, 2);

        txfDouble = new TextField();
        pane.add(txfDouble, 0, 3);

        Label lblSingle = new Label("Singlepris");
        pane.add(lblSingle, 2, 0);

        txfSingle = new TextField();
        pane.add(txfSingle, 2, 1);

        konferenceCheckBox = new CheckBox("Konference");
        pane.add(konferenceCheckBox, 0, 4);
        ChangeListener<Boolean> listener = (ov, oldValue, newValue) -> selectedKonferenceChanged(newValue);
        konferenceCheckBox.selectedProperty().addListener(listener);

        konferenceComboBox = new ComboBox<>();
        pane.add(konferenceComboBox, 0, 5);
        konferenceComboBox.getItems().addAll(Controller.getKonference());
        konferenceComboBox.setDisable(true);

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 6);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 6);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        lblError = new Label();
        pane.add(lblError, 0, 7);
        lblError.setStyle("-fx-text-fill: red");

        initControls();

    }

    private void initControls() {
        if (hotel != null) {
            txfName.setText(hotel.getName());
            txfDouble.setText("" + hotel.getDoubelPrice());
            txfSingle.setText("" + hotel.getSinglePrice());
            if (hotel.getKonference() != null) {
                konferenceCheckBox.setSelected(true);
                konferenceComboBox.getSelectionModel().select(hotel.getKonference());
            } else {
                konferenceComboBox.getSelectionModel().select(0);
            }
        } else {
            txfName.clear();
            txfDouble.clear();
            txfSingle.clear();
            konferenceComboBox.getSelectionModel().select(0);
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        double singlepris = -1;
        double doublepris = -1;

        if (name.length() == 0) {
            lblError.setText("Indtast hotelnavn");
        } else {

            try {
                singlepris = Double.parseDouble(txfSingle.getText().trim());
            } catch (NumberFormatException ex) {
            }
            if (singlepris < 0) {
                lblError.setText("Indtast singlepris");
            } else {

                try {
                    doublepris = Double.parseDouble(txfDouble.getText().trim());
                } catch (NumberFormatException ex) {
                }
                if (doublepris < 0) {
                    lblError.setText("Indtast doublepris");
                } else {
                    if (!(konferenceCheckBox.isSelected())) {
                        lblError.setText("Vælg konference");
                    } else {
                        boolean konferenceIsSelected = konferenceCheckBox.isSelected();
                        Konference newKonference = konferenceComboBox.getSelectionModel().getSelectedItem();

                        if (hotel != null) {
                            if (konferenceIsSelected) {
                                Controller.addHotelToKonference(hotel, newKonference);
                            }
                        } else {
                            if (konferenceIsSelected) {
                                Controller.createHotel(name, (int) singlepris, newKonference, (int) doublepris);
                            } else {
                                Controller.createHotel(name, (int) singlepris, newKonference, (int) doublepris);
                                Controller.updateKonference(konference, name, startDato, slutDato, sted);
                            }
                        }
                        hide();
                    }
                }
            }

        }
    }


    private void selectedKonferenceChanged(boolean checked) {konferenceComboBox.setDisable(!checked);
    }

}


