package services;

import dao.AdminDao;
import model.*;
import model.enumaration.compteType;
import utils.DisplayUtil;
import utils.InputUtils;
import data.Stock;
import model.User;

import java.util.List;
import java.util.Scanner;

import static utils.DisplayUtil.successMsg;

public class AdminService {

    public Admin register(Admin admin){
        AdminDao adminDao = new AdminDao();
        admin = adminDao.createAdmin(admin);
        return admin;
    }

    public static void getCustomers(){
        List<Customer> customers = Stock.getCustomerList();
        if (customers.isEmpty()){
            DisplayUtil.display(" Pas de clients enregistres pour le moment ");
        }else {
            DisplayUtil.display("=======================================================");
            DisplayUtil.display("| NOM | PRENOMS | EMAIL | NUMERO DE TELEPHONE | SOLDE |");
            DisplayUtil.display("=======================================================");
            for (Customer customer : customers){
                System.out.printf("= %s | %s | %s | %s | %s =%n",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        InputUtils.format_FCFA(customer.getWallet().getBalance())

                );
                DisplayUtil.display("----------------------------------------------------------");
            }
            DisplayUtil.display("=======================================================");
        }


    }

    public static void getMerchants(){
        List<Marchant> marchants = Stock.getMarchantList();
        if (marchants.isEmpty()){
            DisplayUtil.display(" Pas de marchants enregistres pour le moment ");
        }else {
            DisplayUtil.display("======================================================================");
            DisplayUtil.display("| NOM | PRENOMS | EMAIL | NUMERO DE TELEPHONE | SOLDE | LOCALISATION |");
            DisplayUtil.display("======================================================================");
            for (Marchant marchant : marchants){
                System.out.printf("= %s | %s | %s | %s | %s | %s =%n",
                        marchant.getFirstName(),
                        marchant.getLastName(),
                        marchant.getEmail(),
                        marchant.getPhoneNumber(),
                        InputUtils.format_FCFA(marchant.getWallet().getBalance()),
                        marchant.getLocation()

                );
                DisplayUtil.display("----------------------------------------------------------");
            }
            DisplayUtil.display("=======================================================");
        }

    }

    public static void getAdmins(){
        List<Customer> customers = Stock.getCustomerList();
        if (customers.isEmpty()){
            DisplayUtil.display(" Pas d'admins enregistres pour le moment");
        }else {
            DisplayUtil.display("============================================================");
            DisplayUtil.display("| NOM | PRENOMS | EMAIL | NUMERO DE TELEPHONE | PRIVILEGES | ");
            DisplayUtil.display("============================================================");
            for (Customer customer : customers){
                System.out.printf("= %s | %s | %s | %s | %s =%n",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        InputUtils.format_FCFA(customer.getWallet().getBalance())

                );
                DisplayUtil.display("----------------------------------------------------------");
            }
            DisplayUtil.display("=======================================================");
        }

    }

    public static void addUser(){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display(" Veuillez entrer le Nom:");
        String nom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer le Prenom:");
        String prenom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer le numero de tel:");
        String telephone = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer le email:");
        String email = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer le Nom d'utilisateur:");
        String login = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer le mot de passe :");
        String mdp = scanner.nextLine();
        DisplayUtil.display("Informations bien enregistrees !!");


        DisplayUtil.display("Choississez un role :");
        DisplayUtil.display("1 | client ");
        DisplayUtil.display("2 | admin  ");
        DisplayUtil.display("3 | marchand  ");
        String choixRole = scanner.nextLine();


        switch (choixRole){
            case ("1"):{

                DisplayUtil.display(" Quel est son  sexe? (F|M");
                String sexe = scanner.nextLine();
                Customer customer = new Customer(compteType.CUSTOMER,login,mdp,nom,prenom,telephone,email);
                successMsg();
                break;

            }
            case ("2"):{
                Admin admin = new Admin(compteType.ADMIN,login,mdp,nom,prenom,telephone,email);
                successMsg();
                break;

            }
            case ("3"):{
                DisplayUtil.display(" Ou se situe votre magasin?");
                String location = scanner.nextLine();
                Marchant marchant = new Marchant(compteType.MERCHANT,login,mdp,nom,prenom,telephone,email,location);
                successMsg();
                break;
            }
            default:

        }

    }


//    //promouvoir un simple user en admin
//    public static void promoteUser (String idStr, compteType type){
//        Long id = Long.parseLong(idStr);
//
//        List<BasicInfo> Users = Stock.getInfoList();
//        for(BasicInfo user : Users){
//            if (user.getUserAccount().getLogin().equals(login) && user.getUserAccount().getPassword().equals(mdp)){
//                return user;
//            }
//        }
//        return null;
//        user.getUserAccount().setCompteType(type);
//    }

    //suprimer un user
    public static void deleteUser(User user){
        Stock.deleteInfo((BasicInfo) user);
    }
}
