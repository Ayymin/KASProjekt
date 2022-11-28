package gui;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class KonferencePane extends GridPane {

        private TextField txfName, txfHours, txacompanySize;
        private TextArea txaEmps;

        public KonferencePane() {
            this.setPadding(new Insets(20));
            this.setHgap(20);
            this.setVgap(10);
            this.setGridLinesVisible(true);

            Label lblComp = new Label("Konference");
            this.add(lblComp, 0, 0);


            Label lblName = new Label("Name:");
            this.add(lblName, 1, 1);
        }
    }

