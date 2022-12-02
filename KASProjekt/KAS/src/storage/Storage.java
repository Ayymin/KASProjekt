package storage;

import application.controller.Controller;
import application.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Storage {
    private static ArrayList<Hotel> hotels = new ArrayList<>();

    private static ArrayList<Deltager> deltagers = new ArrayList<>();

    private static ArrayList<Udflugt> udflugts = new ArrayList<>();

    private static ArrayList<Ledsager> ledsagers = new ArrayList<>();
    static Konference konference = new Konference("Milijøkonferencen", "20-05-2022"
            , "Odense Universitet", "Hav og Himmel",
            "Konference om Hav og Himmel", "18-05-2022", 1500);

    static Hotel denHvideSvane = new Hotel("Den Hvide Svane", 1250, 1050);
    static Hotel hoetelPhoenix = new Hotel("Hotel Phønix", 800, 700);
    static Hotel pensionTusindfryd = new Hotel("Pension Tusindfryd", 600, 500);

    static Udflugt byrundtur = new Udflugt("Byrundtur", "18-05-2022", "Odense Universitet", "Vi skal se på de forskellige serværdigheder i odense", 125);
    static Udflugt egeskov = new Udflugt("Egeskov", "19-05-2022", "Odense", "Vi skal ud og se på Egetræer", 75);
    static Udflugt trapholdtMuseumKolding = new Udflugt("Trapholt Museum Kolding", "20-05-2022", "Æblehaven 23, Kolding", "Vi skal se på modern kunst", 200);


    public static ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public static ArrayList<Udflugt> getUdflugts() {
        return udflugts;
    }

    public static ArrayList<Deltager> getDeltagers() {
        return deltagers;
    }

    public static ArrayList<Ledsager> getLedsagers() {
        return ledsagers;
    }

    public static Konference getKonference() {
        return konference;
    }

}
