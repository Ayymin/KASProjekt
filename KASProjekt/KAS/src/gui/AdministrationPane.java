package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;


public class AdministrationPane extends GridPane {
    private ArrayList<String> names;
    private ListView<String> lvwNames;


    public AdministrationPane(GridPane pane) {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Button btnOpret = new Button("Opret konference");
        add(btnOpret, 5, 4);

        lvwNames = new ListView<String>();
        pane.add(lvwNames, 1, 3,2,1);
        lvwNames.setPrefWidth(200);
        lvwNames.setPrefHeight(200);
       // lvwNames.getItems().setAll(this.initNames());

      //  private ArrayList<String> initNames() {
            names = new ArrayList<String>();
            names.add("Hav og himmel");
            names.add("Hav og himmel");
            names.add("Hav og himmel");
            names.add("Hav og himmel");
            names.add("Hav og himmel");
            names.add("Hav og himmel");
          //  return names;
        }

    }

