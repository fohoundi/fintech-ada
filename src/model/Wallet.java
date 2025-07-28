package model;

import java.math.BigDecimal;

public class Wallet {

    private  Long id;

    private Long compteurId = 0L;

    private BigDecimal balance;


    public Wallet() {
        this.id = compteurId;
        this.balance = BigDecimal.valueOf(0f);

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean retirer(BigDecimal montant){
        if (this.getBalance().compareTo(montant) < 0){
            this.setBalance(this.getBalance().subtract(montant));
            return true;
        }
        return false;

    }

    public void deposer(BigDecimal montant){
        this.setBalance(this.getBalance().add(montant));
    }

}
