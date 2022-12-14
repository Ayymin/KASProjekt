package application.model;

import java.util.ArrayList;

public class Hotel {

    private int adress;
    private String name;
    private double doubelPrice;
    private double singlePrice;
    private ArrayList<String> tilmeldinger = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();
    private Konference konference;

    public Hotel(String name, double doublePrice, double singlePrice) {
        this.name = name;
        this.doubelPrice = doublePrice;
        this.singlePrice = singlePrice;
    }

    public int getAdress() {
        return adress;
    }

    public void setAdress(int adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDoubelPrice() {
        return doubelPrice;
    }

    public void setDoubelPrice(double doubelPrice) {
        this.doubelPrice = doubelPrice;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public void addService(Service service){
        services.add(service);
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public double getPrice(boolean single) {
        if (single) {
            return singlePrice;
        }
        return doubelPrice;
    }

    public Konference getKonference() {
        return konference;
    }

    @Override
    public String toString() {return name + " (Singlepris: "+ singlePrice + " kr) " + "(Doublepris: " + doubelPrice + " kr)";}




}
