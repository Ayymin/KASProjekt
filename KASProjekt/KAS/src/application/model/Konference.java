package application.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Konference {

    private String name;
    private String adress;
    private String topic;
    private String date;
    private LocalTime time;
    private String description;
    private String endDate;
    private double priceADay;
    private ArrayList<Hotel> hotels = new ArrayList<>();



    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    /**
     *
     * @param name
     * @param endDate
     */
    public Konference(String name, String endDate, String adress, String topic, String description, String date, double priceADay) {
        this.name = name;
        this.date = date;
        this.endDate = endDate;
        this.description = description;
        this.adress = adress;
        this.time = time;
        this.topic = topic;
        this.priceADay = priceADay;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public void setUdflugter(ArrayList<Udflugt> udflugter) {
        this.udflugter = udflugter;
    }

    public double getPriceADay() {
        return priceADay;
    }



}
