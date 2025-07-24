package UseCases;

import model.Admin;
import model.BasicInfo;
import model.User;
import model.enumaration.compteType;
import utils.DisplayUtil;
import utils.InputUtils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

    public static void showMenu(BasicInfo user){
        compteType accountType = user.getUserAccount().getCompteType();

        switch (accountType){
            case MERCHANT, CUSTOMER -> menuUser((User) user);
            case ADMIN -> menuAdmin((Admin) user);
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
            DisplayUtil.display("4 | Promouvoir un utilisateur");
            DisplayUtil.display("5 | Supprimer un utilisateur");
            DisplayUtil.display("6 | Me deconnecter");

            String choix = scanner.nextLine();
            switch (choix){
                case "1":{
                    DisplayUtil.loading("chargement de la liste");
                    AdminUseCases.getCustomers();
                    break;
                }
                case "2":{
                    DisplayUtil.loading("chargement de la liste");
                    AdminUseCases.getMerchants();
                    break;

                }
                case "3":{
                    BasicInfoUseCases.register();
                    break;
                }
                case "6":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:DisplayUtil.display("choix invalide");


            }
        }

    }

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

    public static String showBalance(User user){
        return InputUtils.format_FCFA(BalanceUseCases.getBalance(user));
    }

    public static void makeTransaction(User user ,String operation){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez le montant :");
        String montantStr = scanner.nextLine();
        try {
            BigDecimal montant = new BigDecimal(montantStr);
            switch (operation){
                case "retrait": {
                    BalanceUseCases.retrait((User) user, montant);
                    break;

                }
                case "depot": {
                    BalanceUseCases.depot((User) user, montant);
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
