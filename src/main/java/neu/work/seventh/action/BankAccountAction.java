package neu.work.seventh.action;
import com.alibaba.fastjson.JSONObject;
import neu.work.seventh.service.IBankAccountService;
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

@Controller
@RequestMapping("/BankAccount")
public class BankAccountAction {
   /**
    *
    * @Title: loginCheck
    * @Description: 登录验证
    * @param request, response]
    * @return
    */

   @RequestMapping(path = "/loginCheck",method = RequestMethod.POST)
   public void loginCheck(HttpServletRequest request, HttpServletResponse response){
       response.setCharacterEncoding("utf-8");
       response.setContentType("text/plain");
       JSONObject jsonObject= Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
       //实例化Service对象
       IBankAccountService iLoginSvc= (IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");

       boolean isSuccess=iLoginSvc.loginCheck(jsonObject);
       //验证成功
       if(isSuccess){
           //将登录成功的账户放在session
           HttpSession httpSession=request.getSession();
           httpSession.setAttribute("curAccount", jsonObject);
           //session存在时间为20分钟
           httpSession.setMaxInactiveInterval(60*20);
           try {
               response.getWriter().write("true");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else {
           try {
               response.getWriter().write("fail");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   /**
    *
    * @Title: queryBalance
    * @Description: 查询余额
    * @param request, response]
    * @return
    */

   @RequestMapping(path = "/queryBalance",method = RequestMethod.POST)
   public void queryBalance(HttpServletRequest request, HttpServletResponse response){
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
           //实例化service对象
           JSONObject jsonObject= (JSONObject) httpSession.getAttribute("curAccount");
           IBankAccountService iQueryBalanceSvc=(IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");
           BigDecimal balance= iQueryBalanceSvc.queryBanlance(jsonObject);
           try {
               //返回余额
               response.getWriter().write(String.valueOf(balance));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   /**
    *
    * @Title: saveMoney
    * @Description: 存钱
    * @param request, response]
    * @return
    */

   @RequestMapping(path = "/saveMoney",method = RequestMethod.POST)
   public void saveMoney(HttpServletRequest request, HttpServletResponse response){
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
           //获取已登录账号
           JSONObject sessionJsonObject= (JSONObject) httpSession.getAttribute("curAccount");
           IBankAccountService iQueryBalanceSvc=(IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");
           JSONObject jsonObject= Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
           //将用户账号和存钱金额类型打包为JSONObject给service
           JSONObject saveMoneyJSON=new JSONObject();
           saveMoneyJSON.put("accountCardId", sessionJsonObject.getString("accountCardId"));
           saveMoneyJSON.put("saveMoneyNumber", jsonObject.getString("saveMoneyNumber"));
           saveMoneyJSON.put("saveMoneyType", jsonObject.getString("saveMoneyType"));
           //存钱
           boolean isSuccess=iQueryBalanceSvc.saveMoney(saveMoneyJSON);
           try {
               response.getWriter().write(String.valueOf(isSuccess));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   /**
    *
    * @Title: changePassword
    * @Description: 修改密码
    * @param request, response]
    * @return
    */

   @RequestMapping(path = "/changePassword",method = RequestMethod.POST)
   public void changePassword(HttpServletRequest request, HttpServletResponse response){
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
       }else {
           //获取已登录账号
           JSONObject sessionJsonObject = (JSONObject) httpSession.getAttribute("curAccount");
           IBankAccountService iBankAccountService = (IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");
           JSONObject jsonObject = Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
           //将用户账号和新密码打包为JSONObject给service
           JSONObject changePwdJSON=new JSONObject();
           changePwdJSON.put("accountCardId", sessionJsonObject.getString("accountCardId"));
           changePwdJSON.put("newPwd", jsonObject.getString("newPwd"));
           //修改密码
           boolean isSuccess=iBankAccountService.changePassword(changePwdJSON);
           try {
               response.getWriter().write(String.valueOf(isSuccess));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   /**
    *
    * @Title: getMoney
    * @Description: 取钱
    * @param request, response]
    * @return
    */

    @RequestMapping(path = "/getMoney",method = RequestMethod.POST)
   public void getMoney(HttpServletRequest request, HttpServletResponse response){
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
        }else {
            //获取已登录账号
            JSONObject sessionJsonObject = (JSONObject) httpSession.getAttribute("curAccount");
            IBankAccountService iBankAccountService = (IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");
            JSONObject jsonObject = Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
            //将用户账号和取钱金额打包为JSONObject给service
            JSONObject getMoneyJSON=new JSONObject();
            getMoneyJSON.put("accountCardId", sessionJsonObject.getString("accountCardId"));
            getMoneyJSON.put("getMoneyNumber", jsonObject.getString("getMoneyNumber"));
            //取钱
            boolean isSuccess=iBankAccountService.getMoney(getMoneyJSON);
            try {
                response.getWriter().write(String.valueOf(isSuccess));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
   /**
    *
    * @Title: transferMoney
    * @Description: 转账
    * @param request, response]
    * @return
    */

    @RequestMapping(path = "/transferMoney",method = RequestMethod.POST)
    public void transferMoney(HttpServletRequest request, HttpServletResponse response){
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
        }else {
            //获取已登录账号
            JSONObject sessionJsonObject = (JSONObject) httpSession.getAttribute("curAccount");
            IBankAccountService iBankAccountService = (IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");
            JSONObject jsonObject = Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
            //将用户账号和转账钱金额和目标账号打包为JSONObject给service
            JSONObject transferJSON=new JSONObject();
            transferJSON.put("accountCardId", sessionJsonObject.getString("accountCardId"));
            transferJSON.put("transferMoneyNumber", jsonObject.getString("transferMoneyNumber"));
            transferJSON.put("transferAccountId", jsonObject.getString("transferAccountId"));
            //转钱
            boolean isSuccess=iBankAccountService.transferMoney(transferJSON);

            try {
                response.getWriter().write(String.valueOf(isSuccess));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     *
     * @Title: creteAccount
     * @Description: 创建账户
     * @param request, response]
     * @return
     */

    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
    public void creteAccount(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        JSONObject jsonObject= Json2ObjectUtil.getJSONObjectFromRequest(request, "utf-8");
        //实例化Service对象
        IBankAccountService iBankAccountService=(IBankAccountService) FactoryUtil.getBean("neu.work.seventh.service.Impl.BankAccountServiceImpl");

        String accountId=iBankAccountService.createAccount(jsonObject);
        //创建成功
        if(!"".equals(accountId)){
            try {
                response.getWriter().write(accountId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write("fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
