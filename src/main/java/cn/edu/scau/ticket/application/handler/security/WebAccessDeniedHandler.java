package cn.edu.scau.ticket.application.handler.security;

import cn.edu.scau.ticket.application.beans.ResultEntity;
import cn.edu.scau.ticket.application.beans.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 没有权限处理器
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultEntity noPermission = ResultEntity.getResultEntity(ResultStatus.NO_PERMISSION);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(noPermission));
        writer.flush();
        writer.close();
    }
}
