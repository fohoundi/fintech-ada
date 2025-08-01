package ci.ada.services.bridge;


import ci.ada.model.Wallet;

import java.math.BigDecimal;

public class VipBalanceService implements TransactionAction{
    @Override
    public Wallet create(Wallet wallet) {
        return null;
    }

    @Override
    public void retrait(Wallet wallet, BigDecimal montant) {
        System.out.println("Retrait Vip");
    }

    @Override
    public BigDecimal getBalance(Wallet wallet) {
        return null;
    }

    @Override
    public void depot(Wallet wallet, BigDecimal montant) {
        System.out.println("Depot Vip");
    }
}
