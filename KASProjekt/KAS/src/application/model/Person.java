package application.model;

public abstract class Person {

    private String name;
    private String adress;
    private int phoneNr;
    private String city;



    private String country;

    public Person(String name, String adress, int phoneNr, String city, String country) {
        this.name = name;
        this.adress = adress;
        this.phoneNr = phoneNr;
        this.city = city;
        this.country = country;

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

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }





}


