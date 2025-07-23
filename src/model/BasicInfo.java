package model;

import model.enumaration.compteType;
import utils.Stock;

public class BasicInfo {

    private final Long id;

    private Long compteurId = 0L;

    private String lastName;

    private String firstName;

    private String phoneNumber;

    private String email;

    private UserAccount userAccount;

    public BasicInfo(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        this.id = compteurId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAccount = new UserAccount(type,login,password,false);
        Stock.addInfo(this);
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Long getId() {
        return this.id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Boolean getConnected() {
        return this.userAccount.getConnected();
    }

    public void setConnected(Boolean connected) {
        this.userAccount.setConnected(connected);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
