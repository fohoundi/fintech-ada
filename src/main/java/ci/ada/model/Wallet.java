    package ci.ada.model;

    import java.math.BigDecimal;

    public class Wallet implements Cloneable {

        private  Long id;

        private Long compteurId = 0L;

        private BigDecimal balance;


        public Wallet(){
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

        @Override
        public Wallet clone() {
            try {
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return (Wallet) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
