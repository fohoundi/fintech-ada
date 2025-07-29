package model;

import model.enumaration.Gender;
import model.enumaration.compteType;
import data.Stock;

public class Customer  extends User {

    private Gender gender;


    public Customer(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        super(type, login, password, lastName, firstName, phoneNumber, email);

        Stock.addCustomer(this);
    }

    public Customer() {
        super();
    }
    public Customer(UserAccount userAccount,Wallet wallet, String lastName, String firstName, String phoneNumber, String email,Gender gender){
        super(userAccount,lastName,firstName, phoneNumber,email, wallet);
        this.gender = gender;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
