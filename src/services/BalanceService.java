package services;

import dao.WalletDao;
import model.User;
import model.Wallet;
import utils.DisplayUtil;

import java.math.BigDecimal;

public class BalanceService {
    private final WalletDao walletDao = new WalletDao();

    public Wallet create(Wallet wallet){
        wallet = walletDao.createWallet(wallet);
        return wallet;
    }

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


    public  BigDecimal getBalance(Wallet wallet){

        wallet = walletDao.readWallet(wallet.getId());
        return wallet.getBalance();
    }


    public  void depot(Wallet wallet,  BigDecimal montant){

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            DisplayUtil.display("Entrez un montant supérieur à 0");
            return;
        }

        wallet = walletDao.readWallet(wallet.getId());
        wallet.setBalance(wallet.getBalance().add(montant));
        wallet = walletDao.updateWallet(wallet);

        if (wallet != null) {
            DisplayUtil.display("Retrait effectué avec succès");
        } else {
            DisplayUtil.display("Échec de l'opération");
        }

    }
}
