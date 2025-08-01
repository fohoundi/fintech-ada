package builders;

import model.Customer;
import model.UserAccount;
import model.Wallet;
import model.enumaration.Gender;
import services.bridge.TransactionAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerBuilder  {

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private UserAccount userAccount;
    //private String matricule;
    private final List<Integer> generatedNum = new ArrayList<>();
    private static final Random random = new Random();
    private TransactionAction transactionAction;
    private Gender gender;
    private Wallet wallet;

    //@Override
    public CustomerBuilder setFirstname(String firstName) {
        this.firstName = firstName;
        return this;
    }

    //@Override
    public CustomerBuilder setLastName(String lastname) {
        this.lastName = lastname;
        return this;
    }

    //@Override
    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    //@Override
    public CustomerBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    //@Override
    //public void setMatricule(String matricule) {this.matricule = matricule;}

    //@Override
    public CustomerBuilder setUserAccount(UserAccount userAccount) {
        this.userAccount=userAccount;
        return this;
    }

   // @Override
    public CustomerBuilder setTransactionAction(TransactionAction transactionAction) {
        this.transactionAction=transactionAction;
        return this;
    }

    //@Override
    public CustomerBuilder setWallet(Wallet wallet) {
        this.wallet=wallet;
        return this;
    }

    public CustomerBuilder setGender(Gender gender) {
        this.gender=gender;
        return this;
    }

    //@Override
    public Customer build(){
        return new Customer(userAccount,wallet,lastName,firstName,phoneNumber,email,gender,transactionAction);
    }
}
