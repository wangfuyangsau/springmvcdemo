package neu.work.seventh.action;

import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.service.IBankAccountService;
import neu.work.seventh.service.IBankBillService;
import neu.work.seventh.utils.FactoryUtil;
import neu.work.seventh.utils.Json2ObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: BankBillAction
 * @Package neu.work.seventh.action
 * @date 2020/5/1
 */
@Controller
@RequestMapping(path = "/BankBill")
public class BankBillAction {
    @RequestMapping(path = "/insertBill",method = RequestMethod.POST)
    public void insertBill(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        //获取session中的信息
        HttpSession httpSession=request.getSession();
        if(httpSession.getAttribute("curAccount")==null){
            try {
                //重定向到登录界面
                response.sendRedirect("../view/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //实例化Service对象
            IBankBillService iBankBillService=(IBankBillService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankBillServiceImpl");
            //获取已登录账号
            JSONObject sessionJsonObject= (JSONObject) httpSession.getAttribute("curAccount");
            //获取交易信息
            JSONObject jsonObject= Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
            //将用户账号额类型打包为JSONObject给service
            JSONObject saveMoneyJSON=new JSONObject();
            saveMoneyJSON.put("accountCardId", sessionJsonObject.getString("accountCardId"));
            saveMoneyJSON.put("billAffairType", jsonObject.getString("billAffairType"));
            saveMoneyJSON.put("billTradeBalance", jsonObject.getString("billTradeBalance"));
            saveMoneyJSON.put("billCurrencyType", jsonObject.getString("billCurrencyType"));


            boolean isSuccess= iBankBillService.insertBillTransfer(saveMoneyJSON);
            try {
                response.getWriter().write(String.valueOf(isSuccess));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
