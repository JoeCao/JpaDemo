package club.newtech.jpademo.domain;

import java.io.Serializable;

public class AccountId implements Serializable {
    private String ledgerId;
    private String accountType;

    public AccountId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        if (!ledgerId.equals(accountId.ledgerId)) return false;
        return accountType.equals(accountId.accountType);
    }

    @Override
    public int hashCode() {
        int result = ledgerId.hashCode();
        result = 31 * result + accountType.hashCode();
        return result;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
