package neu.work.seventh.service.Impl;

import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.dao.Impl.BankBillDaoImpl;
import neu.work.seventh.dao.Impl.BankUserDaoImpl;
import neu.work.seventh.pojo.BankBill;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.service.IBankBillService;
import neu.work.seventh.utils.DButil;
import neu.work.seventh.utils.FactoryUtil;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankBillServiceImpl
 * @Package neu.work.seventh.service.Impl
 * @date 2020/5/1
 */
public class BankBillServiceImpl implements IBankBillService {
    @Override
    public boolean insertBillTransfer(JSONObject jsonObject) {
        boolean flag=false;
        //获取BankBillDao的实例
        BankBillDaoImpl bankBillDaoImpl= (BankBillDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankBillDaoImpl");

        //创建bill
        BankBill bankBill=new BankBill();
        bankBill.setAccountCardId(jsonObject.getString("accountCardId"));
        bankBill.setBillAffairType(jsonObject.getString("billAffairType"));
        bankBill.setBillTradeBalance(jsonObject.getBigDecimal("billTradeBalance"));
        bankBill.setBillCurrencyType(jsonObject.getString("billCurrencyType"));
        //将对象插入数据库
        int insertResult1=bankBillDaoImpl.insert(bankBill);
        if(insertResult1==1){
            flag=true;
        }
        //释放数据库连接
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);
        return flag;

    }
}
