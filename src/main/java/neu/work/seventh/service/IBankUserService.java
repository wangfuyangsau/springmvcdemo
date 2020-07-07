package neu.work.seventh.service;

import com.alibaba.fastjson.JSONObject;
/* *
  * @ClassName: IBankUserService
  * @Description: 用户服务接口
  * @author Fuyang Wang
  * @date 2020/5/1
  * @since JDK 1.8
 */
public interface IBankUserService {
    boolean createUser(JSONObject jsonObject);
    boolean deleteUser(JSONObject jsonObject);
}
