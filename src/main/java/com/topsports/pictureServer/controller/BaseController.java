package com.topsports.pictureServer.controller;

import com.topsports.pictureServer.util.ReturnFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang.cj on 2017/8/3.
 */
@Controller
public abstract class BaseController {
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取请求参数
     * @param req
     * @return
     */
    public Map<String, Object> builderParams(HttpServletRequest req) {
        Map<String, Object> retParams = new HashMap<String,Object>(req.getParameterMap().size());
        Map<String, String[]> params = req.getParameterMap();
        if (null != params && params.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Map.Entry<String, String[]> p : params.entrySet()) {
                if (null == p.getValue() || p.getValue().length==0)
                    continue;
                String values[] = (String[]) p.getValue();
                String match = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
                if (values[0].matches(match)) {
                    try {
                        retParams.put(p.getKey(), sdf.parse(values[0]));
                    } catch (ParseException e) {
                        retParams.put(p.getKey(), values);
                        e.printStackTrace();
                    }
                } else {
                    retParams.put(p.getKey(), values[0]);
                }
            }
        }

        return retParams;
    }

    protected String retContent(int errorCode,Object data) {
        return ReturnFormat.retParam(errorCode, data);
    }
}
