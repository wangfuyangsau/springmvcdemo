package neu.work.seventh.action;

import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.service.IBankUserService;
import neu.work.seventh.service.Impl.BankUserServiceImpl;
import neu.work.seventh.utils.FactoryUtil;
import neu.work.seventh.utils.Json2ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankUserAction
 * @Package neu.work.seventh.action
 * @date 2020/5/1
 */
@Controller
@RequestMapping("/BankUser")
public class BankUserAction {
    @Autowired
    IBankUserService bankUserService;
    /**
     *
     * @Title: creteUser
     * @Description: 创建用户
     * @param request, response]
     * @return
     */

    @RequestMapping(path = "/createUser",method = RequestMethod.POST)
    public void creteUser(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        JSONObject jsonObject= Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
        //实例化Service对象
        //IBankUserService iBankUserService=(IBankUserService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankUserServiceImpl");

        boolean isSuccess=bankUserService.createUser(jsonObject);
        try {
            response.getWriter().write(String.valueOf(isSuccess));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
