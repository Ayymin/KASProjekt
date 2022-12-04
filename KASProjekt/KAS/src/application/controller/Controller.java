package application.controller;

import application.model.*;
import storage.Storage;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Controller {


    public static double getTotalPrice(Tilmelding tilmelding) {
        int totalPrice = 0;


        if (tilmelding.getHotel() != null) {
            System.out.println("Afaef");
            for (int i = 0; i < (getTotalDays(tilmelding.getArrivalDate(), tilmelding.getDepatureDate()) - 1); i++) {    //Loops and subtracts with 1, to calculate for 2 stays instead of 1.
                if (tilmelding.getLedsager() != null) {
                    totalPrice += tilmelding.getHotel().getDoubelPrice();
                } else {
                    totalPrice += tilmelding.getHotel().getSinglePrice();
                }

                for (Service aService : tilmelding.getServices()) {
                    totalPrice += aService.getPrice();
                }
            }
        }

        if (tilmelding.getLedsager() != null) {
            for (Udflugt aUdflugt : tilmelding.getLedsager().getUdflugter()) {
                totalPrice += aUdflugt.getPrice();
            }
        }

        if (!tilmelding.getDeltager().isSpeaker()) {
            System.out.println("aefaef");
            totalPrice += tilmelding.getKonference().getPriceADay() * (getTotalDays(tilmelding.getArrivalDate(), tilmelding.getDepatureDate()));
        }

        return totalPrice;
    }

    public static long getTotalDays(String arrivalDate, String depatureDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        long totalDays = 0;
        try {
            LocalDateTime date1 = LocalDate.parse(arrivalDate, dtf).atStartOfDay();
            LocalDateTime date2 = LocalDate.parse(depatureDate, dtf).atStartOfDay();
            totalDays = Duration.between(date1, date2).toDays() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalDays;
    }

    public static ArrayList<Hotel> getHotels() {
        ArrayList<Hotel> hotels = Storage.getHotels();

        return hotels;
    }

    /*public static ArrayList<Udflugt> getUdflugter() {
        ArrayList<Udflugt> udflugts = Storage.getUdflugter();
        return udflugts;
    }*/
    public static ArrayList<Udflugt> getUdflugter() {return Storage.getUdflugter();
    }

    public static ArrayList<Deltager> getDeltager() {
        ArrayList<Deltager> deltagers = Storage.getDeltagers();
        return deltagers;
    }

    public static ArrayList<Ledsager> getLedsager() {
        ArrayList<Ledsager> ledsagers = Storage.getLedsagers();
        return ledsagers;
    }

    public static Konference getKonference() {
        return Storage.getKonference();
    }

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return Storage.getTilmeldinger();
    }

    public static void addTilmelding(Tilmelding tilmelding) {
        Storage.addTilmelding(tilmelding);
    }

    public static void updateKonference(Konference konference, String name, String startDato, String slutDato, String sted) {
        konference.setName(name);
        konference.setDate(startDato);
        konference.setEndDate(slutDato);
        konference.setTopic(sted);
    }

    public static void deleteKonference(Konference konference) {
        Storage.removeKonference(konference);
    }

    public static void createKonference(String name, String endDate, String adress, String topic, String description, String date, double priceADay) {
        Konference konference = new Konference(name, endDate, adress, topic, description, date, priceADay);
        Storage.addKonference(konference);

    }

    public static void addHotelToKonference(Hotel hotel, Konference konference) {
        konference.addHotel(hotel);
    }

    public static Hotel createHotel(String name, int singlepris, int doublepris) {
        Hotel hotel = new Hotel(name, singlepris, doublepris);
        Storage.addHotel(hotel);
        return hotel;
    }
    public static Hotel createHotel(String name, int singlepris, Konference konference, int doublepris) {
        Hotel hotel = createHotel(name, singlepris, doublepris);
        konference.addHotel(hotel);
        return hotel;
    }

    public static void addUdflugtToKonference(Udflugt udflugt, Konference konference) {
        konference.addUdflugt(udflugt);
    }

    public static Udflugt createUdflugt(Konference konference, String name, String date, String adress, String description, int price) {
        Udflugt udflugt = new Udflugt(name, date, adress, description, price);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }
}

