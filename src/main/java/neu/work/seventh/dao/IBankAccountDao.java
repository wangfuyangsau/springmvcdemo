package neu.work.seventh.dao;

import neu.work.seventh.pojo.BankAccount;
import neu.work.seventh.pojo.BankUser;

import java.sql.Connection;
import java.util.List;
/* *
  * @ClassName: IBankAccountDao
  * @Description: 账户DAO接口
  * @author Fuyang Wang
  * @date 2020/4/29
  * @since JDK 1.8
 */
public interface IBankAccountDao {
    public int insert(BankAccount bankAccount);
    public int deleteByAccountCardId(String accountCardId);
    public int updateByAccountCardId(BankAccount bankAccount, String accountCardId);
    public List<BankAccount> listByAccountCardId(String accountCardId);

}
