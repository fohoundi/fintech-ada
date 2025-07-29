package services;

import dao.WalletDao;
import model.*;
import model.enumaration.Gender;
import model.enumaration.compteType;
import utils.DisplayUtil;

import java.util.Scanner;

public class MainService {

    public static void mainMenu(Scanner scanner){
        DisplayUtil.display("=== BIENVENU SUR FINTECH ADA ! === ");
        DisplayUtil.display("=== Choisissez une option :=== ");
        DisplayUtil.display("1 | s'inscrire ");
        DisplayUtil.display("2 | s'authentifier  ");
        DisplayUtil.display("0 | Quitter  ");

        String choix = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        /* loading */
        DisplayUtil.loading("chargement");

        switch (choix){
            case ("1"):{
                registerUser();
            }
            case ("2"):{
                UserAccount user = loginUser();

                break;
            }
            case ("0") :{
                DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
            }
            default:
                DisplayUtil.display("Option invalide. Veuillez réessayer.");break;
        }




    }

    public static void registerUser(){

        final Scanner scanner = new Scanner(System.in);
        DisplayUtil.display(" Veuillez entrer votre Nom d'utilisateur:");
        String login = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre mot de passe :");
        String mdp = scanner.nextLine();

        UserAccount userAccount = new UserAccount();
        userAccount.setLogin(login);
        userAccount.setPassword(mdp);

        UserAccountService userAccountService = new UserAccountService();
        userAccount = userAccountService.register(userAccount);

        DisplayUtil.display(" Veuillez entrer votre Nom:");
        String nom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre Prenom:");
        String prenom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre numero de tel:");
        String telephone = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre email:");
        String email = scanner.nextLine();

        DisplayUtil.display("Choississez un role. Vous etes :");
        DisplayUtil.display("1 | client ");
        DisplayUtil.display("2 | admin  ");
        DisplayUtil.display("3 | marchand  ");
        String choixRole = scanner.nextLine();


        switch (choixRole){
            case ("1"):{

                Wallet wallet = new Wallet();
                WalletDao walletDao = new WalletDao();
                wallet = walletDao.createWallet(wallet);

                DisplayUtil.display(" Quel est votre sexe? (WOMEN|MEN");
                Gender sexe = Gender.valueOf(scanner.nextLine());
                Customer customer = new Customer(userAccount,wallet,nom,prenom,telephone,email,sexe);

                CustomerService customerService= new CustomerService();
                customer = customerService.register(customer);

                break;

            }
            case ("2"):{
                Admin admin = new Admin(userAccount,nom,prenom,telephone,email);

                AdminService adminService = new AdminService();
                admin = adminService.register(admin);
                break;

            }
            case ("3"):{


                DisplayUtil.display(" Ou se situe votre magasin?");
                String location = scanner.nextLine();

                Wallet wallet = new Wallet();
                WalletDao walletDao = new WalletDao();
                wallet = walletDao.createWallet(wallet);

                Marchant marchant = new Marchant(userAccount,wallet,nom,prenom,telephone,email,location);

                break;
            }
            default:
        }
        DisplayUtil.display("Compte cree avec succes!!!");
        DisplayUtil.display("///////////////////////////////////////");

    }
    public static UserAccount  loginUser() {
        final Scanner scanner = new Scanner(System.in);
        UserAccount user = new UserAccount();

        while (user == null) {
            DisplayUtil.display("Entrez votre username");
            String username = scanner.nextLine();
            DisplayUtil.display("Entrez votre mot de passe ");
            String password = scanner.nextLine();

            UserAccountService userAccountService = new UserAccountService();

            user = userAccountService.login(username, password);
            if (user == null) {
                DisplayUtil.display(" Informations de connection invalides ! veuillez saisir des infos correctes !");
            }
        }
        return user;
    }

    public static void showMenu(BasicInfo user){
        compteType accountType = user.getUserAccount().getCompteType();

        switch (accountType){
            case MERCHANT, CUSTOMER -> menuUser((User) user);
            case ADMIN -> menuAdmin((Admin) user);
        }

    }

    //menu du marchand et du client
    public static void menuUser(User user){
        final Scanner scanner = new Scanner(System.in);
        boolean actif = true;
        while (actif) {
            //DisplayUtil.display("Bienvenu "+user.getFirstName());
            DisplayUtil.display("MENU :");
            DisplayUtil.display("1 | Retirer de l'argent ");
            DisplayUtil.display("2 | deposer de l'argent ");
            DisplayUtil.display("3 | Consulter son solde ");
            DisplayUtil.display("4 | Me deconnecter");

            String choix = scanner.nextLine();
            switch (choix){
                case "1":makeTransaction( user,"retrait");break;
                case "2":makeTransaction( user,"depot");break;
                case "3":DisplayUtil.display("votre solde est : "+showBalance((User) user));break;
                case "4":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:DisplayUtil.display("choix invalide");break;

            }
        }

    }

    public static void menuAdmin(Admin admin){
        final Scanner scanner = new Scanner(System.in);
        boolean actif = true;
        while (actif) {
            DisplayUtil.display("Bienvenu Admin :"+admin.getFirstName());
            DisplayUtil.display("MENU :");
            DisplayUtil.display("1 | Voir la liste des clients ");
            DisplayUtil.display("2 | Voir la liste des Marchands ");
            DisplayUtil.display("3 | Ajouter un utilisateur ");
//            DisplayUtil.display("4 | Promouvoir un utilisateur");
//            DisplayUtil.display("5 | Supprimer un utilisateur");
            DisplayUtil.display("0 | Me deconnecter");

            String choix = scanner.nextLine();
            switch (choix){
                case "1":{
                    AdminService adminService = new AdminService();

                    DisplayUtil.loading("chargement de la liste");

                    AdminService.getCustomers();
                    break;
                }
                case "2":{
                    DisplayUtil.loading("chargement de la liste");
                    AdminService.getMerchants();
                    break;

                }
                case "3":{
                    BasicInfoUseCases.register();
                    break;
                }
//                case "4":{
//                    DisplayUtil.display("Entrez l'id de utilisateur que vous voulez rendre admin ");
//                    String id =scanner.nextLine();
//                    AdminService.promoteUser(u);
//                }
                case "0":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:DisplayUtil.display("choix invalide");


            }
        }

    }

}
