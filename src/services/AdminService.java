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

    private final  static CustomerService customerService = new CustomerService();
    private final static AdminDao adminDao = new AdminDao();

    public Admin register(Admin admin){
        admin = adminDao.createAdmin(admin);
        return admin;
    }

    public Admin findByLogin(Long id){
        return adminDao.findadminByLogin(id);
    }

    public  void getCustomers(){
        List<Customer> customers = customerService.findAll();

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

    public  void getMerchants(){
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

    public  void getAdmins(){

        List<Admin> admins = adminDao.findAll();
        if (admins.isEmpty()){
            DisplayUtil.display(" Pas d'admins enregistres pour le moment");
        }else {
            DisplayUtil.display("============================================================");
            DisplayUtil.display("| NOM | PRENOMS | EMAIL | NUMERO DE TELEPHONE | PRIVILEGES | ");
            DisplayUtil.display("============================================================");
            for (Admin admin : admins){
                System.out.printf("= %s | %s | %s | %s | %s =%n",
                        admin.getFirstName(),
                        admin.getLastName(),
                        admin.getEmail(),
                        admin.getPhoneNumber()
                        // A terminer

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
    public static void promoteUser (UserAccount userAccount){
        //recuperer login
        compteType compteType = userAccount.getCompteType();
        System.out.println(userAccount.getLogin());

        User user = new User();
        //regqrder compte type et supprimer user de base
        switch (compteType){
            case CUSTOMER -> {
                Customer customer = customerService.findByLogin(userAccount.getId());
                System.out.println("id du client : "+customer.getId());
                customerService.delete(customer.getId());
                user = (User) customer;


                break;
            }
            default -> {
                return;
            }

        }

        //
        // ajouter admin
        Admin admin = new Admin(userAccount, user.getLastName(), user.getFirstName(), user.getPhoneNumber(), user.getEmail());
        admin.setPrivileges(List.of("read","write"));
        adminDao.createAdmin(admin);
        System.out.println("Promotion accordee !");


    }

    //suprimer un user
    public static void deleteUser(User user){
        Stock.deleteInfo((BasicInfo) user);
    }
}
