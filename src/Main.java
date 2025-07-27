import UseCases.BasicInfoUseCases;
import UseCases.Menu;
import dao.CustomerDao;
import dao.UserAccountDao;
import model.BasicInfo;
import model.Customer;
import model.UserAccount;
import model.enumaration.compteType;
import utils.DisplayUtil;

import java.util.Scanner;

import static UseCases.Menu.initApp;
import static UseCases.Menu.mainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        initApp();//creation des premiers users pour les tests

        UserAccountDao userAccountDao = new UserAccountDao();
        UserAccount userAccount = new UserAccount(compteType.CUSTOMER,"Eyyli","44BS0507");
        userAccountDao.create(userAccount);

    //    userAccountDao.deleteUserAccount("timitou");
    //    userAccount.setCompteType(compteType.MERCHANT);
    //    userAccount.setPassword("0092929");
    //    userAccountDao.update(userAccount);

        UserAccount userNew = userAccountDao.readUserAccount(userAccount.getLogin());
        System.out.println(userNew.getId());

        CustomerDao customerDao = new CustomerDao();
        Customer client = new Customer(compteType.CUSTOMER,userAccount.getLogin(),userAccount.getPassword(),"daddy","kone","9987654","gqgg@hh");

        customerDao.createCustomer(client);
        client.setFirstName("Koua");
        Customer newclient = customerDao.updateCustomer(client);
        customerDao.deleteCustomer(client);
        newclient = customerDao.readCustomer(client);

        final Scanner scanner = new Scanner(System.in); //declaration du scanner pour les saisies de l'utilisateur

        boolean run = true;

        while (run){    //boucle d'affichage du menu principal
            run= mainMenu(scanner);
        }
    }
}