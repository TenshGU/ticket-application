package cn.edu.scau.ticket.application.handler;

import cn.edu.scau.ticket.application.beans.ResultStatus;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
public class Converter {
    public static String EnumtoJson(ResultStatus enumExample) {
        JSONObject obj = new JSONObject(new LinkedHashMap<>());
        obj.put("code",enumExample.getCode());
        obj.put("msg",enumExample.getMsg());
        return obj.toJSONString();
    }
}
