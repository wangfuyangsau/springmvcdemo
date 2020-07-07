package neu.work.seventh.dao.Impl;

import neu.work.seventh.dao.IBankUserDao;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.utils.DButil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankUserDaoImpl
 * @Package neu.work.seventh.dao.Impl
 * @date 2020/4/25
 */
@Repository("BankUserDaoImpl")
public class BankUserDaoImpl implements IBankUserDao {
    /**
     *
     * @Title: insert
     * @Description: 插入用户
     * @param bankUser
     * @return 影响条目数
     */

    @Override
    public int insert(BankUser bankUser) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="insert into user(userName,userIdentify,userTelnum,userAddress) values(?,?,?,?)";
        Object[] params=new Object[]{bankUser.getUserName(),bankUser.getUserIdentify(),
                bankUser.getUserTelnum(),bankUser.getUserAddress()
        };

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        System.out.println("cahrudaouser");
        return count;
    }
/**
 *
 * @Title: deleteByUserIdentify
 * @Description: 根据身份证删除用户
 * @param userIdentify
 * @return int
 */

    @Override
    public int deleteByUserIdentify(String userIdentify) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="delete from user where userIdentify=?";
        Object[]params=new Object[]{userIdentify};
        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }
/**
 *
 * @Title: updateByUserIdentify
 * @Description: 更新用户
 * @param bankUser, userIdentify]
 * @return 影响条目数
 */

    @Override
    public int updateByUserIdentify(BankUser bankUser,String userIdentify) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="update user set userName=?,userIdentify=?,userTelnum=?,userAddress=? where userIdentify=?";
        Object[] params=new Object[]{bankUser.getUserName(),bankUser.getUserIdentify(),
                bankUser.getUserTelnum(),bankUser.getUserAddress(),userIdentify
        };
        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }


/**
 *
 * @Title: listByUserIdentify
 * @Description:  查询用户
 * @param userIdentify
 * @return 用户list
 */

    @Override
    public List<BankUser> listByUserIdentify(String userIdentify) {
        List<BankUser>  bankUserList=new ArrayList<BankUser>();
        Connection conn=DButil.getConnection();
        String sql="select * from user where userIdentify=?";

        Object[] params=new Object[]{userIdentify};

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        ResultSet rs =DButil.executeQuery((PreparedStatement)ps);
        while (true){
            try {
                if (!rs.next()) break;
                BankUser bu=new BankUser();
                bu.setUserName(rs.getString(1));
                bu.setUserIdentify(rs.getString(2));
                bu.setUserTelnum(rs.getString(3));
                bu.setUserAddress(rs.getString(4));
                bankUserList.add(bu);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        DButil.releaseConnection(null,ps,rs);
        return bankUserList;
    }
}
