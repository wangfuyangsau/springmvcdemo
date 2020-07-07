package neu.work.seventh.dao.Impl;

import neu.work.seventh.dao.IBankBillDao;
import neu.work.seventh.pojo.BankBill;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankBillDaoImpl
 * @Package neu.work.seventh.dao.Impl
 * @date 2020/5/1
 */
public class BankBillDaoImpl implements IBankBillDao {

    @Override
    public int insert(BankBill bankBill) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="insert into bill(accountCardId,billAffairType,billTradeBalance,billCurrencyType) values(?,?,?,?)";
        Object[] params=new Object[]{bankBill.getAccountCardId(),bankBill.getBillAffairType(),
                bankBill.getBillTradeBalance(),bankBill.getBillCurrencyType()
        };

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        return count;

    }

    @Override
    public int deleteByUserIdentify(int billId) {
        int count;
        Connection conn=DButil.getConnection();
        String sql="delete from bill where billId=?";
        Object[]params=new Object[]{billId};
        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }

    @Override
    public int updateByUserIdentify(BankBill bankBill, int billId) {
        int count;
        Connection conn=DButil.getConnection();

        String sql="update bill set accountCardId=?,billAffairType=?,billTradeBalance=?,billCurrencyType=? where billId=?";
        Object[] params=new Object[]{bankBill.getAccountCardId(),bankBill.getBillAffairType(),
                bankBill.getBillTradeBalance(),bankBill.getBillCurrencyType(),billId
        };

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        count=DButil.executeUpdate((PreparedStatement)ps);
        DButil.releaseConnection(null, ps, null);
        return count;
    }

    @Override
    public List<BankBill> listByUserIdentify(int billId) {
        List<BankBill>  bankBillList=new ArrayList<BankBill>();
        Connection conn=DButil.getConnection();

        String sql="select * from bill where billId=?";

        Object[] params=new Object[]{billId};

        PreparedStatement ps=DButil.createPreparedstatement(conn,sql,params);
        ResultSet rs =DButil.executeQuery((PreparedStatement)ps);
        while (true){
            try {
                if (!rs.next()) break;
                BankBill bu=new BankBill();
                bu.setBillId(rs.getInt(1));
                bu.setAccountCardId(rs.getString(2));
                bu.setBillAffairType(rs.getString(3));
                bu.setBillTradeTime(rs.getDate(4));
                bu.setBillTradeBalance(rs.getBigDecimal(5));
                bu.setBillCurrencyType(rs.getString(6));
                bankBillList.add(bu);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        DButil.releaseConnection(null,ps,rs);
        return bankBillList;
    }


}
