package neu.work.seventh.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankUser
 * @Package neu.work.seventh.pojo
 * @date 2020/4/25
 */
public class BankUser implements Serializable {
    private String userName;
    private String userIdentify;
    private String userTelnum;
    private String userAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentify() {
        return userIdentify;
    }

    public void setUserIdentify(String userIdentify) {
        this.userIdentify = userIdentify;
    }

    public String getUserTelnum() {
        return userTelnum;
    }

    public void setUserTelnum(String userTelnum) {
        this.userTelnum = userTelnum;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "BankUser{" +
                "userName='" + userName + '\'' +
                ", userIdentify='" + userIdentify + '\'' +
                ", userTelnum='" + userTelnum + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
