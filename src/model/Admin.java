package model;

import model.enumaration.compteType;
import utils.Stock;

import java.util.List;

public class Admin extends BasicInfo {

    private List<String> Privileges;

    public Admin(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email){
        super(type,  login,  password,  lastName,  firstName,  phoneNumber,  email);
        Stock.addAdmin(this);
    }

    public List<String> getPrivileges() {
        return Privileges;
    }

    public void setPrivileges(List<String> privileges) {
        Privileges = privileges;
    }




}
