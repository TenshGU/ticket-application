package cn.edu.scau.ticket.application.utils;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 把JSON结果写到输出流
 * @author: Tensh
 * @createDate: 2021/4/6
 */
public class JsonWriter {
    public static void writeResultToResponse(HttpServletResponse httpServletResponse, ResultEntity resultEntity) {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSON.toJSONString(resultEntity));
        writer.flush();
        writer.close();
    }

    public static <T> Map<String, Object> convertObj2MapInfo(Class<T> clazz, Object obj){
        Map<String, Object> map = new LinkedHashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object o = field.get(obj);
                String name = field.getName();
                map.put(name, o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
