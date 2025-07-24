import UseCases.BasicInfoUseCases;
import UseCases.Menu;
import model.BasicInfo;
import utils.DisplayUtil;

import java.util.Scanner;

import static UseCases.Menu.initApp;
import static UseCases.Menu.mainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        initApp();//creation des premiers users pour les tests
        final Scanner scanner = new Scanner(System.in); //declaration du scanner pour les saisies de l'utilisateur
        boolean run=true;
        while (run){    //boucle d'affichage du menu principal
            run= mainMenu(scanner);
        }
    }
}