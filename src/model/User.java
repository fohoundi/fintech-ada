package model;

import model.enumaration.compteType;

import java.math.BigDecimal;
import java.util.List;

public class User extends BasicInfo{

    private String profileImage;

    private Wallet wallet;



    public User(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        super(type, login, password, lastName, firstName, phoneNumber, email);
        this.wallet = new Wallet(this);

    }

    public User() {
        super();
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }



}
