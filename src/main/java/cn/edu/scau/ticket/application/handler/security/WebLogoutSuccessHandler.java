package cn.edu.scau.ticket.application.handler.security;

import cn.edu.scau.ticket.application.beans.ResultStatus;
import cn.edu.scau.ticket.application.handler.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
public class WebLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultStatus loginOutSuccess = ResultStatus.LOGIN_OUT_SUCCESS;
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(Converter.EnumtoJson(loginOutSuccess));
        writer.flush();
        writer.close();
    }
}
