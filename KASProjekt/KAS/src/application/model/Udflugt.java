package application.model;

import java.util.ArrayList;

public class Udflugt {

    private String adress;
    private String name;
    private String date;
    private String description;
    private int price;


    private ArrayList<Tilmelding> tilmelidnger;

    private Tilmelding tilmelding;

    public Udflugt(String name, String date, String adress, String description, int price) {
        this.name = name;
        this.date = date;
        this.adress = adress;
        this.description = description;
        this.price = price;
    }

    public String toString() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Tilmelding getTilmelding(){
        return tilmelding;
    }

    public void setTilmelidnger(Tilmelding tilmelding){
        if(this.tilmelding != tilmelding){
            Tilmelding oldTilmeling = this.tilmelding;
            if(oldTilmeling != null){
                oldTilmeling.removeUdflugt(this);
            }
            this.tilmelding = tilmelding;
            if(tilmelding != null)
                tilmelding.addUdlugt(this);
        }
    }



}
