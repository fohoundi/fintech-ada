import fonctionnalites.Authentification;
import model.Admin;
import model.BasicInfo;
import model.Customer;
import model.Marchant;
import model.enumaration.compteType;
import utils.DisplayUtil;
import utils.Stock;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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

                    Authentification.register();

                    Boolean authenticated = false;
                    while (authenticated){
                        boolean isConnected = Authentification.login();
                    }

                }
                case ("2"):{
                    Boolean authenticated = false;
                    while (authenticated){
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