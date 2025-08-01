package services;

import model.Admin;
import model.BasicInfo;
import model.Customer;
import model.User;
import model.enumaration.compteType;
import services.bridge.BalanceService;
import utils.DisplayUtil;
import utils.InputUtils;
import data.Stock;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu_old {

    //creation de l admin et d'un client a l'initialisation de l'app
    public static void initApp(){
        Admin superAdmin = new Admin(compteType.ADMIN,"admin", "admin","Behiblo","fohoundi","0798673112","fohoundibehiblo@gmail.com");
        Customer line = new Customer(compteType.CUSTOMER,"lino","lino0","kouassi","Line","07070707","line@kassi");

        Stock.addInfo(superAdmin);
        Stock.addInfo(line);

    }

    public static boolean mainMenu(Scanner scanner){

        DisplayUtil.display("=== BIENVENU SUR FINTECH ADA ! === ");
        DisplayUtil.display("=== Choisissez une option :=== ");
        DisplayUtil.display("1 | s'inscrire ");
        DisplayUtil.display("2 | s'authentifier  ");
        DisplayUtil.display("0 | Quitter  ");

        String choix = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        DisplayUtil.loading("chargement");
        BasicInfo user = null;
        switch (choix){
            case ("1"):{
                BasicInfoUseCases.register();
            }
            case ("2"):{
                while (user == null){
                    user = BasicInfoUseCases.login();
                }
                showMenu(user);
                break;
            }
            case ("0") :{
                DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                return false;
            }
            default:
                DisplayUtil.display("Option invalide. Veuillez réessayer.");break;
        }
        return true;
    }

    //Afficher le menu Principal
    public static void showMenu(BasicInfo user){
        compteType accountType = user.getUserAccount().getCompteType();

        switch (accountType){
            case MERCHANT, CUSTOMER -> menuUser((User) user);
            case ADMIN -> menuAdmin((Admin) user);
        }

    }

    //menu de l'administrateur
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
                    DisplayUtil.loading("chargement de la liste");
             //       AdminService.getCustomers();
                    break;
                }
                case "2":{
                    DisplayUtil.loading("chargement de la liste");
               //     AdminService.getMerchants();
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


    // Afficher le solde du user ( client et marchand
    public static String showBalance(User user){
        BalanceService balanceService = new BalanceService();

        return InputUtils.format_FCFA(balanceService.getBalance(user.getWallet()));
    }


    // Effectuer une transaction pour un user ( marchant et client
    public static void makeTransaction(User user ,String operation){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez le montant :");
        String montantStr = scanner.nextLine();
        try {
            BigDecimal montant = new BigDecimal(montantStr);
            switch (operation){
                case "retrait": {
                    BalanceService balanceService = new BalanceService();
                    balanceService.retrait(user.getWallet(), montant);
                    break;

                }
                case "depot": {
                   // BalanceService.depot((User) user, montant);
                    DisplayUtil.display("operation effectuee avec succes !");
                    break;
                }
                default: DisplayUtil.display("operation invalide");break;
            }

        } catch (NumberFormatException e) {
            System.out.println("Montant invalide. Veuillez entrer un nombre valide.");
        }


    }
}
