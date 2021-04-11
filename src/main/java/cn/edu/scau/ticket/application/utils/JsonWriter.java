package cn.edu.scau.ticket.application.utils;

import cn.edu.scau.ticket.application.beans.ResultEntity;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 把JSON结果写到输出流
 * @author: Tensh
 * @createDate: 2021/4/6
 */
public class JsonWriter {
    public static void writeResultToResponse(HttpServletResponse httpServletResponse, ResultEntity resultEntity) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(resultEntity));
        writer.flush();
        writer.close();
    }
}
