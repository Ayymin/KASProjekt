package application.model;

import java.util.ArrayList;

public class Ledsager extends Person {

    ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Ledsager(String name, String adress, int phoneNr, String city, String country){
        super(name, adress, phoneNr, city, country);

    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }





}
