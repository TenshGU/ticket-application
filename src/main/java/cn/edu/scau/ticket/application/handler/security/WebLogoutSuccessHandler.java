package cn.edu.scau.ticket.application.handler.security;

import cn.edu.scau.ticket.application.beans.ResultEntity;
import cn.edu.scau.ticket.application.beans.ResultStatus;
import cn.edu.scau.ticket.application.utils.JsonWriter;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
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
        ResultEntity loginOutSuccess = ResultEntity.getResultEntity(ResultStatus.LOGIN_OUT_SUCCESS);
        JsonWriter.writeResultToResponse(httpServletResponse,loginOutSuccess);
    }
}
