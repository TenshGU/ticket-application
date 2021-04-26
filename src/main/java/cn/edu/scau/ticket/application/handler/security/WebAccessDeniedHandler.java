package cn.edu.scau.ticket.application.handler.security;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.utils.JsonWriter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        JsonWriter.writeResultToResponse(httpServletResponse,noPermission);
    }
}
