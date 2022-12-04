import application.controller.Controller;
import application.model.*;

public class Main {

    public static void main(String[] args) {

        Konference konference = new Konference("Milijøkonferencen", "20-05-2022"
                , "Odense Universitet", "Hav og Himmel",
                "Konference om Hav og Himmel", "18-05-2022", 1500);

        Hotel denHvideSvane = new Hotel("Den Hvide Svane", 1250, 1050);
        Hotel hoetelPhoenix = new Hotel("Høtel Phønix", 800, 700);
        Hotel pensionTusindfryd = new Hotel("Pension Tusindfryd", 600, 500);

        konference.addHotel(denHvideSvane);
        konference.addHotel(hoetelPhoenix);
        konference.addHotel(pensionTusindfryd);


        Udflugt byrundtur = new Udflugt("Byrundtur", "18-05-2022", "Odense Universitet", "Vi skal se på de forskellige serværdigheder i odense", 125);
        Udflugt egeskov = new Udflugt("Egeskov", "19-05-2022", "Odense", "Vi skal ud og se på Egetræer", 75);
        Udflugt trapholdtMuseumKolding = new Udflugt("Trapholt Museum Kolding", "20-05-2022", "Æblehaven 23, Kolding", "Vi skal se på modern kunst", 200);



        Service denHvideSvaneWifi = new Service("Wifi", 50);
        Service hoetelPhoenixWifi = new Service("Wifi", 75);

        Service hoetelPhoenixBad = new Service("Bad", 200);

        Service pensionTusindfrydMorgenmad = new Service("Mad", 100);

        denHvideSvane.addService(denHvideSvaneWifi);
        hoetelPhoenix.addService(hoetelPhoenixWifi);
        hoetelPhoenix.addService(hoetelPhoenixBad);
        pensionTusindfryd.addService(pensionTusindfrydMorgenmad);


        Deltager peterSommer = new Deltager("Peter Sommer", "Klosterbakken 7D", 81912019, "Aarhus", "Denmark", false, "NPC", 92301020);
        Deltager finnMadsen = new Deltager("Finn Madsen", "Klosterbakken 5A", 56713455, "Denmark", "Denmark", false, "NPS", 76298800);
        Deltager nielsPetersen = new Deltager("Niels Petersen", "Aarhusvej 6", 2243522, "Aarhus", "Denmark", false, "NPK", 13531513);
        Deltager loneJensen = new Deltager("Lone Jensen", "Vejen 26", 88776655, "Aarhus", "Spain", true, "NpS", 62667788);

        Ledsager mieSommer = new Ledsager("Mie Sommer", "Klosterbakken 7D", 90682019, "Aarhus", "Denmark");
        Ledsager janMadsen = new Ledsager("Jan Madsen", "Vejen 26", 351426246, "Aarhus", "Spain");

        Tilmelding loneJensenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", loneJensen);
        loneJensenTilmelding.setLedsager(janMadsen);
        loneJensenTilmelding.setHotel(denHvideSvane);
        loneJensenTilmelding.addService(denHvideSvaneWifi);

        janMadsen.addUdflugt(byrundtur);
        janMadsen.addUdflugt(egeskov);


        Tilmelding peterSommerTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", peterSommer);
        peterSommerTilmelding.setLedsager(mieSommer);
        peterSommerTilmelding.setHotel(denHvideSvane);
        peterSommerTilmelding.addService(denHvideSvaneWifi);

        mieSommer.addUdflugt(trapholdtMuseumKolding);
        mieSommer.addUdflugt(egeskov);

        Tilmelding finnMadsenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", finnMadsen);

        Tilmelding nielsPetersenTilmelding = new Tilmelding("18-05-2022", konference, "20-05-2022", nielsPetersen);
        nielsPetersenTilmelding.setHotel(denHvideSvane);

        System.out.println("Finn skal betale: " + Controller.getTotalPrice(finnMadsenTilmelding));
        System.out.println("Peter Sommer skal betale: " + Controller.getTotalPrice(peterSommerTilmelding));
        System.out.println("Niels Petersen skal betale: " + Controller.getTotalPrice(nielsPetersenTilmelding));
        System.out.println("Lone skal betale: " + Controller.getTotalPrice(loneJensenTilmelding));
    }
}
