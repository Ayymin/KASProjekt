package gui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.swing.*;

public class TilmeldingPane extends GridPane {
    private final String[] labels = {"Deltagernavn:", "Adresse:", "By/Land:", "Ankomstdato:", "Firmanavn:", "Foredragsholder:", "Tlf.nr:", "Afrejsedato:", "Firma tlf.nr:"};
    private final String[] svar = {"Ja", "Nej"};
    private final ToggleGroup fHolder = new ToggleGroup();


    public TilmeldingPane() {
        initContent(this);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);
        int tilmeldTextFields = 8;
        for (int j = 0; j < tilmeldTextFields - 3; j++) {               //Prints the first column of Textfields
            TextField tilmelding = new TextField();
            tilmelding.setAlignment(Pos.CENTER);
            add(tilmelding, 1, j);
        }

        for (int j = 1; j < tilmeldTextFields - 4; j++) {               //Prints the second column of Textfields
            TextField tilmelding = new TextField();
            tilmelding.setAlignment(Pos.CENTER);
            add(tilmelding, 4, j);
        }

        for (int i = 0; i < labels.length - 4; i++) {                   //Prints Strings of ArrayList
            Label lbl = new Label(labels[i]);
            add(lbl, 0, 0 + i);
        }
        for (int j = 5; j < labels.length; j++) {                       //Prints String of ArrayList
            Label lbl2 = new Label(labels[j]);
            add(lbl2, 3, j - 5);
        }
    }

    private void initContent(GridPane pane) {
        HBox box = new HBox();
        pane.add(box, 4, 0);

        for (int i = 0; i < svar.length; i++) {
            RadioButton rb = new RadioButton();
            box.getChildren().add(rb);
            rb.setText(svar[i]);
            rb.setToggleGroup(fHolder);
        }

        Button tilmeld = new Button("Tilmeld");
        add(tilmeld, 5, 4);
        GridPane.setMargin(tilmeld, new Insets(10, 10, 0, 10));
        tilmeld.setOnAction(event -> System.out.println("test"));
    }

    //<---------------------------------------------------------------------------------------------------------------->
    private void setLabels() {
        RadioButton rb = (RadioButton) fHolder.getSelectedToggle();
    }
    //TODO Advarselsvindue hvis man efterlader et felt tomt i tilmeldingspan vinduet.
}
