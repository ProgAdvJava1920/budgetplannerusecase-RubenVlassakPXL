package be.pxl.student.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NamedQueries({
        @NamedQuery(name = "findById", query = "select a from Account a where a.id = :id"),
        @NamedQuery(name = "findByName", query = "select a from Account a where a.name = :name"),
        @NamedQuery(name = "findByIBAN", query = "select a from Account a where a.IBAN = :iban"),
        @NamedQuery(name = "findAllAccounts", query = "select a from Account a")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IBAN;
    private String name;
    @OneToMany(mappedBy = "account")
    private List<Payment> payments;

    public Account() {
        // JPA only
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Long getId() {
        return id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", payments=[" + payments.stream().map(Payment::toString).collect(Collectors.joining(",")) + "]}";
    }
}
