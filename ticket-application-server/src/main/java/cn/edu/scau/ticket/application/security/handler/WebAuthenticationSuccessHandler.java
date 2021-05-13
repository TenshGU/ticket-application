package cn.edu.scau.ticket.application.security.handler;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.utils.JWTUtil;
import cn.edu.scau.ticket.application.utils.JsonWriter;
import io.jsonwebtoken.Claims;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Component
public class WebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        ResultEntity loginSuccess = ResultEntity.getResultEntity(ResultStatus.LOGIN_SUCCESS);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put(Claims.SUBJECT, authentication.getName());
        String token = jwtUtil.generateToken(hashMap);
        loginSuccess.addInfo("username",authentication.getName());
        loginSuccess.addInfo("bear", token);
        JsonWriter.writeResultToResponse(httpServletResponse, loginSuccess);
    }
}
