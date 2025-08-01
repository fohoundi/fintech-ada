package ci.ada.builders;

import ci.ada.model.User;
import ci.ada.model.UserAccount;
import ci.ada.model.Wallet;
import ci.ada.services.bridge.TransactionAction;

public interface UserBuilderInterface <T extends User> {
    UserBuilderInterface setFirstname(String firstName );
    UserBuilderInterface setLastName(String lastname);
    UserBuilderInterface setEmail(String email);
    UserBuilderInterface setPhoneNumber(String phoneNumber);
    //UserBuilderInterface setMatricule(String matricule);
    UserBuilderInterface setUserAccount(UserAccount userAccount);
    UserBuilderInterface setTransactionAction(TransactionAction transactionAction);
    //UserBuilderInterface setGender(Gender gender);
    UserBuilderInterface setWallet(Wallet wallet);

    T build();
}
