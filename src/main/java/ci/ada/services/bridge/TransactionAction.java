package ci.ada.services.bridge;


import ci.ada.model.Wallet;

import java.math.BigDecimal;

public interface TransactionAction {
      Wallet create(Wallet wallet);
      void retrait(Wallet wallet, BigDecimal montant);
      BigDecimal getBalance(Wallet wallet);
      void depot(Wallet wallet,  BigDecimal montant);
}
