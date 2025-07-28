package model;

import model.enumaration.compteType;
import data.Stock;

import java.util.Objects;

public class BasicInfo {

    private static Long compteurId = 0L; // partagé entre toutes les instances

    private  Long id=0L;

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    private UserAccount userAccount;

    public BasicInfo(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        this.id = compteurId++; // ID unique pour chaque nouvel objet
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAccount = new UserAccount(type, login, password, false);
        Stock.addInfo(this);
    }

    public static void setCompteurId(Long compteurId) {
        BasicInfo.compteurId = compteurId;
    }

    public BasicInfo() {

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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Boolean getConnected() {
        return this.userAccount.getConnected();
    }

    public void setConnected(Boolean connected) {
        this.userAccount.setConnected(connected);
    }

    // 🔁 equals basé sur l'id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicInfo that = (BasicInfo) o;
        return Objects.equals(id, that.id);
    }

    // 🔁 hashCode basé sur l'id aussi
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
