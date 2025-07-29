package services;

import model.User;
import utils.DisplayUtil;

import java.math.BigDecimal;

public class BalanceUseCases {

    public static void retrait(User user, BigDecimal montant){

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            DisplayUtil.display(" Entrez un montant supérieur à 0 ");
        }
        if (user.getWallet().retirer(montant)) {
            DisplayUtil.display("retrait effectué avec succes ");
        } else {
            DisplayUtil.display("solde insuffisant");
        }
    }

    public static BigDecimal getBalance(User user){
        return user.getWallet().getBalance();
    }
    public static void depot(User user, BigDecimal montant){
        user.getWallet().deposer(montant);
    }
}
