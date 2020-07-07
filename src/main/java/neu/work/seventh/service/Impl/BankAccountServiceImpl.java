package neu.work.seventh.service.Impl;

import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.dao.Impl.BankAccountDaoImpl;
import neu.work.seventh.pojo.BankAccount;
import neu.work.seventh.service.IBankAccountService;
import neu.work.seventh.utils.DButil;
import neu.work.seventh.utils.FactoryUtil;
import neu.work.seventh.utils.QueryRateUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankAccountServiceImpl
 * @Package neu.work.seventh.service.Impl
 * @date 2020/4/30
 */
public class BankAccountServiceImpl implements IBankAccountService {
    /**
     *
     * @Title: loginCheck
     * @Description: 登录验证
     * @param jsonObject
     * @return boolean
     */

    @Override
    public boolean loginCheck(JSONObject jsonObject) {
        boolean flag=false;
        String inputAccountCardId=jsonObject.getString("accountCardId");
        String inputAccountPassword=jsonObject.getString("accountPassword");
        //获取账户DAO
        BankAccountDaoImpl iBankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");

        if(iBankAccountDao.listByAccountCardId(inputAccountCardId).size()==1){
            BankAccount bankAccount;
            bankAccount=iBankAccountDao.listByAccountCardId(inputAccountCardId).get(0);
            //验证密码
            if(bankAccount.getAccountPassword().equals(inputAccountPassword)){
                flag= true;
            }else{
               flag= false;
            }
        }else {
            flag= false;
        }
        //关连接
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);
        return flag;
    }
/**
 *
 * @Title: queryBanlance
 * @Description: 查询余额
 * @param jsonObject
 * @return
 */

    @Override
    public BigDecimal queryBanlance(JSONObject jsonObject) {
        //获取BankAccountDao的实例
        BankAccountDaoImpl iBankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");

        //查询用户余额
        BankAccount bankAccount;
        bankAccount=iBankAccountDao.listByAccountCardId(jsonObject.getString("accountCardId")).get(0);
        BigDecimal balance=bankAccount.getAccountBalance();
        //关连接
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);

        return balance ;
    }
/**
 *
 * @Title: saveMoney
 * @Description: 存钱
 * @param jsonObject
 * @return
 */

    @Override
    public boolean saveMoney(JSONObject jsonObject) {
        boolean flag=false;
        //获取BankAccountDao的实例
        BankAccountDaoImpl iBankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");
        //读取账号和存储金额和类型
        String accoutId=jsonObject.getString("accountCardId");
        String saveNumber=jsonObject.getString("saveMoneyNumber");
        String saveType=jsonObject.getString("saveMoneyType");
        //将货币转化为人民币
        BigDecimal rmbMoney= QueryRateUtil.getTransformMoney(saveType, "CNY", saveNumber);
        //获取账户修改余额
        BankAccount bankAccount;
        bankAccount=iBankAccountDao.listByAccountCardId(accoutId).get(0);
        bankAccount.setAccountBalance(bankAccount.getAccountBalance().add(rmbMoney));
        //保存账户
        int count=iBankAccountDao.updateByAccountCardId(bankAccount, accoutId);
        if(count==1){
            flag= true;
        }
        else{
            flag= false;
        }
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);

        return flag;
    }
/**
 *
 * @Title: changePassword
 * @Description: 修改密码
 * @param jsonObject
 * @return
 */

    @Override
    public boolean changePassword(JSONObject jsonObject) {

        boolean flag=false;
        //获取BankAccountDao的实例
        BankAccountDaoImpl iBankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");
        //读取账号和新密码
        String accoutId=jsonObject.getString("accountCardId");
        String newPwd=jsonObject.getString("newPwd");
        //获取账户修改余额
        BankAccount bankAccount;
        bankAccount=iBankAccountDao.listByAccountCardId(accoutId).get(0);
        bankAccount.setAccountPassword(newPwd);
        //保存账户
        int count=iBankAccountDao.updateByAccountCardId(bankAccount, accoutId);
        if(count==1){
            flag= true;
        }
        else{
            flag=false;
        }
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);

        return flag;
    }
/**
 *
 * @Title: getMoney
 * @Description: 取钱
 * @param jsonObject
 * @return
 */

    @Override
    public boolean getMoney(JSONObject jsonObject) {
        boolean flag=false;
        int count=0;
        //获取BankAccountDao的实例
        BankAccountDaoImpl iBankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");

        //读取账号和取钱金额
        String accountId=jsonObject.getString("accountCardId");
        String getNumber=jsonObject.getString("getMoneyNumber");
        //获取账户修改余额
        BankAccount bankAccount;
        bankAccount=iBankAccountDao.listByAccountCardId(accountId).get(0);
        //判断余额
        if(bankAccount.getAccountBalance().compareTo(new BigDecimal(getNumber))!=-1){
            bankAccount.setAccountBalance(bankAccount.getAccountBalance().subtract(new BigDecimal(getNumber)));
            //保存账户
            count=iBankAccountDao.updateByAccountCardId(bankAccount, accountId);
            if(count==1)
                flag=true;
        }else{
            //判断账户类型
            if(bankAccount.getAccountType().equals("credit")){
                if (bankAccount.getAccountLimit().compareTo(new BigDecimal(getNumber))!=-1){
                    bankAccount.setAccountBalance(bankAccount.getAccountBalance().subtract(new BigDecimal(getNumber)));
                    bankAccount.setAccountLimit(bankAccount.getAccountLimit().subtract(new BigDecimal(getNumber)));
                    count=iBankAccountDao.updateByAccountCardId(bankAccount, accountId);
                    if(count==1)
                        flag=true;
                }else{
                    flag=false;
                }
            }else{
                flag=false;
            }
        }
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);

        return flag;

    }
