package neu.work.seventh.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankBill
 * @Package neu.work.seventh.pojo
 * @date 2020/5/1
 */
public class BankBill implements Serializable {
    private int billId;
    private String accountCardId;
    private String billAffairType;
    private Date billTradeTime;
    private BigDecimal billTradeBalance;
    private String billCurrencyType;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getAccountCardId() {
        return accountCardId;
    }

    public void setAccountCardId(String accountCardId) {
        this.accountCardId = accountCardId;
    }

    public String getBillAffairType() {
        return billAffairType;
    }

    public void setBillAffairType(String billAffairType) {
        this.billAffairType = billAffairType;
    }

    public Date getBillTradeTime() {
        return billTradeTime;
    }

    public void setBillTradeTime(Date billTradeTime) {
        this.billTradeTime = billTradeTime;
    }

    public BigDecimal getBillTradeBalance() {
        return billTradeBalance;
    }

    public void setBillTradeBalance(BigDecimal billTradeBalance) {
        this.billTradeBalance = billTradeBalance;
    }

    public String getBillCurrencyType() {
        return billCurrencyType;
    }

    public void setBillCurrencyType(String billCurrencyType) {
        this.billCurrencyType = billCurrencyType;
    }

    @Override
    public String toString() {
        return "BankBill{" +
                "billId=" + billId +
                ", accountCardId='" + accountCardId + '\'' +
                ", billAffairType='" + billAffairType + '\'' +
                ", billTradeTime=" + billTradeTime +
                ", billTradeBalance=" + billTradeBalance +
                ", billCurrencyType='" + billCurrencyType + '\'' +
                '}';
    }
}
