package storage;

import application.controller.Controller;
import application.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Storage {
    private static ArrayList<Hotel> hotels = new ArrayList<>();

    private static ArrayList<Deltager> deltagers = new ArrayList<>();

    private static ArrayList<Udflugt> udflugter = new ArrayList<>();
    private static ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    private static ArrayList<Ledsager> ledsagers = new ArrayList<>();
    private static Konference konference;
    private static ArrayList<Konference> konferencer = new ArrayList<>();


    public static void initContent() {
        konference = new Konference("Milijøkonferencen", "20-05-2022"
                , "Odense Universitet", "Hav og Himmel",
                "Konference om Hav og Himmel", "18-05-2022", 1500);

        Hotel denHvideSvane = new Hotel("Den Hvide Svane", 1250, 1050);
        Hotel hoetelPhoenix = new Hotel("Hotel Phønix", 800, 700);
        Hotel pensionTusindfryd = new Hotel("Pension Tusindfryd", 600, 500);

        Udflugt byrundtur = new Udflugt("Byrundtur", "18-05-2022", "Odense Universitet", "Serværdigheder i odense", 125);
        Udflugt egeskov = new Udflugt("Egeskov", "19-05-2022", "Odense", "Egetræer", 75);
        Udflugt trapholdtMuseumKolding = new Udflugt("Trapholt Museum Kolding", "20-05-2022", "Æblehaven 23, Kolding", "Moderne kunst", 200);

        Deltager peterSommer = new Deltager("Peter Sommer", "Klosterbakken 7D", 81912019, "Aarhus", "Denmark", false, "NPC", 92301020);
        Deltager finnMadsen = new Deltager("Finn Madsen", "Klosterbakken 5A", 56713455, "Denmark", "Denmark", false, "NPS", 76298800);
        Deltager nielsPetersen = new Deltager("Niels Petersen", "Aarhusvej 6", 2243522, "Aarhus", "Denmark", false, "NPK", 13531513);
        Deltager loneJensen = new Deltager("Lone Jensen", "Vejen 26", 88776655, "Aarhus", "Spain", true, "NpS", 62667788);

        Ledsager mieSommer = new Ledsager("Mie Sommer", "Klosterbakken 7D", 90682019, "Aarhus", "Denmark");
        Ledsager janMadsen = new Ledsager("Jan Madsen", "Vejen 26", 351426246, "Aarhus", "Spain");

        Service denHvideSvaneWifi = new Service("Wifi", 50);
        Service hoetelPhoenixWifi = new Service("Wifi", 75);
        Service hoetelPhoenixBad = new Service("Bad", 200);
        Service pensionTusindfrydMorgenmad = new Service("Mad", 100);

        Tilmelding loneJensenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", loneJensen);
        loneJensenTilmelding.setLedsager(janMadsen);
        loneJensenTilmelding.setHotel(denHvideSvane);
        loneJensenTilmelding.addService(denHvideSvaneWifi);

        janMadsen.addUdflugt(byrundtur);
        janMadsen.addUdflugt(egeskov);


        Tilmelding peterSommerTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", peterSommer);
        peterSommerTilmelding.setLedsager(mieSommer);
        peterSommerTilmelding.setHotel(denHvideSvane);
        //peterSommerTilmelding.addService(denHvideSvaneWifi);

        mieSommer.addUdflugt(trapholdtMuseumKolding);
        mieSommer.addUdflugt(egeskov);

        Tilmelding finnMadsenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", finnMadsen);

        Tilmelding nielsPetersenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", nielsPetersen);
        nielsPetersenTilmelding.setHotel(denHvideSvane);


        tilmeldinger.add(finnMadsenTilmelding);
        tilmeldinger.add(nielsPetersenTilmelding);
        tilmeldinger.add(peterSommerTilmelding);

        denHvideSvane.addService(denHvideSvaneWifi);
        hoetelPhoenix.addService(hoetelPhoenixWifi);
        hoetelPhoenix.addService(hoetelPhoenixBad);
        pensionTusindfryd.addService(pensionTusindfrydMorgenmad);

        hotels.add(denHvideSvane);
        hotels.add(hoetelPhoenix);
        hotels.add(pensionTusindfryd);

        udflugter.add(byrundtur);
        udflugter.add(egeskov);
        udflugter.add(trapholdtMuseumKolding);
    }

    public static ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public static ArrayList<Udflugt> getUdflugter() {
        return udflugter;
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

    public static void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public static void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }
    public static void removeKonference(Konference konference) {
        Storage.konferencer.remove(konference);
    }
    public static void addKonference(Konference konference) {
        Storage.konferencer.add(konference);
    }

    public static void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public static void removeUdflugt(Udflugt udflugt) {
        udflugter.remove(udflugt);
    }
}
