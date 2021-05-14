package cn.edu.scau.ticket.application.security.filter;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.utils.JsonWriter;
import cn.edu.scau.ticket.application.utils.NetWorkUtil;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证码过滤器
 * @author: Tensh
 * @createDate: 2021/5/13
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {

    private final String DEFAULT_URL = "/login";

    @Autowired
    private Redisson redisson;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if ("POST".equalsIgnoreCase(req.getMethod()) && DEFAULT_URL.equals(req.getServletPath())) {
            String inputCode = request.getParameter("vCode");
            String realIP = NetWorkUtil.getClientRealIP(req);
            RBucket<String> bucket = redisson.getBucket(realIP + ":login:vCode");
            String code = bucket.get();
            if (!StringUtils.hasText(inputCode) || !inputCode.equalsIgnoreCase(code)) {
                ResultEntity resultEntity = ResultEntity.getResultEntity(ResultStatus.LOGIN_FAIL);
                resultEntity.addInfo("error","验证码错误");
                JsonWriter.writeResultToResponse(resp, resultEntity);
                return;
            }
            //登录成功，去除redis中的验证码
            bucket.delete();
        }
        chain.doFilter(request,response);
    }
}
