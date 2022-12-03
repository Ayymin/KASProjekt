package application.model;

import application.controller.Controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tilmelding {

    private double totalPrice;
    private String arrivalDate;
    private String depatureDate;
    private Deltager deltager;
    private Ledsager ledsager;
    private Hotel hotel;
    private Konference konference;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();


    public Tilmelding(String arrivalDate, Konference konference, String depatureDate, Deltager deltager) {
        this.arrivalDate = arrivalDate;
        this.deltager = deltager;
        this.depatureDate = depatureDate;
        this.konference = konference;

    }

    public double getTotalPrice() {
        return Controller.getTotalPrice(this);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepatureDate() {
        return depatureDate;
    }

    public void setDepatureDate(String depatureDate) {
        this.depatureDate = depatureDate;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public void setDeltager(Deltager deltager) {
        this.deltager = deltager;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public Konference getKonference() {
        return konference;
    }

    public void removeUdflugt(Udflugt udflugt) {
    }

    public void addUdlugt(Udflugt udflugt) {
    }
}