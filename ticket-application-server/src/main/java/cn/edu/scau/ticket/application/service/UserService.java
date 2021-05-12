package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.UserAuthority;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
public interface UserService {
    String DEFAULT_IMG_PATH = "group1/M00/00/00/eQXtRWCXOLuAXoD5AAAMz79eafk023.jpg";

    Set<UserAuthority> DEFAULT_AUTHORITIES = new HashSet<>(Collections.singletonList(new UserAuthority("commonUser")));

    ResultEntity saveUser(User user, MultipartFile file);

    ResultEntity validUserError(BindingResult bindingResult);

    //生成验证码
    void generateVerifyCode(HttpServletRequest req, HttpServletResponse response) throws IOException;
    String getCode();
}
