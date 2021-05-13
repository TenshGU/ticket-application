package cn.edu.scau.ticket.application.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 网络实用类
 * @author: Tensh
 * @createDate: 2021/5/13
 */
@Component
public class NetWorkUtil {
    public static String getClientRealIP(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff == null) {
            return request.getRemoteAddr();
        } else {
            return xff.contains(",") ? xff.split(",")[0] : xff;
        }
    }
}
