package neu.work.seventh.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public interface IBankBillService {
    boolean insertBillTransfer(JSONObject jsonObject);

}
