import UseCases.BasicInfoUseCases;
import UseCases.Menu;
import model.BasicInfo;
import utils.DisplayUtil;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        boolean run=true;
        while (run){
            //Accueil
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
                    Menu.showMenu(user);
                    break;
                }
                case ("0") :{
                    DisplayUtil.display("Merci d’avoir utilisé FinTech ADA. À bientôt !");
                    run = false;
                    break;
                }
                default:
                    DisplayUtil.display("Option invalide. Veuillez réessayer.");break;
            }


        }


    }
}