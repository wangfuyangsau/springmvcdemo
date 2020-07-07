package neu.work.seventh.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
/* *
  * @ClassName: IBankAccountService
  * @Description: (账户服务接口
  * @author Fuyang Wang
  * @date 2020/5/1
  * @since JDK 1.8
 */
public interface IBankAccountService {
     boolean loginCheck(JSONObject jsonObject);
     BigDecimal queryBanlance(JSONObject jsonObject);
     boolean saveMoney(JSONObject jsonObject);
     boolean changePassword(JSONObject jsonObject);
     boolean getMoney(JSONObject jsonObject);
     boolean transferMoney(JSONObject jsonObject);
     String createAccount(JSONObject jsonObject);
}
