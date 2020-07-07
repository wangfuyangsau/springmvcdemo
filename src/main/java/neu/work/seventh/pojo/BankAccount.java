package neu.work.seventh.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankAccount
 * @Package neu.work.seventh.pojo
 * @date 2020/4/25
 */
public class BankAccount implements Serializable {
    private String accountCardId;
    private BigDecimal accountBalance;
    private String userIdentify;
    private String accountPassword;
    private String accountType;
    private BigDecimal accountLimit;
    private int accountLimitDate;
    private String accountStatus;
    private Date accountCreateDate;

    public String getAccountCardId() {
        return accountCardId;
    }

    public void setAccountCardId(String accountCardId) {
        this.accountCardId = accountCardId;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getUserIdentify() {
        return userIdentify;
    }

    public void setUserIdentify(String userIdentify) {
        this.userIdentify = userIdentify;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
    }

    public int getAccountLimitDate() {
        return accountLimitDate;
    }

    public void setAccountLimitDate(int accountLimitDate) {
        this.accountLimitDate = accountLimitDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAccountCreateDate() {
        return accountCreateDate;
    }

    public void setAccountCreateDate(Date accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountCardId='" + accountCardId + '\'' +
                ", accountBalance=" + accountBalance +
                ", userIdentify='" + userIdentify + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountLimit=" + accountLimit +
                ", accountLimitDate=" + accountLimitDate +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountCreateDate=" + accountCreateDate +
                '}';
    }
}
