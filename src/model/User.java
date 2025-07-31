package model;

import model.enumaration.compteType;
import services.transactions.TransactionAction;

import java.math.BigDecimal;

public class User extends BasicInfo {

    protected TransactionAction transactionAction;

    private String profileImage;

    private Wallet wallet;



    public User(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        super(type, login, password, lastName, firstName, phoneNumber, email);
        //this.wallet = new Wallet(this);

    }

    public User(UserAccount userAccount, String lastName, String firstName, String phoneNumber, String email, Wallet wallet) {
        super(userAccount, lastName, firstName, phoneNumber, email);
        this.wallet = wallet;

    }
    public User(TransactionAction transactionAction) {
        super();
        this.transactionAction = transactionAction;
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
    public void setTransactionAction(TransactionAction transactionAction) {
        this.transactionAction = transactionAction;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    public void retrait(Wallet wallet, BigDecimal montant){

    }
    public BigDecimal getBalance(Wallet wallet){return null;}

    public void depot(Wallet wallet,  BigDecimal montant){

    }


}
