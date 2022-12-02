package gui;


import application.controller.Controller;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;

public class KonferencePane extends GridPane {

        private TextField txfName, txfDesc, txfDate;


        private TextArea txaEmps;

        private ListView<Konference> lvwKonferencer;

        public KonferencePane() {
            this.setPadding(new Insets(20));
            this.setHgap(20);
            this.setVgap(10);
            this.setGridLinesVisible(true);

            txfName = new TextField();
            this.add(txfName,3,1);

            Label lblName = new Label("Name:");
            this.add(lblName, 2, 1);

            txfDate = new TextField();
            this.add(txfDate,3,2);

            Label lbDate = new Label("Date");
            this.add(lbDate,2,2);

            txfDesc = new TextField();
            this.add(txfDesc,3,3,2,4);

            Label lblComp = new Label("Konference");
            this.add(lblComp, 0, 0);



            lvwKonferencer = new ListView<>();
            this.add(lvwKonferencer,0,1,1,3);
            lvwKonferencer.setPrefSize(100,150);
            lvwKonferencer.getItems().setAll(Controller.getKonference());

            ChangeListener<Konference> listener = (ov, o, n) -> this.selectedKonferenceChanged();

            lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);
        }

    private void selectedKonferenceChanged() {
            Konference selectedKonference = lvwKonferencer.getSelectionModel().getSelectedItem();

            txfName.setText(selectedKonference.getName());
            txfDate.setText(selectedKonference.getDate());
            txfDesc.setText(selectedKonference.getDescription());

        }
    }


