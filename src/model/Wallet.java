package model;

import java.math.BigDecimal;

public class Wallet {

    private final Long id;

    private Long compteurId = 0L;

    private BigDecimal balance;

    private User user;

    public Wallet( User user) {
        this.id = compteurId;
        this.balance = BigDecimal.valueOf(0f);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void retirer(BigDecimal montant){
        this.setBalance(this.getBalance().subtract(montant));
    }

    public void deposer(BigDecimal montant){
        this.setBalance(this.getBalance().add(montant));
    }

}
