package neu.work.seventh.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.dao.IBankUserDao;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.service.IBankUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: ssmTest
 * @Package neu.work.seventh.utils
 * @date 2020/5/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationcontext.xml"})
public class ssmTest {


    @Autowired
    IBankUserService iBankUserService;
@Test
    public  void testInsert() {
    //创建用户对象
    BankUser bankUser = new BankUser();
    bankUser.setUserName("123");
    bankUser.setUserIdentify("12");
    bankUser.setUserTelnum("23");
    bankUser.setUserAddress("sd");
    JSONObject JS = (JSONObject) JSONObject.toJSON(bankUser);
//6.使用代理对象执行查询所有方法
    boolean a = iBankUserService.createUser(JS);
    System.out.println(a);
}
    @Test
    public void testDeleteUser(){
        String userId= "{userIdentify:\"211324163417111111\"}";
        System.out.println(iBankUserService.deleteUser(JSON.parseObject(userId)));
    }

}

