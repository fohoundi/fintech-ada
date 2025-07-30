package services;

import dao.WalletDao;
import model.*;
import model.enumaration.Gender;
import model.enumaration.compteType;
import services.transactions.BalanceService;
import services.transactions.VipBalanceService;
import utils.DisplayUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainService {
    private static final BalanceService balanceService = new BalanceService();
    private static final VipBalanceService vipBalanceService = new VipBalanceService();
    private static final UserAccountService userAccountService = new UserAccountService();
    private static final CustomerService customerService = new CustomerService();
    private static final AdminService adminService = new AdminService();
    private static final MarchandService marchandService = new MarchandService();

    public boolean mainMenu(Scanner scanner){
        DisplayUtil.display("=== BIENVENU SUR FINTECH ADA ! === ");
        DisplayUtil.display("=== Choisissez une option :=== ");
        DisplayUtil.display("1 | s'inscrire ");
        DisplayUtil.display("2 | s'authentifier  ");
        DisplayUtil.display("0 | Quitter  ");

        String choix = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        switch (choix){
            case "1":{
                registerUser();
                break;
            }
            case "2":{
                UserAccount user = this.loginUser();
                if(user != null) {
                    showMenu(user);
                }
                break;
            }
            case "0" :{
                DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                return false;
            }
            default:
                DisplayUtil.display("Option invalide. Veuillez réessayer.");
                break;
        }

        return true;
    }

    public void registerUser() {
        final Scanner scanner = new Scanner(System.in);

       UserAccount userAccount = userAccountService.register(utilCreateUserAccountObject(scanner));

        DisplayUtil.display("Veuillez entrer votre Nom:");
        String nom = scanner.nextLine();
        DisplayUtil.display("Veuillez entrer votre Prénom:");
        String prenom = scanner.nextLine();
        DisplayUtil.display("Veuillez entrer votre numéro de téléphone:");
        String telephone = scanner.nextLine();
        DisplayUtil.display("Veuillez entrer votre email:");
        String email = scanner.nextLine();

        DisplayUtil.display("Choisissez un rôle :");
        DisplayUtil.display("1 | Client");
        DisplayUtil.display("2 | Admin");
        DisplayUtil.display("3 | Marchand");

        String choixRole = scanner.nextLine();

        switch (choixRole) {
            case "1" -> {
                Wallet wallet = new Wallet();
                WalletDao walletDao = new WalletDao();
                wallet = walletDao.createWallet(wallet);

                DisplayUtil.display("Quel est votre sexe ? (WOMEN | MEN)");
                Gender sexe = Gender.valueOf(scanner.nextLine());

                Customer customer = new Customer(userAccount, wallet, nom, prenom, telephone, email, sexe);
                //customer.setTransactionAction(balanceService); // injection du bridge
               // customer.setTransactionAction(vipBalanceService);

                customer.setWallet(wallet);
                customer = (Customer) customerService.register(customer);
            }
            case "2" -> {
                userAccount.setCompteType(compteType.ADMIN);
                userAccount = userAccountService.updateUser(userAccount);
                Admin admin = new Admin(userAccount, nom, prenom, telephone, email);
                adminService.register(admin);
            }
            case "3" -> {
                DisplayUtil.display("Où se situe votre magasin ?");
                String location = scanner.nextLine();

                userAccount.setCompteType(compteType.MERCHANT);
                userAccount = userAccountService.updateUser(userAccount);

                Wallet wallet = new Wallet();
                WalletDao walletDao = new WalletDao();
                wallet = walletDao.createWallet(wallet);

                Marchant marchant = new Marchant(userAccount, wallet, nom, prenom, telephone, email, location);
                marchant.setWallet(wallet);
                marchandService.register(marchant);
            }
            default -> DisplayUtil.display("Choix invalide.");
        }

        DisplayUtil.display(" Compte créé avec succès !");
        DisplayUtil.display("///////////////////////////////////////");
    }


    public UserAccount loginUser() {
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez votre username");
        String username = scanner.nextLine();
        DisplayUtil.display("Entrez votre mot de passe ");
        String password = scanner.nextLine();

        UserAccount user = userAccountService.login(username, password);
        if (user == null) {
            DisplayUtil.display("Informations de connection invalides ! veuillez saisir des infos correctes !");
        }
        return user;
    }

    public void showMenu(UserAccount user){
        compteType accountType = user.getCompteType();

        switch (accountType){
            case CUSTOMER -> {
                Customer customer = customerService.findByLogin(user.getId());
                if (customer != null) {
                    //customer.setTransactionAction(vipBalanceService);
                    customer.setTransactionAction(balanceService);
                    menuUser(customer);
                } else {
                    DisplayUtil.display("Client non trouvé.");
                }
            }
            case MERCHANT -> {

                Marchant marchant = marchandService.findByLogin(user.getId());
                if (marchant != null){
                    marchant.setTransactionAction(balanceService);
                    menuUser(marchant);
                }else {
                    DisplayUtil.display("Marchand non trouve");
                }
            }
            case ADMIN -> {
                Admin admin = adminService.findByLogin(user.getId());
                if (admin != null) {
                    menuAdmin(admin);
                } else {
                    DisplayUtil.display("Admin non trouvé.");
                }
            }
        }
    }

    public void menuUser(User user){
        final Scanner scanner = new Scanner(System.in);
        boolean actif = true;

        while (actif) {
            DisplayUtil.display("MENU :");
            DisplayUtil.display("1 | Retirer de l'argent ");
            DisplayUtil.display("2 | deposer de l'argent ");
            DisplayUtil.display("3 | Consulter son solde ");
            DisplayUtil.display("4 | Me deconnecter");

            String choix = scanner.nextLine();
            switch (choix){
                case "1": makeTransactionBridge(user, "retrait"); break;
                case "2": makeTransactionBridge(user, "depot"); break;
                case "3": DisplayUtil.display("votre solde est : " + balanceService.getBalance(user.getWallet())+"F CFA"); break;
                case "4":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:
                    DisplayUtil.display("choix invalide");
                    break;
            }
        }
    }

    public void menuAdmin(Admin admin){
        final Scanner scanner = new Scanner(System.in);
        boolean actif = true;
        while (actif) {
            DisplayUtil.display("Bienvenu Admin :"+admin.getFirstName());
            DisplayUtil.display("MENU :");
            DisplayUtil.display("1 | Voir la liste des clients ");
            DisplayUtil.display("2 | Voir la liste des Marchands ");
            DisplayUtil.display("3 | Ajouter un utilisateur ");
            DisplayUtil.display("4 | Promouvoir un utilisateur");
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
                    String login = scanner.nextLine();
                    UserAccount userAccount = userAccountService.findByLogin(login);
                    if (userAccount != null){
                        System.out.println("login trouve : "+userAccount.getLogin());
                        AdminService.promoteUser(userAccount);
                    } else {
                        System.out.println("login non trouve");
                    }
                    break;
                }
                case "0":{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    actif = false;
                    break;
                }
                default:
                    DisplayUtil.display("choix invalide");
            }
        }
    }

    /****Utilitaires ****/

    private static UserAccount utilCreateUserAccountObject(Scanner scanner){
        DisplayUtil.display("Veuillez entrer votre Nom d'utilisateur:");
        String login = scanner.nextLine();
        DisplayUtil.display("Veuillez entrer votre mot de passe:");
        String mdp = scanner.nextLine();

        UserAccount userAccount = new UserAccount();
        userAccount.setLogin(login);
        userAccount.setPassword(mdp);

        return userAccount;

    }



    public static void makeTransactionBridge(User user, String operation) {
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez le montant :");
        String montantStr = scanner.nextLine();

        try {
            BigDecimal montant = new BigDecimal(montantStr);

            switch (operation.toLowerCase()) {
                case "retrait":
                    user.retrait(user.getWallet(), montant);
                    break;

                case "depot":
                    user.depot(user.getWallet(), montant);
                    break;

                default:
                    DisplayUtil.display("Opération invalide");
            }

        } catch (NumberFormatException e) {
            DisplayUtil.display("Montant invalide. Veuillez entrer un nombre valide.");
        }
    }

}
