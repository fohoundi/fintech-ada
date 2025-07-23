package utils;
import fonctionnalites.Authentification;
import model.Admin;
import model.BasicInfo;
import model.Customer;
import model.Marchant;
import model.enumaration.compteType;
import utils.DisplayUtil;
import utils.Stock;

import java.util.Scanner;

public class Old {



    //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

        public static void main(String[] args) {

            final Scanner scanner = new Scanner(System.in);

            //accueil
            DisplayUtil.display("=== BIENVENU SUR FINTECH ADA ! === ");
            DisplayUtil.display("=== Choisissez une option :=== ");


            boolean showMenu = true;
            while (showMenu){
                DisplayUtil.display("1 | s'inscrire ");
                DisplayUtil.display("2 | s'authentifier  ");

                String choix = String.valueOf(scanner.nextInt());

                switch (choix){
                    case ("1"):{

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

                        Boolean authenticate = false;
                        while (authenticate){
                            boolean isConnected = Authentification.login();


                        }





                    }




                }

            }

            // s inscrire ou s authentifier (menu



            //choisir son role

            // message de bienvenu avec le nom

            // menu selon le role
            // retrait
            // depot

            //
        }

}
