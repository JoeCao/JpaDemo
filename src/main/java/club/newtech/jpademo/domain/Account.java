package club.newtech.jpademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "T_ACCOUNT")
@IdClass(AccountId.class)
public class Account implements Serializable {

    @Id
    @Column(length = 64, nullable = false)
    String ledgerId;
    @Id
    @Column(length = 64, nullable = false)
    String accountType;
    long balance = 0;


    public boolean enoughBalance(long amount) {

        if (balance >= amount) {
            return true;
        } else {
            return false;
        }

    }

    public long expense(long amount) {
        if (balance > amount) {
            balance = balance - amount;
            return balance;
        } else {
            return 0;
        }
    }

    public long income(long amount) {
        balance = balance + amount;
        return balance;
    }
}
