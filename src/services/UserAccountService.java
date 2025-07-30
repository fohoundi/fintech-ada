package services;

import dao.CustomerDao;
import dao.UserAccountDao;
import data.Stock;
import model.BasicInfo;
import model.Customer;
import model.UserAccount;
import model.enumaration.compteType;
import utils.DisplayUtil;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserAccountService {

    public UserAccount register(UserAccount userAccount){
        UserAccountDao userAccountDao = new UserAccountDao();
        return userAccountDao.createUser(userAccount);
    }

    public  UserAccount login (String login, String password ){
        UserAccountDao userAccountDao = new UserAccountDao();
        UserAccount user;
        user = userAccountDao.findByLogin(login);
        if (user != null && Objects.equals(user.getPassword(), password)){
            user.setConnected(true);
            return user;
        }else {
            return null;
        }
    }

    public UserAccount findById(Long id){
        UserAccountDao userAccountDao = new UserAccountDao();
        UserAccount userAccount = new UserAccount();
        userAccount = userAccountDao.findById(id);
        return userAccount;
    }

    public UserAccount findByLogin(String login){
        UserAccountDao userAccountDao = new UserAccountDao();
        UserAccount userAccount = new UserAccount();
        userAccount = userAccountDao.findByLogin(login);
        return userAccount;
    }

    public List<UserAccount> findAll() {
        UserAccountDao userAccountDao = new UserAccountDao();
        List<UserAccount> userAccounts = userAccountDao.findAll();
        return userAccounts;
    }

    public void delete(Long id){
        UserAccountDao userAccountDao = new UserAccountDao();
        userAccountDao.deleteUserAccount(id);

    }



    /********A IMPLEMENTER *********/
    public UserAccount updateUser(UserAccount userAccount ){
        UserAccountDao userAccountDao = new UserAccountDao();
        return userAccountDao.updateUserAccount(userAccount);
    }


    public UserAccount updateCompteType(){
        return null;
    }







    /******** OLD *********/
    // methode de login : defini le user comme etant connecte
    public static  BasicInfo login(){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez votre username");
        String username = scanner.nextLine();
        DisplayUtil.display("Entrez votre mot de passe ");
        String password = scanner.nextLine();

        /// // verifier le login
        BasicInfo user = BasicInfoUseCases.authentificate(username,password);
        if ( user != null){
            DisplayUtil.display("Bienvenu "+user.getFirstName()+"");
            user.setConnected(true);
            return user;

        }else{
            DisplayUtil.display(" Informations de connection invalides ! veuillez saisir des infos correctes !");
            return null;
        }
    }

}
