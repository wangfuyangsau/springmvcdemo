package neu.work.seventh.utils;

import neu.work.seventh.dao.IBankUserDao;
import neu.work.seventh.pojo.BankUser;
import neu.work.seventh.service.IBankUserService;
import neu.work.seventh.service.Impl.BankUserServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: PropertiesDemo
 * @Package neu.work.seventh.utils
 * @date 2020/4/25
 */
public class PropertiesDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyMMdd HH-mm-ss");
        System.out.println(sf.format(date));
       req.getRequestDispatcher("").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public int  a;
    public int c;
    public  void setp(int a,int c){
        this.a=a;
        this.c=c;
    }



    public static void main(String[] args) throws IOException {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyMMdd HH-mm-ss");
        System.out.println(sf.format(date));
    }

}
class t{
    public  int a;
    public int b;
    public void sett(int a,int b){
        this.a=a;
        this.b=b;

    }
        }
