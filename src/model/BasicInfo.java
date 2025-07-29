package model;

import model.enumaration.compteType;
import data.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BasicInfo {

    private static Long compteurId = 0L; // partagé entre toutes les instances

    private  Long id=0L;

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private UserAccount userAccount;
    private String matricule;
    private final List<Integer> generatedNum = new ArrayList<>();
    private static final Random random = new Random();


    public BasicInfo(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email) {
        this.id = compteurId++; // ID unique pour chaque nouvel objet
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAccount = new UserAccount(type, login, password, false);
        this.matricule = generateMatricule(firstName,lastName);
        Stock.addInfo(this);
    }

    public BasicInfo(UserAccount userAccount, String lastName, String firstName, String phoneNumber, String email) {
        this.id = compteurId++; // ID unique pour chaque nouvel objet
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAccount = userAccount;
        this.matricule = generateMatricule(firstName,lastName);
        Stock.addInfo(this);
    }
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String generateMatricule(String nom, String prenom){
        String nomComplet = nom+" "+prenom;
        String[] noms = nomComplet.trim().split("\\s+");
        StringBuilder initiales = new StringBuilder();

        for (String name : noms) {
            if (!name.isEmpty()) {
                initiales.append(Character.toUpperCase(name.charAt(0)));
            }
        }

        int nb = generateNum();

        return "ADA-CH3-2025-" + initiales + "-" + String.format("%04d", nb);

    }
    private int generateNum (){
        int numero;
        do {
            numero = 1 + random.nextInt(1000); // 1 à 1000 inclus
        } while (generatedNum.contains(numero));

        generatedNum.add(numero);
        return numero;
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
