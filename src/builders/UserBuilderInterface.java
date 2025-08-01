package builders;

import model.User;
import model.UserAccount;
import model.Wallet;
import services.bridge.TransactionAction;

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
