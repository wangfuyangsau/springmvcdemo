package neu.work.seventh.dao;

import neu.work.seventh.pojo.BankBill;
import neu.work.seventh.pojo.BankUser;

import java.util.List;

public interface IBankBillDao {
    public int insert(BankBill bankBill);
    public int deleteByUserIdentify(int billId);
    public int updateByUserIdentify(BankBill bankBill, int billId);
    public List<BankBill> listByUserIdentify( int billId);

}
