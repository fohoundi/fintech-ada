import UseCases.BasicInfoUseCases;
import UseCases.Menu;
import dao.*;
import model.*;
import model.enumaration.Gender;
import model.enumaration.compteType;
import utils.DisplayUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static UseCases.Menu.initApp;
import static UseCases.Menu.mainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //initApp();//creation des premiers users pour les tests

        /* Test Wallet */
            /*Wallet wallet = new Wallet();
            WalletDao walletDao = new WalletDao();
            walletDao.createWallet(wallet);
            wallet.setBalance(BigDecimal.valueOf(300));
            walletDao.updateWallet(wallet); */
            //Wallet newWallet = walletDao.readWallet(21L);
            //System.out.println(newWallet.getBalance());


        /* Test Custoner */
            /*UserAccount userAccount = new UserAccount(compteType.CUSTOMER,"eliot","82dhhhd82");
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccountDao.create(userAccount);
            //UserAccount userAccount = userAccountDao.readUserAccountById(6L);
            System.out.println(userAccount.getLogin());

            Customer customer = new Customer();
            customer.setWallet(wallet);
            customer.setUserAccount(userAccount);
            customer.setEmail("eliot@kk");
            customer.setGender(Gender.WOMEN);
            customer.setFirstName("eliot");
            customer.setLastName("edeer");
            customer.setPhoneNumber("584455578");
            CustomerDao customerDao = new CustomerDao();
            //create
            customer = customerDao.createCustomer(customer);
            System.out.println(customer.getUserAccount().getId());
            //update
            customer.setPhoneNumber("122221");
            customerDao.updateCustomer(customer);
            //read
            Customer client = customerDao.readCustomer(28L);
            System.out.println(client.getEmail()); */


        /* TEST MARCHAND */
        /*Wallet wallet = new Wallet();
            WalletDao walletDao = new WalletDao();
            wallet = walletDao.createWallet(wallet);
            UserAccount userAccount = new UserAccount(compteType.MERCHANT,"gra","gra777");
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccount = userAccountDao.create(userAccount);

            Marchant marchant = new Marchant();
            marchant.setLocation("cocody");
            marchant.setWallet(wallet);
            marchant.setEmail("gra@00");
            marchant.setFirstName("gra");
            marchant.setLastName("gro");
            marchant.setPhoneNumber("0444499333");
            marchant.setUserAccount(userAccount);

            MerchantDao marchantDao = new MerchantDao();
            marchant = marchantDao.createMerchant(marchant);

            marchant.setLocation("yop");
            marchantDao.updateMarchant(marchant);
            marchant = marchantDao.readMarchant(marchant.getId());
            System.out.println(marchant.getLocation());
          */
         /* Test Admin */
        UserAccount userAccount = new UserAccount(compteType.ADMIN,"adou","adou776");
        UserAccountDao userAccountDao = new UserAccountDao();
        userAccount = userAccountDao.create(userAccount);

        Admin admin = new Admin();
        admin.setEmail("adou@joud");
        admin.setFirstName("adou");
        admin.setLastName("adoua");
        admin.setPhoneNumber("34445533");
        List<String> privileges = Arrays.asList("read", "write");
        admin.setPrivileges(privileges);
        admin.setUserAccount(userAccount);
        AdminDao adminDao = new AdminDao();
        admin = adminDao.createAdmin(admin);

        admin.setEmail("hhdhdh");
        adminDao.updateAdmin(admin);



        final Scanner scanner = new Scanner(System.in); //declaration du scanner pour les saisies de l'utilisateur

        boolean run = true;

        while (run){    //boucle d'affichage du menu principal
            run= mainMenu(scanner);
        }
    }
}