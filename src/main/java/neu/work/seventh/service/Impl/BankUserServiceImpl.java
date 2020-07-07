package neu.work.seventh.service.Impl;

import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.dao.IBankUserDao;
import neu.work.seventh.dao.Impl.BankUserDaoImpl;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.service.IBankUserService;
import neu.work.seventh.utils.DButil;
import neu.work.seventh.utils.FactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankUserServiceImpl
 * @Package neu.work.seventh.service.Impl
 * @date 2020/5/1
 */
@Service("BankUserServiceImpl")
public class BankUserServiceImpl implements IBankUserService {
    @Qualifier("IBankUserDao")
    @Autowired
    public IBankUserDao iBankUserDao;

    public void setiBankUserDao(IBankUserDao iBankUserDao) {
        this.iBankUserDao = iBankUserDao;
    }

    /**
     *
     * @Title: createUser
     * @Description:
     * @param jsonObject
     * @return
     */

    @Override
    public boolean createUser(JSONObject jsonObject) {
        boolean flag=false;
        //获取BankUserDao的实例
        //BankUserDaoImpl bankUserDaoImpl= (BankUserDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankUserDaoImpl");

        //创建用户
        BankUser bankUser=new BankUser();
        bankUser.setUserTelnum(jsonObject.getString("userTelnum"));
        bankUser.setUserName(jsonObject.getString("userName"));
        bankUser.setUserIdentify(jsonObject.getString("userIdentify"));
        bankUser.setUserAddress(jsonObject.getString("userAddress"));
        //将用户对象插入数据库
        int insertResult1=iBankUserDao.insert(bankUser);
        if(insertResult1==1){
            flag=true;
        }
       return flag;
    }

    @Override
    public boolean deleteUser(JSONObject jsonObject) {
        boolean flag=false;
        int insertResult1=iBankUserDao.deleteByUserIdentify(jsonObject.getString("userIdentify"));
        if(insertResult1==1){
            flag=true;
        }
        return flag;
    }
}