/**
 *
 * @Title: transferMoney
 * @Description: 转账
 * @param jsonObject
 * @return
 */

    @Override
    public boolean transferMoney(JSONObject jsonObject) {
        boolean flag=false;
        int count=0;
        int count2=0;
        //获取BankAccountDao的实例
        BankAccountDaoImpl bankAccountDao= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");
        //读取账号和转账金额目标账号
        String accountId=jsonObject.getString("accountCardId");
        String Number=jsonObject.getString("transferMoneyNumber");
        String transferAccountId=jsonObject.getString("transferAccountId");

        //获取转出账户修改余额
        BankAccount bankAccount;
        bankAccount=bankAccountDao.listByAccountCardId(accountId).get(0);
        //判断余额
        if(bankAccount.getAccountBalance().compareTo(new BigDecimal(Number))!=-1){
            bankAccount.setAccountBalance(bankAccount.getAccountBalance().subtract(new BigDecimal(Number)));
        }else{
            //判断账户类型
            if(bankAccount.getAccountType().equals("credit")){
                if (bankAccount.getAccountLimit().compareTo(new BigDecimal(Number))!=-1){
                    bankAccount.setAccountBalance(bankAccount.getAccountBalance().subtract(new BigDecimal(Number)));
                    bankAccount.setAccountLimit(bankAccount.getAccountLimit().subtract(new BigDecimal(Number)));
                }else{
                   return  false;
                }
            }else{
               return false;
            }
        }

        //获取转入账户修改余额
        BankAccount bankAccountTarget=null;
        if(bankAccountDao.listByAccountCardId(transferAccountId).size()==1){
            bankAccountTarget=bankAccountDao.listByAccountCardId(transferAccountId).get(0);
            bankAccountTarget.setAccountBalance(bankAccountTarget.getAccountBalance().add(new BigDecimal(Number)));
        }else{
            return false;
        }
        //开启事务
        try {
            DButil.getConnection();
            DButil.CONNECTIONS_POOL.get().setAutoCommit(false);
            //保存账户1
             count=bankAccountDao.updateByAccountCardId(bankAccount, accountId);
            //保存账户2
             count2=bankAccountDao.updateByAccountCardId(bankAccountTarget, transferAccountId);
             //提交事务
            DButil.CONNECTIONS_POOL.get().commit();
            if(count2==1&&count==1)
                 flag=true;
        } catch (SQLException e) {
            try {
                DButil.CONNECTIONS_POOL.get().rollback();
                flag=false;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);
        }
     return flag;
    }
/**
 *
 * @Title: createAccount
 * @Description: 创建用户
 * @param jsonObject
 * @return
 */

    @Override
    public String createAccount(JSONObject jsonObject) {
        boolean flag=false;
        //实例化BankAccountDao对象
        BankAccountDaoImpl bankAccountDaoImpl= (BankAccountDaoImpl) FactoryUtil.getBean("neu.work.seventh.dao.Impl.BankAccountDaoImpl");
        //创建账户
        BankAccount bankAccount=new BankAccount();
        Random random=new Random(System.currentTimeMillis());
        //创建用户账号
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <3 ; i++) {
            sb.append(random.nextInt(9)+1);
        }
        String accountCardId=sb.toString();
        bankAccount.setAccountCardId(accountCardId);
        bankAccount.setAccountBalance(new BigDecimal(0));
        bankAccount.setUserIdentify(jsonObject.getString("userIdentify"));
        bankAccount.setAccountPassword(jsonObject.getString("accountPassword"));
        bankAccount.setAccountType(jsonObject.getString("accountType"));
        if(jsonObject.getString("accountType").equals("debit")){
            bankAccount.setAccountLimit(new BigDecimal(0));
            bankAccount.setAccountLimitDate(0);
        }
        if(jsonObject.getString("accountType").equals("credit")){
            bankAccount.setAccountLimit(new BigDecimal(1000));
            bankAccount.setAccountLimitDate(60);
        }
        bankAccount.setAccountStatus("normal");
        bankAccount.setAccountCreateDate(new Date());

        //将用户对象插入数据库
        int insertResult1=bankAccountDaoImpl.insert(bankAccount);
        //关闭连接
        DButil.releaseConnection(DButil.CONNECTIONS_POOL.get(), null, null);

        if(insertResult1==1){
            flag=true;
            return accountCardId;
        }else{
            return "";
        }

    }
}
