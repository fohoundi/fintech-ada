package model;

import model.enumaration.Gender;
import model.enumaration.compteType;
import data.Stock;

import java.math.BigDecimal;


public class Marchant  extends User {

    private String location;

    @Override
    public void retrait(Wallet wallet, BigDecimal montant) {
        transactionAction.retrait(wallet,montant);

    }

    @Override
    public BigDecimal getBalance(Wallet wallet) {
        return super.getBalance(wallet);
    }

    @Override
    public void depot(Wallet wallet, BigDecimal montant) {
        transactionAction.depot(wallet,montant);
    }

    public Marchant(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email, String location) {
        super(type, login, password, lastName, firstName, phoneNumber, email);
        this.location = location;

        Stock.addMarchant(this);
    }

    public Marchant(UserAccount userAccount, Wallet wallet, String lastName, String firstName, String phoneNumber, String email, String location){
        super(userAccount,lastName,firstName, phoneNumber,email, wallet);
        this.location = location;
    }
    public Marchant(){
        super();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
