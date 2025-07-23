package model;

import model.enumaration.compteType;
import utils.Stock;

import java.util.List;


public class Marchant  extends User{

    private String location;



    public Marchant(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email, String location) {
        super(type, login, password, lastName, firstName, phoneNumber, email);
        this.location = location;

        Stock.addMarchant(this);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
