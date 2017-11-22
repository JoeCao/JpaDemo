package club.newtech.jpademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.FetchType.EAGER;

//@Builder
@Accessors(fluent = true, chain = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_LEDGER")
public class Ledger implements Serializable {
    /**
     * 用户标识,直接使用千米ID、店铺ID、UserID
     */
    @Id
    String id;

    @Column(length = 64, nullable = true)
    String userId;

    @OneToMany(mappedBy = "ledgerId", fetch = EAGER, cascade = CascadeType.ALL)
    @MapKeyColumn(name = "accountType")
    Map<String, Account> accounts = new HashMap<>();

    /**
     * 用户状态
     */
    private int acctState = 1;
    /**
     * 锁定状态
     */
    private int lockedState = 0;

    public void addAccount(Account account) {
        accounts.put(account.getAccountType(), account);
    }

    public void initialize(String type, long amount) {
        if (type == null) {
            type = Type.SHOP_FUNDS;
        }
        if (accounts.get(type) == null) {
            Account account = new Account();
            account.setAccountType(type);
            account.setBalance(amount);
            account.setLedgerId(this.id());
            addAccount(account);
        } else {
            Account account = accounts.get(type);
            account.setBalance(amount);
        }
    }

    public long getBalance(String type) {
        Account account = accounts.get(type);
        if (account != null) {
            return account.getBalance();
        } else {
            return 0;
        }
    }

    public Account getAccount(String type) {
        Account account = accounts.get(type);
        if (account != null) {
            return account;
        } else {
            return null;
        }
    }

}