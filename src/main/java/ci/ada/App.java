package ci.ada;

import ci.ada.services.MainService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        final Scanner scanner = new Scanner(System.in); //declaration du scanner pour les saisies de l'utilisateur
        MainService mainService = new MainService();
        boolean run = true;

        while (run){    //boucle d'affichage du menu principal
            run= mainService.mainMenu(scanner);
        }
    }
}
