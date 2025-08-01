package ci.ada.services.bridge;


import ci.ada.dao.WalletDao;
import ci.ada.model.Wallet;
import ci.ada.utils.DisplayUtil;

import java.math.BigDecimal;

public class BalanceService  implements TransactionAction {
    private final WalletDao walletDao = new WalletDao();

    @Override
    public Wallet create(Wallet wallet){
        wallet = walletDao.createWallet(wallet);
        return wallet;
    }

    @Override
    public  void retrait(Wallet wallet, BigDecimal montant){


        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            DisplayUtil.display("Entrez un montant supérieur à 0");
            return;
        }

        wallet = walletDao.readWallet(wallet.getId());

        if (wallet.getBalance().compareTo(montant) < 0) {
            DisplayUtil.display("Solde insuffisant");
            return;
        }

        wallet.setBalance(wallet.getBalance().subtract(montant));
        wallet = walletDao.updateWallet(wallet);

        if (wallet != null) {
            DisplayUtil.display("Retrait effectué avec succès");
        } else {
            DisplayUtil.display("Échec de l'opération");
        }

    }

    @Override
    public  BigDecimal getBalance(Wallet wallet){

        wallet = walletDao.readWallet(wallet.getId());
        return wallet.getBalance();
    }

    @Override
    public  void depot(Wallet wallet,  BigDecimal montant){

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            DisplayUtil.display("Entrez un montant supérieur à 0");
            return;
        }

        wallet = walletDao.readWallet(wallet.getId());
        wallet.setBalance(wallet.getBalance().add(montant));
        wallet = walletDao.updateWallet(wallet);

        if (wallet != null) {
            DisplayUtil.display("Depot effectué avec succès");
        } else {
            DisplayUtil.display("Échec de l'opération");
        }

    }
}
