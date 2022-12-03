package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Service;
import application.model.Tilmelding;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class HotelPane extends GridPane {
    private final CheckboxGroup cbHotelGroup = new CheckboxGroup();
    private ArrayList<CheckBox> chbHotels = new ArrayList<>();
    private ArrayList<CheckBox> chbHotelsData = new ArrayList<>();



    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);


        Label lblOvernat = new Label("Overnatningsønsker");
        add(lblOvernat, 0, 0);

        Label lblHotel = new Label("Hoteller");
        Label lblprice = new Label("Priser: Dobbelt/enkelt");
        Label lblKryds = new Label("Sæt kryds for\n hotelønske");
        Label lblService = new Label("Sæt kryds for\n valg af tillæg");
        this.add(lblHotel, 0, 1);
        this.add(lblprice, 1, 1);
        this.add(lblKryds, 2, 1);
        this.add(lblService, 3, 1);

        Button btnBook = new Button("Book hotel");
        btnBook.setOnAction(event -> bookHotel());
        this.add(btnBook, 4, 0);

        int pos = 0;
        //TODO SKAL VÆRE MÆNGDEN AF HOTELLER
        //Indsætter hoteller
        for (Hotel aHotel : Controller.getHotels()) {

            CheckBox cbHotel = new CheckBox();

            CheckBox copyCBHotel = new CheckBox();
            copyCBHotel.setUserData(aHotel);
            chbHotelsData.add(copyCBHotel);
            chbHotels.add(cbHotel);

            cbHotel.setOnAction(event -> onHotelSelected(event));

            Label lblHotelNavn = new Label(aHotel.getName());
            Label lblHotelPris = new Label(aHotel.getDoubelPrice() + "/" + aHotel.getSinglePrice());
            this.add(lblHotelNavn, 0, 2 + pos);
            this.add(lblHotelPris, 1, 2 + pos);
            this.add(cbHotel, 2, 2 + pos);


            //TODO SKAL VÆRE MÆNGDEN AF SERVICES

            HBox hbService = new HBox(10);
            this.add(hbService, 3, 2 + pos);
            for (int j = 0; j < aHotel.getServices().size(); j++) {
                RadioButton rb = new RadioButton(aHotel.getServices().get(j).getName());                                                      //Creates service Radiobuttons and disables them
                rb.setDisable(true);
                hbService.getChildren().add(rb);
                rb.setUserData(aHotel.getServices().get(j)); //TODO SKAL VÆRE SERVICE
            }

            cbHotel.setUserData(hbService);
            pos++;
        }
    }

    private void onHotelSelected(ActionEvent event) {                                                    //Method that enables the radio buttons
        CheckBox source = (CheckBox) event.getSource();                                                  //once the Checkboxes are enabled, and disables
        HBox radio = (HBox) source.getUserData();//if the checkboxes are disabled.
        for (Node r : radio.getChildren()) {
            if (source.isSelected()) {
                r.setDisable(false);
            } else {
                r.setDisable(true);
            }
        }
    }

    //Booker hotellet.
    //TODO skal give fejlbeskeder
    public void bookHotel() {


        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Indtast dit navn");
        dialog.setHeaderText("Indtast dit navn for at booke hotellet");

        Optional<String> result = dialog.showAndWait();

        // wait for the modal dialog to close

        String input = "";
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                //Løber gennem alle tilmeldinger
                for (Tilmelding aTilmelding : Controller.getTilmeldinger()) {
                    //Tjekker om en tilmelding indeholder en deltager med det indtastede navn
                    if (aTilmelding.getDeltager().getName().equalsIgnoreCase(input)) {
                        //Tjekker hvilket hotel er booket og sætter tilmeldingen til det hotel
                        for (int i = 0; i < chbHotels.size(); i++) {
                            if (chbHotels.get(i).isSelected()) {
                                aTilmelding.setHotel((Hotel) chbHotelsData.get(i).getUserData());

                                HBox services = (HBox) chbHotels.get(i).getUserData();
                                for (Node aService : services.getChildren()) {
                                    RadioButton rbService = (RadioButton) aService;
                                    if (rbService.isSelected()) {
                                        aTilmelding.addService((Service) aService.getUserData());
                                        System.out.println("aef");
                                    }
                                }

                                System.out.println(aTilmelding.getHotel().getName());
                                System.out.println(aTilmelding.getServices());
                            }
                        }
                    }
                }
            }
        }
    }
}


