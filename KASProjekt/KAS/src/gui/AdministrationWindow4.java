package gui;

import application.controller.Controller;
import application.model.Konference;
import application.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationWindow4 extends Stage {
    private Udflugt udflugt;
    private Konference konference;
    private String startDato;
    private String slutDato;
    private String sted;
    private String name;
    private String date;
    private String adress;
    private String description;
    private int price;

    public AdministrationWindow4(String title, Udflugt udflugt) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public AdministrationWindow4(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfUdflugt, txfDato, txfPris;
    private ComboBox<Konference> konferenceComboBox;
    private CheckBox konferenceCheckBox;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblUdflugt = new Label("Udflugt");
        pane.add(lblUdflugt, 0, 0);

        txfUdflugt = new TextField();
        pane.add(txfUdflugt, 0, 1);
        txfUdflugt.setPrefWidth(200);

        Label lblDato = new Label("Dato");
        pane.add(lblDato, 0, 2);

        txfDato = new TextField();
        pane.add(txfDato, 0, 3);

        Label lblPris = new Label("Pris");
        pane.add(lblPris, 2, 0);

        txfPris = new TextField();
        pane.add(txfPris, 2, 1);

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
        if (udflugt != null) {
            txfUdflugt.setText(udflugt.getName());
            txfDato.setText(udflugt.getDate());
            txfPris.setText("" + udflugt.getPrice());
            if (udflugt.getKonference() != null) {
                konferenceCheckBox.setSelected(true);
                konferenceComboBox.getSelectionModel().select(udflugt.getKonference());
            } else {
                konferenceComboBox.getSelectionModel().select(0);
            }
        } else {
            txfUdflugt.clear();
            txfDato.clear();
            //txfPris.clear();
            konferenceComboBox.getSelectionModel().select(0);
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        hide();
    }

    private void okAction() {
        String name = txfUdflugt.getText().trim();
        String dato = txfDato.getText().trim();
        int pris = -1;

        if (name.length() == 0) {
            lblError.setText("Indtast udflugtnavn");
        } else {
            if (dato.length() == 0) {
                lblError.setText("Indtast dato");
            } else {
                try {
                    pris = Integer.parseInt(txfPris.getText().trim());
                } catch (NumberFormatException ex) {
                }
                if (pris < 0) {
                    lblError.setText("Indtast pris");
                } else {
                    if (!(konferenceCheckBox.isSelected())) {
                        lblError.setText("Vælg konference");
                    } else {

                        boolean konferenceIsSelected = konferenceCheckBox.isSelected();
                        Konference newKonference = konferenceComboBox.getSelectionModel().getSelectedItem();

                        if (udflugt != null) {
                            if (konferenceIsSelected) {
                                Controller.addUdflugtToKonference(udflugt, konference);
                            }
                        } else {
                            if (konferenceIsSelected) {
                                Controller.createUdflugt(newKonference, name, date, adress, description, price);
                            } else {
                                Controller.createUdflugt(newKonference, name, date, adress, description, price);
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


