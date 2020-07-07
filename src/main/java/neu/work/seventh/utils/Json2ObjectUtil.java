package neu.work.seventh.utils;

import com.alibaba.fastjson.JSONObject;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: JsonToObject
 * @Package neu.work.seventh.utils
 * @date 2020/4/29
 */
public class Json2ObjectUtil {
    /**
     *
     * @Title: getJSONObjectFromRequest
     * @Description: 读取request中的数据封装为JSONObject
     * @param request, charSet
     * @return JSONObject
     */

    public static JSONObject getJSONObjectFromRequest(HttpServletRequest request,String charSet ){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(),charSet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line);
        }

        JSONObject jsonObject=JSONObject.parseObject(sb.toString());
        return  jsonObject;
    }
}
