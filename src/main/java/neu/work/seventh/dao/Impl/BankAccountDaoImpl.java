package neu.work.seventh.dao.Impl;

import neu.work.seventh.dao.IBankAccountDao;
import neu.work.seventh.pojo.BankAccount;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.utils.DButil;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankAccountImpl
 * @Package neu.work.seventh.dao.Impl
 * @date 2020/4/25
 */
public class BankAccountDaoImpl implements IBankAccountDao {

    /**
     *
     * @Title: insert
     * @Description: 插入账户
     * @param bankAccount
     * @return  int
     */

    @Override
    public int insert(BankAccount bankAccount) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="INSERT INTO `account` (`accountCardId`, `accountBalance`, `userIdentify`, " +
                "`accountPassword`, `accountType`, `accountLimit`, `accountLimitDate`,`accountStatus`," +
                "`accountCreateDate`) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] params=new Object[]{bankAccount.getAccountCardId(),bankAccount.getAccountBalance(),bankAccount.getUserIdentify()
                ,bankAccount.getAccountPassword(),bankAccount.getAccountType(),bankAccount.getAccountLimit(),
                bankAccount.getAccountLimitDate(),bankAccount.getAccountStatus(),bankAccount.getAccountCreateDate()
        };

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement) ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }
/**
 *
 * @Title: deleteByAccountCardId
 * @Description: 通过账号删除账户
 * @param accountCardId
 * @return int
 */

    @Override
    public int deleteByAccountCardId(String accountCardId) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="delete from account where `accountCardId`=?";
        Object[]params=new Object[]{accountCardId};
        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement) ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }
/**
 *
 * @Title: updateByAccountCardId
 * @Description: 根据账号更新账户
 * @param bankAccount, accountCardId]
 * @return int
 */

    @Override
    public int updateByAccountCardId(BankAccount bankAccount, String accountCardId) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="update `account` set `accountCardId`=?, `accountBalance`=?, `userIdentify`=?, " +
                "`accountPassword`=?, `accountType`=?, `accountLimit`=?, `accountLimitDate`=?,`accountStatus`=?," +
                "`accountCreateDate`=? where `accountCardId`=?";
        Object[] params=new Object[]{bankAccount.getAccountCardId(),bankAccount.getAccountBalance(),bankAccount.getUserIdentify()
                ,bankAccount.getAccountPassword(),bankAccount.getAccountType(),bankAccount.getAccountLimit(),
                bankAccount.getAccountLimitDate(),bankAccount.getAccountStatus(),bankAccount.getAccountCreateDate(),bankAccount.getAccountCardId()
        };

       PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement) ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }
/**
 *
 * @Title: listByAccountCardId
 * @Description: 根据账号获取账户
 * @param accountCardId
 * @return list
 */

    @Override
    public List<BankAccount> listByAccountCardId(String accountCardId) {
        List<BankAccount>  bankUserList=new ArrayList<BankAccount>();
        String sql="select * from account where `accountCardId`=?";

        Object[] params=new Object[]{accountCardId};
        Connection conn=DButil.getConnection();
        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
       ResultSet rs =DButil.executeQuery((PreparedStatement) ps);
        while (true){
            try {
                if (!rs.next()) break;
                BankAccount bu=new BankAccount();
                bu.setAccountCardId(rs.getString(1));
                bu.setAccountBalance(rs.getBigDecimal(2));
                bu.setUserIdentify(rs.getString(3));
                bu.setAccountPassword(rs.getString(4));
                bu.setAccountType(rs.getString(5));
                bu.setAccountLimit(rs.getBigDecimal(6));
                bu.setAccountLimitDate(rs.getInt(7));
                bu.setAccountStatus(rs.getString(8));
                bu.setAccountCreateDate(rs.getDate(9));
                bankUserList.add(bu);
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        DButil.releaseConnection(null, ps, rs);
        return bankUserList;
    }
}
