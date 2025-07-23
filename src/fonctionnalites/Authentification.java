package fonctionnalites;

import model.Admin;
import model.BasicInfo;
import model.Customer;
import model.Marchant;
import model.enumaration.compteType;
import utils.DisplayUtil;
import utils.Stock;

import java.util.List;
import java.util.Scanner;

public  class Authentification {

    public static BasicInfo authentificate(String login, String mdp){

        List<BasicInfo> Users = Stock.getInfoList();
        for(BasicInfo user : Users){
            if (user.getUserAccount().getLogin().equals(login) && user.getUserAccount().getPassword().equals(mdp)){

                return user;
            }
            else return null;
        }
        return null;
    }



    public static  boolean login(){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display("Entrez votre username");
        String username = scanner.nextLine();
        DisplayUtil.display("Entrez votre mot de passe ");
        String password = scanner.nextLine();

        /// // verifier le login
        BasicInfo user = Authentification.authentificate(username,password);
        if ( user != null){
            DisplayUtil.display("Bienvenu"+user.getFirstName()+"!!!!");
            user.setConnected(true);
            return true;

        }else{
            DisplayUtil.display(" Informations de connection invalides ! veuillez saisir des infos correctes !");
             return false;
        }
    }




    public static void register(){
        final Scanner scanner = new Scanner(System.in);

        DisplayUtil.display(" Veuillez entrer votre Nom:");
        String nom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre Prenom:");
        String prenom = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre numero de tel:");
        String telephone = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre email:");
        String email = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre Nom d'utilisateur:");
        String login = scanner.nextLine();
        DisplayUtil.display(" Veuillez entrer votre mot de passe :");
        String mdp = scanner.nextLine();
        DisplayUtil.display("Informations bien enregistrees !!");


        DisplayUtil.display("Choississez un role. Vous etes :");
        DisplayUtil.display("1 | client ");
        DisplayUtil.display("2 | admin  ");
        DisplayUtil.display("3 | marchand  ");
        String choixRole = String.valueOf(scanner.nextInt());


        switch (choixRole){
            case ("1"):{

                DisplayUtil.display(" Quel est votre sexe? (F|M");
                String sexe = scanner.nextLine();

                Customer customer = new Customer(compteType.CUSTOMER,login,mdp,nom,prenom,telephone,email);
            }
            case ("2"):{
                Admin admin = new Admin(compteType.ADMIN,login,mdp,nom,prenom,telephone,email);
            }
            case ("3"):{
                DisplayUtil.display(" Ou se situe votre magasin?");
                String location = scanner.nextLine();
                Marchant marchant = new Marchant(compteType.MERCHANT,login,mdp,nom,prenom,telephone,email,location);
            }

        }
        DisplayUtil.display("Compte cree avec succes!!!");
        DisplayUtil.display("///////////////////////////////////////");
    }
}
