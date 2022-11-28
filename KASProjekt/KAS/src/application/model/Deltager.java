package application.model;

public class Deltager extends Person {

    private boolean isSpeaker;
    private String companyName;
    private int companyPhoneNr;



    public Deltager(String name, String adress, int phoneNr, String city, String country
            ,boolean isSpeaker, String companyName, int companyPhoneNr) {
        super(name, adress, phoneNr, city, country);
        this.isSpeaker = isSpeaker;
        this.companyName = companyName;
        this.companyPhoneNr = companyPhoneNr;

    }

    public boolean isSpeaker() {
        return isSpeaker;
    }

    public void setSpeaker(boolean speaker) {
        isSpeaker = speaker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyPhoneNr() {
        return companyPhoneNr;
    }

    public void setCompanyPhoneNr(int companyPhoneNr) {
        this.companyPhoneNr = companyPhoneNr;
    }







}
