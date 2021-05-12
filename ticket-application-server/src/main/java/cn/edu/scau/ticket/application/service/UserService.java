package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.UserAuthority;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
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
}
