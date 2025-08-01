package model;

import model.enumaration.Gender;
import model.enumaration.compteType;
import data.Stock;
import services.bridge.TransactionAction;

import java.math.BigDecimal;

public class Customer  extends User implements Cloneable {

    private Gender gender;


    public Customer(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        super(type, login, password, lastName, firstName, phoneNumber, email);

        Stock.addCustomer(this);
    }

    public Customer() {
        super();
    }
    public Customer(TransactionAction transactionAction) {
        super( transactionAction);
    }
    public Customer(UserAccount userAccount,Wallet wallet, String lastName, String firstName, String phoneNumber, String email,Gender gender,TransactionAction transactionAction){
        super(userAccount,lastName,firstName, phoneNumber,email, wallet);
        this.gender = gender;
        this.transactionAction = transactionAction;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public BigDecimal getBalance(Wallet wallet) {
        return super.getBalance(wallet);
    }

    @Override
    public void retrait(Wallet wallet, BigDecimal montant) {
        transactionAction.retrait(wallet,montant);
    }

    @Override
    public void depot(Wallet wallet, BigDecimal montant) {
        transactionAction.depot(wallet,montant);
    }

    @Override
    public Customer clone() {
        try {
            Customer cloned = (Customer) super.clone();
            cloned.setWallet(this.getWallet().clone());
            cloned.setUserAccount(this.getUserAccount().clone()); // idem
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clonage échoué", e);
        }
    }

}
