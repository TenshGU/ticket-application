package cn.edu.scau.ticket.application.handler.security;

import cn.edu.scau.ticket.application.beans.ResultStatus;
import cn.edu.scau.ticket.application.handler.Converter;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultStatus noLogin = ResultStatus.NO_LOGIN;
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(Converter.EnumtoJson(noLogin));
        writer.flush();
        writer.close();
    }
}
