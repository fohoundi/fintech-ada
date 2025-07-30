package services;

import dao.WalletDao;
import model.*;
import model.enumaration.Gender;
import model.enumaration.compteType;
import utils.DisplayUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainService {
    private static final BalanceService balanceService = new BalanceService();
    private static final UserAccountService userAccountService = new UserAccountService();
    private static final CustomerService customerService = new CustomerService();
    private static final  AdminService adminService = new AdminService();

    public  boolean mainMenu(Scanner scanner){
        DisplayUtil.display("=== BIENVENU SUR FINTECH ADA ! === ");
        DisplayUtil.display("=== Choisissez une option :=== ");
        DisplayUtil.display("1 | s'inscrire ");
        DisplayUtil.display("2 | s'authentifier  ");
        DisplayUtil.display("0 | Quitter  ");

        String choix = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        /* loading */
       // DisplayUtil.loading("chargement");

        switch (choix){
            case ("1"):{
                registerUser();
            }
            case ("2"):{
                UserAccount user = this.loginUser();
                showMenu(user);
                break;
            }
            case ("0") :{
                DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !"); return false;
            }
            default:
                DisplayUtil.display("Option invalide. Veuillez réessayer.");break;
        }

        return true;


    }

    public  void registerUser(){

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
    public  UserAccount  loginUser() {
        final Scanner scanner = new Scanner(System.in);
        UserAccount user = new UserAccount();

        DisplayUtil.display("Entrez votre username");
        String username = scanner.nextLine();
        DisplayUtil.display("Entrez votre mot de passe ");
        String password = scanner.nextLine();

        user = userAccountService.login(username, password);
        if (user == null) {
            DisplayUtil.display(" Informations de connection invalides ! veuillez saisir des infos correctes !");
        }

        return user;
    }

    public  void showMenu(UserAccount user){
        compteType accountType = user.getCompteType();

        switch (accountType){
            case CUSTOMER -> {
                Customer customer = customerService.findByLogin(user.getId());
                menuUser(customer);
                break;
            }
            case MERCHANT -> {
                break;
            }
            case ADMIN ->{
                Admin admin = adminService.findByLogin(user.getId());
                menuAdmin(admin);
            }
        }

    }

    //menu du marchand et du client
    public  void menuUser(User user){
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
                case "1":makeTransaction( user.getWallet(),"retrait");break;
                case "2":makeTransaction( user.getWallet(),"depot");break;
                case "3":DisplayUtil.display("votre solde est : "+balanceService.getBalance(user.getWallet()));break;
                case "4":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:DisplayUtil.display("choix invalide");break;

            }
        }

    }

    public  void menuAdmin(Admin admin){
        final Scanner scanner = new Scanner(System.in);
        boolean actif = true;
        while (actif) {
            DisplayUtil.display("Bienvenu Admin :"+admin.getFirstName());
            DisplayUtil.display("MENU :");
            DisplayUtil.display("1 | Voir la liste des clients ");
            DisplayUtil.display("2 | Voir la liste des Marchands ");
            DisplayUtil.display("3 | Ajouter un utilisateur ");
            DisplayUtil.display("4 | Promouvoir un utilisateur");
//            DisplayUtil.display("5 | Supprimer un utilisateur");
            DisplayUtil.display("0 | Me deconnecter");

            String choix = scanner.nextLine();
            switch (choix){
                case "1":{

                    DisplayUtil.loading("chargement de la liste");
                    adminService.getCustomers();
                    break;
                }
                case "2":{
                    DisplayUtil.loading("chargement de la liste");
                    adminService.getMerchants();
                    break;

                }
                case "3":{
                    this.registerUser();
                    break;
                }
                case "4":{
                    DisplayUtil.display("Entrez le login de utilisateur que vous voulez rendre admin ");
                    String login =scanner.nextLine();
                    UserAccount userAccount = userAccountService.findByLogin(login);
                    if (userAccount != null){
                        System.out.println("logintrouve : "+userAccount.getLogin());
                    }else {
                        System.out.println("login non trouve");
                    }
                    assert userAccount != null;
                    AdminService.promoteUser(userAccount);
               }
                case "0":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:DisplayUtil.display("choix invalide");


            }
        }

    }

    public static void makeTransaction(Wallet wallet ,String operation){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez le montant :");
        String montantStr = scanner.nextLine();
        try {
            BigDecimal montant = new BigDecimal(montantStr);
            switch (operation){

                case "retrait": {
                    balanceService.retrait(wallet, montant);
                    break;
                }

                case "depot": {
                    balanceService.depot(wallet, montant);
                    break;
                }
                default: DisplayUtil.display("operation invalide");break;
            }

        } catch (NumberFormatException e) {
            System.out.println("Montant invalide. Veuillez entrer un nombre valide.");
        }


    }

}
