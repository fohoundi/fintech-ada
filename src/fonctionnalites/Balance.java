package fonctionnalites;

import model.User;

import java.math.BigDecimal;

public class Balance {

    public static void retrait(User user, BigDecimal montant){
        user.getWallet().retirer(montant);
    }

    public static void depot(User user, BigDecimal montant){
        user.getWallet().deposer(montant);
    }
}
