package model;

import model.enumaration.Gender;
import model.enumaration.compteType;
import utils.Stock;

import java.util.List;

public class Customer  extends User{

    private Gender gender;


    public Customer(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        super(type, login, password, lastName, firstName, phoneNumber, email);

        Stock.addCustomer(this);
    }



    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
