package model;

import model.enumaration.compteType;

import java.util.List;

public class UserAccount {

    private  Long id;

    private compteType compteType;

    private String login;

    private String password;

    private Boolean isConnected;

    private static Long compteurId = 1L;

    public UserAccount( compteType compteType, String login, String password, Boolean isConnected) {
        this.id = compteurId ++;
        this.compteType = compteType;
        this.login = login;
        this.password = password;
        this.isConnected = false;
    }

    public UserAccount(compteType compteType, String login, String password) {
        this.compteType = compteType;
        this.login = login;
        this.password = password;
        this.isConnected = false;
        this.id = compteurId ++;
    }

    public UserAccount() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public compteType getCompteType() {
        return compteType;
    }

    public void setCompteType(compteType type) {
        this.compteType = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /// //////METHODES

    public void signIn(BasicInfo user){
        user.setConnected(true);
    }


}
