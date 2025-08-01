package ci.ada.model;



import ci.ada.data.Stock;
import ci.ada.model.enumaration.compteType;

import java.util.List;

public class Admin extends BasicInfo {

    private List<String> Privileges;

    public Admin() {
        super();
    }

    public Admin(compteType type, String login, String password, String lastName, String firstName, String phoneNumber, String email){
        super(type,  login,  password,  lastName,  firstName,  phoneNumber,  email);
        Stock.addAdmin(this);
    }

    public Admin(UserAccount userAccount, String lastName, String firstName, String phoneNumber, String email){
        super(userAccount,lastName,firstName, phoneNumber,email);

    }

    public List<String> getPrivileges() {
        return Privileges;
    }

    public void setPrivileges(List<String> privileges) {
        Privileges = privileges;
    }




}
