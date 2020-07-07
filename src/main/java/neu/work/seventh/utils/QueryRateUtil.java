package neu.work.seventh.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: QueryRate
 * @Package neu.work.seventh.utils
 * @date 2020/5/1
 */
public class QueryRateUtil {
    /**
     *
     * @Title: getTransformMoney
     * @Description: 实现实时汇率查询
     * @param fromCode, toCode, money]
     * @return BigDecimal
     */

    public static BigDecimal getTransformMoney(String fromCode, String toCode, String money){
        String host = "https://ali-waihui.showapi.com";
        String path = "/waihui-transform";
        String method = "GET";
        String appcode = "9629627b23224b1684bbbda389acc2b3";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("fromCode", fromCode);
        querys.put("money", money);
        querys.put("toCode", toCode);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            JSONObject jsonObject=JSONObject.parseObject( EntityUtils.toString(response.getEntity()));
            JSONObject jsonMoney=jsonObject.getJSONObject("showapi_res_body");
            return new BigDecimal(jsonMoney.getString("money"));
            /*{
                "showapi_res_error": "",
                    "showapi_res_id": "f72eb3ff9c494d4e90343ac4c36dbf1b",
                    "showapi_res_code": 0,
                    "showapi_res_body": {"ret_code":0,"money":"1585.0875"} //转换后的金额，单位元
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
