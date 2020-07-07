package neu.work.seventh.dao;

import neu.work.seventh.pojo.BankUser;

import java.util.List;
/* *
  * @ClassName: IBankUserDao
  * @Description: 账户DAO接口
  * @author Fuyang Wang
  * @date 2020/4/29
  * @since JDK 1.8
 */
public interface IBankUserDao {
    public int insert(BankUser bankUser);
    public int deleteByUserIdentify(String userIdentify);
    public int updateByUserIdentify(BankUser bankUser, String userIdentify);
    public List<BankUser> listByUserIdentify(String userIdentify);


}
